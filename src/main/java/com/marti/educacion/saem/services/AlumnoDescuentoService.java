package com.marti.educacion.saem.services;

import java.text.ParseException;
import java.util.List;

import com.marti.educacion.saem.dto.DescuentoForm;

public interface AlumnoDescuentoService {
	
	public void save(DescuentoForm descuentoform, Integer idAlumno) throws ParseException;
	
	public DescuentoForm findOne(Integer idBeca);
	
	public List<DescuentoForm> findListAlumno(Integer idAlumno);
	
	public void deleteDescuentoById(Integer idDescuento);

}
