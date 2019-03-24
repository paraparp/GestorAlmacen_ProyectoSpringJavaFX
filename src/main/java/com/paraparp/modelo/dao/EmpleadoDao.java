package com.paraparp.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paraparp.modelo.entities.Empleado;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {




	

}