package com.marti.educacion.saem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.SignupForm;
import com.marti.educacion.saem.dto.UserDetailsImpl;
import com.marti.educacion.saem.dto.UserForm;
import com.marti.educacion.saem.entities.Usuario;
import com.marti.educacion.saem.repositories.UsuarioRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class UserServiceImpl implements UserService, UserDetailsService{

	private UsuarioRepository userRepository;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl(UsuarioRepository userRepository, PasswordEncoder passwordEncoder){
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void signup(SignupForm signupForm) {
		Usuario user = new Usuario();
		user.setEmail(signupForm.getEmail());
		user.setUsuario(signupForm.getName());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		
		userRepository.save(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userRepository.findByUsuario(username);
		if(user == null)
			throw new UsernameNotFoundException(username);
		
		return new UserDetailsImpl(user);
			
		
	}

	@Override
	public Usuario findUserById(int usuarioId) {
		// TODO Auto-generated method stub
		return userRepository.findUserById(usuarioId);
	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public int deleteUserById(int usuarioId) {
		// TODO Auto-generated method stub
		return userRepository.deleteById(usuarioId);
	}

	@Override
	public void newUser(UserForm userForm) {
		Usuario user = new Usuario();
		user.setEmail(userForm.getEmail());
		user.setUsuario(userForm.getName());
		user.setPassword(passwordEncoder.encode(userForm.getPassword()));
		user.setRol_id(userForm.isAdmin()?1:0);
		
		userRepository.save(user);
	}

}
