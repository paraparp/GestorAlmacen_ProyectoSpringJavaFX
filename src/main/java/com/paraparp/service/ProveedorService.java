package com.paraparp.service;

import java.util.List;

import com.paraparp.modelo.entities.Proveedor;

public interface ProveedorService {
	
	public List<Proveedor> findAll();
	public Proveedor save(Proveedor proveedor);
	public Proveedor findById(Long id);

	public void delete(Long id);


}
