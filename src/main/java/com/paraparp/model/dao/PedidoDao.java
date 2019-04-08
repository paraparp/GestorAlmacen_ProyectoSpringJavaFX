package com.paraparp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.model.entities.Pedido;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Long> {




	

}
