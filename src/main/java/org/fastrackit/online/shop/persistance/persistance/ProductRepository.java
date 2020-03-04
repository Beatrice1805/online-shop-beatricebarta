package org.fastrackit.online.shop.persistance.persistance;

import org.fastrackit.online.shop.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
