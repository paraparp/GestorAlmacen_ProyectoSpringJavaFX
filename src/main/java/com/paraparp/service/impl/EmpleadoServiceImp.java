package com.paraparp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paraparp.model.dao.EmpleadoDao;
import com.paraparp.model.entities.Empleado;
import com.paraparp.service.interfaces.EmpleadoService;

@Service
public class EmpleadoServiceImp implements EmpleadoService{

	
	@Autowired
	private EmpleadoDao empleadoDao;
	
	@Override
	@Transactional
	public List<Empleado> findAll() {
	
		return (List<Empleado>) empleadoDao.findAll();
	}

	@Override
	@Transactional
	public Empleado save(Empleado empleado) {
		 return empleadoDao.save(empleado);
		
	}

	@Override
	@Transactional
	public Empleado findById(Long id) {
	
		return empleadoDao.getOne(id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		empleadoDao.deleteById(id);
		
	}

	@Override
	public boolean authenticacion(String userName, String Password) {
		
		List<Empleado> listaEmpleados = empleadoDao.findAll();
		
		for (Empleado empleado : listaEmpleados) {
			if (empleado.getUsername().contentEquals(userName) && empleado.getPassword().contentEquals(Password))
				return true;
		}
		return false;
	}



}
