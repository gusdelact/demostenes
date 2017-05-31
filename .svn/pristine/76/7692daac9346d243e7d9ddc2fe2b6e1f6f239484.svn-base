package com.gfi.bin.admctasweb.mail.service;

import java.io.IOException;
import java.util.Map;

import org.springframework.core.io.Resource;

import com.gfi.bin.admctasweb.mail.model.DatosEmail;

public interface EmailMessageProvider {
	
	/**
	 * Obtiene el subject del email
	 * @return
	 */
	String getSubject();
	
	/**
	 * Obtiene el cuerpo del email
	 * @return
	 */
	String getBody();
	
	/**
	 * Mapa que contiene los recursos incrustados en el reporte
	 * @return
	 */
	Map<String, Resource> getInlineFiles();
	
	/**
	 * Llena los valores que se ocupan dentro de la plantilla velocity
	 * @return
	 */
	void fillblanks(String templateVelocity,  DatosEmail datosEmail,Map<String,Object> model) throws IOException;
	
	/**
	 * Metodo que se ocupa para sobreescribir el subject del correo, el subject inicial se puede definir el archivo xml del email
	 * @param subject
	 * @throws IOException
	 */
	void changeSubject(String subject) throws IOException;

}