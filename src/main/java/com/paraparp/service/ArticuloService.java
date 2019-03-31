package com.paraparp.service;

import java.util.List;
import java.util.Set;

import com.paraparp.modelo.entities.Articulo;

public interface ArticuloService {
	
	public List<Articulo> findAll();
	public Articulo save(Articulo articulo);
	public Articulo findById(Long id);
	public void delete(Long id);
	public Set<String> findTallas();
	public Set<String> findColores();
	public List<Articulo> findByParam(String param);
	public Articulo findByCodigoBarras(String codigo_barras);
	public List<Articulo> findByCategoria(String categoria);
	public List<Articulo> findByMarca(String marca);




}
