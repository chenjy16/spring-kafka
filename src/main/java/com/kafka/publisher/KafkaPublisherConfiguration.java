package com.kafka.publisher;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaPublisherConfiguration {

  
  
  	@Value("${kafka.bootstrap.servers}")
  	private String bootstrapServers;

  	@Bean
  	public Map<String, Object> producerConfigs() {
  		Map<String, Object> props = new HashMap<>();
  		
  		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
      props.put(ProducerConfig.ACKS_CONFIG, "1");
  		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
  		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
  		props.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, 5000);
  		props.put(ProducerConfig.RETRIES_CONFIG, 3);
      props.put(ConsumerConfig.CLIENT_ID_CONFIG, "cjyproducer");
  		props.put("producer.type","async");
  		props.put("batch.num.messages",200);
      props.put("queue.buffer.max.ms",2000);
  		return props;
  	}
	
	  @Bean
    public ProducerFactory producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate kafkaTemplate() {
        return new KafkaTemplate(producerFactory());
    }
    
    @Bean
    public KafkaPublisher userKafkaPublisher() {
        return new KafkaPublisher();
    }
}
