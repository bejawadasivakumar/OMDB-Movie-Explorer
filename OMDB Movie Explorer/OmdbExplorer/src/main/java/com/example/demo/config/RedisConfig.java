package com.example.demo.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

@Configuration
@EnableCaching
public class RedisConfig {
	
	@Bean
	public LettuceConnectionFactory redisConnectionFactory(
	        @Value("${spring.redis.host}") String host,
	        @Value("${spring.redis.port}") int port) {
	    return new LettuceConnectionFactory(new RedisStandaloneConfiguration(host, port));
	}
	
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
	    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
	        .serializeValuesWith(
	            RedisSerializationContext.SerializationPair.fromSerializer(
	                new GenericJackson2JsonRedisSerializer() 
	            )
	        )
	        .entryTtl(Duration.ofMinutes(30));// Cache expiry 30 mins

	    return RedisCacheManager.builder(redisConnectionFactory)
	        .cacheDefaults(config)
	        .build();
	}

}
