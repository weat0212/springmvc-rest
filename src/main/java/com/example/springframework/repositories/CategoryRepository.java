package com.example.springframework.repositories;

import com.example.springframework.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author I-Chung, Wang
 * @date 2021/3/24 下午 02:07
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
}
