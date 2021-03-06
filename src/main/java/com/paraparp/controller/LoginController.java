package com.paraparp.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.paraparp.service.interfaces.EmpleadoService;
import com.paraparp.util.Constantes;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@Controller
public class LoginController implements Initializable {

    @FXML
    private JFXButton btnAcceso;

    @FXML
    private JFXTextField txtUser;

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private Label lbMsg;

    @FXML
    private JFXButton btnSalir;


	@Autowired
	private EmpleadoService empleadoService;

	public void initialize(URL location, ResourceBundle resources) {

	}

	@Autowired
	private ApplicationContext springContext;

	@FXML
	public void login() throws IOException {

		if (empleadoService.authenticacion(txtUser.getText(), txtPassword.getText())) {

			Stage loginStage = (Stage) btnAcceso.getScene().getWindow();
			loginStage.close();
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constantes.PRINCIPAL_APP_PATH));
			fxmlLoader.setControllerFactory(springContext::getBean);
			Parent root = fxmlLoader.load();
			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.setTitle("Gestor Almacen");
			stage.getIcons().add(new Image(Constantes.ICONO_APP_PATH));
			stage.initStyle(StageStyle.UNDECORATED);
			stage.setScene(scene);
			stage.show();
		} else {
			lbMsg.setText("Login fallido");
		}
	}
	
	@FXML
	private void salir() {
		System.exit(0);
	}

}
