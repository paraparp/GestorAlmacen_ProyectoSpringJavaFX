package com.paraparp.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.modelo.entities.Articulo;

@Repository
public interface ArticuloDao extends JpaRepository<Articulo, Long> {




	

}
