package com.paraparp.controller;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.paraparp.model.entities.Articulo;
import com.paraparp.model.entities.Lineapedido;
import com.paraparp.model.entities.Pedido;
import com.paraparp.model.entities.Productogenerico;
import com.paraparp.service.interfaces.ArticuloService;
import com.paraparp.service.interfaces.LineaPedidoService;
import com.paraparp.service.interfaces.PedidoService;
import com.paraparp.service.interfaces.ProductoGenericoService;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

@Controller
public class LineaPedidoController implements Initializable {

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
	private JFXComboBox<Pedido> cmbPedido;
	
	@FXML
	private JFXTextField txtPrecio;

	@FXML
	private JFXTextField txtCantidad;

	@FXML
	private JFXTextArea txtDetalles;

	@FXML
	private JFXTextField txtImagen;
	
	@FXML
	private JFXButton btnGuardar;

	@FXML
	private JFXButton btnEliminar;

	@FXML
	private TableView<Lineapedido> tablaLineasPedido;

	@FXML
	private TableColumn<Lineapedido, Long> colId;

	@FXML
	private TableColumn<Lineapedido, String> colCodigo;

	@FXML
	private TableColumn<Lineapedido, String> colNombre;

	@FXML
	private TableColumn<Lineapedido, String> colCategoria;

	@FXML
	private TableColumn<Lineapedido, String> colMarca;

	@FXML
	private TableColumn<Lineapedido, String> colTalla;

	@FXML
	private TableColumn<Lineapedido, String> colColor;

	@FXML
	private TableColumn<Lineapedido, BigDecimal> colPrecio;

	@FXML
	private TableColumn<Lineapedido, Integer> colCantidad;

	@FXML
	private JFXButton btnFiltrar;

	@FXML
	private JFXTextField txtBuscaTipo;
	
	@FXML
	private JFXButton btnBorrar;
	
	@FXML
	private JFXButton btnImagen;
	
	@FXML
	private JFXComboBox<Productogenerico> cmbTipo;

	@FXML
	private Label lbCostePedido;

	@Autowired
	private ArticuloService articuloService;

	@Autowired
	private ProductoGenericoService prodGenService;

	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private LineaPedidoService lineaPedidoService;
	
	private Articulo articulo;

	private ObservableList<Lineapedido> lineasPedidoList = FXCollections.observableArrayList();
	private ObservableList<String> coloresList = FXCollections.observableArrayList();
	private ObservableList<String> tallasList = FXCollections.observableArrayList();
	private ObservableList<Productogenerico> tiposList = FXCollections.observableArrayList();
	private ObservableList<Pedido> pedidosList = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		loadCmbTipo();
		loadCmbColor();
		loadCmbTalla();
		loadCmbPedido();
		cargarColumnas();

