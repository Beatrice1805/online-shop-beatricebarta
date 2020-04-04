package org.fastrackit.online.shop;

import org.fastrackit.online.shop.domain.Cart;
import org.fastrackit.online.shop.domain.Customer;
import org.fastrackit.online.shop.domain.Product;
import org.fastrackit.online.shop.persistance.CartRepository;
import org.fastrackit.online.shop.service.CartService;
import org.fastrackit.online.shop.service.CustomerService;
import org.fastrackit.online.shop.service.ProductService;
import org.fastrackit.online.shop.transfer.cart.AddProductsToCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MokitoUnitRunner.class)
public class CartServiceUnitTests {
    private CartService cartService ;

    @Mock
    private CartRepository cartRepository;
    @Mock
    private CustomerService customerService;

    @Mock
    private ProductService productService;
    @Before
    public void setup() {
        cartService = new CartService((CartRepository, customerService
        , productService);
    }
    @Test
    public void addProductsToCart_WhenNewCart_thenNoErrorIsThrown(){
        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());
        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("FirstName");
        customer.setLastName("LastName");
         when(customerService.getCustomer(anyLong())).thenReturn(customer);
         Product product = new Product();
         product.setId(2);
         product.setName("TestProduct");
         product.setDescription("desc");
         product.setPrice(30);
         product.setQuantity(100);

          when(productService.findProduct(anyLong()).thenReturn(product));

          when(cartRepository.save(any(Cart.class)));

          AddProductsToCartRequest request = new AddProductsToCartRequest();

          request.setProductIds(Collections.singletonList(product.getId()));
          request.setCustomerId(customer.getId());

          cartService.addProductsToCart(request);

          verify(cartRepository).findById(anyLong());
          verify(customerService).getCustomer(anyLong());
          verify(productService).findProduct(anyLong());
          verify(cartRepository).save(any(Cart.class));

    }



}
