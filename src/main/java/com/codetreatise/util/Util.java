package com.codetreatise.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.codetreatise.Main;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Util {
	
    

	public static Date LocalDateToDate(LocalDate ld) {

		return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}
	
	public  static LocalDate DateToLocalDate(Date date) {
		return  ((java.sql.Date) date).toLocalDate();
		
	}
	
	public static void alertaInformacion (String titulo, String texto) {
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(titulo);
		alert.setContentText(texto);
		alert.showAndWait();
		
	}
	
	

}
