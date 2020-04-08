package org.fasttrackit.online.shop.web;

import org.fasttrackit.online.shop.service.CartService;
import org.fasttrackit.online.shop.transfer.cart.AddProductsToCartRequest;
import org.fasttrackit.online.shop.transfer.cart.CartResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RequestMapping("/carts")
@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService){
        this.cartService = cartService;

}
    //POST means create ..we're doing createOrUpdate = > PUT
    @PutMapping
    public ResponseEntity<Void > addProductsToCart(
        @Valid @RequestBody AddProductsToCartRequest request) {
        cartService.addProductsToCart(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        @GetMapping("/{id}")
                public ResponseEntity<CartResponse> getCart(@PathVariable("id") long customerId){
                CartResponse cart =cartService.getCart(customerId);
                return new ResponseEntity<>(cart,HttpStatus.OK);

        }

        }