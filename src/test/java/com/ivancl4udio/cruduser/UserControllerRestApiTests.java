package com.ivancl4udio.cruduser;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.ivancl4udio.cruduser.controller.UserController;
import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.service.UserService;

@WebMvcTest(UserController.class)
class UserControllerRestApiTests {

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void should_return_users_list() throws Exception {
        //Given
        User user = this.buildTestingUser();
        //When
        when(userService.findAllUsers()).thenReturn(List.of(user));
        //Then
        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id",is(1)))
            .andExpect(jsonPath("$[0].firstName",is("Ivan")))
            .andExpect(jsonPath("$[0].lastName",is("Cruz")))
            .andExpect(jsonPath("$[0].userName",is("icruz")))
            .andExpect(jsonPath("$[0].password",is("123456"))
        );
    }

    @Test
    void should_return_user() throws Exception{
        //Given
        User user = this.buildTestingUser();
        //When
        when(userService.findUserById(1L)).thenReturn(java.util.Optional.of(user));
        //Then
        mockMvc.perform(get("/api/users/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.firstName",is("Ivan")))
        .andExpect(jsonPath("$.lastName",is("Cruz")))
        .andExpect(jsonPath("$.userName",is("icruz")))
        .andExpect(jsonPath("$.password",is("123456"))
        );
    }

    @Test
    void should_add_new_user() throws Exception  {
        //Given
        User user = this.buildTestingUser();
        //when
        // Changed matchers to use from Mockito because casting is not working with org.hamcrest.Matchers
        //noinspection RedundantCast
        when(userService.saveUser((User) org.mockito.ArgumentMatchers.any(User.class))).thenReturn(user);
        //Then
        mockMvc.perform(post("/api/users")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"firstName\":\"Ivan\",\"lastName\":\"Cruz\",\"userName\":\"icruz\",\"password\":\"123456\"}"))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id",is(1)))
        .andExpect(jsonPath("$.firstName",is("Ivan")))
        .andExpect(jsonPath("$.lastName",is("Cruz")))
        .andExpect(jsonPath("$.userName",is("icruz")))
        .andExpect(jsonPath("password",is("123456"))
        );
    }

    @Test
    void should_remove_user() throws Exception{
        //Given
        User user = this.buildTestingUser();
        //when
        when(userService.findUserById(1L)).thenReturn(java.util.Optional.of(user));
        //Then
        mockMvc.perform(delete("/api/users/1"))
        .andExpect(status().isNoContent());
    }

    private User buildTestingUser() {
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Cruz");
        user.setUserName("icruz");
        // file deepcode ignore HardcodedPassword/test: is not exposed any user
        user.setPassword("123456");
        return user;
    }
    
}
