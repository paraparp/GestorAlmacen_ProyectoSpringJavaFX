package com.paraparp.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.paraparp.modelo.entities.Articulo;
import com.paraparp.modelo.entities.Empleado;
import com.paraparp.modelo.entities.Pedido;
import com.paraparp.modelo.entities.Productogenerico;
import com.paraparp.modelo.entities.Proveedor;
import com.paraparp.service.EmpleadoService;
import com.paraparp.service.PedidoService;
import com.paraparp.service.ProveedorService;
import com.paraparp.util.Util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

@Controller
public class PedidosController implements Initializable {

	@FXML
	private Label lbId;

	@FXML
	private JFXButton btnEliminar;

	@FXML
	private JFXButton btnGuardar;

	@FXML
	private JFXButton btnBorrar;

	@FXML
	private JFXTextField txtGastos;

	@FXML
	private JFXTextField txtCodigo;

	@FXML
	private JFXDatePicker datePedido;

	@FXML
	private JFXDatePicker dateRecibido;

	@FXML
	private JFXComboBox<Proveedor> CmbProveedor;

	@FXML
	private JFXComboBox<Empleado> CmbEmpleado;

	@FXML
	private TableView<Pedido> tablaPedidos;

	@FXML
	private TableColumn<Pedido, Long> colId;

	@FXML
	private TableColumn<Pedido, String> colCodigo;

	@FXML
	private TableColumn<Pedido, Date> colFechaP;

	@FXML
	private TableColumn<Pedido, LocalDate> colFechaR;

	@FXML
	private TableColumn<Pedido, Double> colGastos;

	@FXML
	private TableColumn<Pedido, String> colCoste;

	@FXML
	private TableColumn<Pedido, String> colProveedor;

	@FXML
	private TableColumn<Pedido, String> colEmpleado;
	@FXML
	private JFXButton btnEditarPedido;

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private EmpleadoService empleadoService;

	@Autowired
	private ProveedorService proveedorService;

	private ObservableList<Pedido> pedidosList = FXCollections.observableArrayList();
	private ObservableList<Empleado> empleadosList = FXCollections.observableArrayList();
	private ObservableList<Proveedor> proveedoresList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadCmbEmpleados();
		loadCmbProveedores();

		cargarColumnas();
		cargarPedidos();

		tablaPedidos.setItems(pedidosList);
	}

	@FXML
	void editarPedido(ActionEvent event) {

	}

	@FXML
	void savePedido(ActionEvent event) {

		Pedido p = new Pedido();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {
			saveNew(p, alert);
		} else {
			saveUpdt(p, alert);
		}
		alert.showAndWait();
		limpiarCampos();

	}

	private void saveNew(Pedido p, Alert alert) {

		p.setCodigo(txtCodigo.getText());
		p.setFechaPedido(Util.LocalDateToDate(datePedido.getValue()));
		p.setFechaRecibido(Util.LocalDateToDate(dateRecibido.getValue()));
		p.setGastos(new BigDecimal(txtGastos.getText()));
		p.setProveedor(CmbProveedor.getValue());
		p.setEmpleado(CmbEmpleado.getValue());

		if (txtCodigo.getText().isEmpty()) {
			alert.setContentText("Completa al menos el campo  Codigo");

		} else {
			Pedido newP = pedidoService.save(p);
			alert.setContentText("Añadido pedido con codigo: " + newP.getCodigo());
		}
	}

	private void saveUpdt(Pedido p, Alert alert) {

		p = pedidoService.findById(Long.parseLong(lbId.getText()));

		p.setCodigo(txtCodigo.getText());
		p.setFechaPedido(Util.LocalDateToDate(datePedido.getValue()));
		p.setFechaRecibido(Util.LocalDateToDate(dateRecibido.getValue()));
		p.setGastos(new BigDecimal(txtGastos.getText()));
		p.setProveedor(CmbProveedor.getValue());
		p.setEmpleado(CmbEmpleado.getValue());
		Pedido udtP = pedidoService.save(p);

		alert.setContentText("Editado pedido con codigo: " + udtP.getCodigo());
	}

	@FXML
	private void deletePedido(ActionEvent e) {

		Pedido ped = tablaPedidos.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar borrado");
		alert.setHeaderText(null);
		alert.setContentText("¿Deseas borrar al empleado " + ped.getCodigo() + "?");
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			try {
				pedidoService.delete(ped.getId());
			} catch (Exception ex) {
				Util.alertaInformacion("Error al intentar eliminar pedido",
						"Este pedido contiene artículos, no puede ser eliminado sin elimar antes todos los articulos del pedido");
			}

		limpiarCampos();

	}

	private void loadCmbEmpleados() {

		empleadosList.addAll(empleadoService.findAll());
		CmbEmpleado.setItems(empleadosList);

	}

	private void loadCmbProveedores() {

		proveedoresList.addAll(proveedorService.findAll());
		CmbProveedor.setItems(proveedoresList);
	}

	@FXML
	void seleccionarPedido(MouseEvent event) {

		Pedido pedido = tablaPedidos.getSelectionModel().getSelectedItem();
		if (pedido != null) {

			lbId.setText(Long.toString(pedido.getId()));

			txtCodigo.setText(pedido.getCodigo());
			datePedido.setValue(Util.DateToLocalDate(pedido.getFechaPedido()));
			dateRecibido.setValue(Util.DateToLocalDate(pedido.getFechaRecibido()));
			txtGastos.setText((pedido.getGastos().toString()));
			
			for (Empleado empleado : empleadosList) {
				if (empleado.getId() == pedido.getEmpleado().getId())
					CmbEmpleado.getSelectionModel().select(empleado);
			}
			
			for (Proveedor proveedor : proveedoresList) {
				if (proveedor.getId() == pedido.getProveedor().getId())
					CmbProveedor.getSelectionModel().select(proveedor);
			}

		}
	}
	

	private void cargarColumnas() {

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colFechaP.setCellValueFactory(new PropertyValueFactory<>("fechaPedido"));
		colFechaR.setCellValueFactory(new PropertyValueFactory<>("fechaRecibido"));
		colGastos.setCellValueFactory(new PropertyValueFactory<>("gastos"));
//
//		colCoste.setCellValueFactory(new Callback<CellDataFeatures<Pedido, String>, ObservableValue<String>>() {
//			public ObservableValue<String> call(CellDataFeatures<Pedido, BigDecimal> pedido) {
//				return new SimpleStringProperty(String.valueOf(pedidoService.costeTotal(pedido)));
//			}
//		});
//	
		
		
		
		colProveedor.setCellValueFactory(new PropertyValueFactory<>("empleado"));
		colEmpleado.setCellValueFactory(new PropertyValueFactory<>("proveedor"));
	}

	private void cargarPedidos() {

		pedidosList.clear();

		pedidosList.addAll(pedidoService.findAll());

		tablaPedidos.setItems(pedidosList);
	}

	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtCodigo.clear();
		datePedido.getEditor().clear();
		dateRecibido.getEditor().clear();
		txtGastos.clear();
		CmbProveedor.getSelectionModel().clearSelection();
		CmbEmpleado.getSelectionModel().clearSelection();

		cargarPedidos();

	}

}
