package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContextTest.xml"})
@Transactional
public class GestionArchivosXMLServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(GestionArchivosXMLServiceTest.class);

	@Autowired
	private GestionArchivosXMLService gestionArchivosXMLService;
	
	@Autowired
	private BitacoraCargaAutomaticaService bitCargaAutomaticaService;
	
	@Autowired
	private NotificacionProcesoAutomaticoService notificacionProcesoAutomaticoService;
	
	@Autowired
	private RespaldoCargaAutomatica respaldoCargaAutomatica;
	
	@Autowired
	private ValidacionCargaAutomatica validacionCargaAutomatica;
	
	@Test
	public void leerArchivosXMLTest() {
		LOGGER.debug("Inicia ejecucion de descomprimirArchivosRepositorioTest()");
		
		try{
			if(validacionCargaAutomatica.validarPrecondiciones()){
				List<BitacoraCargaAutomaticaModel> listaCargaAutomatica = gestionArchivosXMLService.leerArchivosXMLRepositorio();
				listaCargaAutomatica = bitCargaAutomaticaService.guardarCargaAutomatica(listaCargaAutomatica);
//				respaldoCargaAutomatica.respaldarArchivosXML();
				notificacionProcesoAutomaticoService.enviarNotificacion(listaCargaAutomatica);
			}
			else{
				LOGGER.warn("No se cumplen las precondiciones para iniciar la carga automatica");
			}
			
			LOGGER.debug("Termina ejecucion de descomprimirArchivosRepositorioTest()");
			
		}catch(Exception serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
	}
}