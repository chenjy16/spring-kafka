package com.kafka;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class KafkaSpringBootApplicationTests {
  
  
  @Autowired
  private KafkaTemplate kafkaTemplate;
  
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void send() throws Exception{
          System.out.println("开始");
    	    ListenableFuture<SendResult> future = kafkaTemplate.send("moni", "cjy", "ccccc");
    	    future.addCallback(new ListenableFutureCallback<SendResult>() {
    	      
              @Override
              public void onSuccess(SendResult result) {
                  System.out.println("成功");
              }
              
              @Override
              public void onFailure(Throwable ex) {
                  System.out.println(ex.getMessage());
              }
              
              
    	  });
    	  System.in.read();
	}
	

}
