package com.ivancl4udio.cruduser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ivancl4udio.cruduser.exception.UserServiceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.repository.UserRepository;
import com.ivancl4udio.cruduser.service.UserServiceImpl;
import org.springframework.web.client.HttpServerErrorException;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@ExtendWith(MockitoExtension.class)
class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    
    @Test
    void findAll_should_return_user_list() throws UserServiceException {
        //Given
        User user = this.buildTestingUser();

        //When
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> returnedUsers = userServiceImpl.findAllUsers();

        //Then
        assertEquals(1, returnedUsers.size());
        verify(this.userRepository).findAll();

    }

    @Test
    void findById_should_return_user() throws UserServiceException {
        //Given
        User user = this.buildTestingUser();

        //When
        when(userRepository.findById(user.getId())).thenReturn(Optional.of(user));
        Optional<User> returnedUser = userServiceImpl.findUserById(user.getId());

        //Then
        assertEquals(user.getId(), returnedUser.get().getId());
        verify(this.userRepository).findById(user.getId());
    }

    @Test
    void save_should_insert_new_user() throws UserServiceException {
        //Given
        User user = this.buildTestingUser();

        //When
        this.userServiceImpl.saveUser(user);

        //Then
        verify(this.userRepository).save(user);

    }

    @Test
    void findByLastName_should_return_user() throws UserServiceException {
        //Given
        User user = this.buildTestingUser();

        //When
        when(userRepository.findByLastName("Cruz")).thenReturn(List.of(user));
        List<User> returnedUser = userServiceImpl.findByLastName("Cruz");

        //Then
        assertEquals(user.getLastName(), returnedUser.get(0).getLastName());
        verify(this.userRepository).findByLastName(user.getLastName());
    }
    @Test
    void deleteById_should_delete_user() throws UserServiceException {
        //Given
        User user = this.buildTestingUser();

        //When
        this.userServiceImpl.deleteUserById(user.getId());

        //Then
        verify(this.userRepository).deleteById(user.getId());
    }

    @Test
    void findAllUsers_test_UserServiceException()  {
        when(userRepository.findAll()).thenThrow(new RuntimeException("Error getting users from database."));
        UserServiceException thrown = Assertions.assertThrows(
                UserServiceException.class,
                () -> {
                    this.userServiceImpl.findAllUsers();
                }, "Error getting users from database.");
        Assertions.assertEquals("Error getting users from database.", thrown.getMessage());
    }

    @Test
    void findUserById_test_UserServiceException() {
        UUID testId = UUID.randomUUID();
        when(userRepository.findById(testId)).thenThrow(new RuntimeException("Error getting user from database."));
        UserServiceException thrown = Assertions.assertThrows(
                UserServiceException.class,
                () -> {
                    this.userServiceImpl.findUserById(testId);
                }, "Error getting user from database.");
        Assertions.assertEquals("Error getting user from database.", thrown.getMessage());
    }

    @Test
    void saveUser_test_UserServiceException() {
        User user = this.buildTestingUser();
        when(userRepository.save(user)).thenThrow(new RuntimeException("Error saving user on database."));
        UserServiceException thrown = Assertions.assertThrows(
                UserServiceException.class,
                () -> {
                    this.userServiceImpl.saveUser(user);
                }, "Error saving user on database.");
        Assertions.assertEquals("Error saving user on database.", thrown.getMessage());
    }

    @Test
    void deleteUserById_test_UserServiceException() {
        UUID testId = UUID.randomUUID();
        doThrow(new RuntimeException("Error deleting user on database.")).when(userRepository).deleteById(testId);
        UserServiceException thrown = Assertions.assertThrows(
                UserServiceException.class,
                () -> {
                    this.userServiceImpl.deleteUserById(testId);
                }, "Error deleting user on database.");

        Assertions.assertEquals("Error deleting user on database.", thrown.getMessage());
    }

    @Test
    void findByLastName_test_UserServiceException() {
        User user = this.buildTestingUser();
        when(userRepository.findByLastName(user.getLastName())).thenThrow(new RuntimeException("User with Last Name: " + user.getLastName() + " not found on database."));
        UserServiceException thrown = Assertions.assertThrows(
                UserServiceException.class,
                () -> {
                    this.userServiceImpl.findByLastName(user.getLastName());
                }, "User with Last Name: " + user.getLastName() + " not found on database.");
        Assertions.assertEquals("User with Last Name: " + user.getLastName() + " not found on database.", thrown.getMessage());
    }

    private User buildTestingUser(){
        User user = new User();
            user.setId(UUID.randomUUID());
            user.setFirstName("Ivan");
            user.setLastName("Cruz");
            user.setUserName("icruz");
            // file deepcode ignore HardcodedPassword/test: This password it is only valid during testing
            user.setPassword("123456");
        return user;
    }
    
}
