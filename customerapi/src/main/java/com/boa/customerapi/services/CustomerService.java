package com.boa.customerapi.services;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;
	
    public Customer saveCustomer(Customer customer) {
    	return this.customerRepository.save(customer);
    }
}
