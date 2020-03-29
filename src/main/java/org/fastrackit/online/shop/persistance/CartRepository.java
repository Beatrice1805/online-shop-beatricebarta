package org.fastrackit.online.shop.persistance;

import org.fastrackit.online.shop.domain.Cart;

public interface CartRepository {
    long findById(long customerId);

    void save(Cart cart);
}
