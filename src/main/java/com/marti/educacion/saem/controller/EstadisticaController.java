package com.marti.educacion.saem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Controller
public class EstadisticaController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstadisticaController.class);
	
	//@RequestMapping(value="/estadisticas", method=RequestMethod.GET)
	//public String estadisticas(Model model){
	public String estadisticas(Model model){
		logger.debug("Estadisticas");
		
		//model.addAttribute(new EstadisticaForm());
		return "estadisticas";
	}
	
	//@RequestMapping(value="/reportes", method=RequestMethod.GET)
	public String reportes(Model model){
		logger.debug("Reportes");
		
		return "reportes";
	}

}
