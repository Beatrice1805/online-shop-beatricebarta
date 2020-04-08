package org.fasttrackit.online.shop.persistance;

import org.fasttrackit.online.shop.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    //finding reviews by nested property(id property of product)
    Page<Review> findByProductId(long productId,Pageable pageable);

}
