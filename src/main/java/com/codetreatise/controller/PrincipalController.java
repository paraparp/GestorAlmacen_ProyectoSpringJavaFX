package com.codetreatise.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;

@Component
public class PrincipalController implements Initializable {

	
	
	@FXML
	private JFXButton btnSalir;
	@FXML
	private Tab tabPrincipal;

	@FXML
	private Tab tabProveedores;
	@FXML
	private ProveedorController proveedorControle1r;

	@FXML
	private Tab tabArticulos;
	@FXML
	private ArticuloController articuloControle1r;

	@FXML
	private Tab tabPedidos;
	@FXML
	private PedidosController pedidosControler;
	@FXML
	private Tab tabEditarPedido;
	@FXML
	private LineaPedidoController lineaPedidoController;

	@FXML
	private Tab tabEmpleados;
	@FXML
	private EmpleadosController empleadosController;

	@FXML
	private Tab tabGenericos;
	@FXML
	private ProductogenericoController productoGenericoController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void salir() {
		System.exit(0);
	}
	
	void logged (String user) {
		
		System.out.println(user);
		
	}
	

}
