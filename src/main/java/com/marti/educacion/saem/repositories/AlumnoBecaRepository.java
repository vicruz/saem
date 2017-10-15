package com.marti.educacion.saem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marti.educacion.saem.entities.AlumnoBeca;
import com.marti.educacion.saem.entities.AlumnoPago;

public interface AlumnoBecaRepository{/* extends JpaRepository<AlumnoBeca, Integer> {
	
	List<AlumnoBeca> findByIdAlumno(Integer idAlumno);
	
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
*/
}
