package com.example.springframework.api.v1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author I-Chung, Wang
 * @date 2021/3/29 下午 01:40
 */

@Data
public class CustomerDTO {

    @ApiModelProperty(value = "This is the first name", required = true)
    private String firstName;

    @ApiModelProperty(value = "Last Name" ,required = true)
    private String lastName;

    @JsonProperty("customer_url")
    private String customerUrl;
}
