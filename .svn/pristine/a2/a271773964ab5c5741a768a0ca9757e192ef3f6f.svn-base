package com.gfi.bin.admctasweb.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author ESS3ESPP
 *
 */
public final class LectorPropertiesUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LectorPropertiesUtil.class);
	
	/**
	 * Método que obtiene el valor de una propiedad contenida en el archivo a leer
	 * 
	 * @param nombreArchivoPropiedades
	 * @param propiedad
	 * @return String
	 */
	public static String obtenerValoresEtiqueta(String nombreArchivoPropiedades, String propiedad){
		LOGGER.debug("Comienza ejecución de obtenerValoresEtiqueta");
		String valoresRetornar = null;
		Properties propiedades = new Properties();
		
		try{
			InputStream inputStream = LectorPropertiesUtil.class.getClassLoader().getResourceAsStream(nombreArchivoPropiedades);
			propiedades.load(inputStream);
			valoresRetornar = propiedades.getProperty(propiedad.trim());
			
		}catch(FileNotFoundException fileNotFoundException){
			LOGGER.error("Error, no se encuentra archivo de propiedades: "+fileNotFoundException.getLocalizedMessage());
			fileNotFoundException.printStackTrace();
		}catch(IOException ioException){
			LOGGER.error("Error al leer archivo de propiedades: "+ioException.getLocalizedMessage());
			ioException.printStackTrace();
		}
		LOGGER.debug("Termina ejecución de obtenerValoresEtiqueta");
		
		return valoresRetornar;
	}
	
	/**
	 * Obtiene el archivo de propiedades que se indique
	 * @param nombreArchivo
	 * @return
	 */
	public static final Properties obtenerArchivoPropiedades(String nombreArchivo)
	{
		Properties propiedades = new Properties();		
		InputStream inputStream = LectorPropertiesUtil.class.getClassLoader().getResourceAsStream(nombreArchivo);
		try {
			propiedades.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Error al leer archivo de propiedades: " + e.getLocalizedMessage());
		}
		finally
		{
			if(inputStream != null)
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return propiedades;
	}
	
}