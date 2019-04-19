package com.gs.cache.cacheutils;

import static org.junit.Assert.assertTrue;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.gs.cache.annotation.EnableRedisCaching;
import com.gs.cache.redis.RedisCachingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {RedisCachingService.class})
@EnableRedisCaching
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedisCachingTesting {
	
	@Autowired
	private RedisCachingService redisService;
	
	
	@Test
	public void test1addValue() {
		redisService.addOpsValue("key", "value");
	}
	
	@Test
	public void test2GetValue() {
		assertTrue(redisService.getFromOpsValue("key").equals("value"));
	}
}
