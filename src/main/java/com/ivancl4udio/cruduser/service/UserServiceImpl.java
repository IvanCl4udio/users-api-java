package com.ivancl4udio.cruduser.service;

import java.util.List;
import java.util.Optional;

import com.ivancl4udio.cruduser.exception.UserServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.repository.UserRepository;

/**
 * UserServiceImpl
 */
@Service
public class UserServiceImpl implements UserService {

    final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     *
     * @return
     * @throws UserServiceException
     */
    @Override
    public List<User> findAllUsers() throws UserServiceException {
        try {
            logger.info("Getting all users from database.");
            return this.userRepository.findAll();
        } catch (Exception e) {
            logger.error("Error getting users from database.");
            throw new UserServiceException("Error getting users from database.");
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws UserServiceException
     */
    @Override
    public Optional<User> findUserById(Long id) throws UserServiceException {
        try {
            logger.info("Getting user with id from database.");
            return this.userRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error getting user from database.");
            throw new UserServiceException("Error getting user from database.");
        }
    }

    /**
     *
     * @param user
     * @return
     * @throws UserServiceException
     */
    @Override
    public User saveUser(User user) throws UserServiceException {
        try {
            logger.info("Saving user on database.");
            return this.userRepository.save(user);
        } catch (Exception e) {
            logger.error("Error saving user on database.");
            throw new UserServiceException("Error saving user on database.");
        }
    }

    /**
     *
     * @param id
     * @return
     * @throws UserServiceException
     */
    @Override
    public void deleteUserById(Long id) throws UserServiceException {
        try {
            logger.info("Deleting user on database.");
            this.userRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting user on database.");
            throw new UserServiceException("Error deleting user on database.");
        }
    }

    /**
     *
     * @param lastName
     * @return
     * @throws UserServiceException
     */
    @Override
    public List<User> findByLastName(String lastName) throws UserServiceException {
        try{
            logger.info("Getting user by last name from database.");
            return this.userRepository.findByLastName(lastName);
        } catch (Exception e) {
            logger.info("User with Last Name not found on database.");
            throw new UserServiceException("User with Last Name: " + lastName + " not found on database.");
        }
    }
}
