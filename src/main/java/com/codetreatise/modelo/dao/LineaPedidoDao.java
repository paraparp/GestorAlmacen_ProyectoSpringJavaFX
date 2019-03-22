package com.codetreatise.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.modelo.entities.Lineapedido;

@Repository
public interface LineaPedidoDao extends JpaRepository<Lineapedido, Long> {




	

}
