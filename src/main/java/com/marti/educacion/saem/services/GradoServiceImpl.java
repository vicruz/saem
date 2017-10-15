package com.marti.educacion.saem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.GradoForm;
import com.marti.educacion.saem.entities.Grado;
import com.marti.educacion.saem.repositories.GradoRepository;

//@Service
//@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class GradoServiceImpl implements GradoService{
/*
	private GradoRepository gradoRepository;
	
	@Autowired
	public GradoServiceImpl(GradoRepository gradoRepository){
		this.gradoRepository = gradoRepository;
		
	}

	@Override
	public List<Grado> findAll() {
		//return gradoRepository.findAll();
		return null;
	}

	@Override
	public Grado findByName(String name) {
		//return gradoRepository.findByName(name);
		return null;
	}
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public GradoForm save(GradoForm gradoForm) throws Exception {
		Grado grado = new Grado();
		//grado.setId(gradoForm.getGradoId());
		grado.setName(gradoForm.getNombre());
		
		grado = gradoRepository.save(grado);
		
		gradoForm.setGradoId(grado.getId());
		return gradoForm;
	}

	@Override
	public GradoForm findById(Integer gradoId) {
		GradoForm form = null;
		
		Grado grado = gradoRepository.findOne(gradoId);
		
		if(grado!=null){
			form = new GradoForm();
			form.setGradoId(gradoId);
			form.setNombre(grado.getName());
		}
		return form;
	}

	@Override
	public void update(GradoForm gradoForm) {
		
		Grado grado = gradoRepository.findOne(gradoForm.getGradoId());
		grado.setName(gradoForm.getNombre());
		
		gradoRepository.save(grado);
	}
	*/
}
