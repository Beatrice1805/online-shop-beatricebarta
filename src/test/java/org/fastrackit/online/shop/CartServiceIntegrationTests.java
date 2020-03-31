package org.fastrackit.online.shop;

import org.fastrackit.online.shop.domain.Cart;
import org.fastrackit.online.shop.domain.Customer;
import org.fastrackit.online.shop.domain.Product;
import org.fastrackit.online.shop.service.CartService;
import org.fastrackit.online.shop.steps.CustomerTestSteps;
import org.fastrackit.online.shop.steps.ProductTestSteps;
import org.fastrackit.online.shop.transfer.cart.AddProductsToCartRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

@SpringBootTest
public class CartServiceIntegrationTests {

    @Autowired
    private CartService cartService;

    @Autowired
    private CustomerTestSteps customerTestSteps;

    @Autowired
    private ProductTestSteps productTestSteps;


    @Test
    void addProductsToCart_whenNewCart_ThenCartIsCreated() {
        Customer customer = customerTestSteps.createCustomer();
        Product product = productTestSteps.createProduct();

        AddProductsToCartRequest cartRequest = new AddProductsToCartRequest();
        cartRequest.setCustomerId(customer.getId());

        cartRequest.setProductIds(Collections.singletonList(product.getId()));

        cartService.addProductsToCart(cartRequest);

        Cart cart = cartService.getCart(customer.getId());

        assertThat(cart, notNullValue());
        assertThat(cart.getId(),is(customer.getId()));

        assertThat(cart.getProducts(),notNullValue());
        assertThat(cart.getProducts(),hasSize(1));

        Iterator<Product> productIterator = cart.getProducts().iterator();
        assertThat(productIterator.hasNext(), is (true));
        Product nextProduct = productIterator.next();
        assertThat(nextProduct, notNullValue());
        assertThat(nextProduct.getId(), is(product.getId()));
        assertThat(nextProduct.getName(), is(product.getName()));
        assertThat(nextProduct.getPrice(), is(product.getPrice()));

    }
}

