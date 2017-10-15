package com.marti.educacion.saem.schedule;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.marti.educacion.saem.services.AlumnoPagoService;


//@Component
public class SchedulePayments {

	private static final Logger log = LoggerFactory.getLogger(SchedulePayments.class);
	private AlumnoPagoService alumnoPagoService;
	
//	@Autowired
	public SchedulePayments(AlumnoPagoService alumnoPagoService){
		this.alumnoPagoService = alumnoPagoService;
	}
	
	//Se ejecuta todos los dias a las 00:10 a.m.
	//@Scheduled(cron="0 10 0 * * *")
//	@Scheduled(cron="0 10 0 * * *") //Para debug, cada 5 minutos
    public void updatePayments() {
        log.info("Iniciando la busqueda de pagos vencidos: " + Calendar.getInstance().getTime());
//        alumnoPagoService.updateMontoFechaExceed();
        log.info("Terminando la busqueda de pagos vencidos: " + Calendar.getInstance().getTime());
    }
}
