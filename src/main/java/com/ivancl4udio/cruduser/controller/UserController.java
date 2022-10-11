package com.ivancl4udio.cruduser.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ivancl4udio.cruduser.model.User;
import com.ivancl4udio.cruduser.repository.UserRepository;

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

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);
	
	private UserRepository userRepository;
	
	@Autowired
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(@RequestParam(required=false) String lastName) {
		try {
			
			logger.info("Listing all users from db");
			
			List<User> users = new ArrayList<User>();

			if (lastName == null) {
				userRepository.findAll().forEach(users::add);
			} else {
				userRepository.findByLastName(lastName).forEach(users::add);
			}
			
			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
			
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
	}
	
	@GetMapping("users/{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		
		logger.info("Getting a single user with id: " + id);
		
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("/users")
	public ResponseEntity<User> createNewUser(@RequestBody User user){
		logger.info("Creating a new user");
		try {
			User _user = userRepository
					.save(new User(user.getUserName(), user.getLastName(), user.getFirstName(), user.getPassword()));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<User>	updateUser(@PathVariable ("id") long id, @RequestBody User user){
		logger.info("Changing user with id: " + id);
		
		Optional<User> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			User _user = userData.get();
			_user.setUserName(user.getUserName());
			_user.setLastName(user.getLastName());
			_user.setFirstName(user.getFirstName());
			_user.setPassword(user.getPassword());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
		try {
			logger.info("Deleting user with id: " + id);
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			logger.error(e.toString());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
