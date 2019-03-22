package com.codetreatise.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.codetreatise.modelo.entities.Empleado;
import com.codetreatise.service.EmpleadoService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

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
public class EmpleadosController implements Initializable {

	@FXML
	private TableView<Empleado> tablaEmpleados;

	@FXML
	private JFXButton btnGuardar;

	@FXML
	private JFXButton btnEliminar;
	
	@FXML
	private JFXButton btnBorrar;


	@FXML
	private Label lbId;

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXTextField txtDni;

	@FXML
	private JFXTextField txtEmail;

	@FXML
	private JFXTextField txtTelefono;

	@FXML
	private JFXTextField txtUsername;

	@FXML
	private JFXTextField txtPassword;

	@FXML
	private TableColumn<Empleado, Long> colId;

	@FXML
	private TableColumn<Empleado, String> colNombre;

	@FXML
	private TableColumn<Empleado, String> colDni;

	@FXML
	private TableColumn<Empleado, String> colTelefono;

	@FXML
	private TableColumn<Empleado, String> colEmail;

	@FXML
	private TableColumn<Empleado, String> colUsername;

	@FXML
	private TableColumn<Empleado, String> colPassword;

	private ObservableList<Empleado> empList = FXCollections.observableArrayList();

	@Autowired
	private EmpleadoService empleadoService;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cargarColumnas();
		cargarEmpleados();
		tablaEmpleados.setItems(empList);

	}

	@FXML
	private void saveEmp(ActionEvent event) {

		Empleado e = new Empleado();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {
			saveNew(e, alert);
		} else {
			saveUpdt(e, alert);
		}
		alert.showAndWait();
		limpiarCampos();

	}

	private void saveNew(Empleado e, Alert alert) {
		e.setNombre(txtNombre.getText());
		e.setDni(txtDni.getText());
		e.setEmail(txtEmail.getText());
		e.setTelefono(txtTelefono.getText());
		e.setUsername(txtUsername.getText());
		e.setPassword(txtPassword.getText());

		if (txtUsername.getText().isEmpty() || txtPassword.getText().isEmpty() || txtNombre.getText().isEmpty()) {
			alert.setContentText("Completa al menos los campos de Nombre, Username y Password");

		} else {
			Empleado newE = empleadoService.save(e);
			alert.setContentText("Añadido empleado con nombre: " + newE.getNombre());
		}
	}

	private void saveUpdt(Empleado e, Alert alert) {

		e = empleadoService.findById(Long.parseLong(lbId.getText()));

		e.setNombre(txtNombre.getText());
		e.setDni(txtDni.getText());
		e.setEmail(txtEmail.getText());
		e.setTelefono(txtTelefono.getText());
		e.setUsername(txtUsername.getText());
		e.setPassword(txtPassword.getText());
		Empleado udtE = empleadoService.save(e);

		alert.setContentText("Editado empleado con nombre: " + udtE.getNombre());
	}

	@FXML
	private void deleteEmp(ActionEvent e) {

		Empleado emp = tablaEmpleados.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar borrado");
		alert.setHeaderText(null);
		alert.setContentText("¿Deseas borrar al empleado " + emp.getNombre() + "?");
		Optional<javafx.scene.control.ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			empleadoService.delete(emp.getId());

		limpiarCampos();
	}

	@FXML
	private void seleccionarEmp() {

		Empleado emp = tablaEmpleados.getSelectionModel().getSelectedItem();
		
		if (emp != null) {

		lbId.setText(Long.toString(emp.getId()));
		txtNombre.setText(emp.getNombre());
		txtDni.setText(emp.getDni());
		txtEmail.setText(emp.getEmail());
		txtTelefono.setText(emp.getTelefono());
		txtUsername.setText(emp.getUsername());
		txtPassword.setText(emp.getPassword());
		}
	}

	@FXML
	private void updateEmp(ActionEvent e) {

		cargarEmpleados();
	}

	private void cargarColumnas() {
		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
		colTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
		colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
	}

	private void cargarEmpleados() {
		empList.clear();

		empList.addAll(empleadoService.findAll());

		tablaEmpleados.setItems(empList);
	}

	
	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtNombre.clear();
		txtDni.clear();
		txtEmail.clear();
		txtTelefono.clear();
		txtUsername.clear();
		txtPassword.clear();
		cargarEmpleados();

	}

}
