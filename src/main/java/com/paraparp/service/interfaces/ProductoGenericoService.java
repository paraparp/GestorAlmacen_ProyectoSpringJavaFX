package com.paraparp.service.interfaces;

import java.util.List;
import java.util.Set;

import com.paraparp.model.entities.Productogenerico;

public interface ProductoGenericoService {
	
	public List<Productogenerico> findAll();
	public Productogenerico save(Productogenerico productoGenerico);
	public Productogenerico findById(Long id);
	public void delete(Long id);
	public Set<String> findMarcas();
	public Set<String> findCategorias();
	public List<Productogenerico> findByParam(String param);


}
