package com.codetreatise.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.modelo.entities.Articulo;

@Repository
public interface ArticuloDao extends JpaRepository<Articulo, Long> {




	

}
