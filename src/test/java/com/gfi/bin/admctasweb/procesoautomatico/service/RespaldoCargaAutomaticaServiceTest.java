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
public class RespaldoCargaAutomaticaServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RespaldoCargaAutomaticaServiceTest.class);
	
	@Autowired
	private RespaldoCargaAutomatica respaldoCargaAutomatica;
	
	@Test
	public void respaldarArchivosXML(){
		LOGGER.debug("Comienza respaldarArchivosXML");
		
		try {
			respaldoCargaAutomatica.respaldarArchivosXML();
			
		} catch (ServiceException serviceException) {
			LOGGER.error("Error en ejecución de respaldarArchivosXML: "+serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
	}
}