package com.marti.educacion.saem.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.entities.User;
import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.json.UserJson;
import com.marti.educacion.saem.repositories.UserRepository;

@RestController
public class UserRestController {

	private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
	
	private UserRepository userRepository;
	
	public UserRestController(UserRepository userRepository){
		this.userRepository = userRepository;
	}
	
	
	@RequestMapping(value="/userRest",method = RequestMethod.POST)
	public JSon adminRest(){
		
		JSon value = new JSon();
		Authentication userAuth= SecurityContextHolder.getContext().getAuthentication();
        
		
		logger.info("Usuario POST request"+userAuth.getName());
		List<UserJson> lstJson = new ArrayList<UserJson>();
		
		
		List<User> lstAdmin = userRepository.findAllMinus(userAuth.getName());
		
		if(lstAdmin!=null){
			for(User administrador : lstAdmin){
				UserJson json = new UserJson();
				json.setUsuario(administrador.getUsuario());
				json.setEmail(administrador.getEmail());
				json.setId(administrador.getId());
				json.setUrl("/usuario/"+administrador.getId());
				lstJson.add(json);
			}
		}
		
		value.setData(lstJson);
		return value;
	}
	
	
	}
