package com.example.springframework.api.v1.mapper;

import com.example.springframework.domain.Customer;
import com.example.springframework.model.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @author I-Chung, Wang
 * @date 2021/3/29 下午 01:41
 */

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDtoToCustomer(CustomerDTO customerDTO);
}
