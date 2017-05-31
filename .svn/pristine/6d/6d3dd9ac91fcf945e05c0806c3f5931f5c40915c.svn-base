package com.gfi.bin.admctasweb.operativos.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/applicationContextTest.xml"})
@Transactional
public class OficioRespuestaNegativaServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OficioRespuestaNegativaServiceTest.class);
	
	@Autowired
	private OficioRespuestaNegativaService oficioRespuestaNegativaService;
	
	@Test
	public void modificarOficiosRespuestasNegativasTest(){
		LOGGER.debug("Metodo modificarOficiosRespuestasNegativasTest");
		MultipartFile file = null;
		
		try{
			Boolean resultado = oficioRespuestaNegativaService.modificarOficiosRespuestasNegativas("060831_HAC_1.txt", "12345", file);
			
			Assert.assertTrue(resultado);
		}
		catch(ServiceException serviceException){
			LOGGER.error("Error en buscarOficiosPorNombreArchivoTest");
		}
	}
}