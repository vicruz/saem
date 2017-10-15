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

import com.marti.educacion.saem.dto.BecaForm;
import com.marti.educacion.saem.json.AlumnoBecaJson;
import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.services.AlumnoBecaService;

//@RestController
//@RequestMapping(path="/beca")
public class BecaRestController {

	private static final Logger logger = LoggerFactory.getLogger(BecaRestController.class);
	
//	@Autowired
	private AlumnoBecaService alumnoBecaService;
	
	public BecaRestController(AlumnoBecaService alumnoBecaService){
		this.alumnoBecaService = alumnoBecaService;
	}
	
	
	//@RequestMapping(value="/alumno/{idAlumno}", method = RequestMethod.GET)
	public JSon getCatalogoPago(@PathVariable("idAlumno") Integer idAlumno){
		
		logger.info("Buscando la beca del alumno: " + idAlumno);
		
		AlumnoBecaJson json;
		JSon value = new JSon();
		
		List<AlumnoBecaJson> lst = new ArrayList<AlumnoBecaJson>();
	/*	List<BecaForm> lstBecas = alumnoBecaService.findListAlumno(idAlumno);
		
		for(BecaForm form : lstBecas){
			json = new AlumnoBecaJson();
			
			json.setFin(form.getFechaFin());
			json.setId(form.getBecaId());
			json.setInicio(form.getFechaInicio());
			json.setPorcentaje(form.getPorcentaje());
			json.setUrlBorrar("/beca/delete/"+form.getBecaId());
			
			lst.add(json);
		}
	*/	
		value.setData(lst);
		
		return value;
	}
	
	/**
	 * Elimina la beca asignada al alumno
	 * @param idBeca
	 * @return
	 */
	//@RequestMapping(value="/delete/{idBeca}", method = RequestMethod.GET)
	public JSon deleteBeca(@PathVariable("idBeca") Integer idBeca){
		
		logger.info("Eliminando beca : " + idBeca);
		
		JSon value = new JSon();
		List<Boolean> lst = new ArrayList<Boolean>();
		lst.add(true);
		
//		alumnoBecaService.deleteBeca(idBeca);
				
		value.setData(lst);
		
		return value;
	}
	
}
