/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class AutoridadesServiceTest extends TestCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AutoridadesServiceTest.class);
	
	@Autowired
	private AutoridadesService autoridadesService;
	
	
	@Test
	public void testConsultarAutoridades() {
		LOGGER.info("testConsultarAutoridades");
		
		List<AutoridadesModel> autoridadesModelList = null;
		try {
			autoridadesModelList = this.autoridadesService.consultarAutoridades(new AutoridadesModel());
			if (autoridadesModelList != null && !autoridadesModelList.isEmpty()) {
				for (AutoridadesModel a: autoridadesModelList) {
					LOGGER.info(a.toString());
				}
			}			
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	
	@Test
	public void insertarAutoridades() {
		LOGGER.info("insertarAutoridades");
		
		AutoridadesModel autoridadesModel = new AutoridadesModel();
		
		autoridadesModel.setCveAutoridad("FMD");
		autoridadesModel.setNomAutoridad("NOMBRE PRUEBA FMD");
		autoridadesModel.setSitAutoridad("IN");
//		autoridadesModel.setFhAlta(new Date());
//		autoridadesModel.setCveUsuAlta("FMUNIVE1");

		LOGGER.info(autoridadesModel.toString());
		
		try {
			this.autoridadesService.insertarAutoridad(autoridadesModel);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}

	
	@Test
	public void actualizarAutoridades() {
		LOGGER.info("actualizarAutoridades");
		
		try {			
			AutoridadesModel autoridadesModel = new AutoridadesModel();
			autoridadesModel.setCveAutoridad("FMD");
			
			autoridadesModel = this.autoridadesService.consultarAutoridadPorClave(autoridadesModel);
			if (autoridadesModel != null) {
				autoridadesModel.setNomAutoridad("NOMBRE PRUEBA FMD 2");
				autoridadesModel.setSitAutoridad("IN");
//				autoridadesModel.setFhUltMod(new Date());
//				autoridadesModel.setCveUsuMod("FMUNIVE2");
	
				LOGGER.info(autoridadesModel.toString());
				
				this.autoridadesService.actualizarAutoridad(autoridadesModel);
			}
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage());
			
		}
	}
	
	
	@Test
	public void eliminarAutoridades() {
		LOGGER.info("eliminarAutoridades");
		
		try {
			AutoridadesModel autoridadesModel = new AutoridadesModel();
			autoridadesModel.setCveAutoridad("FMD");
			autoridadesModel = this.autoridadesService.consultarAutoridadPorClave(autoridadesModel);
			if (autoridadesModel != null) {
				LOGGER.info(autoridadesModel.toString());
				this.autoridadesService.eliminarAutoridad(autoridadesModel);
			}
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}
		
}
