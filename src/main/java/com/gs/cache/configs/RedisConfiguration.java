package com.gs.cache.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.util.StringUtils;

@Configuration
@ConditionalOnProperty(value="cache.redis.enable",havingValue="true")
public class RedisConfiguration {

	@Value("${cache.redis.hostname}")
	private String hostname;
	
	@Value("${cache.redis.port}")
	private int port;
	
	@Value("${cache.redis.password}")
	private String password;
	
	
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
		config.setHostName(hostname);
		if (!StringUtils.isEmpty(password))
			config.setPassword(password);
		if (port > 0)
			config.setPort(port);
		JedisConnectionFactory jedisConnection = new JedisConnectionFactory(config);
		return jedisConnection;
	}
	
	@Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return template;
    }
}
