/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;


/**
 * @author LUGL4884
 * Clase de Servicios para Oficio
 *
 */
public interface OficioService {
	
	/**
	 * Servicio que guarda un registro de Oficio.
	 * @param oficio
	 * @return boolean
	 */
	public boolean guardarOficio(OficioModel oficio) throws ServiceException;
	
	/**
	 * Servicio la actualiza un registro de Oficio.
	 * @param oficio
	 * @return boolean
	 */
	public boolean modificarOficio(OficioModel oficio) throws ServiceException;
	
	/**
	 * Servicio que Elimina un registro de Oficio.
	 * @param oficio
	 * @return boolean
	 */
	public boolean eliminarOficio(OficioModel oficio) throws ServiceException;
	
	/**
	 * Servicio que Busca un registro de Oficio deacuerdo a su Llave Primaria.
	 * @param numOficio
	 * @param tipoOficio
	 * @return OficioModel
	 */
	public OficioModel buscarOficioPorLlave(String numOficio, String tipoOficio) throws ServiceException;
	
	/**
	 * Servicio que actualiza el Estatus de Seguimiento del Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @param cveEstatus
	 * @return boolean
	 */
	public boolean modificarSeguimientoOficio(String numOficio, String tipoOficio, String cveEstatus, String cveEstatusPend) throws ServiceException; 

	/**
	 * Método que permite recuperar una lista de oficios positivos (casos especiales)
	 * @author MUDF4038 - Fernando Munive Dorantes
	 * @param CasosEspecialesListModel
	 * @return List<CasosEspecialesModel>
	 * @throws ServiceException
	 */
	List<CasosEspecialesModel> buscarOficiosCasosEspeciales (CasosEspecialesListModel parametros) throws ServiceException;

	/**
	 * Actualiza la situación de un oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @param situacion
	 * @return
	 * @throws ServiceException
	 */
	boolean modificarSituacionOficio(String numOficio, String tipoOficio, String situacion) throws ServiceException;
	
	/**
	 * M&eacute;todo que permite generar el reporte de Casos Especiales
	 * 
	 *  @author MUDF4038 - Fernando Munive Dorantes
	 * 
	 * @param String where
	 * @param String nombrePlantilla
	 * @param String pathLogo
	 * @param String usuarioSesion
	 * @return byte[]
	 */
	byte[] generarReporteCasosEspeciales(CasosEspecialesListModel parametros, String nombrePlantilla, String pathLogo, String usuarioSesion, FormatoReporte formato) throws ServiceException;
	
}
