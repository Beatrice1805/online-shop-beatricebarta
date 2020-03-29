package org.fastrackit.online.shop;
import org.fastrackit.online.shop.domain.Customer;
import org.fastrackit.online.shop.service.CartService;
import org.fastrackit.online.shop.steps.CustomerTestSteps;
import org.fastrackit.online.shop.transfer.cart.AddProductsToCartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CartServiceIntegrationTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerTestSteps customerTestSteps;

    @Test
    void addProductsToCart_whenNewCart_ThenCartIsCreated() {
        Customer customer = customerTestSteps.createCustomer();

        AddProductsToCartRequest cartRequest = new AddProductsToCartRequest();
        cartRequest.setCustomerId(customer.getId());

        cartService.addProductsToCart(cartRequest);
    }
}

