package com.paraparp.service.interfaces;

import java.util.List;

import com.paraparp.model.entities.Lineapedido;

public interface LineaPedidoService {
	
	public List<Lineapedido> findAll();
	public Lineapedido save(Lineapedido lineaPedido);
	public Lineapedido findById(Long id);
	public void delete(Long id);
	public List<Lineapedido> findLineasPedido(Long idPedido);


}
