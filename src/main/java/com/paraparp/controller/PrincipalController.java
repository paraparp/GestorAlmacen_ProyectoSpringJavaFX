package com.paraparp.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.input.MouseEvent;

@Component
public class PrincipalController implements Initializable {

	@FXML
	private JFXButton btnSalir;

	@FXML
	private Tab tabPrincipal;

	@FXML
	private Tab tabProveedores;

	@FXML
	@Autowired
	private ProveedorController proveedorController;

	@FXML
	private Tab tabArticulos;
	
	@FXML
	@Autowired
	private ArticuloController articuloController;

	@FXML
	private Tab tabPedidos;

	@FXML
	@Autowired
	private PedidosController pedidosControler;

	@FXML
	private Tab tabEditarPedido;

	@FXML
	@Autowired
	private LineaPedidoController lineaPedidoController;

	@FXML
	private Tab tabEmpleados;

	@FXML
	@Autowired
	private EmpleadosController empleadosController;

	@FXML
	private Tab tabGenericos;

	@FXML
	@Autowired
	private ProductogenericoController productoGenericoController;
	
	
    @FXML
    private JFXTabPane navBar;

    @FXML
    void cargarDatos(MouseEvent event) {

    	lineaPedidoController.cargar();
    	articuloController.cargar();

    }
    


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	@FXML
	private void salir() {
		System.exit(0);
	}

}
