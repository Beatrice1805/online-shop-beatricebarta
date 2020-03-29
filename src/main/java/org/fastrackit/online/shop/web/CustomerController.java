package org.fastrackit.online.shop.web;

import org.fastrackit.online.shop.domain.Customer;
import org.fastrackit.online.shop.service.CustomerService;
import org.fastrackit.online.shop.transfer.customer.SaveCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/customer")
@RestController
@CrossOrigin
public class CustomerController {
    private final CustomerService customerService;

@Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    public ResponseEntity<Customer> createCustomer(@Valid @RequestBody SaveCustomerRequest request) {
    Customer customer = customerService.createCustomer(request);
    return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }
    @GetMapping("/id")
    public ResponseEntity<Customer> getCustomer(long id ){
    customerService.getCustomer(id);
    return new ResponseEntity<>(customer, HttpStatus.OK);

    }
}
