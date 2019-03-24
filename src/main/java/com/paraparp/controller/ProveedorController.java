package com.paraparp.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.paraparp.modelo.entities.Empleado;
import com.paraparp.modelo.entities.Proveedor;
import com.paraparp.service.ProveedorService;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

@Controller
public class ProveedorController implements Initializable {

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXButton btnGuardar;

	@FXML
	private JFXTextField txtCif;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtTelefono;

	@FXML
	private JFXTextField txtDireccion;

	@FXML
	private JFXButton btnEliminar;

	@FXML
	private Label lbId;

	@FXML
	private JFXTextArea txtDetalles;

	@FXML
	private JFXButton btnBorrar;

	@FXML
	private TableView<Proveedor> tablaProveedores;

	@FXML
	private TableColumn<Proveedor, String> colId;

	@FXML
	private TableColumn<Proveedor, String> colNombre;

	@FXML
	private TableColumn<Proveedor, String> colCif;

	@FXML
	private TableColumn<Proveedor, String> colTelefono;

	@FXML
	private TableColumn<Proveedor, String> colEmail;

	@FXML
	private TableColumn<Proveedor, String> colDireccion;

	@FXML
	private TableColumn<Proveedor, String> colDetalles;

	private ObservableList<Proveedor> provList = FXCollections.observableArrayList();

	@Autowired
	private ProveedorService proveedorService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cargarColumnas();
		cargarProveedores();
		tablaProveedores.setItems(provList);

	}

	@FXML
	private void saveProveedor(ActionEvent event) {

		Proveedor p = new Proveedor();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {
			saveNew(p, alert);
		} else {
			saveUpdt(p, alert);
		}
		alert.showAndWait();
		limpiarCampos();

	}

	private void saveNew(Proveedor p, Alert alert) {
		p.setNombre(txtNombre.getText());
		p.setCif(txtCif.getText());
		p.setEmail(txtEmail.getText());
		p.setTelefono(txtTelefono.getText());
		p.setDireccion(txtDireccion.getText());
		p.setDescripcion(txtDetalles.getText());

		if (txtNombre.getText().isEmpty() || txtCif.getText().isEmpty() || txtEmail.getText().isEmpty()) {
			alert.setContentText("Completa al menos los campos de Nombre, CIF y Email");

		} else {
			Proveedor newP = proveedorService.save(p);
			alert.setContentText("Añadido proveedor: " + newP.getNombre());
		}
	}

	private void saveUpdt(Proveedor p, Alert alert) {

		p = proveedorService.findById(Long.parseLong(lbId.getText()));

		p.setNombre(txtNombre.getText());
		p.setCif(txtCif.getText());
		p.setEmail(txtEmail.getText());
		p.setTelefono(txtTelefono.getText());
		p.setDireccion(txtDireccion.getText());
		p.setDescripcion(txtDetalles.getText());
		Proveedor udtP = proveedorService.save(p);

		alert.setContentText("Editado proveedor: " + udtP.getNombre());
	}

	@FXML
	private void deleteProveedor(ActionEvent e) {

		Proveedor prov = tablaProveedores.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar borrado");
		alert.setHeaderText(null);
		alert.setContentText("¿Deseas borrar al proveedor " + prov.getNombre() + "?");
		Optional<javafx.scene.control.ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			proveedorService.delete(prov.getId());

		limpiarCampos();
	}

	@FXML
	private void seleccionarProveedor() {

		Proveedor prov = tablaProveedores.getSelectionModel().getSelectedItem();
		
		if (prov != null) {

		lbId.setText(Long.toString(prov.getId()));
		txtNombre.setText(prov.getNombre());
		txtCif.setText(prov.getCif());
		txtEmail.setText(prov.getEmail());
		txtTelefono.setText(prov.getTelefono());
		txtDireccion.setText(prov.getDireccion());
		txtDetalles.setText(prov.getDescripcion());
		}

	}

	@FXML
	private void updateProveedores(ActionEvent e) {

		cargarProveedores();
	}

	private void cargarColumnas() {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colCif.setCellValueFactory(new PropertyValueFactory<>("cif"));
		colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
		colDetalles.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
	}

	private void cargarProveedores() {
		provList.clear();

		provList.addAll(proveedorService.findAll());

		tablaProveedores.setItems(provList);
	}

	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtNombre.clear();
		txtCif.clear();
		txtEmail.clear();
		txtTelefono.clear();
		txtDireccion.clear();
		txtDetalles.clear();
		cargarProveedores();

	}

}
