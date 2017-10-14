package com.marti.educacion.saem.services;

import java.util.Date;

import com.marti.educacion.saem.json.EstadisticaJson;

public interface EstadisticaService {

	EstadisticaJson currentMonthPays(boolean total);
	
	EstadisticaJson betweentMonthPays(Date dateInicio, Date dateFinal, boolean total);
	
}
