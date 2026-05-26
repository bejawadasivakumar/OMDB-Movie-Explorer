package com.example.demo.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {
	

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory factory) {
		RedisCacheConfiguration movieSearchConfig =
                RedisCacheConfiguration.defaultCacheConfig()

                // TTL
                .entryTtl(Duration.ofMinutes(5))
                
                //TTI
                .enableTimeToIdle()

                // Key Serialization
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(new StringRedisSerializer())
                )

                // Value Serialization
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(
                                        new GenericJackson2JsonRedisSerializer()
                                )
                )

                // Don't cache null values
                .disableCachingNullValues();
		
		RedisCacheConfiguration movieDetailsConfig =
                RedisCacheConfiguration.defaultCacheConfig()

                // TTL
                .entryTtl(Duration.ofMinutes(10))
                
                //TTI
                .enableTimeToIdle()

                // Key Serialization
                .serializeKeysWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(new StringRedisSerializer())
                )

                // Value Serialization
                .serializeValuesWith(
                        RedisSerializationContext.SerializationPair
                                .fromSerializer(
                                        new GenericJackson2JsonRedisSerializer()
                                )
                )

                // Don't cache null values
                .disableCachingNullValues();

		return RedisCacheManager.builder(factory)
				 .withCacheConfiguration("movieSearch", movieSearchConfig)
				 .withCacheConfiguration("movieDetails", movieDetailsConfig)
				 .build();
	}

}
/*  suppose  return RedisCacheManager.builder(factory)
               .cacheDefaults(config)
                .build();
 What cacheDefaults() Does?
.cacheDefaults(config)

means:

Use this configuration for all caches by default.

So every cache:

@Cacheable("movies")
@Cacheable("details")

will use the same:

TTL
key serializer
value serializer
null handling

Real Purpose of Multiple Configurations?
Multiple configurations are useful when different caches need different behavior.

Example:

Cache	          TTL
Movie Search	5 mins
Movie Details	30 mins
Trending Movies	1 min

Proper Way to Use Multiple Cache Configurations
Use:
.withCacheConfiguration(cacheName, config) */
