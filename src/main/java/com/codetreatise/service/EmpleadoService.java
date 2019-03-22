package com.codetreatise.service;

import java.util.List;

import com.codetreatise.modelo.entities.Empleado;

public interface EmpleadoService {
	
	public List<Empleado> findAll();
	public Empleado save(Empleado empleado);
	public Empleado findById(Long id);
	public void delete(Long id);
	public boolean authenticacion(String userName, String Password);


}
