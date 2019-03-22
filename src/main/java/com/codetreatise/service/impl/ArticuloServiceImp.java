package com.codetreatise.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.modelo.dao.ArticuloDao;
import com.codetreatise.modelo.entities.Articulo;
import com.codetreatise.service.ArticuloService;

@Service
public class ArticuloServiceImp implements ArticuloService {

	@Autowired
	private ArticuloDao articuloDao;

	@Override
	@Transactional
	public List<Articulo> findAll() {

		return (List<Articulo>) articuloDao.findAll();
	}

	@Override
	@Transactional
	public Articulo save(Articulo proveedor) {
		return articuloDao.save(proveedor);

	}

	@Override
	@Transactional
	public Articulo findById(Long id) {

		return articuloDao.findOne(id);
	}

	@Override
	@Transactional
	public Articulo findByCodigoBarras(String codigo_barras) {

		Articulo articulo = new Articulo();

		for (Articulo art : (ArrayList<Articulo>) findAll())
			if (art.getCodigoBarras().contentEquals(codigo_barras))
				articulo = art;

		return articulo;
	}

	@Override
	public void delete(Long id) {
		articuloDao.delete(id);

	}

	@Override
	public Set<String> findTallas() {
		ArrayList<String> tallas = new ArrayList<>();
		for (Articulo articulo : (ArrayList<Articulo>) findAll()) {
			tallas.add(articulo.getTalla());
		}

		return new HashSet<String>(tallas);
	}

	@Override
	public Set<String> findColores() {
		ArrayList<String> colores = new ArrayList<>();
		for (Articulo articulo : (ArrayList<Articulo>) findAll()) {
			colores.add(articulo.getColor());
		}

		return new HashSet<String>(colores);
	}

	@Override
	public List<Articulo> findByParam(String param) {

		List<Articulo> filtrados = new ArrayList<Articulo>();

		for (Articulo a : findAll()) {

			if (
//			a.getCodigoBarras().toLowerCase().contains(param)
//					|| 
			a.getProductogenerico().getNombre().toLowerCase().contains(param))

			{
				System.out.println(a.getProductogenerico().getNombre().toLowerCase() + "--->" + param);
				filtrados.add(a);
			}

		}

		return filtrados;
	}

}
