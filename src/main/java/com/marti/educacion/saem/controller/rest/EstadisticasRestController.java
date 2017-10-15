package com.marti.educacion.saem.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.json.EstadisticaJson;
import com.marti.educacion.saem.services.EstadisticaService;

//@RestController
//@RequestMapping(path="/estadisticas")
public class EstadisticasRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(EstadisticasRestController.class);

	private EstadisticaService estadisticaService;
	
//	@Autowired
	public EstadisticasRestController(EstadisticaService estadisticaService){
		this.estadisticaService = estadisticaService;
	}
	
	//@RequestMapping(value="/now/chart/{total}",method = RequestMethod.GET)
	public EstadisticaJson currentMonth(@PathVariable("total") String total){
		Calendar cal = Calendar.getInstance();
		logger.info("Estadisticas del mes: " + (cal.get(Calendar.MONTH)+1));
		
		//Obtiene los montos correspondientes al mes en curso
		//TOTAL, Pago y Adeudo
		/*
		select pg.id, ap.monto, ap.pago
		from sae.pago_grado pg
		join sae.alumno_pago ap on pg.id = ap.id_pago_grado
		where pg.mes_corresponde = 12 and pg.anio_corresponde = 2016;
		*/
		/*if(total.equals("true")){
			return estadisticaService.currentMonthPays(true);
		}else{
			return estadisticaService.currentMonthPays(false);
		}*/
		return null;
		
	}
	
	//@RequestMapping(value="/between/chart/{total}",method = RequestMethod.POST)
	public EstadisticaJson betweenMonth(@PathVariable("total") String total, String inicio, String fin) 
			throws ParseException{
		
		logger.info("Estadisticas entre mes: " + inicio + " y " + fin);
		
		//Obtiene los montos correspondientes entre fechas establecidas
		/*select pg.id, ap.monto, ap.pago
		from sae.pago_grado pg
		join sae.alumno_pago ap on pg.id = ap.id_pago_grado
		where (pg.mes_corresponde >= 12 and pg.mes_corresponde <= 12)
		and (pg.anio_corresponde >= 2016 and pg.anio_corresponde <= 2016);*/
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date dateIni, dateFin;
		
		dateIni = formatter.parse(inicio);
		dateFin = formatter.parse(fin);
		
//		if(total.equals("true")){
//			return estadisticaService.betweentMonthPays(dateIni, dateFin, true);
//		}else{
//			return estadisticaService.betweentMonthPays(dateIni, dateFin, false);
//		}
		return null;
	}
	
	public void gradeCurrentMonth(){
		//Obtiene la suma por grado de los pagos correspondientes al mes en curso
		/*select pg.id_grado, sum(ap.pago)
		from sae.pago_grado pg
		join sae.alumno_pago ap on pg.id = ap.id_pago_grado
		where pg.mes_corresponde = 12 and pg.anio_corresponde = 2016
		group by pg.id_grado
		order by pg.id_grado;
		*/
	}
	
	public void gradeBetweenMonth(){
		//Obtiene la suma por grado de los pagos indicados entre la fecha
		/*select pg.id_grado, sum(ap.pago)
		from sae.pago_grado pg
		join sae.alumno_pago ap on pg.id = ap.id_pago_grado
		where (pg.mes_corresponde >= 12 and pg.anio_corresponde <= 2016)
		and (pg.mes_corresponde >= 12 and pg.anio_corresponde <= 2016)
		group by pg.id_grado
		order by pg.id_grado;*/
	}
	
}
