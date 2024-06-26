package model;
// Generated Mar 24, 2024, 12:07:53 AM by Hibernate Tools 5.4.33.Final

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private Integer idUser;
	private String name;
	private String surname;
	private String tel;
	private String email;
	private String password;
	private String role;
	@JsonIgnore
	private Set reserves = new HashSet(0);
	@JsonIgnore
	private Set orderLevels = new HashSet(0);

	public User() {
	}

	public User(String name, String surname, String tel, String email, String password, String role) {
		this.name = name;
		this.surname = surname;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(String name, String surname, String tel, String email, String password, String role, Set reserves,
			Set orderLevels) {
		this.name = name;
		this.surname = surname;
		this.tel = tel;
		this.email = email;
		this.password = password;
		this.role = role;
		this.reserves = reserves;
		this.orderLevels = orderLevels;
	}

	public Integer getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set getReserves() {
		return this.reserves;
	}

	public void setReserves(Set reserves) {
		this.reserves = reserves;
	}

	public Set getOrderLevels() {
		return this.orderLevels;
	}

	public void setOrderLevels(Set orderLevels) {
		this.orderLevels = orderLevels;
	}

}
