package com.paraparp.service;

import java.util.List;

import com.paraparp.modelo.entities.Lineapedido;

public interface LineaPedidoService {
	
	public List<Lineapedido> findAll();
	public Lineapedido save(Lineapedido lineaPedido);
	public Lineapedido findById(Long id);
	public void delete(Long id);
	public List<Lineapedido> findLineasPedido(Long idPedido);


}
