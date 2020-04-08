package org.fasttrackit.online.shop.persistance;

import org.fasttrackit.online.shop.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
