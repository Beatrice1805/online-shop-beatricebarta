package org.fasttrackit.online.shop.service;

import org.fasttrackit.online.shop.domain.Cart;
import org.fasttrackit.online.shop.domain.Customer;
import org.fasttrackit.online.shop.domain.Product;
import org.fasttrackit.online.shop.exception.ResourceNotFoundException;
import org.fasttrackit.online.shop.persistance.CartRepository;
import org.fasttrackit.online.shop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.online.shop.transfer.cart.CartResponse;
import org.fasttrackit.online.shop.transfer.cart.ProductInCartResponse;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;

@Service
    public class CartService<productDtos, cartResponse> {
    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(CartService.class);

    private CartRepository cartRepository = null;
    private final CustomerService customerService;
    private final ProductService productService;
    private CartResponse cart;
    private long customerId;


    public static Logger getLOGGER() {
        return LOGGER;
    }

    public CartRepository getCartRepository() {
        return cartRepository;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public CartService(CartRepository cartRepository,
                       CustomerService customerService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.customerService = customerService;
        this.productService = productService;
    }

    @Transactional
    public void addProductsToCart(AddProductsToCartRequest request) {
        LOGGER.info("Adding products to cart:{}" + request);

        Cart cart = cartRepository.findById(request.getCustomerId())
                .orElse(new Cart());

        if (cart.getCustomer() == null) {
            Customer customer = customerService.getCustomer((request.getCustomerId()));

            cart.setCustomer(customer);
        }
        for (Long id : request.getProductIds()) {
            Product product = productService.findProduct(id);
            cart.addTProductToCart(product);
        }
        cartRepository.save(cart);
    }

    //returning DTO to avoid lazy loading exceptions

    @Transactional
    public void getCart(long customerId) {
        LOGGER.info
                ("Retrieving cart items for customer:{}" + customerId);}

    public CartResponse getCart() {
        return cart;
    }

    public Object setCart(CartResponse cart) {
        this.cart = cart;


        CartResponse cartResponse = cartRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Cart" + customerId + "does not exist."));
        cartResponse = new CartResponse();
        cartResponse.setId(cart.getId());

         Set<ProductInCartResponse> productDtos = new HashSet<>();

        Optional getCustomerId;
            return customerId;
            this.customerId = customerId;
        }

        Iterator<ProductInCartResponse> productIterator = cart.getProducts().iterator();
            while (productIterator.hasNext()) {
            ProductInCartResponse nextProduct = productIterator.next();

            ProductInCartResponse productDto = new ProductInCartResponse();
            productDto.setId(nextProduct.getId());
            productDto.setPrice(nextProduct.getPrice());
            productDtos.add(productDto);
        }
    }

