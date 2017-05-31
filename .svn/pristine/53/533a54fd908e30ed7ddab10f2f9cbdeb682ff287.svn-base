package com.gfi.bin.admctasweb.catalogos.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * Servicios para los registros de Documentos.
 * @author LUGL4884
 *
 */
public interface DocumentoService {
	
	/**
	 * Guardar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	boolean guardarDocumento(DocumentoModel documento) throws ServiceException;
	
	/**
	 * Modificar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	boolean modificarDocumento(DocumentoModel documento) throws ServiceException;
	
	/**
	 * Eliminar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	boolean eliminarDocumento(DocumentoModel documento) throws ServiceException;
	
	/**
	 * Ejecuta la busqueda de un Documento por su PK
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return DocumentoModel
	 * @throws ServiceException 
	 */
	DocumentoModel buscarDocumentoPorId(String numOficio, String tipoOficio, Integer numDocto) throws ServiceException;
	
	/**
	 * Ejecuta una busqueda de Documentos relacionadas a un Oficio.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<DocumentoModel>
	 * @throws ServiceException 
	 */
	List<DocumentoModel> buscarDocumentosPorOficio(String numOficio, String tipoOficio) throws ServiceException;
	
	/**
	 * Ejecuta una busqueda de Documentos relacionadas a un Oficio.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return boolean
	 * @throws ServiceException 
	 */
	boolean existeDocumento(String numOficio, String tipoOficio, Integer numDocto) throws ServiceException;
	
	/**
	 * Obtiene el siguiente consecutivo del Documento.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	int obtenerConsecutivo(String numOficio, String tipoOficio) throws ServiceException; 
	
	/**
	 * Arma la ruta del Repositorio en la que se encuentra el Documento.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param nomArchivo
	 * @return String
	 * @throws IOException 
	 */
	String armaRutaDocumento(String numOficio, String tipoOficio, String nomDocto) throws IOException;
	
	/**
	 * A partir de los oficios recibidos, guarda documentos y archivo en ruta de acuerdo a la fecha del oficio
	 * 
	 * @param listaOficios
	 * @param multipartFile
	 * @return boolean
	 * @throws ServiceException
	 */
	boolean guardarDocumentosArchivo(List<OficioModel> listaOficios, MultipartFile multipartFile) throws ServiceException;
}