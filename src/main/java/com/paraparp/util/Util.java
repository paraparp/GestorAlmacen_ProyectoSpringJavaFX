package com.paraparp.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class Util {
	
    

	public static Date LocalDateToDate(LocalDate ld) {

		if (ld==null) {
			return null;
		}
		return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}
	
	public  static LocalDate DateToLocalDate(Date date) {
		
		if (date==null) {
			return null;
		}
		return  ((java.sql.Date) date).toLocalDate();
		
	}
	
	public static void alertaInformacion (String titulo, String texto) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setContentText(texto);
		alert.showAndWait();
		
	}
	
	public static String imageChooser() {
		FileChooser chooser = new FileChooser();
		ExtensionFilter fileExtensions = new FileChooser.ExtensionFilter("Imagenes JPG, GIF, PNG o JPEG", "*.jpg",
				"*.gif", "*.png", "*.jpeg");

		chooser.getExtensionFilters().add(fileExtensions);
		String filePath = "";
		try {
			filePath = chooser.showOpenDialog(new Stage()).getCanonicalPath();
		} catch (Exception e) {
			Util.alertaInformacion("Error al seleccionar imagen", "No has seleccionado una imagen");
		}

		return filePath;
	}
	


}
