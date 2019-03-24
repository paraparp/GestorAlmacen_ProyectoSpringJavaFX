package com.paraparp.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.modelo.dao.LineaPedidoDao;
import com.paraparp.modelo.entities.Lineapedido;
import com.paraparp.service.LineaPedidoService;

@Service
public class LineaPedidoServiceImp implements LineaPedidoService {

	@Autowired
	private LineaPedidoDao lineaPedidoDao;

	@Override
	@Transactional
	public List<Lineapedido> findAll() {

		return (List<Lineapedido>) lineaPedidoDao.findAll();
	}

	@Override
	@Transactional
	public List<Lineapedido> findLineasPedido(Long idPedido) {

		List<Lineapedido> listaLineas = lineaPedidoDao.findAll();

		List<Lineapedido> listaLineasPedido = new ArrayList<Lineapedido>();

		for (Lineapedido lineapedido : listaLineas)
			if (lineapedido.getPedido().getId() == idPedido)
				listaLineasPedido.add(lineapedido);

		return listaLineasPedido;
	}

	@Override
	@Transactional
	public Lineapedido save(Lineapedido lineaPedido) {
		return lineaPedidoDao.save(lineaPedido);

	}

	@Override
	@Transactional
	public Lineapedido findById(Long id) {

		return lineaPedidoDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		lineaPedidoDao.delete(id);

	}

}
