package com.paraparp.service;

import java.math.BigDecimal;
import java.util.List;

import com.paraparp.modelo.entities.Pedido;

public interface PedidoService {
	
	public List<Pedido> findAll();
	public Pedido save(Pedido pedido);
	public Pedido findById(Long id);
	public void delete(Long id);

	public BigDecimal costeTotal(Pedido pedido);


}
