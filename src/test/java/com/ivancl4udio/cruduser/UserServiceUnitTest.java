package com.ivancl4udio.cruduser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.repository.UserRepository;
import com.ivancl4udio.cruduser.service.UserServiceImpl;

@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    
    @Test
    void findAll_should_return_user_list() {
        //Given
        User user = this.buildTestingUser();

        //When
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        List<User> returnedUsers = userServiceImpl.findAllUsers();

        //Then
        assertEquals(1, returnedUsers.size());
        verify(this.userRepository).findAll();

    }

    @Test
    void findById_should_return_user() {
        //Given
        User user = this.buildTestingUser();

        //When
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> returnedUser = userServiceImpl.findUserById(1L);

        //Then
        assertEquals(user.getId(), returnedUser.get().getId());
        verify(this.userRepository).findById(1L);
    }

    @Test
    void save_should_insert_new_user() {
        //Given
        User user = this.buildTestingUser();

        //When
        this.userServiceImpl.saveUser(user);

        //Then
        verify(this.userRepository).save(user);

    }

    @Test
    void deleteById_should_delete_user() {
        //Given
        User user = this.buildTestingUser();

        //When
        this.userServiceImpl.deleteUserById(user.getId());

        //Then
        verify(this.userRepository).deleteById(user.getId());
    }

    private User buildTestingUser(){
        User user = new User();
        user.setId(1L);
        user.setFirstName("Ivan");
        user.setLastName("Cruz");
        user.setUserName("icruz");
        // file deepcode ignore HardcodedPassword/test: This password it is only valid during testing
        user.setPassword("123456");
        return user;
    }
    
}
