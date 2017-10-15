package com.marti.educacion.saem.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.AlumnoForm;
import com.marti.educacion.saem.entities.Alumno;
import com.marti.educacion.saem.entities.Grado;
import com.marti.educacion.saem.json.AlumnoJson;
import com.marti.educacion.saem.json.JSon;
import com.marti.educacion.saem.repositories.AlumnoRepository;
import com.marti.educacion.saem.util.Constantes;


//@Service
//@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class AlumnoServiceImpl implements AlumnoService {
/*	
	private AlumnoRepository alumnoRepository;
	
	@Autowired
	private AlumnoServiceImpl(AlumnoRepository alumnoRepository){
		this.alumnoRepository =alumnoRepository;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void save(AlumnoForm alumnoForm) throws Exception {
		Alumno alumno = new Alumno();
		Grado grado = new Grado();
		grado.setId(alumnoForm.getGradoId());
		alumno.setApMaterno(alumnoForm.getApMaterno());
		alumno.setApPaterno(alumnoForm.getApPaterno());
		alumno.setNombre(alumnoForm.getNombre());
		alumno.setGrado(grado);
		alumno.setIdSemaforo(4);
		if(alumnoForm.getBecaId()!=null && alumnoForm.getBecaId())
			alumno.setBeca(1);
		else
			alumno.setBeca(0);
		alumnoRepository.save(alumno);
	}

	@Override
	public void update(AlumnoForm alumnoForm) {
		Alumno alumno = alumnoRepository.findOne(alumnoForm.getId());
		Grado grado = new Grado();
		
		alumno.setApMaterno(alumnoForm.getApMaterno());
		alumno.setApPaterno(alumnoForm.getApPaterno());
		alumno.setNombre(alumnoForm.getNombre());
		grado.setId(alumnoForm.getGradoId());
		alumno.setGrado(grado);
		
		alumnoRepository.save(alumno);
	}

	@Override
	public Alumno findById(Integer id) {
		return alumnoRepository.findOne(id);
	}

	@Override
	public void delete(AlumnoForm alumnoForm) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Obtiene una lista de alumnos. Esta lista se muestra en la lista de alumnos
	 */
/*	@Override
	public JSon getList() {
		JSon value = new JSon();
		//logger.debug("Alumnos GET request");
		List<AlumnoJson> lstJson = new ArrayList<AlumnoJson>();
		
		List<Alumno> lstAlumno = alumnoRepository.findAll();
		
		if(lstAlumno!=null){
			for(Alumno alumno : lstAlumno){
				AlumnoJson json = new AlumnoJson();
				json.setApMaterno(alumno.getApMaterno());
				json.setApPaterno(alumno.getApPaterno());
				json.setId(alumno.getId());
				json.setNombre(alumno.getNombre());
				json.setGrado(alumno.getGrado().getName());
				
				//TODO Pendiente asignar los pagos de cada alumno
				if(alumno.getIdSemaforo()==0){
					switch(alumno.getGrado().getId()){
					case 1:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 2:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 3:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					case 4:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 5:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 6:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					case 7:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 8:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 9:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					default:
						json.setGrado("Baja");
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					}
				}else{
					switch(Long.valueOf(alumno.getIdSemaforo()).intValue()){
					case 1:
						json.setSemaforo("<span class=\"label label-sm label-success\">Pagado</span>");
						break;
					case 2:
						json.setSemaforo("<span class=\"label label-sm label-warning\">Parcial</span>");
						break;
					case 3:
						json.setSemaforo("<span class=\"label label-sm label-danger\">Adeudo</span>");
						break;
					default:
						json.setSemaforo("<span class=\"label label-sm label-info\">Pendiente</span>");
						break;
					}
				}
				
				json.setUrl("/alumnos/"+alumno.getId()+"/pagos");
				json.setUrlEditar("/alumnos/"+alumno.getId()+"/editar");
				
				if(alumno.getActivo()==1){
					json.setActivo("<span class=\"label label-sm label-success\">Activo</span>");
				}else{
					json.setActivo("<span class=\"label label-sm label-danger\">Inactivo</span>");
				}
				
				lstJson.add(json);
			}
		}
		
		value.setData(lstJson);
		return value;
		
	}

	@Override
	public List<Alumno> findAll() {
		
		return alumnoRepository.findAll();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public List<Alumno> findByGrado(Integer idGrado) {
		Grado grado = new Grado();
		grado.setId(idGrado);
		return alumnoRepository.findByGrado(grado);
	}

	/**
	 * Pone el estatus de los alumnos en INACTIVO
	 */
/*	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void deactivateAllAlumnos() {
//		alumnoRepository.updateActivo(Constantes.ESTATUS_INACTIVO);
	}
	
	/**
	 * Pone el estatus de los alumnos en ACTIVO
	 */
/*	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void activateAllAlumnos() {
//		alumnoRepository.updateActivo(Constantes.ESTATUS_ACTIVO);
	}
	
	/**
	 * Establece el estatus de un alumno en particular por el indicado
	 */
/*	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public void changeEstatusByAlumno(int activo, int alumnoId) {
//		alumnoRepository.changeActivoByAlumno(activo, alumnoId);
	}

	@Override
	public List<Alumno> findByGradoAndActivo(Integer idGrado) {
		Grado grado = new Grado();
		grado.setId(idGrado);
		return alumnoRepository.findByGradoAndActivo(grado,Constantes.ESTATUS_ACTIVO);
	}
*/
}
