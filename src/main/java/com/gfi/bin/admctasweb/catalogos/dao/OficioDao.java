/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.Date;
import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;

/**
 * Interfaz DAO para el manejo de Oficios.
 * @author LUGL4884
 *
 */
public interface OficioDao {
	
	/**
	 * Realiza el guardado de un registro de Oficio en la BD.
	 * @param oficio
	 * @return boolean
	 */
	public boolean guardarOficio(OficioModel oficio) throws DAOException;
	
	/**
	 * Realiza la busqueda de la clave de Caracter de un registro de Oficio en la BD.
	 * @param oficio
	 * @return boolean
	 */
	public boolean guardarCaracter(OficioModel oficio) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Oficio en la BD.
	 * @param oficio
	 * @return boolean
	 */
	public boolean modificarOficio(OficioModel oficio) throws DAOException;
	
	/**
	 * Realiza el guardado de un registro de Oficio en la BD.
	 * @param oficio
	 * @return boolean
	 */
	public boolean eliminarOficio(OficioModel oficio) throws DAOException;
	
	/**
	 * Realiza la consulta de un Oficio por su PK en la BD.
	 * @param numOficio Numero del Oficio a Buscar
	 * @param tipoOficio Tipo del Oficio a Buscar
	 * @return OficioModel
	 */
	public OficioModel buscarOficioPorLlave(String numOficio, String tipoOficio) throws DAOException;
	
	/**
	 * Servicio que actualiza el Estatus de Seguimiento del Oficio.
	 * @param numOficio
	 * @param tipoOficio
	 * @param cveEstatus
	 * @return boolean
	 */
	public boolean modificarSeguimientoOficio(String numOficio, String tipoOficio, String cveEstatus, String cveEstatusPend) throws DAOException;

	/**
	 * Método que permite recuperar una lista de oficios positivos (casos especiales)
	 * @author MUDF4038 - Fernando Munive Dorantes
	 * @param CasosEspecialesListModel
	 * @return List<CasosEspecialesModel>
	 * @throws DAOException
	 */
	List<CasosEspecialesModel> buscarOficiosCasosEspeciales (CasosEspecialesListModel parametros) throws DAOException;
	
	/**
	 * Actualiza la situación de un oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @param cveEstatus
	 * @return
	 * @throws DAOException
	 */
	boolean modificarSituacionOficio(String numOficio, String tipoOficio, String situacion) throws DAOException;
	
	/**
	 * Busca los oficicios que concuerden con los criterios de busqueda,
	 * consulta que se ocupar para la generacion del archivo de la cnbv
	 * @author Manuel Reyes
	 * @param parametros
	 * @return
	 */
	List<OficioCnbvModel> buscarArhivoCnbvModel(ArhivoCnbvModel parametros);

	/**
	 * Actualiza los oficion a estatus EN, se utiliza cuando se crea el archivo para la CNBV
	 * @param oficioCnbvModel
	 * @return
	 */
	boolean updateArchivotoCnbv(Date fechaEnvio, String cveUsuEnvio,
			String txNomArch, String numOficio, String tipoOficio);
	
	/**
	 * Busca todos los oficios que tengan asociado el nombre de archivo de texto que llega como parámetro
	 * 
	 * @param nombreArchivo
	 * @return List<OficioModel>
	 * @throws DAOException
	 */
	List<OficioModel> buscarOficiosPorNombreArchivo(String nombreArchivo) throws DAOException;
	
	/**
	 * Modifica el nombre del acuse, se asigna la cadena del parámetro acuse
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param acuse
	 * @param tieneAcuse
	 * @return boolean
	 * @throws DAOException
	 */
	boolean modificarAcuseOficio(String numOficio, String tipoOficio, String acuse, String tieneAcuse) throws DAOException;
}