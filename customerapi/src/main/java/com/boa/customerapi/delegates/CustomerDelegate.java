package com.boa.customerapi.delegates;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.boa.customerapi.models.Customer;



@Component("registrationDelegate")
public class CustomerDelegate implements JavaDelegate{
    @Autowired
	private RestTemplate restTemplate;
	@Value("${customerurl}")
    private String url;     
    
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Customer customer=new Customer();
		customer.setName(execution.getVariable("name").toString());
		customer.setMobileNo(Long.parseLong(execution.getVariable("mobileNo").toString()));
		DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date=execution.getVariable("dob")
				.toString().substring(0, 10);
		System.out.println(date);
		LocalDate localDate=LocalDate.parse(date,formatter);
		customer.setDob(localDate);
		System.out.println(customer.getName());
		//invoke rest service
		HttpHeaders headers = new HttpHeaders();
	       headers.setContentType(MediaType.APPLICATION_JSON);
	    HttpEntity request = new HttpEntity<>(customer,headers);

		ResponseEntity<?> response=restTemplate.
	 		      postForEntity(url,request, String.class);

		
		
		
	}

}
