package com.gs.cache.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCachingService implements IRedisCaching {
	
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	@Override
	public void addToHash(String key,String hkey, Object hvalue) {
		redisTemplate.opsForHash().put(key, hkey, hvalue);
	}
	
	
	@Override
	public void addToHash(String key, String hkey, Object hvalue, long expiredInSecond) {
		redisTemplate.opsForHash().put(key, hkey, hvalue);
		redisTemplate.expire(key, expiredInSecond, TimeUnit.SECONDS);
	}


	@Override
	public Object getFromHash(String key, String hkey) {
		return redisTemplate.opsForHash().get(key, hkey);
	}
	
	@Override
	public void removeFromHash(String key, String hkey) {
		redisTemplate.opsForHash().delete(key, hkey);
	}

	@Override
	public void addOpsValue(String key, Object value) {
		redisTemplate.opsForValue().set(key, value);
		
	}

	@Override
	public void addOpsValue(String key, Object value, long expiredInSecond) {
		redisTemplate.opsForValue().set(key, value);
		redisTemplate.expire(key, expiredInSecond, TimeUnit.SECONDS);
		
	}

	@Override
	public boolean removeFromOpsValue(String key) {
		return redisTemplate.delete(key);
		
	}

	@Override
	public Object getFromOpsValue(String key) {
		return redisTemplate.opsForValue().get(key);
	}

}
