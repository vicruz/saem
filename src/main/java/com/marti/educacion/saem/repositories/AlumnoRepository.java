package com.marti.educacion.saem.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.entities.Alumno;
import com.marti.educacion.saem.entities.Grado;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	List<Alumno> findByGrado(Grado grado);
	
	@Modifying
	@Transactional
	@Query("update Alumno set activo = ?1")
	void updateActivo(int activo);
	
	@Modifying
	@Transactional
	@Query("update Alumno set activo = ?1 where id = ?2")
	void changeActivoByAlumno(int activo, int alumunoId);
	
	//@Query("Select al from Alumno al join al.grado gr where gr.id = ?1 and al.activo = ?2")
	List<Alumno> findByGradoAndActivo(Grado grado, int activo);
}