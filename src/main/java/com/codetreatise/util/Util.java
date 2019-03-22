package com.codetreatise.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import com.codetreatise.Main;

public class Util {
	
    

	public static Date LocalDateToDate(LocalDate ld) {

		return Date.from(ld.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

	}
	
	public  static LocalDate DateToLocalDate(Date date) {
		return  ((java.sql.Date) date).toLocalDate();
		
	}
	
	

}
