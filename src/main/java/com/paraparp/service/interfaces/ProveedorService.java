package com.paraparp.service.interfaces;

import java.util.List;

import com.paraparp.model.entities.Proveedor;

public interface ProveedorService {
	
	public List<Proveedor> findAll();
	public Proveedor save(Proveedor proveedor);
	public Proveedor findById(Long id);

	public void delete(Long id);


}
