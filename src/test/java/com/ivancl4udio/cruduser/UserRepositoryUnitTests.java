package com.ivancl4udio.cruduser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.repository.UserRepository;


@DataJpaTest
class UserRepositoryUnitTests {
    
    @Autowired
    private UserRepository userRepository;

    User user1;

    @BeforeEach
    void setUp() {
        user1 = new User("ifernandes", "Fernandes", "Ivan","123456");
        userRepository.save(user1);
    }

    @Test
    void findAllUsers_should_return_all_users_list() {
        //When
        List<User> users = userRepository.findAll();
        
        //Then
        assertEquals(1, users.size());
    }

    @Test
    void findById_should_return_user() {
        //When
        Optional<User> user = userRepository.findById(user1.getId());
        
        //Then
        assertTrue(user.isPresent());
    }

    @Test
    void save_should_insert_new_user(){
        User user2 = new User("iclaudio", "Claudio", "Ivan","123456");

        //When
        User userSaved  = this.userRepository.save(user2);

        //Then
        assertNotNull(userSaved);
        assertNotNull(userSaved.getId());
    }

    @Test
    void save_should_update_existing_user(){
        User currentUser = new User("iclaudio", "Claudio", "Ivan","123456");
        
        //When
        User updatedUser= this.userRepository.save(currentUser);
        
        //Then
        assertNotNull(updatedUser);
        assertEquals("iclaudio", updatedUser.getUserName());
        assertEquals("Claudio", updatedUser.getLastName());
    }

    @Test
    void deleteById_should_delete_user() {
        try {
            //When
            userRepository.deleteById(user1.getId());
            Optional<User> userSearched = userRepository.findById(user1.getId());
            //Then
            assertFalse(userSearched.isPresent());
        } catch (Exception e) {
            // Do nothing
        }
    }
}
