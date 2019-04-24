package com.paraparp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import com.paraparp.util.Constantes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

@SpringBootApplication
@PropertySources({
    @PropertySource(value = "classpath:application.properties"),
    @PropertySource(value = "file:external.properties", ignoreResourceNotFound = true)
})
public class Main extends Application {

	private ConfigurableApplicationContext springContext;
	private Parent rootNode;

	public static void main(final String[] args) {
		Application.launch(args);
	}

	@Override
	public void init() throws Exception {
		springContext = SpringApplication.run(Main.class);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(Constantes.LOGIN_APP_PATH));
		fxmlLoader.setControllerFactory(springContext::getBean);
		rootNode = fxmlLoader.load();
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setScene(new Scene(rootNode));
		stage.setTitle("Gestor Almacen");
		stage.getIcons().add(new Image(Constantes.ICONO_APP_PATH));
		stage.initStyle(StageStyle.UNDECORATED);
		stage.show();
	}

	@Override
	public void stop() throws Exception {
		springContext.close();
	}

}
