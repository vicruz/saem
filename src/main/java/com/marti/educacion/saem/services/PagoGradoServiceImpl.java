package com.marti.educacion.saem.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.PagoGradoRelForm;
import com.marti.educacion.saem.entities.CatPagos;
import com.marti.educacion.saem.entities.PagoGrado;
import com.marti.educacion.saem.repositories.PagoGradoRepository;

@Service
@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class PagoGradoServiceImpl implements PagoGradoService {
	
	private PagoGradoRepository pagoGradoRepository;
	
	
	@Autowired
	public PagoGradoServiceImpl(PagoGradoRepository pagoGradoRepository){
		this.pagoGradoRepository = pagoGradoRepository;
	}

	@Override
	public PagoGrado findOne(Integer idPagoGrado) {
		return pagoGradoRepository.findOne(idPagoGrado);
	}

	@Override
	public List<PagoGrado> getByIdGrado(Integer idGrado) {
		return  pagoGradoRepository.findByIdGrado(idGrado);
	}

	@Override
	public List<PagoGrado> findAll() {
		return pagoGradoRepository.findAll();
	}

	@Override
	public PagoGrado addNew(PagoGradoRelForm pagoGrado) throws Throwable {

		PagoGrado pago= new PagoGrado();
		CatPagos catpago = new CatPagos();
		catpago.setId(pagoGrado.getIdPago());
		Calendar cal = Calendar.getInstance();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		
		Date fechaLimite =formatter.parse(pagoGrado.getFechaLimite());
		int anio = Integer.valueOf(pagoGrado.getAnio());
		
		cal.set(Calendar.YEAR, anio);
		cal.set(Calendar.MONTH, pagoGrado.getMes()-1);
		cal.set(Calendar.DAY_OF_MONTH,1);
		
		pago.setIdGrado(pagoGrado.getIdGrado());
		pago.setFechaLimite(fechaLimite);
		pago.setAnio_corresponde(anio);
		pago.setMes_corresponde(pagoGrado.getMes());
		pago.setCatPago(catpago);
		pago.setFechaCorresponde(cal.getTime());

		return pagoGradoRepository.save(pago);
	}

	@Override
	public List<PagoGrado> findByIdGradoNotInAlumno(Integer idGrado, Integer idAlumno) {
		try{
			return pagoGradoRepository.findByIdGradoNotInAlumno(idGrado, idAlumno);
		}catch(NoResultException e){
			e.printStackTrace();
			return new ArrayList<PagoGrado>();
		}catch(Exception e){
			e.printStackTrace();
			return new ArrayList<PagoGrado>();
		}
	}

	@Override
	@Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
	public void save(PagoGrado pagoGrado) {
		pagoGradoRepository.save(pagoGrado);
	}

	@Override
	public void flush(){
		pagoGradoRepository.flush();
	}

	@Override
	public int findPagoMade(int idPagoGrado) {
		int pagosMade = 0;
		
		try{
			pagosMade = pagoGradoRepository.countPagosMade(idPagoGrado);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return pagosMade;
	}

	@Override
	public void delete(Integer idPagoGrado) {
		pagoGradoRepository.delete(idPagoGrado);
	}


}
