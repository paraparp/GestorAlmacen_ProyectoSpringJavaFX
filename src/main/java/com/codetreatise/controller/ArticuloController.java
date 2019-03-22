package com.codetreatise.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.codetreatise.modelo.entities.Articulo;
import com.codetreatise.modelo.entities.Productogenerico;
import com.codetreatise.service.ArticuloService;
import com.codetreatise.service.ProductoGenericoService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
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
	private JFXTextField txtPVenta;

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
	private TableColumn<Articulo, BigDecimal> colPVenta;

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
	private JFXComboBox<Productogenerico> cmbTipo;

	@Autowired
	private ArticuloService articuloService;

	@Autowired
	private ProductoGenericoService prodGenService;

	private ObservableList<Articulo> articuloList = FXCollections.observableArrayList();

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

		ObservableList<Articulo> articuloListFiltered = FXCollections
				.observableArrayList(articuloService.findByParam(filtro));

		tablaArticulo.setItems(articuloListFiltered);

	}

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
		colPVenta.setCellValueFactory(new PropertyValueFactory<>("precioVenta"));
		colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
		colDetalles.setCellValueFactory(new PropertyValueFactory<>("detalles"));

	}

	@FXML
	void deleteArticulo(ActionEvent event) {
		Articulo articulo = tablaArticulo.getSelectionModel().getSelectedItem();

		articulo.setLineapedidos(null);
		// articulo.setProductogenerico(null);

		articuloService.save(articulo);

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar borrado");
		alert.setHeaderText(null);
		alert.setContentText("¿Deseas borrar el articulo" + articulo.getCodigoBarras() + "?");
		Optional<javafx.scene.control.ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK)
			articuloService.delete(articulo.getId());

		limpiarCampos();
	}

	@FXML
	void saveArticulo(ActionEvent event) {

		Articulo a = new Articulo();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {
			saveNew(a, alert);
		} else {
			saveUpdt(a, alert);
		}
		alert.showAndWait();
		limpiarCampos();

	}

	private void saveNew(Articulo a, Alert alert) {

	
		if (txtCodigo.getText().isEmpty() || cmbTipo.getValue() == null  || txtStock.getText().isEmpty()) {
			alert.setContentText("Completa al menos el campo de Tipo, Codigo y Stock");

		} else {
			
			a.setCodigoBarras(txtCodigo.getText());
			a.setProductogenerico(cmbTipo.getValue());
			a.setColor(cmbColor.getValue());
			a.setTalla(cmbTalla.getValue());
			a.setStock(Integer.parseInt(txtStock.getText()));
		
			a.setFoto(txtImagen.getText());
			a.setDetalles(txtDetalles.getText());

			Articulo newA = articuloService.save(a);
			alert.setContentText("Añadido pedido con codigo: " + newA.getCodigoBarras());
		}
	}

	private void saveUpdt(Articulo a, Alert alert) {

		a = articuloService.findById(Long.parseLong(lbId.getText()));

		a.setCodigoBarras(txtCodigo.getText());
		a.setProductogenerico(cmbTipo.getValue());
		a.setColor(cmbColor.getValue());
		a.setTalla(cmbTalla.getValue());
		a.setStock(Integer.parseInt(txtStock.getText()));

		a.setFoto(txtImagen.getText());
		a.setDetalles(txtDetalles.getText());
		Articulo udtA = articuloService.save(a);

		alert.setContentText("Editado pedido con codigo: " + udtA.getCodigoBarras());
	}

	@FXML
	void seleccionarArticulo(MouseEvent event) {

		Articulo articulo = tablaArticulo.getSelectionModel().getSelectedItem();

		if (articulo != null) {
			lbId.setText(Long.toString(articulo.getId()));
			cmbTipo.getSelectionModel().select(articulo.getProductogenerico());
			txtCodigo.setText(articulo.getCodigoBarras());
			cmbColor.getSelectionModel().select(articulo.getColor());
			cmbTalla.getSelectionModel().select(articulo.getTalla());
			txtPVenta.setText(String.valueOf(articulo.getPrecioVenta()));
			txtDetalles.setText(articulo.getDetalles());
			txtStock.setText(String.valueOf(articulo.getStock()));
			txtImagen.setText(articulo.getFoto());
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
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setContentText("Selecciona algún articulo de la lista");
			alert.showAndWait();
		}

	}


	@FXML
	protected void locateFile(ActionEvent event) {
		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		String file = null;
		try {
			file = chooser.showOpenDialog(new Stage()).getCanonicalPath();
		} catch (IOException e) {
			file = "";
		}

		txtImagen.setText(file);
	}

	private void loadCmbTipo() {

		ObservableList<Productogenerico> tipos = FXCollections.observableArrayList();
		tipos.addAll(prodGenService.findAll());
		cmbTipo.setItems(tipos);

	}

	private void loadCmbTalla() {

		ObservableList<String> tallas = FXCollections.observableArrayList();
		tallas.addAll(articuloService.findTallas());
		cmbTalla.setItems(tallas);
	}

	private void loadCmbColor() {

		ObservableList<String> colores = FXCollections.observableArrayList();
		colores.addAll(articuloService.findColores());
		cmbColor.setItems(colores);
	}

	private void loadCmbMarca() {

//		ObservableList<String> marcas = FXCollections.observableArrayList();
//		marcas.addAll(prodGenService.findMarcas());
//		cmbMarca.setItems(marcas);

	}

	private void loadCmbCategoria() {

//		ObservableList<String> categorias = FXCollections.observableArrayList();
//		categorias.addAll(prodGenService.findCategorias());
//		cmbCategoria.setItems(categorias);
	}

	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtCodigo.clear();
		cmbTipo.getSelectionModel().clearSelection();
		cmbColor.getSelectionModel().clearSelection();
		cmbTalla.getSelectionModel().clearSelection();
		txtPVenta.clear();
		txtDetalles.clear();
		txtStock.clear();
		txtImagen.clear();

		cargarArticulos();

	}

}
