package com.paraparp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.model.entities.Proveedor;

@Repository
public interface ProveedorDao extends JpaRepository<Proveedor, Long> {




	

}
