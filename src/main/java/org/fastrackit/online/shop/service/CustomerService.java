package org.fastrackit.online.shop.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.fastrackit.online.shop.persistance.CustomerRepository;
import org.fastrackit.online.shop.domain.Customer;
import org.fastrackit.online.shop.transfer.product.SaveCustomerRequest;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;


    @Autowired
    public CustomerService(CustomerRepository, CustomerRepository,ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }
    public Customer createCustomer(SaveCustomerRequest request){
        LOGGER.info("Creating customer{}", request);
        Customer customer  = objectMapper.convertValue(request, Customer.class);
         return customerRepository.save(customer);
    }
    public Customer getCustomer ( long id){
        LOGGER.info("retrieving customer{}", id);
        return customerRepository.findById(id)

    .orElseThrow =new ResourceNotFound;   }
}
