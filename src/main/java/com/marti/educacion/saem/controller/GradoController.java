package com.marti.educacion.saem.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.marti.educacion.saem.dto.AlumnoPagoForm;
import com.marti.educacion.saem.dto.GradoForm;
import com.marti.educacion.saem.dto.GradoPagoForm;
import com.marti.educacion.saem.dto.PagoGradoRelForm;
import com.marti.educacion.saem.entities.Alumno;
import com.marti.educacion.saem.entities.CatPagos;
import com.marti.educacion.saem.entities.PagoGrado;
import com.marti.educacion.saem.services.AlumnoPagoService;
import com.marti.educacion.saem.services.AlumnoService;
import com.marti.educacion.saem.services.CatPagosService;
import com.marti.educacion.saem.services.GradoService;
import com.marti.educacion.saem.services.PagoGradoService;
import com.marti.educacion.saem.util.Constantes;
import com.marti.educacion.saem.util.MyUtil;

//@Controller
public class GradoController {
	
	private static final Logger logger = LoggerFactory.getLogger(GradoController.class);

	private GradoService gradoService;
	private CatPagosService catPagosService;
	private AlumnoService alumnoService;
	private PagoGradoService pagoGradoService;
	private AlumnoPagoService alumnoPagoService;
	
	public GradoController(GradoService gradoService, CatPagosService catPagosService,
			AlumnoService alumnoService, PagoGradoService pagoGradoService,
			AlumnoPagoService alumnoPagoService){
		this.gradoService = gradoService;
		this.catPagosService = catPagosService;
		this.alumnoService = alumnoService;
		this.pagoGradoService = pagoGradoService;
		this.alumnoPagoService = alumnoPagoService;
	}
	
	////@RequestMapping(value="/grados", method = RequestMethod.GET)
	public String grados(Model model) {
		logger.debug("GradoController - GET");
		
		return "grado";
	}
	
