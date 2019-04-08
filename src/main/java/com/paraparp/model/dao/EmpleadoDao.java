package com.paraparp.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.model.entities.Empleado;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {




	

}
