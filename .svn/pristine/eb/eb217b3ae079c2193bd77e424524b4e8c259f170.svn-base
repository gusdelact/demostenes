package com.gfi.bin.admctasweb.reportes.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class BitacoraSeguimientoServiceTest {
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraSeguimientoServiceTest.class);
	
	@Test
	@Transactional
	public void guardarTest() {
		LOGGER.debug("Inicia GuardarTest...");
		
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		seguimiento.setNumOficio("214-1-434112/2006");
		seguimiento.setTipoOficio("AS");
		seguimiento.setCveEstatus("CE");
		//seguimiento.setFhRegistro(new Date());
		seguimiento.setCveUsuario("XX");
		
		try{
			bitacoraSeguimientoService.guardar(seguimiento);
		}catch(ServiceException serviceException){
			LOGGER.error("Error: "+serviceException.getLocalizedMessage());
		}
		LOGGER.debug("Termina GuardarTest...");
	}
}