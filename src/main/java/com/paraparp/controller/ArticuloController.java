package com.paraparp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.paraparp.modelo.entities.Articulo;
import com.paraparp.modelo.entities.Productogenerico;
import com.paraparp.service.ArticuloService;
import com.paraparp.service.ProductoGenericoService;
import com.paraparp.util.Util;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

@Controller
public class ArticuloController implements Initializable {

	@FXML
	private JFXButton btnDetalles;

	@FXML
	private JFXTextField txtBuscar;

	@FXML
	private JFXTextField txtCodigo;

	@FXML
	private Label lbId;

	@FXML
	private JFXComboBox<String> cmbColor;

	@FXML
	private JFXComboBox<String> cmbTalla;

	@FXML
	private JFXTextArea txtDetalles;

	@FXML
	private JFXTextField txtStock;

	@FXML
	private JFXTextField txtImagen;

	@FXML
	private JFXButton btnGuardar;

	@FXML
	private JFXButton btnEliminar;

	@FXML
	private TableView<Articulo> tablaArticulo;

	@FXML
	private TableColumn<Articulo, Long> colId;

	@FXML
	private TableColumn<Articulo, String> colCodigo;

	@FXML
	private TableColumn<Articulo, String> colNombre;

	@FXML
	private TableColumn<Articulo, String> colCategoria;

	@FXML
	private TableColumn<Articulo, String> colMarca;

	@FXML
	private TableColumn<Articulo, String> colTalla;

	@FXML
	private TableColumn<Articulo, String> colColor;

	@FXML
	private TableColumn<Articulo, Integer> colStock;

	@FXML
	private TableColumn<Articulo, String> colDetalles;

	@FXML
	private JFXComboBox<String> cmbMarca;

	@FXML
	private JFXComboBox<String> cmbCategoria;

	@FXML
	private JFXTextField txtBuscaTipo;

	@FXML
	private JFXButton btnBorrar;

	@FXML
	private JFXButton btnImagen;

	@FXML
	private JFXButton btnReset;

	@FXML
	private JFXComboBox<Productogenerico> cmbTipo;

	@Autowired
	private ArticuloService articuloService;

	@Autowired
	private ProductoGenericoService prodGenService;

	private ObservableList<Articulo> articuloList = FXCollections.observableArrayList();

	private ObservableList<String> tallasList = FXCollections.observableArrayList();

	private ObservableList<String> coloresList = FXCollections.observableArrayList();

	private ObservableList<String> marcasList = FXCollections.observableArrayList();
	
	private ObservableList<Productogenerico> tiposList = FXCollections.observableArrayList();

	private Articulo articulo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		loadCmbTipo();
		loadCmbColor();
		loadCmbTalla();
		loadCmbCategoria();
		loadCmbMarca();

		cargarColumnas();
		cargarArticulos();

