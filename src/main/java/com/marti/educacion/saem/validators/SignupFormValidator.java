package com.marti.educacion.saem.validators;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.marti.educacion.saem.dto.SignupForm;
import com.marti.educacion.saem.entities.Usuario;
import com.marti.educacion.saem.repositories.UsuarioRepository;

@Component
public class SignupFormValidator extends LocalValidatorFactoryBean{
	
	private UsuarioRepository userRepository;
	
	@Resource
	public void setUserRepository(UsuarioRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(SignupForm.class);
	}
	
	@Override
	public void validate(Object obj, Errors errors, Object... validationHints) {
		super.validate(obj, errors, validationHints);
		
		if(!errors.hasErrors()){
			SignupForm signupForm = (SignupForm)obj;
			Usuario user = userRepository.findByUsuario(signupForm.getName());
			if(user!=null){
				errors.reject("name","usuarioNoUnico");
			}
		}
		
	}

}
