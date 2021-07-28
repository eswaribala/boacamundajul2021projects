package com.boa.customerapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.services.CustomerDataPublisher;
import com.boa.customerapi.services.CustomerService;

@RestController
public class RegistrationController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerDataPublisher customerDataPublisher;
	@PostMapping("/customers")
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer){
		Customer customerObj=this.customerService.saveCustomer(customer);
		if(customerObj!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(customerObj);
		}
		else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer Not Added");
	}
	@GetMapping("/customers/{customerId}")
	public void publishCustomer(@PathVariable("customerId") long customerId) {
		
		this.customerDataPublisher.sendMessage(customerId);
		
	}
}
