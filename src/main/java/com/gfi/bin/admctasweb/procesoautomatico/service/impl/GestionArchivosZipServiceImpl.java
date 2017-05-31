package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.service.GestionArchivosZipService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.FileUtil;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;
import com.gfi.bin.admctasweb.util.SFTPUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class GestionArchivosZipServiceImpl implements GestionArchivosZipService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionArchivosZipServiceImpl.class);
	
	private static final String CARGA_AUTOMATICA_PROPERTIES = "cargaAutomatica.properties";
	
	private static final String SFTP_CHANNEL = "sftp";
	
	private static final String SFTP_RUTA_ZIP_BANCO_ASEGURAMIENTO = "sftp.ruta.zip.banco.aseguramiento";
	private static final String SFTP_RUTA_ZIP_BANCO_HACENDARIO = "sftp.ruta.zip.banco.hacendario";
	private static final String SFTP_RUTA_ZIP_BANCO_JUDICIAL = "sftp.ruta.zip.banco.judicial";
	private static final String SFTP_RUTA_ZIP_BANCO_PLD = "sftp.ruta.zip.banco.pld";
	private static final String SFTP_RUTA_ZIP_CASA_ASEGURAMIENTO = "sftp.ruta.zip.casa.aseguramiento";
	private static final String SFTP_RUTA_ZIP_CASA_HACENDARIO = "sftp.ruta.zip.casa.hacendario";
	private static final String SFTP_RUTA_ZIP_CASA_JUDICIAL = "sftp.ruta.zip.casa.judicial";
	private static final String SFTP_RUTA_ZIP_CASA_PLD = "sftp.ruta.zip.casa.pld";
	private static final String SFTP_RUTA_ZIP_ISOSI_ASEGURAMIENTO = "sftp.ruta.zip.isosi.aseguramiento";
	private static final String SFTP_RUTA_ZIP_ISOSI_HACENDARIO = "sftp.ruta.zip.isosi.hacendario";
	private static final String SFTP_RUTA_ZIP_ISOSI_JUDICIAL = "sftp.ruta.zip.isosi.judicial";
	private static final String SFTP_RUTA_ZIP_ISOSI_PLD = "sftp.ruta.zip.isosi.pld";
	
	private static final String LSFTP_RUTA_ZIP_BANCO_ASEGURAMIENTO = "lsftp.ruta.zip.banco.aseguramiento";
	private static final String LSFTP_RUTA_ZIP_BANCO_HACENDARIO = "lsftp.ruta.zip.banco.hacendario";
	private static final String LSFTP_RUTA_ZIP_BANCO_JUDICIAL = "lsftp.ruta.zip.banco.judicial";
	private static final String LSFTP_RUTA_ZIP_BANCO_PLD = "lsftp.ruta.zip.banco.pld";
	private static final String LSFTP_RUTA_ZIP_CASA_ASEGURAMIENTO = "lsftp.ruta.zip.casa.aseguramiento";
	private static final String LSFTP_RUTA_ZIP_CASA_HACENDARIO = "lsftp.ruta.zip.casa.hacendario";
	private static final String LSFTP_RUTA_ZIP_CASA_JUDICIAL = "lsftp.ruta.zip.casa.judicial";
	private static final String LSFTP_RUTA_ZIP_CASA_PLD = "lsftp.ruta.zip.casa.pld";
	private static final String LSFTP_RUTA_ZIP_ISOSI_ASEGURAMIENTO = "lsftp.ruta.zip.isosi.aseguramiento";
	private static final String LSFTP_RUTA_ZIP_ISOSI_HACENDARIO = "lsftp.ruta.zip.isosi.hacendario";
	private static final String LSFTP_RUTA_ZIP_ISOSI_JUDICIAL = "lsftp.ruta.zip.isosi.judicial";
	private static final String LSFTP_RUTA_ZIP_ISOSI_PLD = "lsftp.ruta.zip.isosi.pld";
	
	/**
	 * Método que descomprime archivos tomando como fuente y destino rutas definidas en Constantes
	 * 
	 * @return Boolean
	 */
	public Boolean descomprimirArchivos() throws ServiceException {
		Boolean resultadoDescompresion = false;
		
		LOGGER.debug("Inicia Descompresión de archivos comprimidos");
		
		String rutaComprimidos = LectorPropertiesUtil.obtenerValoresEtiqueta(
				Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_COMPRIMIDOS);
		String rutaDescomprimidos = LectorPropertiesUtil.obtenerValoresEtiqueta(
				Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_DESCOMPRIMIDOS);
		
		resultadoDescompresion = descomprimirArchivos(rutaComprimidos, rutaDescomprimidos);
		LOGGER.debug("Termina Descompresión de archivos comprimidos");
		
		return resultadoDescompresion;
	}
	
	/**
	 * Método recursivo para desempaquetar archivos zip y guardar resultados en rutas definidas en Constantes
	 *  
	 * @param rutaComprimidos
	 * @param rutaDescomprimidos
	 * @return boolean
	 */
	private boolean descomprimirArchivos(String rutaComprimidos, String rutaDescomprimidos) {
		LOGGER.debug("Ruta fuente: "+rutaComprimidos);
		LOGGER.debug("Ruta destino: "+rutaDescomprimidos);
		
		Boolean bandera = true;
		byte[] buffer = new byte[1024];

		try {
			File folderComprimidos = new File(rutaComprimidos);
			File[] listaArchivosDirectorio = folderComprimidos.listFiles();
			String nombreArchivoActual = null;
			
			for (int i=0; i<listaArchivosDirectorio.length; i++){
				File archivoActual = listaArchivosDirectorio[i];
				LOGGER.debug("Archivo actual es: "+archivoActual);
				
				if(archivoActual.isFile()){
					LOGGER.debug("Archivo actual: "+archivoActual+" es archivo...");
					
					nombreArchivoActual = listaArchivosDirectorio[i].getName();
					LOGGER.debug("Nombre archivo actual: "+nombreArchivoActual);
					if (nombreArchivoActual.endsWith(Constantes.EXTENSION_ZIP.toLowerCase()) || nombreArchivoActual.endsWith(Constantes.EXTENSION_ZIP.toUpperCase())){//Que sea archivo y con extensión zip-ZIP
						ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(rutaComprimidos + File.separator + nombreArchivoActual));
						ZipEntry zipEntry = zipInputStream.getNextEntry();
						
						while (zipEntry != null) {
							//Crea directorio de salida si no existe, solo si existen archivos a descomprimir
							File folder = new File(rutaDescomprimidos);
							if (!folder.exists()) {
								folder.mkdir();
							}
							
							//Crear directorio con nombre de archivo zip solo si existen archivos a descomprimir
							LOGGER.debug("Ruta a crear para zip actual: "+rutaDescomprimidos+File.separator+nombreArchivoActual.substring(0, nombreArchivoActual.indexOf('.')));
							File rutaDescompresionZipActual = new File(rutaDescomprimidos+File.separator+nombreArchivoActual.substring(0, nombreArchivoActual.indexOf('.')));
							if (!rutaDescompresionZipActual.exists()) {
								rutaDescompresionZipActual.mkdir();
							}
							
							//Añadir carpeta donde estarán los archivos a obtener del comprimido
							File nuevoArchivo = new File(rutaDescompresionZipActual + File.separator + zipEntry.getName());
							LOGGER.debug("Archivo a descomprimir: " + nuevoArchivo.getAbsoluteFile());
							new File(nuevoArchivo.getParent()).mkdirs();
							FileOutputStream fileOutputStream = new FileOutputStream(nuevoArchivo);

							int longitud;
							while ((longitud = zipInputStream.read(buffer)) > 0) {
								fileOutputStream.write(buffer, 0, longitud);
							}

							fileOutputStream.close();
							zipEntry = zipInputStream.getNextEntry();
						}
						zipInputStream.closeEntry();
						zipInputStream.close();
						
						LOGGER.debug("Termina descompresión de archivos encontrados en: "+rutaComprimidos);
					}//Es archivo zip
					else if(nombreArchivoActual.endsWith(Constantes.EXTENSION_XLS.toLowerCase()) || nombreArchivoActual.endsWith(Constantes.EXTENSION_XLS.toUpperCase())){//Se copia al destino
						File folderActual = new File(rutaDescomprimidos);
						if(!folderActual.exists())
							folderActual.mkdir();
						
						FileUtil.copiarArchivo(new File(rutaComprimidos+File.separator+nombreArchivoActual), new File(rutaDescomprimidos+File.separator+nombreArchivoActual));
					}
				}//Es archivo
				else if(archivoActual.isDirectory()){
					LOGGER.debug("Archivo actual: "+archivoActual+" es directorio...");
					descomprimirArchivos(archivoActual.getAbsolutePath(), rutaDescomprimidos+File.separator+archivoActual.getName());
				}//Es directorio
			}//For lista archivos comprimidos
		} catch (IOException ioException) {
			LOGGER.error(ioException.getLocalizedMessage());
			ioException.printStackTrace();
			
			bandera = false;
		}
		return bandera;
	}
	
	/**
	 * Método que elimina los archivos comprimidos
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	@Override
	public Boolean eliminarArchivos() throws ServiceException {
		LOGGER.debug("Comienza eliminarArchivos");
		
		String rutaComprimidos = LectorPropertiesUtil.obtenerValoresEtiqueta(
				Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_COMPRIMIDOS);
		
		LOGGER.debug("Se eliminarán archivos ZIP locales");
		Boolean resultado = FileUtil.eliminarArchivos(rutaComprimidos);
		
		
		
		LOGGER.debug("Se eliminarán archivos ZIP en el servidor SFTP");
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			//Conexion a servidor SFTP
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new ServiceException("No se pudo obtener una sesión SFTP");
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			
			String rutaBancoAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_ASEGURAMIENTO);
			String rutaBancoHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_HACENDARIO);
			String rutaBancoJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_JUDICIAL);
			String rutaBancoPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_PLD);
			String rutaCasaAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_ASEGURAMIENTO);
			String rutaCasaHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_HACENDARIO);
			String rutaCasaJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_JUDICIAL);
			String rutaCasaPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_PLD);
			String rutaIsosiAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_ASEGURAMIENTO);
			String rutaIsosiHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_HACENDARIO);
			String rutaIsosiJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_JUDICIAL);
			String rutaIsosiPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_PLD);
			
			
			eliminarArchivosSFTP(sftpChannel, rutaBancoAseguramiento);
			eliminarArchivosSFTP(sftpChannel, rutaBancoHacendario);
			eliminarArchivosSFTP(sftpChannel, rutaBancoJudicial);
			eliminarArchivosSFTP(sftpChannel, rutaBancoPLD);
			
			eliminarArchivosSFTP(sftpChannel, rutaCasaAseguramiento);
			eliminarArchivosSFTP(sftpChannel, rutaCasaHacendario);
			eliminarArchivosSFTP(sftpChannel, rutaCasaJudicial);
			eliminarArchivosSFTP(sftpChannel, rutaCasaPLD);
			
			eliminarArchivosSFTP(sftpChannel, rutaIsosiAseguramiento);
			eliminarArchivosSFTP(sftpChannel, rutaIsosiHacendario);
			eliminarArchivosSFTP(sftpChannel, rutaIsosiJudicial);
			eliminarArchivosSFTP(sftpChannel, rutaIsosiPLD);
			
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
		
		return resultado;
	}
	
	@Override
	public Boolean descargarArchivos() throws ServiceException{
		Session session = null;
		ChannelSftp sftpChannel = null;
		
		try {
			//Conexion a servidor SFTP
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new ServiceException("No se pudo obtener una sesión SFTP");
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			
			String rutaBancoAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_ASEGURAMIENTO);
			String rutaBancoHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_HACENDARIO);
			String rutaBancoJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_JUDICIAL);
			String rutaBancoPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_BANCO_PLD);
			String rutaCasaAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_ASEGURAMIENTO);
			String rutaCasaHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_HACENDARIO);
			String rutaCasaJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_JUDICIAL);
			String rutaCasaPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_CASA_PLD);
			String rutaIsosiAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_ASEGURAMIENTO);
			String rutaIsosiHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_HACENDARIO);
			String rutaIsosiJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_JUDICIAL);
			String rutaIsosiPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, SFTP_RUTA_ZIP_ISOSI_PLD);
			
			String localBancoAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_BANCO_ASEGURAMIENTO);
			String localBancoHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_BANCO_HACENDARIO);
			String localBancoJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_BANCO_JUDICIAL);
			String localBancoPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_BANCO_PLD);
			String localCasaAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_CASA_ASEGURAMIENTO);
			String localCasaHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_CASA_HACENDARIO);
			String localCasaJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_CASA_JUDICIAL);
			String localCasaPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_CASA_PLD);
			String localIsosiAseguramiento = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_ISOSI_ASEGURAMIENTO);
			String localIsosiHacendario = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_ISOSI_HACENDARIO);
			String localIsosiJudicial = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_ISOSI_JUDICIAL);
			String localIsosiPLD = LectorPropertiesUtil.obtenerValoresEtiqueta(CARGA_AUTOMATICA_PROPERTIES, LSFTP_RUTA_ZIP_ISOSI_PLD);
			
			
			obtenerArchivos(sftpChannel, rutaBancoAseguramiento, localBancoAseguramiento);
			obtenerArchivos(sftpChannel, rutaBancoHacendario, localBancoHacendario);
			obtenerArchivos(sftpChannel, rutaBancoJudicial, localBancoJudicial);
			obtenerArchivos(sftpChannel, rutaBancoPLD, localBancoPLD);
			
			obtenerArchivos(sftpChannel, rutaCasaAseguramiento, localCasaAseguramiento);
			obtenerArchivos(sftpChannel, rutaCasaHacendario, localCasaHacendario);
			obtenerArchivos(sftpChannel, rutaCasaJudicial, localCasaJudicial);
			obtenerArchivos(sftpChannel, rutaCasaPLD, localCasaPLD);
			
			obtenerArchivos(sftpChannel, rutaIsosiAseguramiento, localIsosiAseguramiento);
			obtenerArchivos(sftpChannel, rutaIsosiHacendario, localIsosiHacendario);
			obtenerArchivos(sftpChannel, rutaIsosiJudicial, localIsosiJudicial);
			obtenerArchivos(sftpChannel, rutaIsosiPLD, localIsosiPLD);
			
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
		return false;
	}
	
	@SuppressWarnings("rawtypes")
	private void obtenerArchivos(ChannelSftp channelSFTP, String fuente, String destino) throws SftpException{
		System.out.println("Ruta a leer: "+fuente+ " ,"+destino);
		channelSFTP.cd(fuente);
		Vector vectorJudicial = channelSFTP.ls(fuente);
		System.out.println("Tamaño vector: "+vectorJudicial.size());
		
		for(int i=0; i<vectorJudicial.size(); i++){
			Object obj = vectorJudicial.elementAt(i);
			if(obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry){
                  System.out.println(((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename());
                  String nombreArchivo = ((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename();
                  if(nombreArchivo.toUpperCase().endsWith(Constantes.EXTENSION_ZIP.toUpperCase())){
                	  channelSFTP.get(((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename(), destino);
                  }
                }
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void eliminarArchivosSFTP(ChannelSftp channelSFTP, String ruta) throws SftpException{
		System.out.println("Ruta de archivo a eliminar: "+ruta);
		channelSFTP.cd(ruta);
		Vector vectorJudicial = channelSFTP.ls(ruta);
		System.out.println("Tamaño vector: "+vectorJudicial.size());
		
		for(int i=0; i<vectorJudicial.size(); i++){
			Object obj = vectorJudicial.elementAt(i);
			if(obj instanceof com.jcraft.jsch.ChannelSftp.LsEntry){
                  System.out.println(((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename());
                  String nombreArchivo = ((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename();
                  if(nombreArchivo.toUpperCase().endsWith(Constantes.EXTENSION_ZIP.toUpperCase())){
                	  channelSFTP.rm(((com.jcraft.jsch.ChannelSftp.LsEntry)obj).getFilename());
                  }
            }
		}
	}
}