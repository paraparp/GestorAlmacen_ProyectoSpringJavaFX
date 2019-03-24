package com.paraparp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.modelo.dao.PedidoDao;
import com.paraparp.modelo.entities.Pedido;
import com.paraparp.service.PedidoService;

@Service
public class PedidoServiceImp implements PedidoService{

	
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



}
