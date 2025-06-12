package com.ivancl4udio.cruduser.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.ivancl4udio.cruduser.exception.NotFoundException;
import com.ivancl4udio.cruduser.exception.UserNotFoundException;
import com.ivancl4udio.cruduser.exception.UserServiceException;
import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe de REST Controller para os usuários
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

    final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    /**
     * Método construtor utilizado para injetar a dependência de persistência.
     *
     * @param userService - repositório injetado com dependência
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Método que obtém a lista de todos os usuários da base de dados caso não seja informado
     * o parâmetro opcional de lastname.
     *
     * @param lastName - String - last name do cliente.
     * @return List
     */
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) String lastName)
            throws UserServiceException, NotFoundException {

        logger.info("Getting all users");

        List<User> users = new ArrayList<>();

        if (lastName == null) {
            users.addAll(userService.findAllUsers());
        } else {
            logger.info("Getting users by last name");
            users.addAll(userService.findByLastName(lastName));
        }

        if (users.isEmpty()) {
            logger.error("No users found");
            throw new NotFoundException("No users found");
        }
        logger.info("Returning users");
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    /**
     * Método que obtém as informações de um usuário a partir do id passado como parâmetro.
     *
     * @param id - UUID - identificador único do cliente.
     * @return ResponseEntity
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") UUID id)
            throws UserServiceException, UserNotFoundException {
        logger.info("Getting user by id");
        Optional<User> userData = userService.findUserById(id);
        logger.info("Returning user");
        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseThrow(()
                -> new UserNotFoundException("User not found with id: " + id));
    }

    /**
     * Método que permite a criação de um novo usuário na base de dados.
     *
     * @param user - User - informações do usuário.
     * @return - ResponseEntity
     */
    @PostMapping("/users")
    public ResponseEntity<User> createNewUser(@RequestBody User user)
            throws UserServiceException {
        logger.info("Creating new user");
        User newUser = userService
                .saveUser(new User(user.getUserName(), user.getLastName(), user.getFirstName(), user.getPassword()));
        logger.info("Returning new user");
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    /**
     * Método que atualiza as informações de usuário existente na base de dados.
     *
     * @param id   - UUID - identificador do usuário a ser alterado.
     * @param user - User - objeto contendo as informações que devem ser atualizadas
     * @return - ResponseEntity
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") UUID id, @RequestBody User user)
            throws UserServiceException, UserNotFoundException {
        logger.info("Updating user");
        Optional<User> userData = userService.findUserById(id);

        if (userData.isPresent()) {
            User newUser = userData.get();
            newUser.setUserName(user.getUserName());
            newUser.setLastName(user.getLastName());
            newUser.setFirstName(user.getFirstName());
            newUser.setPassword(user.getPassword());
            logger.info("Returning updated user");
            return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.OK);
        } else {
            logger.error("User not found");
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    /**
     * Método que realiza a exclusão de um usuário existente na base de dados.
     *
     * @param id - UUID - identificador único do usuário.
     * @return - HttpStatus
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") UUID id)
            throws UserServiceException {
        logger.info("Deleting user");
        userService.deleteUserById(id);
        logger.info("Returning no content");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}