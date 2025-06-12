package com.ivancl4udio.cruduser.repository;

import java.util.List;
import java.util.UUID;

import com.ivancl4udio.cruduser.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 'Interface' de controle da persistência. Estende JPARepository
 */
public interface UserRepository extends JpaRepository<User, UUID>{

                /**
                 * Método que busca pelo último nome do usuário
                 *
                 * @param lastName - String - último nome do usuário
                 * @return lista de usuários correspondentes ao último nome informado
                 */
                List<User> findByLastName(String lastName);
}