	//@RequestMapping(value="/grado", method = RequestMethod.POST)
	public String grados(@ModelAttribute("gradoForm") @Valid GradoForm gradoForm,
			BindingResult result, RedirectAttributes redirectAttributes){
		logger.debug("GradoController - POST");
		if(result.hasErrors())
			return "grado";
		
		try {
//			gradoService.save(gradoForm);
			MyUtil.flash(redirectAttributes, "success", "gradoSuccess");
		} catch (Exception e) {
			MyUtil.flash(redirectAttributes, "danger", "gradoNoSuccess", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/grado";
	}
	
	//@RequestMapping(value="/grados/{gradoId}/editar", method=RequestMethod.GET)
	public String editar(@PathVariable("gradoId") Integer gradoId, Model model){
		logger.debug("Editar grado: " + gradoId );
		
//		GradoForm gradoForm = gradoService.findById(gradoId);
		Map<String,String> map2 = new HashMap<String,String>();
		List<CatPagos> catPagos;
		
//		catPagos= catPagosService.findAll();
		/*
		for(CatPagos cat: catPagos){
			map2.put(""+cat.getId(), cat.getConcepto() + " - $" + cat.getMonto());
		}
		*/
		model.addAttribute("conceptos", map2);
//		model.addAttribute(gradoForm);
		model.addAttribute(new GradoPagoForm());	
		return "gradoEdit";
	}
	
	//@RequestMapping(value="/grados/{gradoId}/editar", method=RequestMethod.POST)
	public String editarPost(@PathVariable("gradoId") Integer gradoId, 
			@ModelAttribute("gradoForm") @Valid GradoForm gradoForm,
			BindingResult result, RedirectAttributes redirectAttributes, Model model){

		if(result.hasErrors())
			return "grados";

		gradoForm.setGradoId(gradoId);
	
		logger.debug("Update gradoForm: " + gradoForm.toString());
		try {
//			gradoService.update(gradoForm);
			MyUtil.flash(redirectAttributes, "success", "gradoUpdateSuccess");
		} catch (Exception e) {
			//TODO Cambiar el label
			MyUtil.flash(redirectAttributes, "danger", "gradoUpdateNoSuccess", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/grados/"+gradoId+"/editar";
	}
	
	/**
	 * Almacena los pagos correspondientes a un periodo de fechas a un grupo
	 * @param gradoId
	 * @param gradoPagoForm
	 * @param result
	 * @param redirectAttributes
	 * @param model
	 * @return
	 */
	//@RequestMapping(value="/grados/{gradoId}/editar/pago", method=RequestMethod.POST)
	public String editarPagoPost(@PathVariable("gradoId") Integer gradoId, 
			@ModelAttribute("gradoPagoForm") @Valid GradoPagoForm gradoPagoForm,
			BindingResult result, RedirectAttributes redirectAttributes, Model model){

		if(result.hasErrors())
			return "grados";

		gradoPagoForm.setGrupoId(gradoId);
	
		logger.debug("Update pagos a grado: " + gradoPagoForm.toString());
		
		try {
			Calendar fechaInicial = Calendar.getInstance();
			Calendar fechaFinal = Calendar.getInstance();
			Calendar fechaTemp;
			List<Calendar> fechaLst;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
			PagoGradoRelForm pagoGradoRelForm;
			
			int errores = 0;
			String mesError = "";
			
			//Buscar los alumnos que pertenecen al grado y se encuentran activos
			//List<Alumno> alumnoLst = alumnoService.findByGrado(gradoId);
			List<Alumno> alumnoLst = null;//alumnoService.findByGradoAndActivo(gradoId);
			
			//Crear una lista de los meses a crear
			fechaInicial.setTime(sdf.parse(gradoPagoForm.getFechaInicio()));
			fechaFinal.setTime(sdf.parse(gradoPagoForm.getFechaFin()));
			
			fechaLst = new ArrayList<Calendar>();
			while(fechaInicial.before(fechaFinal)){
				fechaTemp = Calendar.getInstance();
				fechaTemp.setTime(fechaInicial.getTime());
				fechaLst.add(fechaTemp);
				fechaInicial.add(Calendar.MONTH, 1);
			}
			fechaLst.add(fechaFinal);
			
			//Almacena los pagos por cada mes y posteriormente en cada alumno
			for(Calendar fecha : fechaLst){
				pagoGradoRelForm = new PagoGradoRelForm();
				pagoGradoRelForm.setAnio(""+fecha.get(Calendar.YEAR));
				pagoGradoRelForm.setMes(fecha.get(Calendar.MONTH)+1);				
				pagoGradoRelForm.setIdGrado(gradoId);
				pagoGradoRelForm.setIdPago(gradoPagoForm.getPagoId());
				
				//Definir la fecha limite
				fecha.set(Calendar.DAY_OF_MONTH, gradoPagoForm.getLimite());
				//fecha.add(Calendar.MONTH, 1); //Correo 25/05/2017 punto 3
				
				//Si la fecha limite de pago es fin de semana, se agrega 1 o 2 días
				//si es domingo o sabado respectivamente
				if(fecha.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY){
					fecha.add(Calendar.DAY_OF_MONTH, 1);
				}else if(fecha.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY){
					fecha.add(Calendar.DAY_OF_MONTH, 2);
				}
				
				pagoGradoRelForm.setFechaLimite(sdf2.format(fecha.getTime()));
				
				try {
					//Se agrega el pago a la relación de pagos por grado
//					PagoGrado pagoGrado = pagoGradoService.addNew(pagoGradoRelForm);
//					pagoGradoService.flush();
					
					//Se obtiene el monto de pago del concepto
//					CatPagos catPago = catPagosService.findById(pagoGradoRelForm.getIdPago());
					/*
					//SI el pago es tipo 'UNICO', no se agrega a los alumnos
					if(catPago.getPagoUnico()==Constantes.ESTATUS_INACTIVO){						
						//Se realiza un ciclo para agregar el pago a cada alumno
						for(Alumno alumno: alumnoLst){
							AlumnoPagoForm alumnoForm = new AlumnoPagoForm();
							
							alumnoForm.setIdPagoGrado(pagoGrado.getId());
							alumnoForm.setIdAlumno(alumno.getId());
							alumnoForm.setMonto(catPago.getMonto());
							alumnoForm.setFechaPago(null);
							alumnoForm.setPago(0.0);
							alumnoForm.setFechaLimite(pagoGradoRelForm.getFechaLimite());
							alumnoForm.setAplicaBeca(catPago.getAplicaBeca());
							alumnoPagoService.save(alumnoForm);
						}						
					}
					*/
					
					
				} catch (Throwable e) {
					// TODO Ver como manejar el avisar cuando no se guarda un pago
					//e.printStackTrace();
					logger.error(pagoGradoRelForm.toString()+": " + e.getMessage());
					if(e.getMessage().contains("ConstraintViolationException")){
						if(mesError.isEmpty()){
							mesError = pagoGradoRelForm.getMes()+"/"+pagoGradoRelForm.getAnio();
						}else{
							mesError = ", "+pagoGradoRelForm.getMes()+"/"+pagoGradoRelForm.getAnio();
						}
					}
					errores++;
				}
				
			}
			
			if(errores==0){
				MyUtil.flash(redirectAttributes, "success", "gradoPagoUpdateSuccess");
			}else if(errores == fechaLst.size()){
				MyUtil.flash(redirectAttributes, "danger", "gradoPagoUpdateNoSuccess", "Pagos dados de alta");
			}else{
				MyUtil.flash(redirectAttributes, "warning", "gradoPagoUpdateNoSuccess", mesError);
			}
		} catch (Exception e) {
			//TODO Cambiar el label
			MyUtil.flash(redirectAttributes, "danger", "gradoPagoUpdateNoSuccess", e.getMessage());
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return "redirect:/grados/"+gradoId+"/editar";
	}
	
}
