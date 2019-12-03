package upjs.sk.pizza_int;

import java.util.ArrayList;
import java.util.List;

public class User {

	private Long id;
	private String name;
	private String surname;
	private String login;
	private String password;
	private String email;
	private int telNumber;
	private String isicCardNumber;
	private int role;
	private List<User> userList = new ArrayList<User>();
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTel_number() {
		return telNumber;
	}
	public void setTel_number(int tel_number) {
		this.telNumber = tel_number;
	}
	public String getIsicCardNumber() {
		return isicCardNumber;
	}
	public void setIsicCardNumber(String isicCardNumber) {
		this.isicCardNumber = isicCardNumber;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", login=" + login + ", password="
				+ password + ", email=" + email + ", tel_number=" + telNumber + ", isicCardNumber=" + isicCardNumber
				+ ", role=" + role + "]";
	}
	
	
	

}
