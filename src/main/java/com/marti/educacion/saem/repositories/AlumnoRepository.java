package com.marti.educacion.saem.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import com.marti.educacion.saem.entities.Alumno;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {

	
}