package com.marti.educacion.saem.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.DescuentoForm;
import com.marti.educacion.saem.entities.Alumno;
import com.marti.educacion.saem.entities.AlumnoDescuento;
import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.repositories.AlumnoBecaRepository;
import com.marti.educacion.saem.repositories.AlumnoDescuentoRepository;
import com.marti.educacion.saem.repositories.AlumnoPagoRepository;
import com.marti.educacion.saem.repositories.AlumnoRepository;
import com.marti.educacion.saem.util.Constantes;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoDescuentoServiceImpl implements AlumnoDescuentoService{

	private AlumnoDescuentoRepository alumnoDescuentoRepository;
	private AlumnoPagoRepository alumnoPagoRepository;
	private AlumnoBecaRepository alumnoBecaRepository;
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	public AlumnoDescuentoServiceImpl(AlumnoDescuentoRepository alumnoDescuentoRepository,
			AlumnoPagoRepository alumnoPagoRepository, AlumnoBecaRepository alumnoBecaRepository,
			AlumnoRepository alumnoRepository){
		this.alumnoDescuentoRepository = alumnoDescuentoRepository;
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.alumnoBecaRepository = alumnoBecaRepository;
		this.alumnoRepository = alumnoRepository;
	}
	
	@Override
	public void save(DescuentoForm descuentoForm, Integer idAlumno) throws ParseException {
		AlumnoDescuento alumnoDescuento = new AlumnoDescuento();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		double diferencia = 0.0;
		double montoDescuento;
		
			montoDescuento = descuentoForm.getMonto();
			alumnoDescuento.setFechaFin(formatter.parse(descuentoForm.getFechaFin()));
			alumnoDescuento.setFechaInicio(formatter.parse(descuentoForm.getFechaInicio()));
			alumnoDescuento.setDescuento(descuentoForm.getMonto());
			alumnoDescuento.setIdAlumno(idAlumno);
			alumnoDescuento.setActivo(Constantes.ESTATUS_ACTIVO);
			
			//Guardar el descuento
			alumnoDescuentoRepository.save(alumnoDescuento);
			
			//Obtener los pagos donde se aplica la beca
			List<AlumnoPago> lstPagos = alumnoBecaRepository.findPagosAplicaBeca(idAlumno, alumnoDescuento.getFechaInicio(), alumnoDescuento.getFechaFin());
			
			//Modificar los pagos y sumar la diferencia de los pagos
			for(AlumnoPago pago : lstPagos){
				
				pago.setMonto(pago.getMonto()-montoDescuento);
				
				if(pago.getPago()>pago.getMonto()){
					diferencia = diferencia + (pago.getPago()-pago.getMonto());
				}
				
				alumnoPagoRepository.save(pago);
			}
			
			//Establecer la diferencia en el alumno
			Alumno alumno = alumnoRepository.findOne(idAlumno);
			alumno.setSaldo(alumno.getSaldo()+diferencia);
			alumnoRepository.save(alumno);
	}

	@Override
	public DescuentoForm findOne(Integer idBeca) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DescuentoForm> findListAlumno(Integer idAlumno) {
		List<DescuentoForm> lst = null;
		List<AlumnoDescuento> lstAlumno;
		DescuentoForm form;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		lstAlumno = alumnoDescuentoRepository.findByIdAlumno(idAlumno);
		
		if(lstAlumno != null){
			lst = new ArrayList<DescuentoForm>();
			for(AlumnoDescuento alumno : lstAlumno){
				form = new DescuentoForm();
				form.setDescuentoId(alumno.getIdDescuento());
				form.setMonto(alumno.getDescuento());
				form.setFechaInicio(formatter.format(alumno.getFechaInicio()));
				form.setFechaFin(formatter.format(alumno.getFechaFin()));
				lst.add(form);
			}
		}
		return lst;
	}

	/**
	 * Elimina el descuento solicitado y restaura los precios de los pagos
	 */
	@Override
	public void deleteDescuentoById(Integer idDescuento) {
		
		//Buscar el descuento
		AlumnoDescuento alumnoDescuento = alumnoDescuentoRepository.findOne(idDescuento);
		
		if(alumnoDescuento.getActivo()==Constantes.ESTATUS_ACTIVO){
			
			//Se obtienen los pagos donde se aplica el descuento
			List<AlumnoPago> lstPagos = alumnoBecaRepository.findPagosAplicaBeca(
					alumnoDescuento.getIdAlumno(), alumnoDescuento.getFechaInicio(), alumnoDescuento.getFechaFin());
			
			//Modificar los pagos sumando el monto de descuento
			for(AlumnoPago pago : lstPagos){
				
				//Se establece el monto de pago sumando el monto del descuento
				pago.setMonto(pago.getMonto()+alumnoDescuento.getDescuento());
				
				//Establece el semáforo del pago
				if(pago.getPago().doubleValue()==pago.getMonto().doubleValue()){
					pago.setIdSemaforo(Constantes.SEMAFORO_PAGADO);
				}else if(pago.getPago().doubleValue()<pago.getMonto().doubleValue()){
					//Si pago es 0 y la fecha limite de pago ha expirado
					if(pago.getPago().doubleValue()==0 && pago.getFechaLimite().before(Calendar.getInstance().getTime())){
						pago.setIdSemaforo(Constantes.SEMAFORO_ADEUDO);
					}else if(pago.getPago().doubleValue()==0 && pago.getFechaLimite().after(Calendar.getInstance().getTime())){
						pago.setIdSemaforo(Constantes.SEMAFORO_PENDIENTE);
					}else{
						pago.setIdSemaforo(Constantes.SEMAFORO_PARCIAL);
					}
				}
				
				//TODO manejo de saldos
				
				alumnoPagoRepository.save(pago);
			}
			
		}
		
		alumnoDescuentoRepository.delete(idDescuento);
	}

}
