package com.boa.customerapi.delegates;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import com.boa.customerapi.models.Customer;

@Component("registrationDelegate")
public class CustomerDelegate implements JavaDelegate{

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		Customer customer=new Customer();
		customer.setName(execution.getVariable("name").toString());
		customer.setMobileNo(Long.parseLong(execution.getVariable("mobileNo").toString()));
		customer.setDob((LocalDateTime) execution.getVariable("dob"));
		System.out.println(customer.getName());
	}

}
