package com.ssm_Oracle.entity;

import javax.persistence.*;

@Table(name="KRRY_USER_TEST")
public class User extends BaseEntity{
	
	@Column(name="ID",nullable=false,unique=true)
	private String id;
	@Column(name="USERNAME",nullable=false,unique=true)
	private String username;
	@Column(name="PASSWORD",nullable=true,unique=false)
	private String password;
	@Column(name="EMAIL",nullable=false,unique=true)
	private String email;
	@Column(name="ESTABLISH_TIME",nullable=true,unique=false)
	private String establishTime;
	
	public String getId() {
		return id;
	}
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getEmail() {
		return email;
	}
	public String getEstablishTime() {
		return establishTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setEstablishTime(String establishTime) {
		this.establishTime = establishTime;
	}
	public User(){}
	public User(String id, String username, String password, String email, String establishTime) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.establishTime = establishTime;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + username + ", password=" + password + ", email=" + email + ", establishTime="
				+ establishTime + "]";
	}
	
}
