package com.marti.educacion.saem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.entities.PagoGrado;

public interface AlumnoPagoRepository extends JpaRepository<AlumnoPago, Integer>{

	List<AlumnoPago> findByIdAlumno(Integer idAlumno);
	
	/*
	select pg.id, ap.monto, ap.pago
	from sae.pago_grado pg
	join sae.alumno_pago ap on pg.id = ap.id_pago_grado
	where pg.mes_corresponde = 12 and pg.anio_corresponde = 2016;
	*/
	@Query("Select ap from AlumnoPago ap where ap.pagoGrado.mes_corresponde = ?1 and ap.pagoGrado.anio_corresponde = ?2")
	List<AlumnoPago> currentMonthPays(Integer mes, Integer anio);
	
	/*
	select pg.id, ap.monto, ap.pago
	from sae.pago_grado pg
	join sae.alumno_pago ap on pg.id = ap.id_pago_grado
	where pg.fecha_corresponde between 01-12-2016 and 31-01-2017;
	*/
	@Query("Select ap from AlumnoPago ap "
			+ "where ap.pagoGrado.fechaCorresponde between ?1 and ?2")
	List<AlumnoPago> betweenMonthPays(Date fechaInicio, Date fechaFin);
	
	/*
	Busca los pagos que han pasado su fecha limite y no son pagos únicos y generan adeudo
	select ap.*
	from alumno_pago ap
    join pago_grado pg on ap.id_pago_grado = pg.id
    join cat_pagos cp on pg.id_pago = cp.id
	where cp.genera_adeudo = 1
    and ap.id_semaforo = 3
	and ap.fecha_limite < sysdate()
	*/
	@Query("Select ap from AlumnoPago ap "
			+ "join ap.pagoGrado pg "
			+ "join pg.catPago cp "
			+ "where cp.generaAdeudo = 1 "
			+ "and ap.idSemaforo = ?1 and ap.fechaLimite < ?2")
	List<AlumnoPago> findPagoLimitExceed(Integer idSemaforo, Date today);
	
	@Query("Select ap from AlumnoPago ap "
			+ "join ap.pagoGrado pg "
			+ "join pg.catPago cp "
			+ "where cp.generaAdeudo = 1 "
			+ "and (ap.idSemaforo = ?1 or ap.idSemaforo = ?2) "
			+ "and ap.fechaLimite < ?3")
	List<AlumnoPago> findPagoLimitExceed2(Integer idSemaforo, Integer idSemaforo2, Date today);
	
	
	List<AlumnoPago> findByPagoGradoAndFechaLimite(PagoGrado pagoGrado, Date fechaLimite);
	
	@Query("Select ap from AlumnoPago ap "
			+ "where ap.idAlumno = ?1 and ap.pagoGrado.fechaCorresponde between ?2 and ?3")
	List<AlumnoPago> findByIdAlumnoAndBetweenMonthPays(Integer idAlumno, Date fechaInicio, Date fechaFin);
	
	//http://docs.spring.io/spring-data/jpa/docs/1.11.4.RELEASE/reference/html/#jpa.modifying-queries.derived-delete
	//In Bulk indica un objeto de la clase, seguido del nombre del objeto y ID del objeto
	@Modifying
	@Transactional
	@Query("delete from AlumnoPago ap where ap.idPagoGrado = ?1")
	void deleteInBulkByPagoGradoId(Integer idPagoGrado);
	
	
}
