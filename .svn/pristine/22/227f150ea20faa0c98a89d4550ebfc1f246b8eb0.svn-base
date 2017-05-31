package com.gfi.bin.admctasweb.catalogos.dao;

import java.util.List;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * 
 * @author ESS3ESPP
 *
 */
public interface DocumentoDAO {
	
	/**
	 * Guardar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	boolean guardarDocumento(DocumentoModel documento) throws DAOException;
	
	/**
	 * Modificar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	boolean modificarDocumento(DocumentoModel documento) throws DAOException;
	
	/**
	 * Eliminar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	boolean eliminarDocumento(DocumentoModel documento) throws DAOException;
	
	/**
	 * Ejecuta la busqueda de un Documento por su PK
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return DocumentoModel
	 * @throws DAOException 
	 */
	DocumentoModel buscarDocumentoPorId(String numOficio, String tipoOficio, Integer numDocto) throws DAOException;
	
	/**
	 * Ejecuta una busqueda de Documentos relacionadas a un Oficio.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<DocumentoModel>
	 * @throws ServiceException 
	 */
	List<DocumentoModel> buscarDocumentosPorOficio(String numOficio, String tipoOficio) throws DAOException;
	
	/**
	 * Valida que exista un Documento en base a su PK.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return int
	 * @throws ServiceException 
	 */
	int existeDocumento(String numOficio, String tipoOficio, Integer numDocto) throws DAOException;
	
	/**
	 * Obtiene el siguiente consecutivo del Documento.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerConsecutivo(String numOficio, String tipoOficio) throws DAOException;
}