		tablaLineasPedido.setItems(lineasPedidoList);

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

//		String filtro = txtBuscar.getText().toLowerCase();
//		lineasPedidoList.clear();
//
//		ObservableList<Lineapedido> articuloListFiltered = FXCollections
//				.observableArrayList(articuloService.findByParam(filtro));
//
//		tablaLineasPedido.setItems(articuloListFiltered);

	}

	private void cargarArticulos() {

//		lineasPedidoList.clear();
//
//		lineasPedidoList.addAll(articuloService.findAll());
//
//		tablaLineasPedido.setItems(lineasPedidoList);

	}

	private void cargarColumnas() {

		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
		colCodigo.setCellValueFactory(new Callback<CellDataFeatures<Lineapedido, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Lineapedido, String> c) {
				return new SimpleStringProperty(c.getValue().getArticulo().getCodigoBarras());
			}
		});
		colNombre.setCellValueFactory(new Callback<CellDataFeatures<Lineapedido, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Lineapedido, String> c) {
				return new SimpleStringProperty(c.getValue().getArticulo().getProductogenerico().getNombre());
			}
		});
		colCategoria
				.setCellValueFactory(new Callback<CellDataFeatures<Lineapedido, String>, ObservableValue<String>>() {
					@Override
					public ObservableValue<String> call(CellDataFeatures<Lineapedido, String> c) {
						return new SimpleStringProperty(
								c.getValue().getArticulo().getProductogenerico().getCategoria());
					}
				});
		colMarca.setCellValueFactory(new Callback<CellDataFeatures<Lineapedido, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Lineapedido, String> c) {
				return new SimpleStringProperty(c.getValue().getArticulo().getProductogenerico().getMarca());
			}
		});
		colTalla.setCellValueFactory(new Callback<CellDataFeatures<Lineapedido, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Lineapedido, String> c) {
				return new SimpleStringProperty(c.getValue().getArticulo().getTalla());
			}
		});
		colColor.setCellValueFactory(new Callback<CellDataFeatures<Lineapedido, String>, ObservableValue<String>>() {
			@Override
			public ObservableValue<String> call(CellDataFeatures<Lineapedido, String> c) {
				return new SimpleStringProperty(c.getValue().getArticulo().getColor());
			}
		});
		colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
		colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));

	}

	@FXML
	void deleteArticulo(ActionEvent event) {
		Lineapedido lineaPedido = tablaLineasPedido.getSelectionModel().getSelectedItem();

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmar borrado");
		alert.setHeaderText(null);
		alert.setContentText("¿Deseas borrar el articulo" + lineaPedido.getId() + "?");
		Optional<ButtonType> action = alert.showAndWait();

		if (action.get() == ButtonType.OK) {
			Articulo articulo = articuloService.findByCodigoBarras(lineaPedido.getArticulo().getCodigoBarras());
			articulo.actualizarStock(-lineaPedido.getCantidad());
			articuloService.save(articulo);

			lineaPedidoService.delete(lineaPedido.getId());
		}

		limpiarCampos();
		cargarListaPedido();
	}

	@FXML
	void saveArticulo(ActionEvent event) {

		articulo = new Articulo();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {

			saveNew(articulo, alert);

		} else {
			saveUpdt(articulo, alert);
		}
		alert.showAndWait();
		// limpiarCampos();

	}

	private void saveNew(Articulo articulo, Alert alert) {

		Lineapedido lp = new Lineapedido();

		lp.setPedido(cmbPedido.getValue());
		lp.setCantidad(Integer.parseInt(txtCantidad.getText()));
		lp.setPrecio(new BigDecimal(txtPrecio.getText()));

		articulo.setCodigoBarras(txtCodigo.getText());

		articulo.setFoto(txtImagen.getText());

		if (txtCodigo.getText().isEmpty()) {
			alert.setContentText("Completa al menos el campo  Codigo");
		} else {
			// Se busca el codigo de barras y si no existe se crea un articulo nuevo
			if (articuloService.findByCodigoBarras(articulo.getCodigoBarras()).getId() != null) {

				articulo = articuloService.findByCodigoBarras(articulo.getCodigoBarras());
				articulo.actualizarStock(Integer.parseInt(txtCantidad.getText()));

			} else {

				articulo.setCodigoBarras(txtCodigo.getText());
				articulo.setProductogenerico(cmbTipo.getValue());
				articulo.setColor(cmbColor.getValue());
				articulo.setTalla(cmbTalla.getValue());
				articulo.setStock(Integer.parseInt(txtCantidad.getText()));

			}
			articuloService.save(articulo);
			lp.setArticulo(articulo);
			Lineapedido newLp = lineaPedidoService.save(lp);

			alert.setContentText("Añadido a linea pedido: " + articulo.getProductogenerico().getNombre() + ". Talla: "
					+ articulo.getTalla() + ".Color: " + articulo.getColor() + ". \n=> Cantidad: " + newLp.getCantidad()
					+ " (Nuevo stock total: " + articulo.getStock() + ")");
		}

		cargarListaPedido();

	}

	private void saveUpdt(Articulo a, Alert alert) {

		a = articuloService.findById(Long.parseLong(lbId.getText()));

		a.setCodigoBarras(txtCodigo.getText());
		a.setProductogenerico(cmbTipo.getValue());
		a.setColor(cmbColor.getValue());
		a.setTalla(cmbTalla.getValue());
		a.setStock(Integer.parseInt(txtCantidad.getText()));
		a.setFoto(txtImagen.getText());
		a.setDetalles(txtDetalles.getText());
		Articulo udtA = articuloService.save(a);

		alert.setContentText("Editado pedido con codigo: " + udtA.getCodigoBarras());
	}

	@FXML
	void seleccionarArticulo(MouseEvent event) {

		Lineapedido lineapedido = tablaLineasPedido.getSelectionModel().getSelectedItem();

		lbId.setText(Long.toString(lineapedido.getId()));
		txtCodigo.setText(lineapedido.getArticulo().getCodigoBarras());
		cmbColor.getSelectionModel().select(lineapedido.getArticulo().getColor());
		cmbTalla.getSelectionModel().select(lineapedido.getArticulo().getTalla());
		txtDetalles.setText(lineapedido.getArticulo().getDetalles());
		txtPrecio.setText(String.valueOf(lineapedido.getPrecio()));
		txtCantidad.setText(String.valueOf(lineapedido.getCantidad()));
		txtImagen.setText(lineapedido.getArticulo().getFoto());

		for (Productogenerico productogenerico : tiposList) {
			if (lineapedido.getArticulo().getProductogenerico().getId() == productogenerico.getId())
				cmbTipo.getSelectionModel().select(productogenerico);
		}

	}

	@FXML
	protected void locateFile(ActionEvent event) {

		String filePath = Util.imageChooser();

		txtImagen.setText(filePath);
	}

	private void loadCmbPedido() {
	
		pedidosList.addAll(pedidoService.findAll());
		cmbPedido.setItems(pedidosList);
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

	@FXML
	void cargarListaPedido() {

		lineasPedidoList.clear();

		lineasPedidoList.addAll(lineaPedidoService.findLineasPedido(cmbPedido.getValue().getId()));

		tablaLineasPedido.setItems(lineasPedidoList);

		String costeTotalPedido = String.valueOf(pedidoService.costeTotal(cmbPedido.getValue()));
		
		lbCostePedido.setText("Coste total del pedido selecionado: " + costeTotalPedido);

	}

	@FXML
	private void limpiarCampos() {

		lbId.setText("");
		txtCodigo.clear();
		cmbTipo.getSelectionModel().clearSelection();
		cmbColor.getSelectionModel().select("");
		cmbTalla.getSelectionModel().select("");
		txtPrecio.clear();
		txtDetalles.clear();
		txtCantidad.clear();
		txtImagen.clear();

		cargarArticulos();
	}

}
