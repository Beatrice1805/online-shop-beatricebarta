package org.fastrackit.online.shop.persistance.persistance;

import org.fastrackit.online.shop.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByNameContaining(String partialName, Pageable pageable);

    Page<Product> findByNameContainingAndQuantityGreaterThanEqual(
        String partialName, int minQuantity, Pageable pageable);

    // JPQL syntax
  //  @Query("SELECT product FROM Product product WHERE name LIKE '%:partialName%'")
    //or using native queries
     @Query(value = "SELECT * FROM product WHERE `name` LIKE '%?0%'",nativeQuery = true)
             Page<Product>findPartialName(String partialName, Pageable pageable);

    }

