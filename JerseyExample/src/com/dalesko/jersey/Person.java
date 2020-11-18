package com.dalesko.jersey;

public class Person {

	private String name;
	private String password;
	public String getId() {
		return name;
	}
	public void setId(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
