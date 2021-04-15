package com.example.springframework.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author I-Chung, Wang
 * @date 2021/3/30 下午 02:32
 */
public abstract class AbstractRestControllerTest {

    public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
