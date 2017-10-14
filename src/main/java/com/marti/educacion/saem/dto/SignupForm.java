package com.marti.educacion.saem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class SignupForm {

	@NotNull
	@Size(min=1, max=255, message="{signup.mail.message.size}")
	//@Pattern(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}")
	private String email;
	
	@NotNull
	@Size(min=1, max=100)
	private String name;
	
	@NotNull
	@Size(min=1, max=30)
	private String password;
	@NotNull
	private String nameAl;

	public String getNameAl() {
		return nameAl;
	}
	public void setNameAl(String nameAl) {
		this.nameAl = nameAl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "SignupForm [email=" + email + ", name=" + name + ", password=" + password + "]";
	}
	
}
