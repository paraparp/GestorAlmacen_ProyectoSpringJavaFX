package com.codetreatise.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codetreatise.modelo.dao.PedidoDao;
import com.codetreatise.modelo.entities.Pedido;
import com.codetreatise.service.PedidoService;

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
