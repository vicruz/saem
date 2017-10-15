package com.marti.educacion.saem.repositories;

public interface PagoGradoRepository {//extends JpaRepository<PagoGrado, Integer>{
/*
	List<PagoGrado> findByIdGrado(Integer idGrado);
	
	@Query("Select pg From  PagoGrado pg")
	List<PagoGrado> findAll();
	
	@Query("Select pg from PagoGrado pg where pg.idGrado = ?1 "
			+ "and pg.id not in (Select ap.pagoGrado.id from AlumnoPago ap where ap.idAlumno = ?2)")
	List<PagoGrado> findByIdGradoNotInAlumno(Integer idGrado, Integer idAlumno);
	
	@Query("select count(ap.id) from AlumnoPago ap join ap.pagoGrado pg "
			+ "where pg.id = ?1 and ap.pago > 0")
	int countPagosMade(int idPago);*/
}
