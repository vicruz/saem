package com.marti.educacion.saem.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Produces;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.marti.educacion.saem.dto.AlumnoPagoForm;
import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.entities.PagoGrado;
import com.marti.educacion.saem.json.AlumnoPagoJson;
import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.json.PagoGradoJson;
import com.marti.educacion.saem.repositories.AlumnoPagoRepository;
import com.marti.educacion.saem.services.AlumnoPagoService;
import com.marti.educacion.saem.services.PagoGradoService;
import com.marti.educacion.saem.util.Constantes;
import com.marti.educacion.saem.util.MyUtil;


//@RestController
public class PagosRestController {

	private static final Logger logger = LoggerFactory.getLogger(PagosRestController.class);
	
	private AlumnoPagoRepository alumnoPagoRepository;
	private AlumnoPagoService alumnoPagoService;
	private PagoGradoService pagoGradoService;
	
//	@Autowired
	public PagosRestController(AlumnoPagoRepository alumnoPagoRepository,
			PagoGradoService pagoGradoService, AlumnoPagoService alumnoPagoService){
		this.alumnoPagoRepository = alumnoPagoRepository;
		this.pagoGradoService = pagoGradoService;
		this.alumnoPagoService = alumnoPagoService;
	}
	
	//@RequestMapping(value="/pagosRest/{id}", method = RequestMethod.POST)
	public JSon findPagosXAlumno(@PathVariable("id") Integer id){
		JSon value = new JSon();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		logger.info("Buscando los pagos del alumno: " + id);
		/*
		List<AlumnoPago> lstAlumnosPago = alumnoPagoRepository.findByIdAlumno(id);
		List<AlumnoPagoJson> lstJson = new ArrayList<AlumnoPagoJson>();
		if(lstAlumnosPago != null){
			for(AlumnoPago alumnoPago : lstAlumnosPago){
				AlumnoPagoJson json = new AlumnoPagoJson();
				json.setId(alumnoPago.getId());
				json.setIdAlumno(id);
				json.setConcepto(alumnoPago.getPagoGrado().getCatPago().getConcepto() + " " +
						MyUtil.getMonth(alumnoPago.getPagoGrado().getMes_corresponde())+ " " +
						alumnoPago.getPagoGrado().getAnio_corresponde());
				if(alumnoPago.getFechaPago()!=null){
					json.setFecha(sdf.format(alumnoPago.getFechaPago()));					
				}
				json.setMonto(alumnoPago.getMonto());
				json.setPago(alumnoPago.getPago());
				
				//TODO debe obtener el valor del catalogo de semaforos
				if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PAGADO){json.setEstatus("<span class=\"label label-sm label-success\">Pagado</span>");}
				else if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PARCIAL){json.setEstatus("<span class=\"label label-sm label-warning\">Parcial</span>");}
				else if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_ADEUDO){json.setEstatus("<span class=\"label label-sm label-danger\">Adeudo</span>");}
				else if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PENDIENTE){json.setEstatus("<span class=\"label label-sm label-info\">Pendiente</span>");}
				
				if(alumnoPago.getIdSemaforo()==Constantes.SEMAFORO_PAGADO){json.setEditar("<button type=\"button\" class=\"btn-table disabled-btn-table\">Pagar</button>"); }
				else{ json.setEditar("<button type=\"button\" class=\"btn-table\" >Pagar</button>"); }
				
				if(alumnoPago.getFechaLimite()!=null){
					json.setFechaLimite(sdf.format(alumnoPago.getFechaLimite()));					
				}
				
				lstJson.add(json);
			}
		}
		*/
		//value.setData(lstJson);
		return value;
	}
	
	
	/**
	 * Obtiene los pagos para el grado del alumno
	 * @param idGrado Grado del alumno
	 * @return JSON con mapa de id - conepto
	 */
	//@RequestMapping(value="/pagosRest/getCatalogo/{idGrado}", method = RequestMethod.GET)
	public List<PagoGradoJson> getCatalogoPago(@PathVariable("idGrado") Integer idGrado){
		
		logger.info("Buscando los conceptos de pago por alumno: " + idGrado);
		
//		List<PagoGrado> lstPagoGrados = pagoGradoService.getByIdGrado(idGrado);
		List<PagoGradoJson> lst = new ArrayList<PagoGradoJson>();
		PagoGradoJson json = new PagoGradoJson();

		//Seleccionar
		json.setIdPagoGrado(0);
		json.setConcepto("Seleccionar");
		json.setMonto(0.0);
		
		lst.add(json);
		
		/*if(lstPagoGrados != null){
			for(PagoGrado pagoGrado : lstPagoGrados){
				json = new PagoGradoJson();
				json.setIdPagoGrado(pagoGrado.getId());
				json.setMonto(pagoGrado.getCatPago().getMonto());
				json.setConcepto(pagoGrado.getCatPago().getConcepto()
						+" " + MyUtil.getMonth(pagoGrado.getMes_corresponde()) 
						+" " + pagoGrado.getAnio_corresponde());
				lst.add(json);
			}
		}*/
		
		return lst;
	}
	
	
	/**
	 * Actualiza el monto de los pagos por alumno
	 * @param id
	 * @param pago
	 * @param userId
	 * @return
	 */
	//@RequestMapping(value="/pagosRest/update/{id}", method = RequestMethod.POST)
	public AlumnoPagoJson updatePago(@PathVariable("id") Integer id, Double pago, Integer userId, Boolean checked, String fechaPago){
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date fechaPagoDate = null;
		
		//Esta fecha se recibe cuando es un administrador el que inserta el pago y lo inserta en una fecha posterior a la requerida
		//puede no recibir la fecha, en ese caso se usará la fecha del sistema
		if(fechaPago!=null && !fechaPago.equals("")){
			try {
				fechaPagoDate = sdf.parse(fechaPago);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		
		logger.info("Sumando el pago: " + pago + " al pago: " + id);
		if(checked==null){
			checked = false;
		}

		AlumnoPagoForm alumno = null;//alumnoPagoService.updatePago(id, pago, userId, checked, fechaPagoDate);
		
		//Se actualiza el estatus del pago
//		alumnoPagoService.updateStatusByPago(alumno.getIdAlumno());
		
		
		AlumnoPagoJson json = new AlumnoPagoJson();
		json.setId(alumno.getId());
		json.setIdAlumno(alumno.getIdAlumno());
		json.setConcepto(alumno.getConcepto());
		if(alumno.getFechaPago()!=null){
			json.setFecha(sdf.format(alumno.getFechaPago()));					
		}
		json.setMonto(alumno.getMonto());
		json.setPago(alumno.getPago());
		json.setEstatus(alumno.getSemaforo());
		json.setSaldo(alumno.getSaldo());
		
		if(alumno.getSemaforo().contains("Pagado")){json.setEditar("<button type=\"button\" class=\"btn-table disabled-btn-table\">Pagar</button>"); }
		else{ json.setEditar("<button type=\"button\" class=\"btn-table\" >Pagar</button>"); }
		
		return json;
	}
	
	/**
	 * Actualiza la fecha limite de pago por alumno
	 * @param id
	 * @param pago
	 * @param userId
	 * @return
	 * @throws ParseException 
	 */
	//@RequestMapping(value="/pagosRest/fechaLimite/{id}", method = RequestMethod.POST)
	//public AlumnoPagoJson updatePago(@PathVariable("id") Integer id, Double pago, Integer userId, Boolean checked, Double saldo){
	public AlumnoPagoJson updateFechaLimite(@PathVariable("id") Integer id, String fechaLimite) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		logger.info("Actualizar la fecha limite del pago id: " + id + " con la fecha: " + fechaLimite);
		
		AlumnoPagoForm alumno = null;//alumnoPagoService.updateFechaLimite(id, sdf.parse(fechaLimite));
		
		//Se actualiza el estatus del pago
		//alumnoPagoService.updateStatusByPago(alumno.getIdAlumno());
		
		AlumnoPagoJson json = new AlumnoPagoJson();
		json.setMonto(alumno.getMonto());
		json.setEstatus(alumno.getSemaforo());
		
		return json;
	}

	/**
	 * Obtiene los pagos para el grado del alumno
	 * @param idGrado Grado del alumno
	 * @return JSON con mapa de id - conepto
	 */
	//@RequestMapping(value="/pagosRest/grado/{idGrado}", method = RequestMethod.GET)
	public JSon getGradoPago(@PathVariable("idGrado") Integer idGrado){
		
		logger.info("Buscando los conceptos de pago para el grado: " + idGrado);
		
		List<PagoGrado> lstPagoGrados = null;//pagoGradoService.getByIdGrado(idGrado);
		List<PagoGradoJson> lst = new ArrayList<PagoGradoJson>();
		PagoGradoJson json = new PagoGradoJson();
		JSon myJson = new JSon();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		/*if(lstPagoGrados != null){
			for(PagoGrado pagoGrado : lstPagoGrados){
				json = new PagoGradoJson();
				json.setIdPagoGrado(pagoGrado.getId());
				
				json.setPagoId(pagoGrado.getCatPago().getId());
				json.setMonto(pagoGrado.getCatPago().getMonto());
				json.setMes(MyUtil.getMonth(pagoGrado.getMes_corresponde()));
				json.setAnio(pagoGrado.getAnio_corresponde());
				json.setConcepto(pagoGrado.getCatPago().getConcepto());
				json.setFechaLimite(sdf.format(pagoGrado.getFechaLimite()));
				json.setUrlBorrar("");
				lst.add(json);
			}
		}*/
		
		myJson.setData(lst);
		return myJson;
	}
	
	/**
	 * Actualiza la fecha limite de pago por grado y de los alumnos asociados al pago
	 * @param idPagoGrado
	 * @param fechaLimite
	 * @return
	 * @throws ParseException 
	 */
	//@RequestMapping(value="/pagosRest/grado/fechaLimite/{idPagoGrado}", method = RequestMethod.POST)
	public void updateGradoFechaLimite(@PathVariable("idPagoGrado") Integer idPagoGrado, String fechaLimite) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		AlumnoPagoForm alumno;
		Date fechaLimiteOld;
		
		logger.info("Actualizar la fecha limite del pagoGrado con id: " + idPagoGrado + " a la fecha: " + fechaLimite);
		
		//Se obtiene el objeto de PagoGrado al cual se le va a actualizar la fecha limite de pago
//		PagoGrado pagoGrado = pagoGradoService.findOne(idPagoGrado);
//		fechaLimiteOld = pagoGrado.getFechaLimite();
//		pagoGrado.setFechaLimite(sdf.parse(fechaLimite));
//		pagoGradoService.save(pagoGrado);
		
		//Buscar todos los alumnos que tengan este pagoGrado y esta fecha 
		//(solo actualiza los que no han modificado fecha limite)
//		List<AlumnoPagoForm> lstAlumnos = alumnoPagoService.findByIdPagoGradoAndFechaLimite(idPagoGrado, fechaLimiteOld);
		
		//Actualizar cada uno de los alumnos
		/*for(AlumnoPagoForm form: lstAlumnos){
			alumno = alumnoPagoService.updateFechaLimite(form.getId(), sdf.parse(fechaLimite));
			
			//Se actualiza el estatus del pago
			alumnoPagoService.updateStatusByPago(alumno.getIdAlumno());
		}*/
		
	}
	
	/**
	 * Elimina el pago si los alumnos no han realizado ningun pago de este ID
	 * @param idPagoGrado
	 * @return 'ok' cuando se eliminaron los pagos mensaje de error cuando no se eliminan
	 * @throws ParseException
	 */
	//@Produces("application/json")
	//@RequestMapping(value="/pagosRest/borrar/pago/grado/{idPagoGrado}", method = RequestMethod.POST)
	public String deletePagoGrado(@PathVariable("idPagoGrado") Integer idPagoGrado) throws ParseException{
		//TODO agregar control de excepciones
		int pagosMade = 0;
		
		logger.info("Elimina el pagoGrado con id: " + idPagoGrado);
		
		//Valida si no se han hecho pagos del id que se desea eliminar
//		pagosMade = pagoGradoService.findPagoMade(idPagoGrado);
		
		if(pagosMade != 0){
			return "Ya se han realizado pagos de este concepto";
		}
		
		//Se eliminan los registros de los alumnos asociados al pago
		//delete from alumnoPago where idPago = x
//		alumnoPagoService.deleteByPagoGradoId(idPagoGrado);
		
		//Eliminar el pago
//		pagoGradoService.delete(idPagoGrado);
		
		return "ok";
	}
	
}