		tablaArticulo.setItems(articuloList);
		tablaArticulo.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	}

	@FXML
	void filtrarTipo(KeyEvent event) {

		String filtroTipo = txtBuscaTipo.getText().toLowerCase();
		cmbTipo.getSelectionModel().clearSelection();

		ObservableList<Productogenerico> tipoListFiltered = FXCollections
				.observableArrayList(prodGenService.findByParam(filtroTipo));

		cmbTipo.setItems(tipoListFiltered);
	}

	@FXML
	void filtrarArticulos(KeyEvent event) {

		articuloList.clear();
		String filtro = txtBuscar.getText().toLowerCase();

		articuloList = FXCollections.observableArrayList(articuloService.findByParam(filtro));

		tablaArticulo.setItems(articuloList);

	}

	@FXML
	void filtrarCategoria(ActionEvent event) {

		articuloList.clear();
		String filtro = cmbCategoria.getSelectionModel().getSelectedItem();
		articuloList = FXCollections.observableArrayList(articuloService.findByCategoria(filtro));
		tablaArticulo.setItems(articuloList);
	}

	@FXML
	void filtrarMarca(ActionEvent event) {

		articuloList.clear();
		String filtro = cmbMarca.getSelectionModel().getSelectedItem();

		articuloList = FXCollections.observableArrayList(articuloService.findByMarca(filtro));

		tablaArticulo.setItems(articuloList);
	}

	@FXML
	private void cargarArticulos() {

		articuloList.clear();

		articuloList.addAll(articuloService.findAll());

		tablaArticulo.setItems(articuloList);

	}

	private void cargarColumnas() {

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigoBarras"));
		colNombre.setCellValueFactory(new Callback<CellDataFeatures<Articulo, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Articulo, String> c) {
				return new SimpleStringProperty(c.getValue().getProductogenerico().getNombre());
			}
		});
		colCategoria.setCellValueFactory(new Callback<CellDataFeatures<Articulo, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Articulo, String> c) {
				return new SimpleStringProperty(c.getValue().getProductogenerico().getCategoria());
			}
		});
		colMarca.setCellValueFactory(new Callback<CellDataFeatures<Articulo, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Articulo, String> c) {
				return new SimpleStringProperty(c.getValue().getProductogenerico().getMarca());
			}
		});
		colTalla.setCellValueFactory(new PropertyValueFactory<>("talla"));
		colColor.setCellValueFactory(new PropertyValueFactory<>("color"));
		colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		colDetalles.setCellValueFactory(new PropertyValueFactory<>("detalles"));

	}

	@FXML
	void deleteArticulo(ActionEvent event) {

		Articulo articulo = tablaArticulo.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar borrado");
		alert.setHeaderText(null);
		alert.setContentText("¿Deseas borrar el articulo" + articulo.getCodigoBarras() + "?");
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			try {
				articuloService.delete(articulo.getId());

			} catch (Exception e) {
				Util.alertaInformacion("Error al intentar eliminar articulo",
						"Este articulo está referenciado a un pedido, no puede borrarlo sin elimarlo antes del pedido o pedidos");
			}
		}
		limpiarCampos();
	}

	@FXML
	void saveArticulo(ActionEvent event) {

		Articulo a = new Articulo();

		if (lbId.getText() == null || lbId.getText() == "") {
			saveNew(a);
		} else {
			saveUpdt(a);
		}

	}

	private void saveNew(Articulo a) {

		if (txtCodigo.getText().isEmpty() || cmbTipo.getValue() == null || txtStock.getText().isEmpty()) {
			Util.alertaInformacion("Error formulario", "Completa al menos el campo de Tipo, Codigo y Stock");

		} else {

			if (txtStock.getText().matches("\\d+")) {
				a.setCodigoBarras(txtCodigo.getText());
				a.setProductogenerico(cmbTipo.getValue());
				a.setColor(cmbColor.getValue());
				a.setTalla(cmbTalla.getValue());
				a.setStock(Integer.parseInt(txtStock.getText()));
				a.setFoto(txtImagen.getText());
				a.setDetalles(txtDetalles.getText());

				Articulo newA = articuloService.save(a);
				Util.alertaInformacion("Info", "Añadido articulo con codigo: " + newA.getCodigoBarras());

				limpiarCampos();
			} else
				Util.alertaInformacion("Error formulario", "Introduce un número entero en la cantidad");
		}
	}

	private void saveUpdt(Articulo a) {

		a = articuloService.findById(Long.parseLong(lbId.getText()));

		if (txtStock.getText().matches("\\d+")) {
			a.setCodigoBarras(txtCodigo.getText());
			a.setProductogenerico(cmbTipo.getValue());
			a.setColor(cmbColor.getValue());
			a.setTalla(cmbTalla.getValue());
			a.setStock(Integer.parseInt(txtStock.getText()));
			a.setFoto(txtImagen.getText());
			a.setDetalles(txtDetalles.getText());
			Articulo udtA = articuloService.save(a);
			Util.alertaInformacion("Info", "Editado articulo con codigo: " + udtA.getCodigoBarras());
			limpiarCampos();

		} else
			Util.alertaInformacion("Error formulario", "Introduce un número entero en la cantidad");

	}

	

	@FXML
	void seleccionarArticulo(MouseEvent event) {

		articulo = tablaArticulo.getSelectionModel().getSelectedItem();

		if (articulo != null) {
			lbId.setText(Long.toString(articulo.getId()));

			txtCodigo.setText(articulo.getCodigoBarras());
			cmbColor.getSelectionModel().select(articulo.getColor());
			cmbTalla.getSelectionModel().select(articulo.getTalla());
			txtDetalles.setText(articulo.getDetalles());
			txtStock.setText(String.valueOf(articulo.getStock()));
			txtImagen.setText(articulo.getFoto());

			// Para rellenar el combobox del objeto hay que buscar el objeto por id, ya que
			// no son el mismo el de la lista que el del comboBox
			for (Productogenerico productogenerico : tiposList) {
				if (articulo.getProductogenerico().getId() == productogenerico.getId())
					cmbTipo.getSelectionModel().select(productogenerico);
			}
		}
	}

	@FXML
	void abrirDetalles() throws IOException {

		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/VerArticulo.fxml"));
		Parent root = (Parent) loader.load();

		Articulo articulo = tablaArticulo.getSelectionModel().getSelectedItem();

		if (articulo != null) {
			VerArticuloController verArticuloController = (VerArticuloController) loader.getController();
			verArticuloController.getArticulo(articulo);
			stage.setScene(new Scene(root));
			stage.setTitle(tablaArticulo.getSelectionModel().getSelectedItem().getProductogenerico().getNombre());
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();

		} else {
			Util.alertaInformacion("Error", "Selecciona algún articulo de la lista");

		}

	}

	@FXML
	public void locateFile(ActionEvent event) throws IOException {

		String pathFile = Util.imageChooser();
		txtImagen.setText(pathFile);
	}

	private void loadCmbTipo() {

		tiposList.addAll(prodGenService.findAll());
		cmbTipo.setItems(tiposList);

	}

	private void loadCmbTalla() {

		tallasList.addAll(articuloService.findTallas());
		cmbTalla.setItems(tallasList);
	}

	private void loadCmbColor() {

		coloresList.addAll(articuloService.findColores());
		cmbColor.setItems(coloresList);
	}

	private void loadCmbMarca() {

		marcasList.addAll(prodGenService.findMarcas());
		cmbMarca.setItems(marcasList);

	}

	private void loadCmbCategoria() {

		ObservableList<String> categorias = FXCollections.observableArrayList();
		categorias.addAll(prodGenService.findCategorias());
		cmbCategoria.setItems(categorias);
	}

	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtCodigo.clear();
		cmbTipo.getSelectionModel().clearSelection();
		cmbColor.getSelectionModel().clearSelection();
		cmbTalla.getSelectionModel().clearSelection();
		txtDetalles.clear();
		txtStock.clear();
		txtImagen.clear();

		cargarArticulos();

	}

}
