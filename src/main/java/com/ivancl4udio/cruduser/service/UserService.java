package com.ivancl4udio.cruduser.service;

import java.util.List;
import java.util.Optional;

import com.ivancl4udio.cruduser.model.User;

public interface UserService {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    List<User> findByLastName(String lastName);
    User saveUser(User user);
    void deleteUserById(Long id);
}
