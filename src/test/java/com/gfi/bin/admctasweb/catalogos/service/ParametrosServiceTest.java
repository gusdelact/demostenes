/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.service;

import java.util.Date;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class ParametrosServiceTest extends TestCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ParametrosServiceTest.class);
	
	@Autowired
	private ParametrosService parametrosService;
	
	
	@Test
	public void testConsultarParametros() {
		LOGGER.info("testConsultarParametros");
		
		List<ParametrosModel> parametrosModelList = null;
		try {
			parametrosModelList = this.parametrosService.consultarParametros(new ParametrosModel());
			if (parametrosModelList != null && !parametrosModelList.isEmpty()) {
				for (ParametrosModel p: parametrosModelList) {
					LOGGER.info(p.toString());
				}
			}			
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	
	@Test
	public void insertarParametros() {
		LOGGER.info("insertarParametros");
		
		ParametrosModel parametrosModel = new ParametrosModel();
		
		parametrosModel.setCveGpoParam("FMDGPO");
		parametrosModel.setCveParam("FMDCVE");
		parametrosModel.setDescParam("PARAMETRO PRUEBA FMD");
		parametrosModel.setSitParam("IN");
		parametrosModel.setFhAlta(new Date());
		parametrosModel.setCveUsuAlta("FMUNIVE1");

		LOGGER.info(parametrosModel.toString());
		
		try {
			this.parametrosService.insertarParametro(parametrosModel);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}

	
	@Test
	public void actualizarParametros() {
		LOGGER.info("actualizarParametros");
		
		try {			
			ParametrosModel parametrosModel = new ParametrosModel();
			parametrosModel.setCveGpoParam("FMDGPO");
			parametrosModel.setCveParam("FMDCVE");
			
			parametrosModel = this.parametrosService.consultarParametroPorClave(parametrosModel);
			if (parametrosModel != null) {
				parametrosModel.setDescParam("NOMBRE PRUEBA FMD 2");
				parametrosModel.setSitParam("IN");
				parametrosModel.setFhUltMod(new Date());
				parametrosModel.setCveUsuMod("FMUNIVE2");
	
				LOGGER.info(parametrosModel.toString());
				
				this.parametrosService.actualizarParametro(parametrosModel);
			}
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage());
			
		}
	}
	
	
	@Test
	public void eliminarParametros() {
		LOGGER.info("eliminarParametros");
		
		try {
			ParametrosModel parametrosModel = new ParametrosModel();
			parametrosModel.setCveGpoParam("FMDGPO");
			parametrosModel.setCveParam("FMDCVE");
			parametrosModel = this.parametrosService.consultarParametroPorClave(parametrosModel);
			if (parametrosModel != null) {
				LOGGER.info(parametrosModel.toString());
				this.parametrosService.eliminarParametro(parametrosModel);
			}
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}
		
}
