package com.paraparp.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.modelo.dao.ProductoGenericoDao;
import com.paraparp.modelo.entities.Productogenerico;
import com.paraparp.service.ProductoGenericoService;

@Service
public class ProductoGenericoServiceImp implements ProductoGenericoService {

	@Autowired
	private ProductoGenericoDao productoGenericoDao;

	@Override
	@Transactional
	public List<Productogenerico> findAll() {

		return (List<Productogenerico>) productoGenericoDao.findAll();
	}

	@Override
	@Transactional
	public Productogenerico save(Productogenerico proveedor) {
		return productoGenericoDao.save(proveedor);

	}

	@Override
	@Transactional
	public Productogenerico findById(Long id) {

		return productoGenericoDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		productoGenericoDao.delete(id);

	}

	@Override
	public Set<String> findMarcas() {

		ArrayList<String> marcas = new ArrayList<>();
		for (Productogenerico productogenerico : (ArrayList<Productogenerico>) findAll()) {
			marcas.add(productogenerico.getMarca());
		}

		return new HashSet<String>(marcas);
	}

	@Override
	public Set<String> findCategorias() {

		ArrayList<String> categorias = new ArrayList<>();
		for (Productogenerico productogenerico : (ArrayList<Productogenerico>) findAll()) {
			categorias.add(productogenerico.getCategoria());
		}

		return new HashSet<String>(categorias);
	}

	@Override
	public List <Productogenerico> findByParam(String param) {

		List<Productogenerico> filtrados = new ArrayList<Productogenerico>();

		for (Productogenerico p : findAll()) {

			if (p.getNombre().toLowerCase().contains(param) || p.getCodigo().toLowerCase().contains(param)) {

				System.out.println(p.getNombre() + " == " + param);

				filtrados.add(p);
			}

		}

		return filtrados;
	}

}
