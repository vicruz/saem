package com.marti.educacion.saem.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marti.educacion.saem.dto.SignupForm;
import com.marti.educacion.saem.services.UserService;
import com.marti.educacion.saem.util.MyUtil;
import com.marti.educacion.saem.validators.SignupFormValidator;

@Controller
public class RootController {

	private static final Logger logger = LoggerFactory.getLogger(RootController.class);
	
	private UserService userService;
	public SignupFormValidator signupFormValidator;
	
//	@RequestMapping("/")
//	public String home() {
//		return "home";
//	}
	
	@Autowired
	public RootController(UserService userService, SignupFormValidator signupFormValidator) {
		this.userService = userService;
		this.signupFormValidator = signupFormValidator;
	}
	
	@InitBinder("signupForm")
	protected void initSignupBinder(WebDataBinder binder){
		binder.setValidator(signupFormValidator);
	}
	
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		System.out.println("ENTRA////");
		//model.addAttribute("name","vic");
		model.addAttribute(new SignupForm());
		
		return "signup";
	}
	
	@RequestMapping(value="/signup", method = RequestMethod.POST)
	public String signup(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes){
		System.out.println("POST////");
		if(result.hasErrors())
			return "signup";
			
		logger.info(signupForm.toString());
		
		userService.signup(signupForm);
		
		MyUtil.flash(redirectAttributes, "success", "signupSuccess");
		
		
		return "redirect:/";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(Model model) {
		
		//model.addAttribute("name","vic");
		model.addAttribute(new SignupForm());
		
		return "login";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(@ModelAttribute("signupForm") @Valid SignupForm signupForm,
			BindingResult result, RedirectAttributes redirectAttributes){
			
		logger.info(signupForm.toString());
		
		return "redirect:/home";
	}
	
	
	
	@RequestMapping(value="/table", method = RequestMethod.GET)
	public String table() {
		return "table";
	}
	
}
