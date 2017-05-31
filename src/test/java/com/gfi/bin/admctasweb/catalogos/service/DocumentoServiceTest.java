package com.gfi.bin.admctasweb.catalogos.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class DocumentoServiceTest{
	
	@Autowired
	private DocumentoService documentoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentoServiceTest.class);
	
	@Test
	@Transactional
	public void guardarTest(){
		LOGGER.info("GuardarTest iniciado");
		
		try{
			DocumentoModel documento = new DocumentoModel();
			
			documento.setNumOficio("000-0/DDD-000000/2020");
			documento.setTipoOficio("HA");
			documento.setNumDocto(2);
			
			documento.setCveDocto("OF");
			documento.setNomDocto("NOMBRE DOCUMENTO PRUEBA");
			documento.setFhAlta(new Date());
			documento.setCveUsuAlta("USU_ALTA");
			documento.setFhUltMod(new Date());
			documento.setCveUsuMod("USU_MOD");
			
			documentoService.guardarDocumento(documento);
			LOGGER.info("GuardarTest terminado");
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	@Transactional
	public void modificarTest(){
		LOGGER.info("ModificarTest iniciado");
		
		try{
			DocumentoModel documento = new DocumentoModel();
			
			documento.setNumOficio("000-0/DDD-000000/2020");
			documento.setTipoOficio("HA");
			documento.setNumDocto(1);
			
			documento.setCveDocto("ON");
			documento.setNomDocto("DOCUMENTOX......");
			documento.setFhAlta(new Date());
			documento.setCveUsuAlta("USU_ALTA");
			documento.setFhUltMod(new Date());
			documento.setCveUsuMod("USU_MOD");
			
			documentoService.modificarDocumento(documento);
			LOGGER.info("ModificarTest terminado");
			
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
		}
	}
	
	
	@Test
	@Transactional
	public void eliminarTest(){
		LOGGER.info("EliminarTest iniciado");
		
		try{
			DocumentoModel documento = new DocumentoModel();
			
			documento.setNumOficio("000-0/DDD-000000/2020");
			documento.setTipoOficio("HA");
			documento.setNumDocto(1);
			
			documentoService.eliminarDocumento(documento);
			LOGGER.info("EliminarTest terminado");
			
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
		}
	}
	
	public void guardarDocumentosArchivoTest(){
		LOGGER.debug("guardarDocumentoArchivoTest");
		
		
		
	}
	
}
