package com.marti.educacion.saem.services;

import java.util.Date;
import java.util.List;

import com.marti.educacion.saem.dto.AlumnoPagoForm;
import com.marti.educacion.saem.entities.AlumnoPago;

public interface AlumnoPagoService {

	public List<AlumnoPago> findAll();
	
	public void save(AlumnoPagoForm alumnoForm) throws Exception;
	
	//public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario, Boolean checked, Double saldo);
	public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario, Boolean checked, Date fechaPago);
	
	public List<AlumnoPagoForm> findByIdAlumno(Integer id);
	
	public void delete(AlumnoPagoForm alumnoForm);
	
	public void getList();
	
	public void updateMontoFechaExceed();
	
	public void updateStatusByPago(Integer idAlumno);
	
	public AlumnoPagoForm updateFechaLimite(Integer idPago, Date fechaLimite);
	
	public List<AlumnoPagoForm> findByIdPagoGradoAndFechaLimite(Integer idPagoGrado, Date fechaLimite);
	
	public void deleteByPagoGradoId(Integer idPagoGrado);
	
}
