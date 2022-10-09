package com.learningtree.spring.rest;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	private String userName;  // JPA maps to column user_name

	private String userPass;  // JPA maps to column user_pass
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name =  "user_name")
	private List<UserRole> userRoles;

	public User() {}

	public User(String userName, String userPass, List<UserRole> userRoles) {
		super();
		this.userName = userName;
		this.userPass = userPass;
		this.userRoles = userRoles;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userName, userPass, userRoles);
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
		return Objects.equals(userName, other.userName) && Objects.equals(userPass, other.userPass)
				&& Objects.equals(userRoles, other.userRoles);
	}

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPass=" + userPass + ", userRoles=" + userRoles + "]";
	}

}
