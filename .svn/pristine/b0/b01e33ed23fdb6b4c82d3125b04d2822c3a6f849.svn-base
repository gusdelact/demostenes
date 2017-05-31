package com.gfi.bin.admctasweb.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Clase que contiene metodos para el manejo de fechas
 * 
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class DateUtil {

	/**
	 * Obtiene el año de una fecha, usa calendar para esto
	 * @param fecha
	 * @return
	 */
	public static int obtenerAnio(Date fecha) {
		Calendar gc = new GregorianCalendar();
		gc.setTime(fecha);
		int anio = gc.get(Calendar.YEAR);
		return anio;
	}
	
	/**
	 * Metodo que puede obtener cualquier dato de una fecha haciendo uso de un format
	 * Ej. formato='yyyy' obtiene el año de la fecha ingresada
	 * Ej. formato='MM' obtiene el mes de la fecha ingresada
	 * Ej. formato='dd' obtiene el mes de la fecha ingresada
	 * @param date
	 * @param formato
	 * @return el formato de tipo int
	 */
	public static int obtenerDatoFecha(Date date, String formato) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return Integer.parseInt(dateFormat.format(date));
	}
	
	/**
	 * Metodo que puede obtener cualquier dato de una fecha haciendo uso de un format
	 * Ej. formato='yyyy' obtiene el año de la fecha ingresada
	 * Ej. formato='MM' obtiene el mes de la fecha ingresada
	 * Ej. formato='dd' obtiene el mes de la fecha ingresada
	 * @param date
	 * @param formato
	 * @return el formato de tipo String
	 */
	public static String obtenerDatoFechaString(Date date, String formato) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(formato);
		return dateFormat.format(date);
	}
	
	/**
	 * Metodo que obtiene la hora de una fecha	en formato de 12 horas indicando am o pm
	 * @return hora formato hh:mm a
	 */
	public static String getHoraFecha(Date fecha) {
		SimpleDateFormat formateador = new SimpleDateFormat("hh:mm a");
		return formateador.format(fecha);
	}
	

}
