package com.paraparp.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.paraparp.model.entities.Productogenerico;
import com.paraparp.service.interfaces.ProductoGenericoService;
import com.paraparp.util.Util;

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
import javafx.scene.input.MouseEvent;

@Controller
public class ProductogenericoController implements Initializable {

	@FXML
	private JFXTextField txtCodigo;

	@FXML
	private JFXButton btnGuardar;

	@FXML
	private JFXButton btnEliminar;

	@FXML
	private JFXButton btnBorrar;

	@FXML
	private Label lbId;

	@FXML
	private JFXComboBox<String> cmbCategoria;

	@FXML
	private JFXComboBox<String> cmbMarca;

	@FXML
	private JFXTextField txtNombre;

	@FXML
	private JFXTextArea txtDescripcion;

	@FXML
	private TableView<Productogenerico> tablaProdGen;

	@FXML
	private TableColumn<Productogenerico, Long> colId;

	@FXML
	private TableColumn<Productogenerico, String> colCodigo;

	@FXML
	private TableColumn<Productogenerico, String> colNombre;

	@FXML
	private TableColumn<Productogenerico, String> colCategoria;

	@FXML
	private TableColumn<Productogenerico, String> colMarca;

	@FXML
	private TableColumn<Productogenerico, String> colDescripcion;

	@Autowired
	private ProductoGenericoService prodGenService;

	private ObservableList<Productogenerico> prodGenList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadCmbMarca();
		loadCmbCategoria();

		cargarColumnas();
		cargarProductos();

		tablaProdGen.setItems(prodGenList);
	}

	@FXML
	void saveGenerico(ActionEvent event) {

		Productogenerico p = new Productogenerico();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {
			saveNew(p, alert);
		} else {
			saveUpdt(p, alert);
		}
		alert.showAndWait();
		limpiarCampos();

	}

	private void saveNew(Productogenerico p, Alert alert) {

		p.setCodigo(txtCodigo.getText());
		p.setCategoria(cmbCategoria.getSelectionModel().getSelectedItem());
		p.setMarca(cmbMarca.getSelectionModel().getSelectedItem());
		p.setNombre(txtNombre.getText());
		p.setDescripcion(txtDescripcion.getText());

		if (txtCodigo.getText().isEmpty()) {
			alert.setContentText("Completa al menos el campo  Codigo");

		} else {
			Productogenerico newPgen = prodGenService.save(p);
			alert.setContentText("Añadido producto: " + newPgen.getCodigo());
		}
	}

	private void saveUpdt(Productogenerico p, Alert alert) {

		p = prodGenService.findById(Long.parseLong(lbId.getText()));

		p.setCodigo(txtCodigo.getText());
		p.setCategoria(cmbCategoria.getSelectionModel().getSelectedItem());
		p.setMarca(cmbMarca.getSelectionModel().getSelectedItem());
		p.setNombre(txtNombre.getText());
		p.setDescripcion(txtDescripcion.getText());
		Productogenerico udtP = prodGenService.save(p);

		alert.setContentText("Editado producto: " + udtP.getNombre());
	}

	@FXML
	private void deleteGenerico(ActionEvent e) {

		Productogenerico prodGen = tablaProdGen.getSelectionModel().getSelectedItem();
		if (prodGen != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmar borrado");
			alert.setHeaderText(null);
			alert.setContentText("¿Deseas borrar el producto " + prodGen.getNombre() + "?");
			Optional<javafx.scene.control.ButtonType> action = alert.showAndWait();

			if (action.get() == ButtonType.OK) {

				try {
					prodGenService.delete(prodGen.getId());
				} catch (Exception ex) {
					Util.alertaInformacion("Error al intentar eliminar genérico",
							"Este genérico no puede ser eliminado, existen articulo con su referencia");
				}
			}

			limpiarCampos();
		} else {
			Util.alertaInformacion("Error al intentar eliminar articulo", "No has seleccionado ningún elemento.");
		}

	}

	private void loadCmbMarca() {

		ObservableList<String> marcas = FXCollections.observableArrayList();
		marcas.addAll(prodGenService.findMarcas());
		cmbMarca.setItems(marcas);
	}

	private void loadCmbCategoria() {

		ObservableList<String> categorias = FXCollections.observableArrayList();
		categorias.addAll(prodGenService.findCategorias());
		cmbCategoria.setItems(categorias);
	}

	@FXML
	void seleccionarGenerico(MouseEvent event) {

		Productogenerico prodGen = tablaProdGen.getSelectionModel().getSelectedItem();

		if (prodGen != null) {

			lbId.setText(Long.toString(prodGen.getId()));
			txtCodigo.setText(prodGen.getCodigo());
			cmbCategoria.getSelectionModel().select(prodGen.getCategoria());
			cmbMarca.getSelectionModel().select(prodGen.getMarca());
			txtNombre.setText(prodGen.getNombre());
			txtDescripcion.setText(prodGen.getDescripcion());
		}
	}

	private void cargarColumnas() {

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));
		colMarca.setCellValueFactory(new PropertyValueFactory<>("marca"));
		colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
	}

	private void cargarProductos() {

		prodGenList.clear();

		prodGenList.addAll(prodGenService.findAll());

		tablaProdGen.setItems(prodGenList);
	}

	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtCodigo.clear();
		cmbCategoria.getSelectionModel().clearSelection();
		cmbMarca.getSelectionModel().clearSelection();
		txtNombre.clear();
		txtDescripcion.clear();

		cargarProductos();

	}

}
