package com.example.springframework.repositories;

import com.example.springframework.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author I-Chung, Wang
 * @date 2021/3/29 下午 01:40
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
