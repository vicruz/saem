package com.marti.educacion.saem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marti.educacion.saem.entities.Grado;

public interface GradoRepository extends JpaRepository<Grado, Integer> {


	@Query("Select gd From Grado gd WHERE gd.name = :name")
	public Grado findByName(@Param("name") String name);
	
}
