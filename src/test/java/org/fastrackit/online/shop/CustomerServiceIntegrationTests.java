package org.fastrackit.online.shop;

import org.fastrackit.online.shop.steps.CustomerTestSteps;
import org.fasttrackit.online.shop.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CustomerServiceIntegrationTests {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private CustomerTestSteps customerTestSteps;

    @Test
    void createCustomer_whenValidRequest_thenCustomerIsCreated(){
       customerTestSteps.createCustomer();
}
}
