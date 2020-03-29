package org.fastrackit.online.shop.service;

import org.fastrackit.online.shop.domain.Cart;
import org.fastrackit.online.shop.domain.Customer;
import org.fastrackit.online.shop.persistance.CartRepository;
import org.fastrackit.online.shop.transfer.cart.AddProductsToCartRequest;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.logging.Logger;

@Service
    public class CartService {
        private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CartService.class);

        private final CartRepository cartRepository;
        private final CustomerService customerService;

    @Autowired
        public CartService(CartRepository cartRepository, CustomerService customerService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
    }

    @Transactional
        public void addProductsToCart(AddProductsToCartRequest request) {
        LOGGER.info("Adding products to cart: {}", request);

        Cart cart = cartRepository.findById(request.getCustomerId())
                .orElse(new Cart());

            if ( cart.getCustomer() == null){
                Customer customer = customerService.getCustomer((request.getCustomerId()));

                cart.setCustomer(customer);
        }
            cartRepository.save(cart);
    }

}
