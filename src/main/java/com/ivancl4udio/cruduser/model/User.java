package com.ivancl4udio.cruduser.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Classe de entidade utilizada para persistência das informações.
 */
@Entity
@Table(name="appuser")
public class User {

	@Id	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name = "username")
	private String userName;
	
	@Column(name = "lastname")
	private String lastName;
	
	@Column(name = "firstname")
	private String firstName;
	
	@Column(name = "password")
	private String password;

	public User() {}
		
	public User(String userName, String lastName, String firstName, String password) {
		super();
		this.userName = userName;
		this.lastName = lastName;
		this.firstName = firstName;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	@Override
	public int hashCode() {
		return Objects.hash(firstName, id, lastName, password, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(password, other.password)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", password=" + password + "]";
	}

}
