package com.paraparp.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.modelo.entities.Productogenerico;

@Repository
public interface ProductoGenericoDao extends JpaRepository<Productogenerico, Long> {




	

}
