package com.gfi.bin.admctasweb.operativos.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;
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
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;
import com.gfi.bin.admctasweb.operativos.service.RespuestaOficioService;	
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class RespuestaOficioServiceTest {

	@Autowired
	RespuestaOficioService respuestaOficioService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RespuestaOficioServiceTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGuardarRespuestaOficio() {
		
		RespuestaOficioModel respuestaOficioModel = new RespuestaOficioModel();
		respuestaOficioModel.setNumOficio("214-1-168672/2007");
		respuestaOficioModel.setTipoOficio("AS");
		respuestaOficioModel.setObservaciones("OBSERVACIONES TEST");
		respuestaOficioModel.setTipoRequerimiento("INM");
		respuestaOficioModel.setTipoSolicitud("TOT");
		respuestaOficioModel.setRespuestaEnviada("F");
		respuestaOficioModel.setIdDireccion(1);
		respuestaOficioModel.setFhAlta(new Date());
		respuestaOficioModel.setCveUsuAlta("cvazquez");		
		
		boolean guardo = false;
		try {
			respuestaOficioModel = respuestaOficioService.guardarRespuestaOficio(respuestaOficioModel);
			guardo = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(guardo, Boolean.TRUE);
	}

	@Test
	public void testModificarRespuestaOficio() {

		RespuestaOficioModel respuestaOficioModel = new RespuestaOficioModel();
		respuestaOficioModel.setNumOficio("214-1-168672/2007");
		respuestaOficioModel.setTipoOficio("AS");
		respuestaOficioModel.setIdRespuesta(1);
		respuestaOficioModel.setObservaciones("OBSERVACIONES MODIFICADAS");
		respuestaOficioModel.setTipoRequerimiento("INM");
		respuestaOficioModel.setTipoSolicitud("TOT");
		respuestaOficioModel.setRespuestaEnviada("V");
		respuestaOficioModel.setIdDireccion(1);
		respuestaOficioModel.setFhUltMod(new Date());
		respuestaOficioModel.setCveUsuMod("cvazquez");
				
		boolean modifico = false;
		try {
			modifico = respuestaOficioService.modificarRespuestaOficio(respuestaOficioModel);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(modifico, Boolean.TRUE);
	}

	@Test
	public void testObtenerRespuestaOficioPorId() {
		
		RespuestaOficioModel respuestaOficioModel = new RespuestaOficioModel();
		respuestaOficioModel.setNumOficio("214-1-168672/2007");
		respuestaOficioModel.setTipoOficio("AS");
		respuestaOficioModel.setIdRespuesta(1);
		
		try {
			respuestaOficioModel = respuestaOficioService.obtenerRespuestaOficioPorId(respuestaOficioModel);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(respuestaOficioModel);
		LOGGER.info(respuestaOficioModel.getObservaciones());
	}
	
	@Test
	public void testObtenerRequerimientos() {
		
		List<ItemModel> requerimientos = null;
		try {
			requerimientos = respuestaOficioService.obtenerRequerimientos("JU");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(requerimientos);
		for(ItemModel requerimiento : requerimientos)
			LOGGER.info(requerimiento.getClave() + "--" + requerimiento.getDescripcion());
	}
	
	@Test
	public void testObtenerSolicitudes() {
		
		List<ItemModel> solicitudes = null;
		try {
			solicitudes = respuestaOficioService.obtenerSolicitudes("JU", "INM");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(solicitudes);
		for(ItemModel requerimiento : solicitudes)
			LOGGER.info(requerimiento.getClave() + "--" + requerimiento.getDescripcion());
	}	
	
	@Test
	public void test()
	{
		String rfc = "SASA 510504 PL9"; 
		
		char chars[] = new char[20];
		rfc.getChars(0, rfc.length() -1 , chars, 0);
				
		for( int i = 0; i < chars.length; i++)  
		     System.out.printf(" char = %c  codepoint = \\u%04x \n", chars[i], Character.codePointAt(chars, i));  
		
		LOGGER.info(rfc);
		LOGGER.info(rfc.replaceAll("\u00A0",""));

	}
	
	@Test
	public void testArmarCadenaDatosOficio() {
		
		RespuestaOficioModel respuestaOficioModel = new RespuestaOficioModel();
		respuestaOficioModel.setNumOficio("214-1-391898/2006");
		respuestaOficioModel.setTipoOficio("AS");
		respuestaOficioModel.setTipoRequerimiento("INM");
		respuestaOficioModel.setTipoSolicitud("PAR");
		String datosOficio = null;
		try {
			datosOficio = respuestaOficioService.armarCadenaDatosOficio(respuestaOficioModel);
		} catch (ServiceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		assertNotNull(datosOficio);
		LOGGER.info(datosOficio);
	}
	
}
