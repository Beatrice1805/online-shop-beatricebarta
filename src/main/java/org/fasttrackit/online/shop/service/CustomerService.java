package org.fasttrackit.online.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fasttrackit.online.shop.domain.Customer;
import org.fasttrackit.online.shop.exception.ResourceNotFoundException;
import org.fasttrackit.online.shop.persistance.CustomerRepository;
import org.fasttrackit.online.shop.transfer.customer.SaveCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;
    private ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository CustomerRepository, ObjectMapper objectMapper){
        this.customerRepository = customerRepository;
        this.ObjectMapper = objectMapper;

        }

    public Customer createCustomer(SaveCustomerRequest request){
        LOGGER.info("Creating customer{}", request);

        Customer customer = objectMapper.convertValue(request, Customer.class);

        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id){
        LOGGER.info("retrieving customer{}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer" +id + " not found"));
        }

}
