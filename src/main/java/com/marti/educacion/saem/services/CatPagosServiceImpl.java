package com.marti.educacion.saem.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.marti.educacion.saem.dto.CatPagosForm;
import com.marti.educacion.saem.entities.CatPagos;
import com.marti.educacion.saem.repositories.CatPagosRepository;

//@Service
//@Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
public class CatPagosServiceImpl implements CatPagosService {
/*
	private CatPagosRepository catPagosRepository;
	//private PagoGradoRepository pagoGradoRepository;
	
	@Autowired
	public CatPagosServiceImpl(CatPagosRepository catPagosRepository){//, PagoGradoRepository pagoGradoRepository){
		this.catPagosRepository = catPagosRepository;
		//this.pagoGradoRepository = pagoGradoRepository;
	}
	
	@Override
	public CatPagos findById(Integer id) {
		//return catPagosRepository.findOne(id);
		return null;
	}

	@Override
	public CatPagos findByPagoGradoId(Integer id) {
		//CatPagos catPagos = catPagosRepository.findById(id);
		//return catPagos;
		return null;
	}

	public List<CatPagos> findAll(){
		//return catPagosRepository.findAll();
		return null;
	}

	@Override
	public void addNuevoPago(CatPagosForm catPagosForm) {
		CatPagos pagos = new CatPagos();
		
		if(catPagosForm.getId()!=null){
			pagos =catPagosRepository.findOne(catPagosForm.getId()); 
		}
		
		pagos.setConcepto(catPagosForm.getConcepto());
		pagos.setMonto(catPagosForm.getMonto());
		pagos.setAplicaBeca(catPagosForm.getBeca()?1:0);
		pagos.setGeneraAdeudo(catPagosForm.getGeneraAdeudo()?1:0);
		pagos.setPagoUnico(catPagosForm.getPagoUnico()?1:0);
		
		catPagosRepository.save(pagos);

	}

	@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
	public int deleteCatPago(int pagoId) {
		// TODO Auto-generated method stub
		//return catPagosRepository.deleteById(pagoId);
		return 0;
	}
*/

}
