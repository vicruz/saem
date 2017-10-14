package com.marti.educacion.saem.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.marti.educacion.saem.entities.AlumnoPagoBitacora;
import com.marti.educacion.saem.entities.vo.AlumnoPagoBitacoraVO;

public interface AlumnoPagoBitacoraRepository extends JpaRepository<AlumnoPagoBitacora, Integer> {

	//Encontrar los pagos por alumno entre fechas
	@Query("Select apb from AlumnoPagoBitacora apb join apb.alumnoPago ap "
			+ "where ap.idAlumno = ?1 "
			+ "and apb.fechaPago between ?2 and ?3")
	public List<AlumnoPagoBitacora> findByAlumnoPago(int idAlumno, Date fechaInicial, Date fechaFinal);
	
	//Encuentra todos los pagos entre fechas
	public List<AlumnoPagoBitacora> findByFechaPagoBetween(Date fechaInicial, Date fechaFinal);
	
	//Encuentra los pagos por alumno pago
	@Query("Select apb from AlumnoPagoBitacora apb where apb.alumnoPago.id = ?1")
	public List<AlumnoPagoBitacora> findByAlumnoPagoId(Integer idAlumnoPago);
	
	//Encuentra los pagos por alumno pago
	@Query("Select new com.mx.visolutions.sae.entities.vo.AlumnoPagoBitacoraVO(apb.alumnoPago.id, sum(apb.pago), apb.fechaPago) "
			+ "from AlumnoPagoBitacora apb where apb.alumnoPago.id = ?1 group by apb.fechaPago")
	public List<AlumnoPagoBitacoraVO> findByAlumnoPagoIdSum(Integer idAlumnoPago);
}
