package com.boa.customerapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boa.customerapi.models.Customer;
import com.boa.customerapi.repositories.CustomerRepository;

@Service
public class CustomerService {
    @Autowired
	private CustomerRepository customerRepository;
	
    public Customer saveCustomer(Customer customer) {
    	return this.customerRepository.save(customer);
    }
    
    public Customer fetchCustomerById(long customerId){
    	return this.customerRepository.findById(customerId).orElse(null);
    }
}
