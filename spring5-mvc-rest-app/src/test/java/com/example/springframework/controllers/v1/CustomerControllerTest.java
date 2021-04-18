package com.example.springframework.controllers.v1;

import com.example.springframework.model.CustomerDTO;
import com.example.springframework.controllers.RestResponseEntityExceptionHandler;
import com.example.springframework.services.CustomerService;
import com.example.springframework.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static com.example.springframework.controllers.v1.AbstractRestControllerTest.asJsonString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author I-Chung, Wang
 * @date 2021/3/29 下午 02:47
 */

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(customerController)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();
    }

    @Test
    void testListOfCustomers() throws Exception {

        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Andy");
        customer1.setLastName("Wang");
        customer1.setCustomerUrl(CustomerController.BASE_URL + "/1");

        CustomerDTO customer2 = new CustomerDTO();
        customer2.setFirstName("Jack");
        customer2.setLastName("Chen");
        customer2.setCustomerUrl(CustomerController.BASE_URL + "/2");

        when(customerService.getAllCustomers()).thenReturn(Arrays.asList(customer1, customer2));

        mockMvc.perform(get(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void testGetCustomerById() throws Exception {

        //given
        CustomerDTO customer1 = new CustomerDTO();
        customer1.setFirstName("Andy");
        customer1.setLastName("Wang");
        customer1.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.getCustomerById(anyLong())).thenReturn(customer1);

        //when
        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Andy")));
    }

    @Test
    void createNewCustomer() throws Exception {

        //given
        CustomerDTO customer = new CustomerDTO();
        customer.setFirstName("Andy");
        customer.setLastName("Wang");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.createNewCustomer(any())).thenReturn(returnDTO);

        //when then
        mockMvc.perform(post(CustomerController.BASE_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customer)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo("Andy")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));

    }

    @Test
    public void testUpdateCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Andy");
        customerDTO.setLastName("Wang");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName(customerDTO.getLastName());
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        //when then
        mockMvc.perform(put(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Andy")))
                .andExpect(jsonPath("$.lastName", equalTo("Wang")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void testPatchCustomer() throws Exception {

        //given
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstName("Andy");

        CustomerDTO returnDTO = new CustomerDTO();
        returnDTO.setFirstName(customerDTO.getFirstName());
        returnDTO.setLastName("Wang");
        returnDTO.setCustomerUrl(CustomerController.BASE_URL + "/1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDTO.class))).thenReturn(returnDTO);

        mockMvc.perform(patch(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Andy")))
                .andExpect(jsonPath("$.lastName", equalTo("Wang")))
                .andExpect(jsonPath("$.customerUrl", equalTo(CustomerController.BASE_URL + "/1")));
    }

    @Test
    void testDeleteCustomer() throws Exception {

        mockMvc.perform(delete(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(customerService).deleteCustomerById(anyLong());
    }

    @Test
    void testNotFoundException() throws Exception {

        when(customerController.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(get(CustomerController.BASE_URL + "/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}