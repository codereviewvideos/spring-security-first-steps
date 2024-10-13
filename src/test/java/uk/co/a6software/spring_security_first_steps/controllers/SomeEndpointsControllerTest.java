package uk.co.a6software.spring_security_first_steps.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SomeEndpointsController.class)
class SomeEndpointsControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void testOpenToAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/open-to-all"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome one and all!"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRestrictedToAuthenticatedUsers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/restricted-to-authenticated-users"))
                .andExpect(MockMvcResultMatchers.content().string("Hello, authenticated user!"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}