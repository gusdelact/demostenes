package com.gfi.bin.admctasweb.procesoautomatico.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContextTest.xml"})
public class GestionArchivosZipServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionArchivosZipServiceTest.class);

	@Autowired
	private GestionArchivosZipService gestionArchivosService;

	@Test
	public void descomprimirArchivosRepositorioTest() {
		LOGGER.debug("Inicia ejecucion de descomprimirArchivosRepositorioTest()");
		try{
			gestionArchivosService.descomprimirArchivos();
			
			LOGGER.debug("Termina ejecucion de descomprimirArchivosRepositorioTest()");
			
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
	}
	
	@Test
	public void eliminarArchivosTest(){
		LOGGER.debug("Inicia ejecucion de eliminarArchivosTest");
		try{
			gestionArchivosService.eliminarArchivos();
			
			LOGGER.debug("Termina ejecucion de eliminarArchivosTest");
			
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
	}
	
	@Test
	public void descargarArchivosTest(){
		LOGGER.debug("Inicia ejecucion de descargarArchivosTest");
		
		try{
			gestionArchivosService.descargarArchivos();
			
			LOGGER.debug("Termina ejecucion de descargarArchivosTest");
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
		
	}
}