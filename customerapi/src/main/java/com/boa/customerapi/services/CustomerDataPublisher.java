package com.boa.customerapi.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.repositories.CustomerRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerDataPublisher {
    @Autowired
	private CustomerRepository customerRepository;
    
    private boolean status;
  //1. General topic with string payload
	
  	@Value(value = "${customer.topic.name}")
      private String customerTopicName;
  	
  	@Autowired
      private KafkaTemplate<String, Customer> kafkaTemplate;
  	
  	public void sendMessage(long customerId) 
	{
  		
		
  		Customer customerObj=this.customerRepository.findById(customerId).orElse(null);
		if(customerObj!=null) {
		ListenableFuture<SendResult<String, Customer>> future 
			= this.kafkaTemplate.send(customerTopicName, customerObj);
		
		future.addCallback(new ListenableFutureCallback<SendResult<String, Customer>>() {
            @Override
            public void onSuccess(SendResult<String, Customer> result) {
            	log.info("Sent message: " + customerObj.getCustomerId() 
            			+ " with offset: " + result.getRecordMetadata().offset());
            	System.out.println("Sent message: " + customerObj.getCustomerId()  
            			+ " with offset: " + result.getRecordMetadata().offset());
           // status=true;
            }
            

            @Override
            public void onFailure(Throwable ex) {
            	log.error("Unable to send customer Data : " + customerObj.getCustomerId(), ex);
              // status=false;
            }
       });
		}
		
	}


}
