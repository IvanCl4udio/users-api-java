package com.ivancl4udio.cruduser.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ivancl4udio.cruduser.exception.UserServiceException;
import com.ivancl4udio.cruduser.model.User;

/**
 * UserService
 */
public interface UserService {

    /**
     * @return List<User>
     * @throws UserServiceException
     */
    List<User> findAllUsers() throws UserServiceException;

    /**
     * @param id
     * @return Optional<User>
     * @throws UserServiceException
     */
    Optional<User> findUserById(UUID id) throws UserServiceException;

    /**
     * @param lastName
     * @return List<User>
     * @throws UserServiceException
     */
    List<User> findByLastName(String lastName)  throws UserServiceException;

    /**
     * @param user
     * @return User
     * @throws UserServiceException
     */
    User saveUser(User user) throws UserServiceException;

    /**
     * @param id
     * @throws UserServiceException
     */
    void deleteUserById(UUID id) throws UserServiceException;
}
