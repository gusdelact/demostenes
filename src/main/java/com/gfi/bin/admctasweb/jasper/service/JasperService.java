package com.gfi.bin.admctasweb.jasper.service;

import java.io.Serializable;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.gfi.bin.admctasweb.jasper.FormatoReporte;

/**
 * Define los metodos a utilizar para la generacion de reportes via jasper reports 
 *  @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 *
 */
public interface JasperService {	
	
	/**
	 * Metodo encargado de compilar un jrxml, y generar un reporte PDF, Excel, CSV genera la ruta del archivo generado
	 * los campos field de llenan con un archivo bean vo o entity, los nombres de los campos deben coincidir con los field del reporte
	 * @param plantilla archivo jrxml
	 *
	 * @param formato tipo de formato generado
	 * @param lista una lista que contiene el bean que llena los fields del reporte
	 * @param pars parametros que se usan dentro de los campos parameter del reporte
	 * @return
	 */
	byte[] generarReporteBean(String plantilla, FormatoReporte formato,List<? extends Serializable> lista, HashMap<String, Object> pars);
	/**
	 * Metodo encargado de compilar un jrxml, y generar un reporte PDF, Excel, CSV genera la ruta del archivo generado
	 * este metodo llena los campos field a travez de una consulta que se encuentra dentro del mismo reporte
	 * @param plantilla
	 * @param formato
	 * @param lista
	 * @param pars
	 * @return
	 */
	byte[] generarReporteSqlJasper(String plantilla,FormatoReporte formato,HashMap<String, Object> params, Connection connection);
}
