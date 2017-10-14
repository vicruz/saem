package com.marti.educacion.saem.controller.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.dto.DescuentoForm;
import com.marti.educacion.saem.json.AlumnoDescuentoJson;
import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.services.AlumnoDescuentoService;

@RestController
@RequestMapping(path="/descuento")
public class DescuentoRestController {

	private static final Logger logger = LoggerFactory.getLogger(DescuentoRestController.class);
	
	
	private AlumnoDescuentoService alumnoDescuentoService;
	
	@Autowired
	public DescuentoRestController(AlumnoDescuentoService alumnoDescuentoService){
		this.alumnoDescuentoService = alumnoDescuentoService;
	}
	
	
	@RequestMapping(value="/alumno/{idAlumno}", method = RequestMethod.GET)
	public JSon getCatalogoDescuento(@PathVariable("idAlumno") Integer idAlumno){
		
		logger.debug("Buscando descuentos del alumno: " + idAlumno);
		
		AlumnoDescuentoJson json;
		JSon value = new JSon();
		
		List<AlumnoDescuentoJson> lst = new ArrayList<AlumnoDescuentoJson>();
		List<DescuentoForm> lstDescuentos = alumnoDescuentoService.findListAlumno(idAlumno);
		
		for(DescuentoForm form : lstDescuentos){
			json = new AlumnoDescuentoJson();
			
			json.setFin(form.getFechaFin());
			json.setId(form.getDescuentoId());
			json.setInicio(form.getFechaInicio());
			json.setDescuento(form.getMonto());
			json.setUrlBorrar("/descuento/delete/"+form.getDescuentoId());
			
			lst.add(json);
		}
		
		value.setData(lst);
		
		return value;
	}
	
	@RequestMapping(value="/delete/{idDescuento}", method = RequestMethod.GET)
	public JSon deleteBeca(@PathVariable("idDescuento") Integer idDescuento){
		
		logger.debug("Eliminando descuento: " + idDescuento);
		
		JSon value = new JSon();
		List<Boolean> lst = new ArrayList<Boolean>();
		lst.add(true);
		
		alumnoDescuentoService.deleteDescuentoById(idDescuento);
				
		value.setData(lst);
		
		return value;
	}
	
}
