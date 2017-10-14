package com.marti.educacion.saem.controller.rest;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.entities.CatPagos;
import com.marti.educacion.saem.entities.Grado;
import com.marti.educacion.saem.entities.PagoGrado;
import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.json.PagoGradoTableJson;
import com.marti.educacion.saem.services.CatPagosService;
import com.marti.educacion.saem.services.GradoService;
import com.marti.educacion.saem.services.PagoGradoService;

@RestController
public class PagoGradoRestController {

	private PagoGradoService pagoGradoService;
	private CatPagosService catPagosService;
	private GradoService gradoService;

	private static final Logger logger = LoggerFactory.getLogger(PagoGradoRestController.class);


	@Autowired
	public PagoGradoRestController(PagoGradoService pagoGradoService, CatPagosService catPagosService,
			GradoService gradoService){
		this.pagoGradoService = pagoGradoService;
		this.catPagosService = catPagosService;
		this.gradoService= gradoService;
	}
	
	@RequestMapping(value="/pagoGradoRest",method = RequestMethod.POST)
	public JSon adminRest(){
		System.out.println("pagoRest");
		JSon value = new JSon();
    
		List<PagoGradoTableJson> lstJson = new ArrayList<PagoGradoTableJson>();
		List<PagoGrado> pagoGradolst = pagoGradoService.findAll();
		List<CatPagos> catpagolst = catPagosService.findAll();
		List<Grado> gradoLst= gradoService.findAll();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	
		for(PagoGrado pd:pagoGradolst ){
			String fechaLimite =formatter.format(pd.getFechaLimite());
			PagoGradoTableJson pgJson= new PagoGradoTableJson();
			
			pgJson.setIdPagoGrado(pd.getId());
			for(Grado gd:gradoLst){
				if(pd.getIdGrado()==gd.getId()){
					pgJson.setGrado(gd.getName());
				}
			}
			for(CatPagos catpg :catpagolst){
				if(pd.getCatPago().getId()==catpg.getId()){
					pgJson.setConcepto(catpg.getConcepto());
					pgJson.setMonto(catpg.getMonto());
				}
			}
			pgJson.setMes(checkMes(pd.getMes_corresponde()));
			pgJson.setAnio(pd.getAnio_corresponde());
			pgJson.setFechaLimite(fechaLimite);
			lstJson.add(pgJson);
		}
	
		value.setData(lstJson);
		return value;
	}

	private String checkMes(Integer mes) {
		// TODO Auto-generated method stub
		if(mes==1)
			return"Enero";
		else if(mes==2)
			return "Febrero";
		else if(mes==3)
			return "Marzo";
		else if(mes==4)
			return "Abril";
		else if(mes==5)
			return "Mayo";
		else if(mes==6)
			return "Junio";
		else if(mes==7)
			return "Julio";
		else if(mes==8)
			return "Agosto";
		else if(mes==9)
			return "Septiembre";
		else if(mes==10)
			return "Octubre";
		else if(mes==11)
			return "Noviembre";
		else if(mes==12)
			return "Diciembre";
		else{
			return "ERROR";
		}
	}
	
	
	
}
