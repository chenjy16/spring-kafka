package com.kafka.receiver;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;




public class Receiver implements AcknowledgingMessageListener<String, String>{

  @Override
  public void onMessage(ConsumerRecord<String, String> data, Acknowledgment acknowledgment) {
    System.out.println(data.offset()+"前");
    System.out.println("Received: " + data.value());
    acknowledgment.acknowledge();
    System.out.println(data.offset()+"后");
    
  }

}
