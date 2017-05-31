package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientoDescargadoModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientosDescargadosModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContextTest.xml"})
public class RequerimientosDescargadosServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(RequerimientosDescargadosServiceTest.class);

	@Autowired
	private RequerimientosDescargadosService requerimientosDescargadosService;

	@Test
	public void leerArchivoXMLTest(){
		LOGGER.debug("Comienza leerArchivoXMLTest");
		try{
//			File archivoXML = new File("C:\\PruebasArchivos\\DescomprimidosPrueba\\Casa\\Aseguramiento\\117318_CB1173U03_20140603085353_5000\\117318_CB1173U03_20140603085353_5000.xml");
			File archivoXML = new File("C:\\PruebasArchivos\\DescomprimidosPrueba\\Banco 030614\\Judicial\\8203_BM0082U02_20140603142619_4531\\8203_BM0082U02_20140603142619_4531.xml");
			
			RequerimientosDescargadosModel requerimientosDescargados = requerimientosDescargadosService.leerArchivoXML(archivoXML);
			
			Assert.assertNotNull(requerimientosDescargados);
			
			for(RequerimientoDescargadoModel requerimiento : requerimientosDescargados.getListaRequerimientosDescargados()){
				LOGGER.info("idFolio: "+requerimiento.getIdFolio());
				LOGGER.info("anio: "+requerimiento.getAnio());
				LOGGER.info("area: "+requerimiento.getArea());
				LOGGER.info("noOficio: "+requerimiento.getNoOficio());
				LOGGER.info("noExpediente: "+requerimiento.getNoExpediente());
				LOGGER.info("fechaPublicacion: "+requerimiento.getFechaPublicacion());
				LOGGER.info("plazoRespuesta: "+requerimiento.getPlazoRespuesta());
				LOGGER.info("oficioFileName: "+requerimiento.getOficioFileName());
				LOGGER.info("requerimientoFileName: "+requerimiento.getRequerimientoFileName());
				LOGGER.info("xmlFileName: "+requerimiento.getXmlFileName()+"\\n\\n\\n");
			}
			
		}catch(ServiceException serviceException){
			LOGGER.error("Error: "+serviceException.getLocalizedMessage());

			serviceException.printStackTrace();
		}
	}
}