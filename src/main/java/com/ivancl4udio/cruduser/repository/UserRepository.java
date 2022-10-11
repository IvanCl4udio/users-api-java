package com.ivancl4udio.cruduser.repository;

import java.util.List;

import com.ivancl4udio.cruduser.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>{

		List<User> findByLastName(String lastName);
}
