package com.kafka.receiver;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;


public class KafkaReceiver {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaReceiver.class);
	private static final String SIGNUP_TOPIC = "moni";
	

	@KafkaListener(topics = SIGNUP_TOPIC)
	public void receiveSignupDetails(ConsumerRecord<String, String> record,Acknowledgment ack) {
		LOGGER.info("received Sign-up Details='{}'", record.offset());
		ack.acknowledge();
    LOGGER.info("received Sign-up Details='{}'", record.offset());
	}
	

}
