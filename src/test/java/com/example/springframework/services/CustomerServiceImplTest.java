package com.example.springframework.services;

import com.example.springframework.api.v1.mapper.CustomerMapper;
import com.example.springframework.api.v1.model.Customer;
import com.example.springframework.api.v1.model.CustomerDTO;
import com.example.springframework.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

/**
 * @author I-Chung, Wang
 * @date 2021/3/30 上午 11:56
 */

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
//        customerService.setCustomerMapper(customerMapper);
//        customerService.setCustomerRepository(customerRepository);
    }

    @Test
    public void getAllCustomers() throws Exception {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Andy");
        customer1.setLastName("Wang");

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jack");
        customer2.setLastName("Chen");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertEquals(2, customerDTOS.size());
    }

    @Test
    public void getCustomerById() throws Exception {

        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Andy");
        customer1.setLastName("Wang");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(customer1));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(1L);

        assertEquals("Andy", customerDTO.getFirstName());

    }

    @Test
    public void createNewCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Andy");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO saveDTO = customerService.createNewCustomer(customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), saveDTO.getFirstName());
        assertEquals("/api/v1/customer/1", saveDTO.getCustomerUrl());
    }

    @Test
    void saveCustomerByDTO() {

        final long ID = 1L;

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Andy");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(ID);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDTO savedDTO = customerService.saveCustomerByDTO(ID, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDTO.getFirstName());
        assertEquals("/api/v1/customer/1", savedDTO.getCustomerUrl());
    }

    @Test
    void deleteCustomerById() throws Exception {

        Long id = 1L;

        customerRepository.deleteById(id);

        verify(customerRepository, times(1)).deleteById(anyLong());
    }
}