package com.ivancl4udio.cruduser.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	 * @param userService - repositório injetado com dependência
	 */
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Método que obtém a lista de todos os usuários da base de dados caso não seja informado
	 * o parâmetro opcional de lastname.
	 * @param lastName - String - last name do cliente.
	 * @return List
	 */
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required=false) String lastName) {
		try {

			List<User> users = new ArrayList<>();

			if (lastName == null) {
				users.addAll(userService.findAllUsers());
			} else {
				users.addAll(userService.findByLastName(lastName));
			}
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(List.of(), HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}

	/**
	 * Método que obtém as informações de um usuário a partir do id passado como parâmetro.
	 * @param id - Long - identificador único do cliente.
	 * @return ResponseEntity
	 */
	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {

		Optional<User> userData = userService.findUserById(id);

		return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Método que permite a criação de um novo usuário na base de dados.
	 * @param user - User - informações do usuário.
	 * @return - ResponseEntity
	 */
	@PostMapping("/users")
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		try {
			User newUser = userService
					.saveUser(new User(user.getUserName(), user.getLastName(), user.getFirstName(), user.getPassword()));
			return new ResponseEntity<>(newUser, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(new User(), HttpStatus.CONFLICT);
		}
	}

	/**
	 * Método que atualiza as informações de usuário existente na base de dados.
	 * @param id - Long - identificador do usuário a ser alterado.
	 * @param user - User - objeto contendo as informações que devem ser atualizadas
	 * @return - ResponseEntity
	 */
	@PutMapping("/users/{id}")
	public ResponseEntity<User>	updateUser(@PathVariable ("id") long id, @RequestBody User user){

		Optional<User> userData = userService.findUserById(id);

		if (userData.isPresent()) {
			User newUser = userData.get();
			newUser.setUserName(user.getUserName());
			newUser.setLastName(user.getLastName());
			newUser.setFirstName(user.getFirstName());
			newUser.setPassword(user.getPassword());
			return new ResponseEntity<>(userService.saveUser(newUser), HttpStatus.OK);
		} else {
			logger.error("Usuário não encontrado");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Método que realiza a exclusão de um usuário existente na base de dados.
	 * @param id - Long - identificador único do usuário.
	 * @return - HttpStatus
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		try {
			userService.deleteUserById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}