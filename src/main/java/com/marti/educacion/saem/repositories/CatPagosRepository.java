package com.marti.educacion.saem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.marti.educacion.saem.entities.CatPagos;

public interface CatPagosRepository extends JpaRepository<CatPagos, Integer>{

	@Modifying
	@Query("Delete From CatPagos pa Where pa.id = :pagoId")
	int deleteById(@Param("pagoId")int pagoId);

	@Query("Select pg From CatPagos pg Where pg.id = :id")
	CatPagos findById(@Param("id") Integer id);
}
