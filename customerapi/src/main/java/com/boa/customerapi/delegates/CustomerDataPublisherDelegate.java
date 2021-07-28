package com.boa.customerapi.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import groovy.util.logging.Slf4j;
import jline.internal.Log;
@Component("customerdatapublisher")
@Slf4j
public class CustomerDataPublisherDelegate implements JavaDelegate{
    @Autowired
	private RestTemplate restTemplate;
    @Value("${customerurl}")
    private String url; 
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		long customerId=Long.parseLong(execution.getVariable("customerId")
				.toString());
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	  
	 	
		HttpEntity request = new HttpEntity<String>(null,headers);

		ResponseEntity<?> responseEntityStr = restTemplate.
		          exchange(url+"/"+customerId,HttpMethod.GET, request,
				  String.class); 

		Log.info("Customer Data published....");
	}

}
