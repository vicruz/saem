package com.marti.educacion.saem.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.services.AlumnoService;
import com.marti.educacion.saem.util.Constantes;

@RestController
public class AlumnoRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(AlumnoRestController.class);
	
	//private AlumnoRepository alumnoRepository;
	private AlumnoService alumnoService;
	
	@Autowired
	public AlumnoRestController(AlumnoService alumnoService){
		this.alumnoService = alumnoService;
	}
	
	@RequestMapping(value="/alumnoRest", method = RequestMethod.POST)
	public JSon alumnosRest() {
		logger.debug("Obtener todos los alumnos");
		return alumnoService.getList();
	}
	
	
	/**
	 * Da de baja a todos los alumnos registrados
	 */
	@RequestMapping(value="/alumnoRest/baja", method = RequestMethod.POST)
	public void deactivateAllAlumnos(){
		logger.debug("Desactivar a todos los alumnos");
		alumnoService.deactivateAllAlumnos();
	}
	
	/**
	 * Da de alta a todos los alumnos registrados
	 */
	@RequestMapping(value="/alumnoRest/alta", method = RequestMethod.POST)
	public void activateAllAlumnos(){
		logger.debug("Activar a todos los alumnos");
		alumnoService.activateAllAlumnos();
	}
	
	/**
	 * Cambia el estatus del alumno a 'baja'
	 */
	@RequestMapping(value="/alumnoRest/bajaById/{idAlumno}", method = RequestMethod.GET)
	public void bajaByAlumno(@PathVariable("idAlumno") Integer alumnoId){
		logger.debug("Baja del alumno: " + alumnoId);
		alumnoService.changeEstatusByAlumno(Constantes.ESTATUS_INACTIVO, alumnoId);
	}
	
	/**
	 * Cambia el estatus del alumno a 'alta'
	 */
	@RequestMapping(value="/alumnoRest/altaById/{idAlumno}", method = RequestMethod.GET)
	public void altaByAlumno(@PathVariable("idAlumno") Integer alumnoId){
		logger.debug("Alta del alumno: " + alumnoId);
		alumnoService.changeEstatusByAlumno(Constantes.ESTATUS_ACTIVO, alumnoId);
	}

}
