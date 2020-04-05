package com.galvanize.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.galvanize.dto.ShoppingDTO;
import com.galvanize.entity.Shopping;
import com.galvanize.service.ShoppingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// AML had to add extra imports before test would run
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ShoppingControllerTest {

    // AML, the main issue for your error was using Mockito to Mock something that can't be mocked. Only use mocks when necessary
    // Also, in this case you can't mock the Mvc because it have a final variable
//    @MockBean
//    MockMvc mvc;

    // Instead use Autowired
    // Autowired tells spring boot you want to use a class with dependencies, and that spring boot should Autowire the whole class, including dependencies for you
    @Autowired
    MockMvc mvc;


    @Autowired
    ShoppingService shoppingService;
    ObjectMapper objectMapper = new ObjectMapper();

    // AML the main issue here was like 52 and lines 55
    @Test
    public void createShopping() throws Exception {
        Shopping expected = new Shopping();
        // make sure your object has a value for the parameter that you are asserting on at the end
        expected.setName("Sample name");
        ShoppingDTO shoppingDTO = new ShoppingDTO();
        // don't need to use mock here
        //when(shoppingService.createShopping(any(Shopping.class))).thenReturn(shoppingDTO);
        mvc.perform(post("/api/shop")
                // don't send in the Shopping.class
                //.content(objectMapper.writeValueAsString(Shopping.class))
                //Instead, send in an instance of the class variable shopping
                .content(objectMapper.writeValueAsString(expected))
                .contentType(MediaType.APPLICATION_JSON)
                // need acceptance type here as well
                .accept(MediaType.APPLICATION_JSON_VALUE)
        )
                .andExpect(status().isOk())
                // the DTO class doesn't have an Id field to assert on
                //.andExpect(jsonPath("$.Id").value(expected.getId()));

                // Instead assert on something the DTO has, like name
                .andExpect(jsonPath("$.name").value(expected.getName()));
    }
}
