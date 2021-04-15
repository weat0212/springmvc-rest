package com.example.springframework.api.v1.mapper;

import com.example.springframework.api.v1.model.CategoryDTO;
import com.example.springframework.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author I-Chung, Wang
 * @date 2021/3/24 下午 02:48
 */

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
