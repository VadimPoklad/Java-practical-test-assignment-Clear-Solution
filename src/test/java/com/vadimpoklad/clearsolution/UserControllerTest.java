package com.vadimpoklad.clearsolution;

import com.vadimpoklad.clearsolution.entities.User;
import com.vadimpoklad.clearsolution.entities.UserNullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDate;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void handleValidationExceptionTest() throws Exception {
        User user = new User(null, "", "Doe", "john@example.com", LocalDate.of(2000, 5, 15), null, null);
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString()))
                .andExpect(status().isBadRequest());
    }


    @Test
    void handleMethodNotSupportedExceptionTest() throws Exception {
        UserNullable user = new UserNullable(null, "Doe", "john@example.com", null, null, null);
        mockMvc.perform(patch("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString()))
                .andExpect(status().isBadRequest());
    }

    @Test
    void handleNoResourceFoundException() throws Exception {
        UserNullable user = new UserNullable(null, "Doe", "john@example.com", null, null, null);
        mockMvc.perform(patch("/some")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(user.toString()))
                .andExpect(status().isNotFound());
    }
}