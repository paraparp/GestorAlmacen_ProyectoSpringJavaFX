package com.codetreatise.service;

import java.util.List;
import java.util.Set;

import com.codetreatise.modelo.entities.Articulo;

public interface ArticuloService {
	
	public List<Articulo> findAll();
	public Articulo save(Articulo articulo);
	public Articulo findById(Long id);
	public void delete(Long id);
	public Set<String> findTallas();
	public Set<String> findColores();
	public List<Articulo> findByParam(String param);
	public Articulo findByCodigoBarras(String codigo_barras);



}
