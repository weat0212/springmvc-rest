package com.example.springframework.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author I-Chung, Wang
 * @date 2021/3/25 下午 03:58
 */

@Data
@AllArgsConstructor
public class CategoryListDTO {
    List<CategoryDTO> categories;
}
