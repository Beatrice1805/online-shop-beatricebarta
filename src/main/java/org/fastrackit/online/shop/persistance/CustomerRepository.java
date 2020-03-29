package org.fastrackit.online.shop.persistance;

import org.fastrackit.online.shop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
