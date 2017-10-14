package com.marti.educacion.saem.services;

import java.util.List;

import com.marti.educacion.saem.dto.GradoForm;
import com.marti.educacion.saem.entities.Grado;

public interface GradoService {

	public List<Grado> findAll();
	public Grado findByName(String name);
	public GradoForm save(GradoForm gradoForm) throws Exception;
	public GradoForm findById(Integer gradoId);
	public void update(GradoForm gradoForm);
}
