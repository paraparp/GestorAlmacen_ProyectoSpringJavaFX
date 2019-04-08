package com.paraparp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.model.entities.Productogenerico;

@Repository
public interface ProductoGenericoDao extends JpaRepository<Productogenerico, Long> {




	

}
