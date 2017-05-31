package com.gfi.bin.admctasweb.procesoautomatico.service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface GestionArchivosZipService {
	
	/**
	 * Metodo que descomprime archivos de ruta definida
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean descomprimirArchivos() throws ServiceException;
	
	/**
	 * Método que elimina los archivos comprimidos
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean eliminarArchivos() throws ServiceException;
	
	/**
	 * Descarga los archivos del servidor sftp al servidor donde está alojada la aplicación
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean descargarArchivos() throws ServiceException;
}