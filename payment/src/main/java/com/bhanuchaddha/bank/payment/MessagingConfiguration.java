package com.bhanuchaddha.bank.payment;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

/**
 * Created by ben on 06-12-2018 11:58 PM.
 */
@Configuration
public class MessagingConfiguration {

    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("payments");
    }

    @Bean
    public RedisTemplate<String, Payment> redisTemplate() {
        final RedisTemplate<String, Payment> template = new RedisTemplate<String, Payment>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Payment>(Payment.class));
        return template;
    }
}
