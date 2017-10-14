package com.marti.educacion.saem.services;

import com.marti.educacion.saem.dto.SignupForm;
import com.marti.educacion.saem.dto.UserForm;
import com.marti.educacion.saem.entities.User;

public interface UserService {

	public abstract void signup(SignupForm signupForm);
	public abstract void newUser(UserForm userForm);
	public User findUserById(int usuarioId);
	public int  deleteUserById(int usuarioId);
	
}
