package com.marti.educacion.saem.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.AlumnoPagoForm;
import com.marti.educacion.saem.entities.Alumno;
import com.marti.educacion.saem.entities.AlumnoBeca;
import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.entities.AlumnoPagoBitacora;
import com.marti.educacion.saem.entities.PagoGrado;
import com.marti.educacion.saem.repositories.AlumnoBecaRepository;
import com.marti.educacion.saem.repositories.AlumnoPagoBitacoraRepository;
import com.marti.educacion.saem.repositories.AlumnoPagoRepository;
import com.marti.educacion.saem.repositories.AlumnoRepository;
import com.marti.educacion.saem.repositories.PagoGradoRepository;
import com.marti.educacion.saem.util.Constantes;
import com.marti.educacion.saem.util.MyUtil;

//@Service
//@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoPagoServiceImpl implements AlumnoPagoService {
	/*
	private AlumnoPagoRepository alumnoPagoRepository;
	private PagoGradoRepository pagoGradoRepository;
	private AlumnoRepository alumnoRepository;
	private AlumnoPagoBitacoraRepository alumnoPagoBitacoraRepository;
	private AlumnoBecaRepository alumnoBecaRepository;
	
	@Autowired
	public AlumnoPagoServiceImpl(AlumnoPagoRepository alumnoPagoRepository, AlumnoRepository alumnoRepository,
			AlumnoPagoBitacoraRepository alumnoPagoBitacoraRepository, PagoGradoRepository pagoGradoRepository,
			AlumnoBecaRepository alumnoBecaRepository) {
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.alumnoRepository = alumnoRepository;
		this.alumnoPagoBitacoraRepository = alumnoPagoBitacoraRepository;
		this.pagoGradoRepository = pagoGradoRepository;
		this.alumnoBecaRepository = alumnoBecaRepository;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
	public List<AlumnoPago> findAll() {
//		return alumnoPagoRepository.findAll();
		return null;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(AlumnoPagoForm alumnoForm) throws Exception {
		AlumnoPago alumnoPago = new AlumnoPago();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		PagoGrado pagoGrado = pagoGradoRepository.findOne(alumnoForm.getIdPagoGrado());
		Double monto = alumnoForm.getMonto(); 
		
		if(alumnoForm!=null){
			
			//Valida si aplica beca para el pago
			if(alumnoForm.getAplicaBeca()==1 || pagoGrado.getCatPago().getAplicaBeca()==1){
				List<AlumnoBeca> lstAlumnoBeca = alumnoBecaRepository.findByIdAlumnoOrderByIdBecaDesc(alumnoForm.getIdAlumno());
				
				//Se determina la beca del alumno
				if(lstAlumnoBeca.size()>0){
					//se obtiene el elemnto 0 del arreglo, es la última beca asignada
					AlumnoBeca alumnoBeca = lstAlumnoBeca.get(0);
					
					//Se valida si el pago se encuentra en el rango de la beca
					if(alumnoBeca.getFechaInicio().before(pagoGrado.getFechaCorresponde()) &&
							alumnoBeca.getFechaFin().after(pagoGrado.getFechaCorresponde())){
						//Genera el nuevo monto
						monto = monto -((monto * alumnoBeca.getPorcentaje())/100);
					}
					
				}
			}
			
			
			alumnoPago.setIdAlumno(alumnoForm.getIdAlumno());
			//alumnoPago.setMonto(alumnoForm.getMonto());
			alumnoPago.setMonto(monto);
			alumnoPago.setPago(alumnoForm.getPago()==null?0D:alumnoForm.getPago());
			if(alumnoForm.getPago()!=null){ 
				alumnoPago.setFechaPago(Calendar.getInstance().getTime());				
			}
			
			if(alumnoPago.getPago()!=null && alumnoPago.getPago().doubleValue()==0){
				alumnoPago.setFechaPago(null);
			}
			
			alumnoPago.setPagoGrado(pagoGrado);
			if(alumnoForm.getPago()==null || alumnoForm.getPago()==0.0){
				alumnoPago.setIdSemaforo(4);
			}else if(alumnoForm.getPago().compareTo(alumnoForm.getMonto())==0){
				alumnoPago.setIdSemaforo(1);
			}else if(alumnoForm.getPago()>0 && alumnoForm.getPago()<alumnoForm.getMonto()){
				alumnoPago.setIdSemaforo(2);
			}else{
				alumnoPago.setIdSemaforo(3);
			}
			
			if(alumnoForm.getFechaLimite()==null){
				alumnoPago.setFechaLimite(pagoGrado.getFechaLimite());
			}else{
				alumnoPago.setFechaLimite(sdf.parse(alumnoForm.getFechaLimite()));				
			}
			
			alumnoPagoRepository.save(alumnoPago);
		}

	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	//public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario, Boolean checked, Double saldo) {
	public AlumnoPagoForm updatePago(Integer idPago, Double pago, Integer idUsuario, Boolean checked, Date fechaPago) {
		Alumno alumno = null;
		AlumnoPago alumnoPago = alumnoPagoRepository.findOne(idPago);
		AlumnoPagoForm alumnoPagoForm = new AlumnoPagoForm();
		AlumnoBeca alumnoBeca;
		Double pagoTotal = 0.0;
		Double saldo = 0.0;
		Double saldoBitacora = 0.0;
		
		int mesesDiff = 0;
		Double montoOriginal;
		Double montoCalculado;
		double montoPorcentaje = 0;
		double porcentaje;
		
		Calendar calFechaPago = Calendar.getInstance();
		Calendar calFechaLimite = Calendar.getInstance();
		
		if(fechaPago!=null && 
				alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_ADEUDO){
			//Cuando es un administrador, puede ingresar la fecha de pago
			//Si el pago es anterior a la fecha límite, se obtiene el pago antes de que se le generen recargos
			
			//Se obtiene la beca del alumno
			alumnoBeca = alumnoBecaRepository.findByIdAlumnoAndDate(alumnoPago.getIdAlumno(),alumnoPago.getFechaLimite());
			if(alumnoBeca!=null){
				montoPorcentaje = alumnoPago.getPagoGrado().getCatPago().getMonto() - 
						(alumnoPago.getPagoGrado().getCatPago().getMonto() * alumnoBeca.getPorcentaje())/100;
			}else{
				montoPorcentaje = alumnoPago.getPagoGrado().getCatPago().getMonto();
			}
			
			
			calFechaPago.setTime(fechaPago);
			calFechaLimite.setTime(alumnoPago.getFechaLimite());
			calFechaLimite.set(Calendar.HOUR_OF_DAY, 23);
			calFechaLimite.set(Calendar.MINUTE, 59);
			calFechaLimite.set(Calendar.SECOND, 59);
			
			if(calFechaPago.before(calFechaLimite)){
				//alumnoPago.setMonto(alumnoPago.getPagoGrado().getCatPago().getMonto());
				alumnoPago.setMonto(montoPorcentaje);
			}
			//Si el pago es posterior a la fecha limite, se obtienen los meses de diferencia entre la fecha límite
			//y la fecha de pago. El resultado es el porcentaje del monto a pagar
			if(calFechaPago.after(calFechaLimite)){
				mesesDiff = MyUtil.calcularMesesAFecha(alumnoPago.getFechaLimite(), fechaPago);
				
				//Se agrega un mes a la diferencia de meses ya que si solo ha pasado 1 día de retardo no
				//se hará modificación alguna
				mesesDiff +=1;
				
				//////////////////////////////////
				//Calculo del % a sumar al pago
				/////////////////////////////////				
				//Monto original
				montoOriginal = montoPorcentaje;//alumnoPago.getPagoGrado().getCatPago().getMonto();
				//Porcentaje
				porcentaje = mesesDiff * 10;
				//Monto calculado
				montoCalculado = montoOriginal + ((montoOriginal*porcentaje)/100);
				//Nuevo monto
				alumnoPago.setMonto(montoCalculado);
			}
		}
		
		if(pago == null){
			pago = 0.0;
		}
		
		//Se obtiene el pago previo del alumno y el monto a pagar
		Double pagoOriginal = alumnoPago.getPago();
		Double monto = alumnoPago.getMonto();
		Integer idSemaforo = alumnoPago.getIdSemaforo();
		
		//Si se utiliza el saldo a favor del pago, se suman los valores de
		//pago y saldo para el pagoTotal.
		alumno = alumnoRepository.findOne(alumnoPago.getIdAlumno());
		if(checked){
			saldo = alumno.getSaldo();
			saldoBitacora = saldo;
			pagoTotal = pago + saldo;
		}else{
			pagoTotal = pago;
		}
		
		//Se suma el nuevo pago al pago original
		pagoOriginal = pagoOriginal + pagoTotal;
		
		//Si la suma de los pagos es mayor al monto a pagar, el residuo queda en el saldo
		//Se reduce el pago original al valor del monto
		if(pagoOriginal>=monto){
			saldo = pagoOriginal-monto;
			pagoOriginal = monto;
		}
		
		//Si el check es true y el pago original es menor a al monto, el saldo se va a 0
		if(checked && (pagoOriginal<monto)){
			saldo = 0.0;
		}

		alumnoPago.setPago(pagoOriginal);
		
		//Pago completo
		if(alumnoPago.getMonto()<=pagoOriginal){
			alumnoPago.setIdSemaforo(Constantes.SEMAFORO_PAGADO);
		}
		//Pago parcial
		else if(alumnoPago.getMonto()>pagoOriginal){
			//Si el pago es pendiente, el semaforo se pone en parcial
			if(idSemaforo==Constantes.SEMAFORO_PENDIENTE || idSemaforo==Constantes.SEMAFORO_ADEUDO){
				alumnoPago.setIdSemaforo(Constantes.SEMAFORO_PARCIAL);
			}
		}
		
		if(fechaPago==null){
			alumnoPago.setFechaPago(Calendar.getInstance().getTime());			
		}else{
			alumnoPago.setFechaPago(fechaPago);
		}
		alumnoPago = alumnoPagoRepository.save(alumnoPago);
		
		//Se actualiza el saldo
		if(checked){
			//Alumno alumno = alumnoRepository.findOne(alumnoPago.getIdAlumno());
			alumno.setSaldo(saldo);
			alumnoRepository.save(alumno);
		}
		
		///////////////////////////////////////////////////////////////////////////////////////////////////////////
		//Se almacena en bitácora el pago realizado, realiza por separado lo que se pagó con saldo y con efectivo
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
		AlumnoPagoBitacora bitacoraPago;
		AlumnoPagoBitacora bitacoraSaldo;
		
		if(pago != 0){
			bitacoraPago = new AlumnoPagoBitacora();
			bitacoraPago.setAlumnoPago(alumnoPago);
			bitacoraPago.setPago(pago);
			bitacoraPago.setFechaPago(alumnoPago.getFechaPago());
			bitacoraPago.setSaldo(false);
			alumnoPagoBitacoraRepository.save(bitacoraPago);
		}
		
		if(checked){
			bitacoraSaldo = new AlumnoPagoBitacora();
			bitacoraSaldo.setAlumnoPago(alumnoPago);
			bitacoraSaldo.setPago(saldoBitacora - saldo);
			bitacoraSaldo.setFechaPago(alumnoPago.getFechaPago());
			bitacoraSaldo.setSaldo(true);
			alumnoPagoBitacoraRepository.save(bitacoraSaldo);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		alumnoPagoForm.setId(alumnoPago.getId());
		alumnoPagoForm.setConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
				MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde())+ " " +
				alumnoPago.getPagoGrado().getAnio_corresponde());
		alumnoPagoForm.setFechaPago(alumnoPago.getFechaPago());
		alumnoPagoForm.setIdAlumno(alumnoPago.getIdAlumno());
		//alumnoPagoForm.setIdConcepto(//alumnoPago.*);
		alumnoPagoForm.setMonto(alumnoPago.getMonto());
		alumnoPagoForm.setPago(alumnoPago.getPago());
		alumnoPagoForm.setSaldo(alumno.getSaldo());
		
		if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PAGADO){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");}
		else if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PARCIAL){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");}
		else if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_ADEUDO){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");}
		else if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PENDIENTE){alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-info\">Pendiente</span>");}
		
		
		return alumnoPagoForm;

		return null;
	}

	@Override
	public List<AlumnoPagoForm> findByIdAlumno(Integer id) {
		return null;

		List<AlumnoPagoForm> alumnosPagoForm = new ArrayList<AlumnoPagoForm>();
		List<AlumnoPago> alumnosPago;
		AlumnoPagoForm alumnoPagoForm;
				
		alumnosPago = alumnoPagoRepository.findByIdAlumno(id);
		
		for(AlumnoPago alumno: alumnosPago){
			alumnoPagoForm = new AlumnoPagoForm();
			alumnoPagoForm.setFechaPago(alumno.getFechaPago());
			alumnoPagoForm.setId(alumno.getId());
			alumnoPagoForm.setMonto(alumno.getMonto());
			alumnoPagoForm.setPago(alumno.getPago());
			if(alumno.getIdSemaforo()==Constantes.SEMAFORO_PAGADO){
				alumnoPagoForm.setSemaforo("Pagado");
			}else if(alumno.getIdSemaforo()==Constantes.SEMAFORO_PARCIAL){
				alumnoPagoForm.setSemaforo("Parcial");
			}else if(alumno.getIdSemaforo()==Constantes.SEMAFORO_ADEUDO){
				alumnoPagoForm.setSemaforo("Adeudo");
			}
			
		}
		
		return alumnosPagoForm;
	}

	@Override
	public void delete(AlumnoPagoForm alumnoForm) { 
		// TODO Auto-generated method stub
		alumnoPagoRepository.delete(alumnoForm.getId());
	}

	@Override
	public void getList() {
		// TODO Auto-generated method stub
	}

	@Override
	public void updateMontoFechaExceed() {
		Calendar fechaActual = Calendar.getInstance();
		//Solo busca los pagos que hayan excedido la fecha límite y se encuentren en "pendiente" o "Adeudo
		//Pagos que tenga activo el campo "Genera Adeudo"
		List<AlumnoPago> lstAlumno = alumnoPagoRepository.findPagoLimitExceed2(Constantes.SEMAFORO_PENDIENTE,
				Constantes.SEMAFORO_ADEUDO,fechaActual.getTime());
		int mesesDiff;
		Double montoOriginal;
		Double montoCalculado;
		int porcentaje;
		Set<Integer> set = new HashSet<Integer>();
		Alumno alumno;
		
		//Se modifican los montos de cada pago
		for(AlumnoPago alumnoPago : lstAlumno){
			
			mesesDiff = MyUtil.calcularMesesAFecha(alumnoPago.getFechaLimite(), fechaActual.getTime());
			
			//Se agrega un mes a la diferencia de meses ya que si solo ha pasado 1 día de retardo no
			//se hará modificación alguna
			mesesDiff +=1;
			
			//////////////////////////////////
			//Calculo del % a sumar al pago
			/////////////////////////////////
			
			//Monto original
			montoOriginal = alumnoPago.getPagoGrado().getCatPago().getMonto();
			
			//Porcentaje
			porcentaje = mesesDiff * 10;
			
			//Monto calculado
			montoCalculado = montoOriginal + ((montoOriginal*porcentaje)/100);
			
			//Nuevo monto
			alumnoPago.setMonto(montoCalculado);
			alumnoPago.setIdSemaforo(Constantes.SEMAFORO_ADEUDO);
			
			//Se agrega el Id del alumno modificado a la lista para posteriormente modificar su estatus
			set.add(alumnoPago.getIdAlumno());
			
			//////////////////////////
			//Se actualiza el pago
			//////////////////////////
			alumnoPagoRepository.save(alumnoPago);
		}
		
		//Una vez modificados los pagos, se modifica el estatus de cada alumno
		for(Integer idAlumno : set){
			alumno = alumnoRepository.findOne(idAlumno);
			alumno.setIdSemaforo(Constantes.SEMAFORO_ADEUDO); //Adeudo
			alumnoRepository.save(alumno);
		}
		
	}

	@Override
	public void updateStatusByPago(Integer idAlumno) {
		Alumno alumno = alumnoRepository.findOne(idAlumno);
		long idSemaforo = alumno.getIdSemaforo();
		
		//Obtener la lista de los pagos del alumno
		List<AlumnoPago> lst = alumnoPagoRepository.findByIdAlumno(idAlumno);
		
		//Hacer un Set para descartar valores repetidos
		Set<Integer> set = new HashSet<Integer>();
		for(AlumnoPago alumnoPago : lst){
			set.add(alumnoPago.getIdSemaforo());
		}
		
		if(set.contains(Constantes.SEMAFORO_ADEUDO)){
			idSemaforo = Constantes.SEMAFORO_ADEUDO;
		}else if(set.contains(Constantes.SEMAFORO_PARCIAL)){
			idSemaforo = Constantes.SEMAFORO_PARCIAL;
		}else if(set.contains(Constantes.SEMAFORO_PAGADO)){
			idSemaforo = Constantes.SEMAFORO_PAGADO;
		}else {
			idSemaforo = Constantes.SEMAFORO_PENDIENTE;
		}
		
		//Se actualiza el semaforo del alumno
		if(idSemaforo != alumno.getIdSemaforo()){
			alumno.setIdSemaforo(idSemaforo);
			alumnoRepository.save(alumno);
		}
		
	}

	@Override
	public AlumnoPagoForm updateFechaLimite(Integer idPago, Date fechaLimite) {
		AlumnoPago alumnoPago = alumnoPagoRepository.findOne(idPago);
		AlumnoPagoForm alumnoPagoForm = new AlumnoPagoForm();
		
		alumnoPago.setFechaLimite(fechaLimite);
		alumnoPago.setMonto(alumnoPago.getPagoGrado().getCatPago().getMonto());
		alumnoPago.setIdSemaforo(Constantes.SEMAFORO_PENDIENTE); //Se pone el estatus como Pendiente
		
		alumnoPagoRepository.save(alumnoPago);
		//updateStatusByPago(alumnoPago.getIdAlumno());
		
		alumnoPagoForm.setIdAlumno(alumnoPago.getIdAlumno());
		alumnoPagoForm.setMonto(alumnoPago.getMonto());
		alumnoPagoForm.setSemaforo("<span class=\"label label-sm label-info\">Pendiente</span>");
		
		return alumnoPagoForm;

		return null;
	}

	@Override
	public List<AlumnoPagoForm> findByIdPagoGradoAndFechaLimite(Integer idPagoGrado, Date fechaLimite) {
		PagoGrado pagoGrado = new PagoGrado();
		AlumnoPagoForm alumnoPagoForm;
		List<AlumnoPagoForm> lstAlumnoPagoForm;
		pagoGrado.setId(idPagoGrado);
		
		List<AlumnoPago> lstAlumnoPago = alumnoPagoRepository.findByPagoGradoAndFechaLimite(pagoGrado, fechaLimite);
		
		lstAlumnoPagoForm = new ArrayList<AlumnoPagoForm>();
		
		for(AlumnoPago alumno: lstAlumnoPago){
			alumnoPagoForm = new AlumnoPagoForm();
			alumnoPagoForm.setFechaPago(alumno.getFechaPago());
			alumnoPagoForm.setId(alumno.getId());
			alumnoPagoForm.setMonto(alumno.getMonto());
			alumnoPagoForm.setPago(alumno.getPago());
			if(alumno.getIdSemaforo()==Constantes.SEMAFORO_PAGADO){
				alumnoPagoForm.setSemaforo("Pagado");
			}else if(alumno.getIdSemaforo()==Constantes.SEMAFORO_PARCIAL){
				alumnoPagoForm.setSemaforo("Parcial");
			}else if(alumno.getIdSemaforo()==Constantes.SEMAFORO_ADEUDO){
				alumnoPagoForm.setSemaforo("Adeudo");
			}
			lstAlumnoPagoForm.add(alumnoPagoForm);
		}
		
		return lstAlumnoPagoForm;
		return null;
	}

	//TODO Realizar control
	@Override
	public void deleteByPagoGradoId(Integer idPagoGrado) {
		//alumnoPagoRepository.deleteInBulkByPagoGradoId(idPagoGrado);
	}

	@Override
	public void delete(AlumnoPagoForm alumnoForm) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getList() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateMontoFechaExceed() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateStatusByPago(Integer idAlumno) {
		// TODO Auto-generated method stub
		
	}
	
	
*/	


}
