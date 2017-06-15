package com.kafka.receiver;
import java.util.HashMap;

import java.util.Map;
import org.springframework.kafka.listener.AbstractMessageListenerContainer.AckMode;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.AbstractMessageListenerContainer;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.config.ContainerProperties;

@Configuration
@EnableKafka
public class KafkaReceiverConfiguration {

	
	@Value("${kafka.bootstrap.servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,StringDeserializer.class);
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "cjy");
		props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
    props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "5");
   // props.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer");
    //设置使用最开始的offset偏移量为该group.id的最早。
    //如果不设置，则会是latest即该topic最新一个消息的offset
		//props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		//props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
		return props;
	}
	
	  @Bean
    public ConsumerFactory consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }
	  
    @Bean
    public ConcurrentKafkaListenerContainerFactory kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        factory.getContainerProperties().setAckMode(AbstractMessageListenerContainer.AckMode.MANUAL_IMMEDIATE);
        factory.getContainerProperties().setAckOnError(false);
        factory.getContainerProperties().setPollTimeout(3000);
        factory.setConcurrency(1);
        return factory;
    }
    
    
    
   /* @Bean
    public Receiver listener() {
      return new Receiver();
    }
    
    @Bean
    public KafkaMessageListenerContainer<String, String> container() {
      ContainerProperties containerProperties = new ContainerProperties("test");
      containerProperties.setMessageListener(listener());
      containerProperties.setAckMode(AckMode.MANUAL_IMMEDIATE);
      return new KafkaMessageListenerContainer(consumerFactory(), containerProperties);
    }*/


    
    
    @Bean
    public KafkaReceiver kafkaReceiver() {
        return new KafkaReceiver();
    }
}
