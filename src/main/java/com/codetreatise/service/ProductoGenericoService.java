package com.codetreatise.service;

import java.util.List;
import java.util.Set;

import com.codetreatise.modelo.entities.Productogenerico;

public interface ProductoGenericoService {
	
	public List<Productogenerico> findAll();
	public Productogenerico save(Productogenerico productoGenerico);
	public Productogenerico findById(Long id);
	public void delete(Long id);
	public Set<String> findMarcas();
	public Set<String> findCategorias();
	public List<Productogenerico> findByParam(String param);


}
