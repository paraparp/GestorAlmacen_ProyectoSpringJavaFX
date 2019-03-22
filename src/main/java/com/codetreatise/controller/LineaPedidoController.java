package com.codetreatise.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.codetreatise.modelo.entities.Articulo;
import com.codetreatise.modelo.entities.Lineapedido;
import com.codetreatise.modelo.entities.Pedido;
import com.codetreatise.modelo.entities.Productogenerico;
import com.codetreatise.service.ArticuloService;
import com.codetreatise.service.LineaPedidoService;
import com.codetreatise.service.PedidoService;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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

	@Autowired
	private ArticuloService articuloService;

	@Autowired
	private ProductoGenericoService prodGenService;

	@Autowired
	private PedidoService pedidoService;
	@Autowired
	private LineaPedidoService lineaPedidoService;

	private ObservableList<Lineapedido> lineasPedidoList = FXCollections.observableArrayList();

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

		Articulo a = new Articulo();
		Alert alert = new Alert(AlertType.INFORMATION);

		if (lbId.getText() == null || lbId.getText() == "") {

			saveNew(a, alert);

		} else {
			saveUpdt(a, alert);
		}
		alert.showAndWait();
		// limpiarCampos();

	}

	private void saveNew(Articulo a, Alert alert) {

		Lineapedido lp = new Lineapedido();

		lp.setPedido(cmbPedido.getValue());
		lp.setCantidad(Integer.parseInt(txtCantidad.getText()));
		lp.setPrecio(new BigDecimal(txtPrecio.getText()));

		a.setCodigoBarras(txtCodigo.getText());

		a.setFoto(txtImagen.getText());
		System.out.println("1");

		if (txtCodigo.getText().isEmpty()) {
			alert.setContentText("Completa al menos el campo  Codigo");
			System.out.println("2");
		} else {
			System.out.println("3");
			// Se busca el codigo de barras y si no existe se crea un articulo nuevo
			if (articuloService.findByCodigoBarras(a.getCodigoBarras()).getId() != null) {

				System.out.println("5");
				a = articuloService.findByCodigoBarras(a.getCodigoBarras());
				a.actualizarStock(Integer.parseInt(txtCantidad.getText()));

			} else {

				a.setCodigoBarras(txtCodigo.getText());
				a.setProductogenerico(cmbTipo.getValue());
				a.setColor(cmbColor.getValue());
				a.setTalla(cmbTalla.getValue());
				a.setStock(Integer.parseInt(txtCantidad.getText()));
				System.out.println("4");
				System.out.println(a);

			}
			System.out.println("6");
			Articulo newA = articuloService.save(a);
			lp.setArticulo(a);
			Lineapedido newLp = lineaPedidoService.save(lp);

			alert.setContentText("Añadido a linea pedido: " + a.getProductogenerico().getNombre() + ". Talla: "
					+ a.getTalla() + ".Color: " + a.getColor() + ". \n=> Cantidad: " + newLp.getCantidad()
					+ " (Nuevo stock total: " + a.getStock() + ")");
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
		a.setPrecioVenta(new BigDecimal(txtPrecio.getText()));
		a.setFoto(txtImagen.getText());
		a.setDetalles(txtDetalles.getText());
		Articulo udtA = articuloService.save(a);

		alert.setContentText("Editado pedido con codigo: " + udtA.getCodigoBarras());
	}

	@FXML
	void seleccionarArticulo(MouseEvent event) {

		Lineapedido lineapedido = tablaLineasPedido.getSelectionModel().getSelectedItem();

		lbId.setText(Long.toString(lineapedido.getId()));
		cmbTipo.getSelectionModel().select(lineapedido.getArticulo().getProductogenerico());
		txtCodigo.setText(lineapedido.getArticulo().getCodigoBarras());
		cmbColor.getSelectionModel().select(lineapedido.getArticulo().getColor());
		cmbTalla.getSelectionModel().select(lineapedido.getArticulo().getTalla());
		txtPrecio.setText(String.valueOf(lineapedido.getArticulo().getPrecioVenta()));
		txtDetalles.setText(lineapedido.getArticulo().getDetalles());
		txtCantidad.setText(String.valueOf(lineapedido.getCantidad()));
		txtImagen.setText(lineapedido.getArticulo().getFoto());

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

	private void loadCmbPedido() {

		ObservableList<Pedido> pedidos = FXCollections.observableArrayList();
		pedidos.addAll(pedidoService.findAll());
		cmbPedido.setItems(pedidos);

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

	@FXML
	void cargarListaPedido() {

		lineasPedidoList.clear();

		lineasPedidoList.addAll(lineaPedidoService.findLineasPedido(cmbPedido.getValue().getId()));

		tablaLineasPedido.setItems(lineasPedidoList);
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
