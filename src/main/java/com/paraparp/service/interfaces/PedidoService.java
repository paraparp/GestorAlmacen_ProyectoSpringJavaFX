package com.paraparp.service.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.paraparp.model.entities.Pedido;

import javafx.scene.control.TableColumn.CellDataFeatures;

public interface PedidoService {
	
	public List<Pedido> findAll();
	public Pedido save(Pedido pedido);
	public Pedido findById(Long id);
	public void delete(Long id);

	public BigDecimal costeTotal(Pedido pedido);


}
