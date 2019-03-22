package com.codetreatise.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.modelo.entities.Proveedor;

@Repository
public interface ProveedorDao extends JpaRepository<Proveedor, Long> {




	

}
