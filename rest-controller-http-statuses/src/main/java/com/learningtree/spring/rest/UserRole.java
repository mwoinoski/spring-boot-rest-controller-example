package com.learningtree.spring.rest;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_roles")
public class UserRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "user_name")  // required so JPA recognizes the join column name in the User class
	private String userName;
	
	private String roleName;  // JPA maps to column role_name

	public UserRole() {}

	public UserRole(Integer id, String userName, String roleName) {
		super();
		this.id = id;
		this.userName = userName;
		this.roleName = roleName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, roleName, userName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRole other = (UserRole) obj;
		return Objects.equals(id, other.id) && Objects.equals(roleName, other.roleName)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "UserRole [id=" + id + ", userName=" + userName + ", roleName=" + roleName + "]";
	}

}
