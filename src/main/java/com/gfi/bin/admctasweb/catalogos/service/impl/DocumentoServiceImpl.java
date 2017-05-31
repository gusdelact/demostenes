package com.gfi.bin.admctasweb.catalogos.service.impl;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gfi.bin.admctasweb.catalogos.dao.DocumentoDAO;
import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.DocumentoService;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;
import com.gfi.bin.admctasweb.util.SFTPUtil;
import com.gfi.bin.admctasweb.util.Util;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Servicios para el manejo de Documentos.
 * @author LUGL4884
 *
 */
@Service
public class DocumentoServiceImpl implements DocumentoService {
	
	final Logger LOGGER = LoggerFactory.getLogger(DocumentoServiceImpl.class);
	
	@Autowired
	private DocumentoDAO documentoDAO;
	
	@Autowired
	private OficioService oficioService;
	
	private static final String SFTP_CHANNEL = "sftp";
	
	private static final String DIAGONAL = "/";
	
	/**
	 * Guardar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean guardarDocumento(DocumentoModel documento) throws ServiceException{
		Boolean result = false;
		
		try{
			result = this.documentoDAO.guardarDocumento(documento);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}
	
	/**
	 * Modificar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean modificarDocumento(DocumentoModel documento) throws ServiceException{
		Boolean result = false;
		
		try{
			result = this.documentoDAO.modificarDocumento(documento);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}
	
	/**
	 * Eliminar documento asociado
	 * 
	 * @param documento
	 * @return boolean
	 */
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean eliminarDocumento(DocumentoModel documento) throws ServiceException{
		Boolean result = false;
		
		try{
			result = this.documentoDAO.eliminarDocumento(documento);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}
	
	/**
	 * Ejecuta la busqueda de un Documento por su PK
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return DocumentoModel
	 * @throws ServiceException 
	 */
	public DocumentoModel buscarDocumentoPorId(String numOficio, String tipoOficio, Integer numDocto) throws ServiceException {
		try{
			return documentoDAO.buscarDocumentoPorId(numOficio, tipoOficio, numDocto);
		}catch(DAOException e){
			LOGGER.error(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Ejecuta una busqueda de Documentos relacionadas a un Oficio.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @return List<DocumentoModel>
	 * @throws ServiceException 
	 */
	public List<DocumentoModel> buscarDocumentosPorOficio(String numOficio,
			String tipoOficio) throws ServiceException {
		try{
			return documentoDAO.buscarDocumentosPorOficio(numOficio, tipoOficio);
		}catch(DAOException e){
			LOGGER.error(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Ejecuta una busqueda de Documentos relacionadas a un Oficio.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param numDocto
	 * @return boolean
	 * @throws ServiceException 
	 */
	public boolean existeDocumento(String numOficio, String tipoOficio,
			Integer numDocto) throws ServiceException {
		Boolean existe = false;
		
		try {
			if(this.documentoDAO.existeDocumento(numOficio, tipoOficio, numDocto) > 0){
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		return existe;
	}
	
	/**
	 * Obtiene el siguiente consecutivo del Documento.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	public int obtenerConsecutivo(String numOficio, String tipoOficio) throws ServiceException {
		try {
			return this.documentoDAO.obtenerConsecutivo(numOficio, tipoOficio);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Arma la ruta del Repositorio en la que se encuentra el Documento.
	 * 
	 * @param numOficio
	 * @param tipoOficio
	 * @param nomArchivo
	 * @return String
	 * @throws IOException 
	 */
	public String armaRutaDocumento(String numOficio, String tipoOficio, String nomDocto) throws IOException {
		String ruta = "";
		String fhOficio = "";
		OficioModel oficio = new OficioModel();
		
		//Consultamos el Oficio para obtener su fecha de recepcion.
		try {
			oficio = this.oficioService.buscarOficioPorLlave(numOficio, tipoOficio);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		fhOficio = Util.dateToString(oficio.getFhOficio(), Constantes.FORMATO_DDMMYYYY);
		
		//Obtenemos la carpeta donde se guardo el documento.
		String nomCarpeta = Util.obtenerCarpetaDoc(fhOficio);
		
		//Leemos la ruta del Repositorio.
		ruta = LectorPropertiesUtil.obtenerValoresEtiqueta(
				Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES,
				Constantes.KEY_SFTP_RUTA_REPOSITORIO_FINAL);
		
		if (StringUtils.isNotBlank(nomDocto)){
			ruta = ruta + DIAGONAL + nomCarpeta + DIAGONAL + nomDocto;
		}else {
			ruta = ruta + DIAGONAL + nomCarpeta;
		}

		return ruta;
	}
	
	public String armaRutaDocumento(Date fechaOficio) throws IOException {
		String ruta = "";
		String fhOficio = "";
		
		fhOficio = Util.dateToString(fechaOficio, Constantes.FORMATO_DDMMYYYY);
		
		String nomCarpeta = Util.obtenerCarpetaDoc(fhOficio);
		
		//Leemos la ruta del Repositorio.
		ruta = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_SFTP_RUTA_REPOSITORIO_FINAL);
		
		ruta = ruta + DIAGONAL + nomCarpeta;
		
		return ruta;
	}
	
	/**
	 * A partir de los oficios recibidos, guarda documentos y archivo en ruta de acuerdo a la fecha del oficio
	 * 
	 * @param listaOficios
	 * @param multipartFile
	 * @return boolean
	 * @throws ServiceException
	 */
	public boolean guardarDocumentosArchivo(List<OficioModel> listaOficios, MultipartFile multipartFile) throws ServiceException{
		Boolean result = true;
		String nombreArchivo = multipartFile.getOriginalFilename();
		Collection<Date> fechasOficiosNoDuplicadas = new HashSet<Date>();
		
		LOGGER.debug("Nombre del archivo a guardar: "+nombreArchivo);
		
		for(OficioModel oficio : listaOficios){
			try{
				Date fechaOficio = oficio.getFhOficio();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(fechaOficio);
				calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);//Se le asigna el dia 1 para que sean iguales
				
				
				fechasOficiosNoDuplicadas.add(calendar.getTime());
				
				int idNuevoDocumento =  obtenerConsecutivo(oficio.getNumOficio(), oficio.getTipoOficio());
				
				DocumentoModel documento = new DocumentoModel();
				documento.setNumOficio(oficio.getNumOficio());
				documento.setTipoOficio(oficio.getTipoOficio());
				documento.setNumDocto(idNuevoDocumento);
				documento.setCveDocto(Constantes.CLAVE_DOCUMENTO_ACUSE);
				documento.setNomDocto(nombreArchivo);
				documento.setFhAlta(new Date());
				documento.setCveUsuAlta(Util.usuarioSesion());
				
				result = documentoDAO.guardarDocumento(documento);
				
				if(result == false)
					return false;
				
			}catch(DAOException e) {
				result = false;
				throw new ServiceException(e);
			}
		}
		
		try{
			LOGGER.debug("Se va a guardar archivo en ruta remota...");
			guardarArchivo(fechasOficiosNoDuplicadas, multipartFile);
			
		}catch(IOException ioException){
			LOGGER.debug("Error al guardar archivo en ruta remota: "+ioException.getLocalizedMessage());
			throw new ServiceException(ioException);
		}catch(ServiceException serviceException){
			LOGGER.debug("Error al guardar archivo en ruta remota: "+serviceException.getLocalizedMessage());
			throw new ServiceException(serviceException);
		}
		
		return result;
	}
	
	private Boolean guardarArchivo(Collection<Date> fechasOficiosNoDuplicadas, MultipartFile multipartFile) throws IOException, ServiceException{
		Boolean resultado = true;
		Session session = null;
		ChannelSftp sftpChannel = null;
		String rutaDocumento = null;
		String nombreOriginalArchivo = multipartFile.getOriginalFilename();
		
		try{
			//Conexion a servidor SFTP
			session = SFTPUtil.obtenerSesion();
			if(session == null){
				resultado = false;
				throw new ServiceException("No se pudo obtener una sesión SFTP");
			}

			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			
			LOGGER.debug("Numero de fechasOficiosNoDuplicadas: "+fechasOficiosNoDuplicadas.size());
			for(Date fechaOficio : fechasOficiosNoDuplicadas){
				
				//Armamos la ruta del documento
				rutaDocumento = armaRutaDocumento(fechaOficio);
				LOGGER.debug("Ruta donde se depositará archivo: "+rutaDocumento);
				
				//Creamos la ruta remota
				try{
					LOGGER.debug("Se va a crear ruta siguiente"+rutaDocumento);
					sftpChannel.mkdir(rutaDocumento);
					
				}catch(Exception e){
					LOGGER.debug("Error al crear directorio en servidor SFTP: "+e.getLocalizedMessage());
				}
				
				//Nos ubicamos en ruta remota
				sftpChannel.cd(rutaDocumento);
				
				//Copiamos el archivo a la ruta remota
				sftpChannel.put(multipartFile.getInputStream(), rutaDocumento+DIAGONAL+nombreOriginalArchivo);
			}
		} catch (JSchException e) {
			resultado = false;
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (SftpException e) {
			resultado = false;
			e.printStackTrace();
			throw new ServiceException(e);
		} catch (Exception e) {
			resultado = false;
			e.printStackTrace();
			throw new ServiceException(e);
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();

			if(session != null)
				session.disconnect();
		}
		
		return resultado;
	}
}