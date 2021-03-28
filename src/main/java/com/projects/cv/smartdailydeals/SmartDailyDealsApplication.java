package com.projects.cv.smartdailydeals;

import com.fasterxml.jackson.databind.JsonSerializer;
import com.projects.cv.smartdailydeals.model.Deal;
import com.projects.cv.smartdailydeals.repository.cache.DealsRepository;
import com.projects.cv.smartdailydeals.repository.cache.DealsRepositoryImpl;
import com.projects.cv.smartdailydeals.repository.stream.KafkaRepository;
import com.projects.cv.smartdailydeals.repository.stream.KafkaRepositoryImpl;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "com.projects.cv.smartdailydeals.repository.cache")
@EnableJpaRepositories(basePackages = "com.projects.cv.smartdailydeals.repository.jpa")
public class SmartDailyDealsApplication {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}

	@Bean
	RedisTemplate<String, Deal> redisTemplate() {
		RedisTemplate<String, Deal> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		return redisTemplate;
	}

	@Bean
	DealsRepository dealsRepository() {
		return new DealsRepositoryImpl(redisTemplate());
	}


	@Bean
	public ProducerFactory<String, Deal> producerFactory() {
		Map<String, Object> config = new HashMap<>();

		config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

		return new DefaultKafkaProducerFactory<>(config);
	}

	@Bean
	KafkaTemplate<String, Deal> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}

	@Bean
	KafkaRepository kafkaRepository() {
		return new KafkaRepositoryImpl();
	}

	public static void main(String[] args) {
		SpringApplication.run(SmartDailyDealsApplication.class, args);
	}

}
