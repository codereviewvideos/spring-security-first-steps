package uk.co.a6software.spring_security_first_steps.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class SomeEndpointsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testOpenToAllWhenUnauthenticatedIsSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/open-to-all"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome one and all!"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithMockUser
    public void testOpenToAllWhenAuthenticatedIsSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/open-to-all"))
                .andExpect(MockMvcResultMatchers.content().string("Welcome one and all!"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testRestrictedToAuthenticatedUsersIsInaccessibleWhenLoggedOut() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/restricted-to-authenticated-users"))
                .andExpect(MockMvcResultMatchers.status().is(401));
    }

    @Test
    @WithMockUser
    public void testRestrictedToAuthenticatedUsersIsAccessibleWhenLoggedIn() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/restricted-to-authenticated-users"))
                .andExpect(MockMvcResultMatchers.content().string("Hello, authenticated user!"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}