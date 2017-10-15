package com.marti.educacion.saem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.entities.AlumnoPago;
import com.marti.educacion.saem.entities.PagoGrado;

public interface AlumnoPagoRepository{/* extends JpaRepository<AlumnoPago, Integer>{

	List<AlumnoPago> findByIdAlumno(Integer idAlumno);
	
	@Query("Select ap from AlumnoPago ap where ap.pagoGrado.mes_corresponde = ?1 and ap.pagoGrado.anio_corresponde = ?2")
	List<AlumnoPago> currentMonthPays(Integer mes, Integer anio);
	
	@Query("Select ap from AlumnoPago ap "
			+ "where ap.pagoGrado.fechaCorresponde between ?1 and ?2")
	List<AlumnoPago> betweenMonthPays(Date fechaInicio, Date fechaFin);
	
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
	*/
	
}
