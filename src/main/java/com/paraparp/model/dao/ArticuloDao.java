package com.paraparp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.model.entities.Articulo;

@Repository
public interface ArticuloDao extends JpaRepository<Articulo, Long> {




	

}
