package com.gfi.bin.admctasweb.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;


public final class SFTPUtil {

	private static final String SFTP_PROPERTIES = "sftp.properties";
	private static final String SFTP_HOST_PROPERTY = "sftp.host";
	private static final String SFTP_USER_PROPERTY = "sftp.username";
	private static final String SFTP_PASSWORD_PROPERTY = "sftp.password";
	private static final String SFTP_PORT_PROPERTY = "sftp.port";
	private static final String SFTP_STRICT_HOST_CHECKING = "sftp.strictHostKeyChecking";
	
	private static final String SFTP_CHANNEL = "sftp";
	private static final String ERROR_SESION = "No se pudo obtener una sesión SFTP";
	private static final String ERROR_PROPERTIES = "No se pudo obtener el archivo de propiedades de SFTP";
	
	/**
	 * Descarga un archivo mediante sftp especificando origen y destino
	 * @param origen ruta de donde se lee archivo
	 * @param destino ruta en que se deposita archivo
	 * @throws Exception
	 */
	public static final void descargarArchivo(String origen, String destino)throws Exception
	{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			sftpChannel.get(origen, destino);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	/**
	 * Descarga un archivo mediante sftp hacia un arreglo de bytes
	 * @param origen ruta de donde se lee archivo
	 * @throws Exception
	 */
	public static final byte[] descargarArchivoBytes(String origen) throws Exception {
		Session session = null;
		ChannelSftp sftpChannel = null;
		InputStream in = null;
		byte[] bytes = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if (session == null)
				throw new Exception(ERROR_SESION);

			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			in = sftpChannel.get(origen);
			
			//Genera el arreglo de bytes.
			bytes = IOUtils.toByteArray(in);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		} finally {
			if (sftpChannel != null)
				sftpChannel.exit();
			if (session != null)
				session.disconnect();
		}
		return bytes;
	}

	
	/**
	 * Sube un archivo por medio de SFTP
	 * @param origen ruta del archivo (Se usa File para obtenerlo de la ruta que se especifique)
	 * @param destino Ruta en donde se depósita el archivo (Se sobreescribe por defecto)
	 * @return
	 * @throws Exception
	 */
	public static final void subirArchivo(String origen, String destino)throws Exception{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			sftpChannel.put(origen, destino);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	/**
	 * Sube un archivo por medio de SFTP
	 * @param origen El origen en este caso es un InputStream para casos en que se tenga el archivo en memoria
	 * @param destino Ruta en donde se depósita el archivo (Se sobreescribe por defecto)
	 * @throws Exception
	 */
	public static final void subirArchivo(InputStream origen, String destino)throws Exception{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			
			sftpChannel.put(origen, destino);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	/**
	 * Genera directorio remoto con el path proporcionado
	 * @param directorio
	 * @throws Exception
	 */
	public static final void crearDirectorioRemoto(String directorio)throws Exception{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			
			
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			sftpChannel.mkdir(directorio);
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	/**
	 * 
	 * @param ruta
	 * @throws Exception
	 */
	public static final void borrarArchivo(String ruta)throws Exception{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);
			
			
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			sftpChannel.rm(ruta);
			
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	/**
	 * Borra directorio. Al parecer puede eliminar con todo y archivos internos.
	 * @param ruta
	 * @throws Exception
	 */
	public static final void borrarDirectorio(String ruta)throws Exception{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);			
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			sftpChannel.rmdir(ruta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	/**
	 * Verifica si existe un directorio
	 * @param directorio
	 * @return
	 * @throws Exception
	 */
	public static final boolean existeDirectorio(String directorio)throws Exception
	{
		Session session = null;
		ChannelSftp sftpChannel = null;
		try {
			session = SFTPUtil.obtenerSesion();
			if(session == null)
				throw new Exception(ERROR_SESION);
			
			Channel channel = session.openChannel(SFTP_CHANNEL);			
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			
			SftpATTRS attrs = sftpChannel.stat(directorio);
			
			return attrs!=null && attrs.isDir();			
		} catch (SftpException e) {
			if(e.id == ChannelSftp.SSH_FX_NO_SUCH_FILE)
				return false;
			throw e;
		}
		finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
		}	
	}
	
	
	
	
	/**
	 * Obtiene sesión sftp
	 * @return
	 * @throws Exception 
	 */
	public static final Session obtenerSesion() throws Exception
	{
		//Obtener propiedades
		Properties p = LectorPropertiesUtil.obtenerArchivoPropiedades(SFTP_PROPERTIES);
		if(p.isEmpty())
			throw new Exception(ERROR_PROPERTIES);
			
		String host = p.getProperty(SFTP_HOST_PROPERTY); 
		String user = p.getProperty(SFTP_USER_PROPERTY);
		String password = p.getProperty(SFTP_PASSWORD_PROPERTY);
		String port = p.getProperty(SFTP_PORT_PROPERTY);
		String strictHostChecking = p.getProperty(SFTP_STRICT_HOST_CHECKING);
		String hostChecking = StringUtils.capitalize(StringUtils.substringAfter(SFTP_STRICT_HOST_CHECKING, "."));
		
		JSch jsch = new JSch();
		Session session = null;
		
		try {
			session = jsch.getSession(user, host, Integer.parseInt(port));    
			session.setConfig(hostChecking, strictHostChecking);
			session.setPassword(password);			  
			session.connect();
		}  
		catch (JSchException e) {
			// TODO Auto-generated catch block
			throw new Exception(e);
		}
				
		return session;
	}
	
}
