package com.marti.educacion.saem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marti.educacion.saem.entities.AlumnoBeca;
import com.marti.educacion.saem.entities.AlumnoPago;

public interface AlumnoBecaRepository extends JpaRepository<AlumnoBeca, Integer> {
	
	List<AlumnoBeca> findByIdAlumno(Integer idAlumno);
	
	/*
	select ap.id_alumno, ap.id_pago_grado, ap.monto, pg.mes_corresponde, pg.anio_corresponde
	from alumno_pago ap
	join pago_grado pg on ap.id_pago_grado = pg.id
	join cat_pagos cp on pg.id_pago = cp.id
	where ap.id_alumno = 1
	and cp.aplica_beca = 1
	and pg.fecha_corresponde between cast('2016-12-01' as date) and cast('2017-02-01' as date);
	*/
	
	@Query("Select ap from AlumnoPago ap "
			+ "join ap.pagoGrado pg "
			+ "join pg.catPago cp "
			+ "where ap.idAlumno = ?1 "
			+ "and cp.aplicaBeca = 1 "
			+ "and pg.fechaCorresponde between ?2 and ?3")
	List<AlumnoPago> findPagosAplicaBeca(Integer idAlumno, Date fechaInicio, Date fechafin);
	
	List<AlumnoBeca> findByIdAlumnoOrderByIdBecaDesc(Integer idAlumno);
	
	@Query("Select ab from AlumnoBeca ab where ab.idAlumno = ?1 and ab.fechaInicio <= CURRENT_DATE and ab.fechaFin >= CURRENT_DATE ")
	AlumnoBeca findByIdAlumnoAndCurrentDate(Integer idAlumno);
	
	@Query("Select ab from AlumnoBeca ab where ab.idAlumno = ?1 and ab.fechaInicio <= ?2 and ab.fechaFin >= ?2 ")
	AlumnoBeca findByIdAlumnoAndDate(Integer idAlumno, Date fecha);

}
