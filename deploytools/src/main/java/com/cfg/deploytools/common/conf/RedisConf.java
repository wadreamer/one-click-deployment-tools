// package com.cfg.deploytools.common.conf;
//
// import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
// import org.springframework.cache.CacheManager;
// import org.springframework.cache.annotation.CachingConfigurerSupport;
// import org.springframework.cache.annotation.EnableCaching;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.redis.cache.RedisCacheConfiguration;
// import org.springframework.data.redis.cache.RedisCacheManager;
// import org.springframework.data.redis.connection.RedisConnectionFactory;
// import org.springframework.data.redis.core.RedisTemplate;
// import org.springframework.data.redis.serializer.StringRedisSerializer;
//
// import java.time.Duration;
// import java.util.HashMap;
// import java.util.HashSet;
// import java.util.Map;
// import java.util.Set;
//
// /**
//  * ClassName: RedisConf
//  * Description: TODO Reids 的配置类
//  * date: 2020/6/5 10:57
//  *
//  * @author CFG
//  * @since JDK 1.8
//  */
// // @EnableCaching
// // @Configuration
// public class RedisConf extends CachingConfigurerSupport {
//
//     @Bean(name = "redisTemplate")
//     public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
//         RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
//         redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//         // value的序列化
//         FastJsonRedisSerializer<Object> serializer = new FastJsonRedisSerializer<Object>(Object.class);
//         redisTemplate.setValueSerializer(serializer);
//         redisTemplate.setHashValueSerializer(serializer);
//
//         // key的序列化
//         redisTemplate.setKeySerializer(new StringRedisSerializer());
//         redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//
//         redisTemplate.setConnectionFactory(redisConnectionFactory);
//
//         return redisTemplate;
//     }
//
//     @Bean
//     public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//         // 生成一个默认配置，通过configuration对象即可对缓存进行自定义配置
//         RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig();
//         // 设置缓存的默认过期时间，也是使用Duration设置
//         configuration = configuration.entryTtl(Duration.ofMinutes(3))
//                 .disableCachingNullValues(); // 不缓存空值
//
//         // 设置一个初始化的缓存空间set集合
//         Set<String> cacheNames = new HashSet<>();
//         cacheNames.add("redis-cache1");
//         cacheNames.add("redis-cache2");
//
//         // 对每个缓存空间应用不同的配置
//         Map<String, RedisCacheConfiguration> configurationMap = new HashMap<>();
//         configurationMap.put("redis-cache1", configuration);
//         configurationMap.put("redis-cache2", configuration.entryTtl((Duration.ofSeconds(120))));
//
//         // 使用自定义的缓存配置初始化一个cacheManager
//         RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
//                 .initialCacheNames(cacheNames) // 注意这两句的调用顺序，一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
//                 .withInitialCacheConfigurations(configurationMap)
//                 .build();
//
//         return cacheManager;
//     }
// }
