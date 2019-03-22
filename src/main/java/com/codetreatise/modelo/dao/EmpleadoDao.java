package com.codetreatise.modelo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codetreatise.modelo.entities.Empleado;

@Repository
public interface EmpleadoDao extends JpaRepository<Empleado, Long> {




	

}
