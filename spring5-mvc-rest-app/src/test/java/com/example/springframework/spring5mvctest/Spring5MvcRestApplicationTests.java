package com.example.springframework.spring5mvctest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:")
class Spring5MvcRestApplicationTests {

    @Test
    void contextLoads() {
    }

}
