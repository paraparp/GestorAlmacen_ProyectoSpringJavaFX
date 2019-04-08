package com.paraparp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.model.dao.ProveedorDao;
import com.paraparp.model.entities.Proveedor;
import com.paraparp.service.interfaces.ProveedorService;

@Service
public class ProveedorServiceImp implements ProveedorService{

	
	@Autowired
	private ProveedorDao proveedorDao;
	
	@Override
	@Transactional
	public List<Proveedor> findAll() {
	
		return (List<Proveedor>) proveedorDao.findAll();
	}

	@Override
	@Transactional
	public Proveedor save(Proveedor proveedor) {
		 return proveedorDao.save(proveedor);
		
	}

	@Override
	@Transactional
	public Proveedor findById(Long id) {
	
		return proveedorDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		proveedorDao.delete(id);
		
	}





}
