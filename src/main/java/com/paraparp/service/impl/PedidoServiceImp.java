package com.paraparp.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.model.dao.PedidoDao;
import com.paraparp.model.entities.Lineapedido;
import com.paraparp.model.entities.Pedido;
import com.paraparp.service.interfaces.PedidoService;

@Service
public class PedidoServiceImp implements PedidoService {

	@Autowired
	private PedidoDao pedidoDao;

	@Override
	@Transactional
	public List<Pedido> findAll() {

		return (List<Pedido>) pedidoDao.findAll();
	}

	@Override
	@Transactional
	public Pedido save(Pedido pedido) {
		return pedidoDao.save(pedido);

	}

	@Override
	@Transactional
	public Pedido findById(Long id) {

		return pedidoDao.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		pedidoDao.delete(id);

	}

	@Override
	public BigDecimal costeTotal(Pedido pedido) {

		List<Lineapedido> lineaPedidos = findById(pedido.getId()).getLineapedidos();
		BigDecimal coste_total = pedido.getGastos();

		for (Lineapedido lineapedido : lineaPedidos) {
			BigDecimal coste_linea = lineapedido.getPrecio().multiply(new BigDecimal(lineapedido.getCantidad()));
			coste_total = coste_total.add(coste_linea);
		}
		return coste_total;
	}
}
