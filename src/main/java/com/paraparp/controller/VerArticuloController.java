package com.paraparp.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.paraparp.modelo.entities.Articulo;
import com.paraparp.modelo.entities.Lineapedido;
import com.paraparp.service.ArticuloService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

@Controller
public class VerArticuloController implements Initializable {

	@FXML
	private Label lbNombre;

	@FXML
	private Label lbTipo;

	@FXML
	private Label lbColor;

	@FXML
	private Label lbTalla;

	@FXML
	private Label lbStock;

	@FXML
	private Label lbDetalles;

	@FXML
	private Label lbCodigo;

	@FXML
	private ImageView imgArticulo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void getArticulo(Articulo articulo) {

		lbNombre.setText(articulo.getProductogenerico().getNombre());
		lbTipo.setText(articulo.getProductogenerico().getCategoria());
		lbColor.setText(articulo.getColor());
		lbTalla.setText(articulo.getTalla());
		lbStock.setText(String.valueOf(articulo.getStock()));
		lbDetalles.setText(articulo.getDetalles());
		lbCodigo.setText(articulo.getCodigoBarras());
		imgArticulo.setImage(new Image("file:" + articulo.getFoto()));

	}

}
