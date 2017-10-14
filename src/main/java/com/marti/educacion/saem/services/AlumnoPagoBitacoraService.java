package com.marti.educacion.saem.services;

import java.util.Date;
import java.util.List;

import com.marti.educacion.saem.entities.AlumnoReportDailyVO;

public interface AlumnoPagoBitacoraService {

	List<AlumnoReportDailyVO> getPagosBetweenFechaPago(Date fechaInicio, Date fechaFin);
	
}
