package com.example.springframework.api.v1.model;

import lombok.Data;

/**
 * @author I-Chung, Wang
 * @date 2021/3/29 下午 01:40
 */

@Data
public class CustomerDTO {

    private String firstName;
    private String lastName;
    private String customerUrl;
}
