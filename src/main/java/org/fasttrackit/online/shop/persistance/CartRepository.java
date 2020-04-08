package org.fasttrackit.online.shop.persistance;

import org.fasttrackit.online.shop.domain.Cart;

public interface CartRepository {
    long findById(long customerId);

    void save(Cart cart);
}
