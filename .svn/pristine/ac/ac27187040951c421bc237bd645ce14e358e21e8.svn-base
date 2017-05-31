package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientoDescargadoModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientosDescargadosModel;
import com.gfi.bin.admctasweb.procesoautomatico.service.RespaldoCargaAutomatica;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.FileUtil;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;
import com.gfi.bin.admctasweb.util.SFTPUtil;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.bin.admctasweb.util.XmlUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class RespaldoCargaAutomaticaImpl implements RespaldoCargaAutomatica {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RespaldoCargaAutomaticaImpl.class);
	
	private static final String SFTP_RUTA_REPOSITORIO = "sftp.ruta.repositorio.final";
	
	private static final String SFTP_CHANNEL = "sftp";
	
	private ChannelSftp sftpChannel = null;
	
	private static final String DIAGONAL = "/";
	
	/**
	 * Metodo que copia los archivos al repositorio indicado, después elimina cada uno de ellos.
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	@Override
	public void respaldarArchivosXML() throws ServiceException {
		
		String rutaDescomprimidos = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_DESCOMPRIMIDOS);
		String rutaRepositorio = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_REPOSITORIO_FINAL);
		String rutaRepositorioSFTP = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, SFTP_RUTA_REPOSITORIO);
		
		respaldarArchivos(rutaDescomprimidos, rutaRepositorio);
		
		//Transferir archivos a Sevidor SFTP
		transferirArchivosRepositorio(rutaRepositorio, rutaRepositorioSFTP);
		
		//Se eliminan todos los archivos
		eliminarArchivosXMLs(rutaDescomprimidos);
		//Se tienen que eliminar los archivos de la rutaRepositorio
		eliminarArchivosXMLs(rutaRepositorio);
	}
	
	/**
	 * Respalda los archivos XML, DOC y TIFF que encuentre en el directorio de descomprimidos, se mueven todos aquellos archivos que
	 * se encuentren en los archivos XML de RequerimientosDescargados (Todos), se mueven a carpetas de acuerdo al tag FECHA_PUBLICACION
	 * 
	 * @param rutaFuente
	 * @param rutaDestino
	 */
	private void respaldarArchivos(String rutaFuente, String rutaDestino){
		LOGGER.debug("Ruta fuente: "+rutaFuente);
		LOGGER.debug("Ruta destino: "+rutaDestino);
		//Leer todos los archivos, cuando encuentre XML, intentar parsear, al encontrar REQDESCARGADOS, entonces hacer todo
		
		File folderComprimidos = new File(rutaFuente);
		File[] listaArchivosDirectorio = folderComprimidos.listFiles();
		String nombreArchivoActual = null;
		Boolean xmlCorrectoHallado = false;
		RequerimientosDescargadosModel listaRequerimientos = null;
		
		for(int i=0; i<listaArchivosDirectorio.length; i++){
			File archivoActual = listaArchivosDirectorio[i];
			LOGGER.debug("Archivo actual es: "+archivoActual);
			
			if(archivoActual.isFile()){
				nombreArchivoActual = archivoActual.getName();
				
				if (!xmlCorrectoHallado && (nombreArchivoActual.endsWith(Constantes.EXTENSION_XML.toLowerCase()) || nombreArchivoActual.endsWith(Constantes.EXTENSION_XML.toUpperCase()))){
					//Parsear XML
					listaRequerimientos = XmlUtil.xmlToRequerimientosDescargados(archivoActual.getAbsolutePath());
					Calendar calFechaPubl = Calendar.getInstance();
					
					if(listaRequerimientos != null){
						xmlCorrectoHallado = true;
						for(RequerimientoDescargadoModel requerimiento : listaRequerimientos.getListaRequerimientosDescargados()){
							Date fechaPublicacion = requerimiento.getFechaPublicacion();
							calFechaPubl.setTime(fechaPublicacion);
							
							LOGGER.debug("FechaPublicacion: "+requerimiento.getFechaPublicacion());
							String carpetaDestinoRespaldo = String.valueOf(calFechaPubl.get(Calendar.YEAR)) + Util.obtenerMesDosDigitos(calFechaPubl.get(Calendar.MONTH)+1);
							
							String rutaDesCompleta = rutaDestino + File.separator +carpetaDestinoRespaldo;
							if(requerimiento.getXmlFileName() != null && !"".equals(requerimiento.getXmlFileName().trim())){
								File archivoXml = new File(rutaFuente + File.separator + requerimiento.getXmlFileName().trim());
								copiarARepositorio(archivoXml, rutaDesCompleta);
							}
							
							String archivoOficio = requerimiento.getOficioFileName().trim();
							File archivoDoc = new File(rutaFuente + File.separator + archivoOficio);
							copiarARepositorio(archivoDoc, rutaDesCompleta);
							
							if(requerimiento.getRequerimientoFileName().trim()!=null){
								String nombreArchivoTIF = requerimiento.getRequerimientoFileName().trim();
								nombreArchivoTIF = nombreArchivoTIF.substring(0, nombreArchivoTIF.lastIndexOf('.'));//Quitando el .enc del final
								
								//Concatenar nombre de oficio
								File archivoTif = new File(rutaFuente + File.separator + archivoOficio + Constantes.GUION_BAJO +nombreArchivoTIF);
								copiarARepositorio(archivoTif, rutaDesCompleta);
							}
							
							LOGGER.debug("\n\n\n");
						}
					}
				}//Es XML
			}//Es archivo
			else if(archivoActual.isDirectory()){
				LOGGER.debug("Archivo actual: "+archivoActual+" es directorio...");
				respaldarArchivos(rutaFuente+File.separator+archivoActual.getName(), rutaDestino);
			}//Es directorio
		}
	}
	
	private void transferirArchivosRepositorio(String rutaRepositorio, String rutaRepositorioSFTP){
		LOGGER.debug("Se van a transferir archivos al repositorio en el servidor SFTP");
		Session session = null;
		
		try {
			//Conexion a servidor SFTP
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new ServiceException("No se pudo obtener una sesión SFTP");
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			
			transferirArchivosRepositorioSFTP(rutaRepositorio, rutaRepositorioSFTP);
			
		} catch (JSchException e) {
			e.printStackTrace();
		} catch (SftpException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			
			if(session != null)
				session.disconnect();
		}
	}
	
	private void transferirArchivosRepositorioSFTP(String rutaRepositorio, String rutaRepositorioSFTP) throws SftpException{
		LOGGER.debug("Metodo transferirArchivosRepositorioSFTP");
		
		File folderRepositorioLocal = new File(rutaRepositorio);
		File[] listaRepositorioLocal = folderRepositorioLocal.listFiles();
		String nombreArchivoActual = null;
		
		for (int i=0; i<listaRepositorioLocal.length; i++){
			File archivoActual = listaRepositorioLocal[i];
			LOGGER.debug("Archivo actual es: "+archivoActual);
			
			if(archivoActual.isFile()){
				nombreArchivoActual = archivoActual.getName();
				LOGGER.debug("Nombre archivo actual: "+nombreArchivoActual);
				if (nombreArchivoActual.toLowerCase().endsWith(Constantes.EXTENSION_DOC_DOC.toLowerCase()) ||
					nombreArchivoActual.toLowerCase().endsWith(Constantes.EXTENSION_DOC_TIF.toLowerCase()) ||
					nombreArchivoActual.toLowerCase().endsWith(Constantes.EXTENSION_DOC_XML.toLowerCase()) ||
					nombreArchivoActual.toLowerCase().endsWith(Constantes.EXTENSION_DOC_PDF.toLowerCase())){
					
					LOGGER.debug("Se va a transferir(DOC, TIFF, XML, PDF): "+rutaRepositorio+File.separator+archivoActual.getName()+
							", a: "+rutaRepositorioSFTP);
					sftpChannel.put(archivoActual.getAbsolutePath(), rutaRepositorioSFTP);
				}
			}//Es archivo
			else if(archivoActual.isDirectory()){
				LOGGER.debug("Archivo actual: "+archivoActual+" es directorio...");
				
				SftpATTRS attrs = null;
				try{
					attrs = sftpChannel.stat(rutaRepositorioSFTP+DIAGONAL+archivoActual.getName());
				}catch(Exception e){
					LOGGER.debug("Error al crear directorio en servidor SFTP: "+e.getLocalizedMessage());
				}
				
				if(attrs != null){
					LOGGER.debug("El directorio existe");
				} else{
					sftpChannel.mkdir(rutaRepositorioSFTP+DIAGONAL+archivoActual.getName());
				}
				
				transferirArchivosRepositorioSFTP(rutaRepositorio+DIAGONAL+archivoActual.getName(), rutaRepositorioSFTP+DIAGONAL+archivoActual.getName());
			}//Es directorio
		}
	}
	
	
	/**
	 * Copia el archivo indicado a la ruta también indicada
	 * 
	 * @param archivo
	 * @param rutaCompletaDestino
	 */
	private void copiarARepositorio(File archivo, String rutaCompletaDestino){
		LOGGER.debug("Se va a copiar archivo...");
		File directorio = new File(rutaCompletaDestino);
		if(!directorio.exists()){
			directorio.mkdir();
		}
		
		File nuevoArchivo = new File(rutaCompletaDestino + File.separator +archivo.getName());
		try{
			FileUtil.copiarArchivo(archivo, nuevoArchivo);
		}catch(IOException ioException){
			LOGGER.error("Error al copiar archivo: "+archivo+", a: "+nuevoArchivo);
			ioException.printStackTrace();
		}
	}
	
	/**
	 * Elimina los archivos XML contenidos en la ruta de comprimidos
	 * 
	 * @param path
	 * @return boolean
	 */
	private boolean eliminarArchivosXMLs(String path) {
		File file = new File(path);
//		if (!file.exists()) {
//			return true;
//		}
		if (!file.isDirectory()) {
			return file.delete();
		}
		return this.deleteChildren(file); //&& file.delete();
	}
	
	/**
	 * Método auxiliar para eliminar por completo el contenido de un directorio
	 * 
	 * @param dir
	 * @return boolean
	 */
	private boolean deleteChildren(File dir) {
		File[] children = dir.listFiles();
		boolean childrenDeleted = true;
		for (int i = 0; children != null && i < children.length; i++) {
			File child = children[i];
			if (child.isDirectory()) {
				childrenDeleted = this.deleteChildren(child) && childrenDeleted;
			}
			if (child.exists()) {
				childrenDeleted = child.delete() && childrenDeleted;
				LOGGER.debug("Eliminacion archivo "+child.getName()+": "+childrenDeleted);
			}
		}
		return childrenDeleted;
	}
}