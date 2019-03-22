package com.codetreatise.service;

import java.util.List;

import com.codetreatise.modelo.entities.Proveedor;

public interface ProveedorService {
	
	public List<Proveedor> findAll();
	public Proveedor save(Proveedor proveedor);
	public Proveedor findById(Long id);

	public void delete(Long id);


}
