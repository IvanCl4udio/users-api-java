package com.ivancl4udio.cruduser.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 * Classe de entidade utilizada para persistência das informações.
 */
@Entity
@Table(name = "appuser")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull(message = "O nome de usuário não pode ser nulo.")
    @Column(name = "username", unique = true, nullable = false)
    private String userName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String userName, String lastName, String firstName, String password) {
        super();
        this.userName = userName;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
