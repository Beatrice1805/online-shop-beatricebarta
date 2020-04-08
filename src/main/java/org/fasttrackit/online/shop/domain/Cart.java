package org.fasttrackit.online.shop.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cart {
    @Id
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Customer customer;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "cart product",
        joinColumns = @JoinColumn(name = "cart id"),
         inverseJoinColumns = @JoinColumn(name ="product id"))

    private Set<Product> products = new HashSet<>();

    public void addTProductToCart(Product product){
        //adding received product to current cart
            products.add(product);
        //adding current cart to the received product carts' set
             product.getCarts().add(this);
    }
    public void removeProductFromCart(Product product){
        products.remove(product);
        product.getCarts().remove(this);
    }

    public long getId() {
        return id;
    }

    public Customer getCustomer() {

        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(long id) {
        this.id = id;

    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;

    }
    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customer=" + customer +
                '}';

    }
}

