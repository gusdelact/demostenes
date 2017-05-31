package com.gfi.bin.admctasweb.catalogos.service;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.catalogos.service.impl.DestinatarioServiceImpl;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

public class DestinatarioServiceTest {

	final Logger log = LoggerFactory.getLogger(DestinatarioServiceTest.class);
	private DestinatarioService destinatarioService;
	
	@SuppressWarnings("resource")
	@Before
	public void setUp() throws Exception {

		try {		
			final String[] configurations = {"applicationContextTest.xml"};
	        ApplicationContext context = new ClassPathXmlApplicationContext(configurations);
	        destinatarioService = (DestinatarioServiceImpl) context.getBean("destinatarioServiceImpl"); 	
	        
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();			
		} 
	
	}

	@Test
	public void testBuscarDestinatario() {
		
		List<DestinatarioModel> destinatarios = null;
		DestinatarioModel destinatarioModel = new DestinatarioModel();
		destinatarioModel.setNombre("Manuel");
		//destinatarioModel.setCorreo("cvazquez");
		try {
			destinatarios = destinatarioService.buscarDestinatario(destinatarioModel);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(destinatarios);
		for(DestinatarioModel destinatario : destinatarios)
		{
			log.debug(destinatario.getNombre());
		}
		
	}
	
	@Test
	public void testBuscarDestinatariosPorEstatus() {
		List<DestinatarioModel> destinatarios = null;
		
		String claveEstatus = "RE";
		
		try {
			destinatarios = destinatarioService.buscarDestinatariosPorEstatus(claveEstatus);
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		assertNotNull(destinatarios);
		for(DestinatarioModel destinatario : destinatarios)
		{
			log.debug(destinatario.getNombre());
		}
		
	}

	@Test
	public void testGuardarDestinatario() {
		
		boolean guardo = false;
		DestinatarioModel destinatarioModel = new DestinatarioModel();
		
		destinatarioModel.setCveEstatus("RE");
		destinatarioModel.setCorreo("cvazquezv@interacciones.com");
		destinatarioModel.setFhAlta(new Date());
		destinatarioModel.setFhUltMod(null);
		destinatarioModel.setNombre("Cristóbal Vázquez");
		destinatarioModel.setSituacion("AC");
		destinatarioModel.setCveUsuAlta("cvazquez");
		destinatarioModel.setCveUsuMod(null);
		
		try {
			destinatarioService.guardarDestinatario(destinatarioModel);
			guardo = true;
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(guardo, Boolean.TRUE);
	}

	@Test
	public void testObtenerDestinatario() {
	
		Integer id = 23;
		DestinatarioModel destinatarioModel= null;
		
		try {
			destinatarioModel = destinatarioService.obtenerDestinatario(id);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNotNull(destinatarioModel);
		log.debug(destinatarioModel.getNombre());
		log.debug(destinatarioModel.getFhAlta().toString());
	}
	
	
	@Test
	public void testModificarDestinatario() {
		boolean modifico = false;
		
		DestinatarioModel destinatarioModel = new DestinatarioModel();
		destinatarioModel.setIdDestinatario(1);
		destinatarioModel.setCveEstatus("RE");
		destinatarioModel.setCorreo("cristobal.vazquez13@gmail.com");
		//destinatarioModel.setFechaRegistro(new Date());
		destinatarioModel.setFhUltMod(new Date());
		destinatarioModel.setNombre("Cristóbal Vázquez");
		destinatarioModel.setSituacion("AC");
		//destinatarioModel.setUsuarioRegistra("cvazquez");
		destinatarioModel.setCveUsuMod("mreyes");
		
		try {
			modifico = destinatarioService.modificarDestinatario(destinatarioModel);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertEquals(modifico, Boolean.TRUE);
		
	}

}
