package com.example.springframework.service;

import com.example.springframework.api.v1.mapper.CategoryMapper;
import com.example.springframework.api.v1.model.CategoryDTO;
import com.example.springframework.domain.Category;
import com.example.springframework.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * @author I-Chung, Wang
 * @date 2021/3/25 下午 02:04
 */

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Andy";

    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    public void setUp() {
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    void getAllCategories() {

        //given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());

        when(categoryRepository.findAll()).thenReturn(categories);

        //when
        List<CategoryDTO> categoryDTOS = categoryService.getAllCategories();

        //then
        assertEquals(3, categoryDTOS.size());
    }

    @Test
    void getCategoryByName() {

        //given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        //when
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        //then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}