package com.kafka.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.kafka.model.User;

public class KafkaPublisher {

	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaPublisher.class);
	
	@Autowired
	private KafkaTemplate<Integer, User> kafkaTemplate;
	
	
	public void sendMessage(String topic, User user) {
        ListenableFuture<SendResult<Integer, User>> future = kafkaTemplate
                .send(topic, user);
        future.addCallback(new ListenableFutureCallback<SendResult<Integer, User>>() {

              @Override
              public void onSuccess(SendResult<Integer, User> result) { 
                LOGGER.info("sent message='{}' with offset={}",user.toString(),result.getRecordMetadata().offset());
              }
  
              @Override
              public void onFailure(Throwable ex) {
                  LOGGER.error("unable to send message='{}'",user.toString(), ex);
              }
        });
	}
}
