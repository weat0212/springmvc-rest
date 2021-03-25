package com.example.springframework.services;

import com.example.springframework.api.v1.model.CategoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author I-Chung, Wang
 * @date 2021/3/25 下午 02:01
 */
@Service
public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
