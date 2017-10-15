package com.marti.educacion.saem.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.json.EstadisticaDataJson;
import com.marti.educacion.saem.json.EstadisticaJson;
import com.marti.educacion.saem.repositories.AlumnoPagoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class EstadisticaServiceImpl implements EstadisticaService {
	/*
	private AlumnoPagoRepository alumnoPagoRepository;
	
	@Autowired
	public EstadisticaServiceImpl(AlumnoPagoRepository alumnoPagoRepository){
		this.alumnoPagoRepository = alumnoPagoRepository;
	}

	@Override
	public EstadisticaJson currentMonthPays(boolean total) {
		Calendar cal = Calendar.getInstance();
		
		List<AlumnoPago> lstAlumno = alumnoPagoRepository.currentMonthPays(cal.get(Calendar.MONTH)+1, cal.get(Calendar.YEAR)); 
		
		//Obtiene los datos de la primer grafica [total, adeudo, pago]
		if(total){
			return generaEstadisticaTotal(lstAlumno);
		}else{
			return generaEstadisticaXGrado(lstAlumno);
		}
	}

	@Override
	public EstadisticaJson betweentMonthPays(Date dateInicio, Date dateFinal, boolean total) {
		Calendar calInit = Calendar.getInstance();
		Calendar calFin = Calendar.getInstance();
		
		calInit.setTime(dateInicio);
		calFin.setTime(dateFinal);
		
//		List<AlumnoPago> lstAlumno = alumnoPagoRepository.betweenMonthPays(calInit.get(Calendar.MONTH)+1, calFin.get(Calendar.MONTH) + 1, 
//				calInit.get(Calendar.YEAR), calFin.get(Calendar.YEAR));
		List<AlumnoPago> lstAlumno = alumnoPagoRepository.betweenMonthPays(dateInicio, dateFinal);
		
		if(total){
			return generaEstadisticaTotal(lstAlumno);
		}else{
			return generaEstadisticaXGrado(lstAlumno);
		}
	}
	
	//Obtiene los datos de la primer grafica [total, adeudo, pago]
	private EstadisticaJson generaEstadisticaTotal(List<AlumnoPago> lstAlumno){
		EstadisticaJson jsonTotal = new EstadisticaJson();
		double total = 0.0;
		double adeudo = 0.0;
		double pago = 0.0;
		
		for(AlumnoPago alumnoPago : lstAlumno){
			total += alumnoPago.getMonto();
			pago += alumnoPago.getPago();
		}
		adeudo = total - pago;
		jsonTotal.setTotal(total);
		jsonTotal.getData().add(new EstadisticaDataJson("Adeudo",adeudo));
		jsonTotal.getData().add(new EstadisticaDataJson("Pagos",pago));
		jsonTotal.getColors().add("#FC4A4A");
		jsonTotal.getColors().add("#2EC829");
		
		return jsonTotal;
	}
	
	//Obtiene los datos de la segunda grafica [total, kinder 1, kinder 2 ..]
	private EstadisticaJson generaEstadisticaXGrado(List<AlumnoPago> lstAlumno){
		EstadisticaJson jsonTotal = new EstadisticaJson();
		double total = 0.0;
		double pagok1 = 0.0;
		double pagok2 = 0.0;
		double pagok3 = 0.0;
		double pagop1 = 0.0;
		double pagop2 = 0.0;
		double pagop3 = 0.0;
		double pagop4 = 0.0;
		double pagop5 = 0.0;
		double pagop6 = 0.0;
		
		for(AlumnoPago alumnoPago : lstAlumno){
			total += alumnoPago.getPago();
			
			switch(alumnoPago.getPagoGrado().getIdGrado()){
			case 1:
				pagok1 += alumnoPago.getPago();
				break;
			case 2:
				pagok2 += alumnoPago.getPago();
				break;
			case 3:
				pagok3 += alumnoPago.getPago();
				break;
			case 4:
				pagop1 += alumnoPago.getPago();
				break;
			case 5:
				pagop2 += alumnoPago.getPago();
				break;
			case 6:
				pagop3 += alumnoPago.getPago();
				break;
			case 7:
				pagop4 += alumnoPago.getPago();
				break;
			case 8:
				pagop5 += alumnoPago.getPago();
				break;
			case 9:
				pagop6 += alumnoPago.getPago();
				break;
			}
		}		
		jsonTotal.setTotal(total);
		
		if(pagok1!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Kinder 1",pagok1));
			jsonTotal.getColors().add("#F4FA58");
		}
		if(pagok2!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Kinder 2",pagok2));
			jsonTotal.getColors().add("#58FA82");
		}
		if(pagok3!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Kinder 3",pagok3));
			jsonTotal.getColors().add("#5882FA");
		}
		if(pagop1!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Primaria 1",pagop1));
			jsonTotal.getColors().add("#04B4AE");
		}
		if(pagop2!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Primaria 2",pagop2));
			jsonTotal.getColors().add("#FF4000");
		}
		if(pagop3!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Primaria 3",pagop3));
			jsonTotal.getColors().add("#0174DF");
		}
		if(pagop4!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Primaria 4",pagop4));
			jsonTotal.getColors().add("#01DFA5");
		}
		if(pagop5!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Primaria 5",pagop5));
			jsonTotal.getColors().add("#3ADF00");
		}
		if(pagop6!=0){
			jsonTotal.getData().add(new EstadisticaDataJson("Primaria 6",pagop6));
			jsonTotal.getColors().add("#DBA901");
		}
		return jsonTotal;
	}
*/
}
