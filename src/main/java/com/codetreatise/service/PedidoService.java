package com.codetreatise.service;

import java.util.List;

import com.codetreatise.modelo.entities.Pedido;

public interface PedidoService {
	
	public List<Pedido> findAll();
	public Pedido save(Pedido pedido);
	public Pedido findById(Long id);
	public void delete(Long id);


}
