package com.gfi.bin.admctasweb.operativos.service;

import org.springframework.web.multipart.MultipartFile;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

public interface OficioRespuestaNegativaService {
	/**
	 * Realiza la actualización de todos los oficios que contengan el mismo archivo TXT, se les actualiza 
	 * el numero de folio, se registra un nuevo documento para cada oficio y se agrega el archivo seleccionado
	 * al repositorio de acuerdo a la fecha de cada oficio
	 * 
	 * @param nombreArchivo
	 * @param nombreAcuse
	 * @param multipartFile
	 * @return Boolean
	 * @throws ServiceException
	 */
	Boolean modificarOficiosRespuestasNegativas(String nombreArchivo, String nombreAcuse, MultipartFile multipartFile) throws ServiceException;
}