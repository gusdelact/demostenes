package com.gfi.bin.admctasweb.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.service.RespuestaOficioService;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.Session;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml", "/mail-config.xml"})
public class SFTPUtilTest {

	
	
	final Logger LOGGER = LoggerFactory.getLogger(SFTPUtilTest.class);
	
	@Autowired
	RespuestaOficioService respuestaOficioService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	
	
	
	@Test
	public void testDescargarArchivoStringString() {
		String origen = "ftproot/ejemplo.pdf";
		String destino = "C:\\Interacciones\\";
		
		try {
			SFTPUtil.descargarArchivo(origen, destino);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testDescargarArchivoString() {
		String origen = "ftproot/ejemplo.pdf";
		InputStream in = null;
		ChannelSftp sftpChannel = null;
		OutputStream outputStream = null;
		Session session = null;
		try {
			session = SFTPUtil.obtenerSesion();
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			sftpChannel = (ChannelSftp) channel;
			in = sftpChannel.get(origen);
			
			outputStream = new FileOutputStream(new File("C:\\Interacciones\\ejemplo-new.pdf"));
 
			int read = 0;
			byte[] bytes = new byte[1024];
 
			while ((read = in.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}			
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(sftpChannel != null)
				sftpChannel.exit();
			if(session != null)
				session.disconnect();
			if(outputStream != null)
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	@Test
	public void testSubirArchivoStringString() {
		String origen = "C:\\Interacciones\\GeneracionRespuestasView.js";
		String destino = "ftproot/";
		
		try {
			SFTPUtil.subirArchivo(origen, destino);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSubirArchivoInputStreamString() {
		String numOficio = "214-1-391898/2006";
		String tipoOficio = "AS";
		String destino = "ftproot/reporte.pdf";
		Integer idRespuesta = 1;
		byte[] reporte = null;
		try {
			reporte = respuestaOficioService.generarReporte(numOficio, tipoOficio, idRespuesta);
			if(reporte != null){
				
				InputStream is = new ByteArrayInputStream(reporte);
				SFTPUtil.subirArchivo(is, destino);
			}
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testSubirArchivoInputStream() {
		
		String destino = "ftproot/DiagramaBD.jpeg";
		try {			
			SFTPUtil.subirArchivo(new FileInputStream("C:\\Interacciones\\DiagramaBD.jpeg"), destino);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testCrearDirectorio() {
		
		String directorio = "ftproot/nuevo/";
		
		
		try {			
			SFTPUtil.crearDirectorioRemoto(directorio);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void testBorrarArchivo() {
		
		String ruta = "ftproot/PersonaCorpGrid.js";
		
		
		try {			
			SFTPUtil.borrarArchivo(ruta);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testBorrarDirectorio() {
		
		String ruta = "ftproot/nuevo/";
		
		
		try {			
			SFTPUtil.borrarDirectorio(ruta);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Test
	public void testExisteDirectorio() {
		
		String ruta = "/ftproot/Repositorio/201406/";
		boolean existe = false;
		
		try {			
			existe = SFTPUtil.existeDirectorio(ruta);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LOGGER.info("existe: " + existe);
	}

}
