package com.gfi.bin.admctasweb.jasper;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ReportDataSource implements JRDataSource {

	/**
	 * Log de mensajes
	 */
	private static final Log logger = LogFactory.getLog(ReportDataSource.class);
	
	/**
	 * Iterador de los objetos
	 */
	private Iterator<? extends Serializable> lista;
	
	/**
	 * Objeto que sera mostrado en el reporte
	 */
	private Serializable data;
	
	/**
	 * @param lista Lista de objetos que seran mostrados en el reporte
	 */
	public ReportDataSource(List<? extends Serializable> lista) {
		this.lista = lista.iterator();
	}
	
	/**
	 * Obtiene el valor del campo en el reporte
	 */
	public Object getFieldValue(JRField field) throws JRException {
		try {
			Field fieldData = data.getClass().getDeclaredField(field.getName());
			fieldData.setAccessible(true);
			Object result = fieldData.get(data);
			logger.debug("Campo: " + field.getName() + "=" + result);
			return result;
		}
		catch(Exception err) {
			//logger.error("Error: ", err);
			return null;
		}
	}
	
	/**
	 * Verifica la existencia de mas objetos
	 */
	public boolean next() throws JRException {
		boolean next = lista.hasNext();
		logger.debug("Existen mas objetos: " + next);
		if(next) {
			data = lista.next();
		}
		return next;
	}
	
}
