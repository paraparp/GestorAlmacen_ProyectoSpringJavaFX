package com.paraparp.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.modelo.entities.Pedido;

@Repository
public interface PedidoDao extends JpaRepository<Pedido, Long> {




	

}
