package com.example.springframework.bootstrap;

import com.example.springframework.domain.Customer;
import com.example.springframework.domain.Category;
import com.example.springframework.repositories.CategoryRepository;
import com.example.springframework.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author I-Chung, Wang
 * @date 2021/3/24 下午 02:08
 */

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        loadCategories();
        loadCustomers();
    }

    private void loadCategories() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Data Loaded = " + categoryRepository.count());
    }

    private void loadCustomers() {
        //given
        Customer customer1 = new Customer();
        customer1.setId(1L);
        customer1.setFirstName("Andy");
        customer1.setLastName("Wang");
        customerRepository.save(customer1);

        Customer customer2 = new Customer();
        customer2.setId(2L);
        customer2.setFirstName("Jack");
        customer2.setLastName("Chen");
        customerRepository.save(customer2);

        System.out.println("Customers Loaded: " + customerRepository.count());
    }
}
