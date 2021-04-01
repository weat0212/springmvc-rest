package com.example.springframework.services;

import com.example.springframework.api.v1.model.CustomerDTO;

import java.util.List;

/**
 * @author I-Chung, Wang
 * @date 2021/3/29 下午 01:41
 */
public interface CustomerService {

    List<CustomerDTO> getAllCustomers();

    CustomerDTO getCustomerById(Long id);

    CustomerDTO createNewCustomer(CustomerDTO customerDTO);

    CustomerDTO saveCustomerByDTO(Long id, CustomerDTO customerDTO);

    CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO);
}
