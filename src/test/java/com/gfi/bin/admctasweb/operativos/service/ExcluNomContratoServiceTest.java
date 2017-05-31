package com.gfi.bin.admctasweb.operativos.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/applicationContextTest.xml"})
@Transactional
public class ExcluNomContratoServiceTest {
	
	@Autowired
	private ExcluNomContratoService excluNomContratoService;
	
	private final Logger logger = LoggerFactory.getLogger(ExcluNomContratoServiceTest.class);
	
	@Test
	public void obtenerSiguienteValorSecuenciaTest(){
		logger.debug("obtenerSiguienteValorSecuenciaTest");
		Integer valorSecuencia = 0;
		try{
			valorSecuencia = excluNomContratoService.obtenerSiguienteValorSecuencia();
			logger.debug("valorSecuencia es: "+valorSecuencia);
			
		}catch(ServiceException serviceException){
			logger.error("Excepcion en obtenerSiguienteValorSecuenciaTest: "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void insertarExclusionTest(){
		logger.debug("insertarExclusionTest");
		ExclusionModel exclusion = new ExclusionModel();
		
		exclusion.setDescripcionExclusion("BANCO DD NUEVO S.A de C.V.");
		exclusion.setActivo(true);
		
		try{
			Boolean resultado = excluNomContratoService.insertarExclusion(exclusion);
			
			Assert.assertTrue(resultado);
		}catch(ServiceException serviceException){
			logger.error("Error en insertarExclusionTest: "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void actualizarExclusionTest(){
		logger.debug("actualizarExclusionTest");
		ExclusionModel exclusion = new ExclusionModel();
		
		exclusion.setIdExclusion(5);
		exclusion.setDescripcionExclusion("BANQUINI");
		exclusion.setActivo(false);
		
		try{
			Boolean resultado = excluNomContratoService.actualizarExclusion(exclusion);
			
			Assert.assertTrue(resultado);
		}catch(ServiceException serviceException){
			logger.error("Error en actualizarExclusion: "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void eliminarExclusionTest(){
		logger.debug("eliminarExclusionTest");
		Integer idExclusion = 6;
		
		try{
			Boolean resultado = excluNomContratoService.eliminarExclusion(idExclusion);
			
			Assert.assertTrue(resultado);
		}catch(ServiceException serviceException){
			logger.error("Error en eliminarExclusionTest: "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void obtenerExclusionesActivasTest(){
		logger.debug("obtenerExclusionesActivasTest(estatus)");
		Boolean estatus = true;
		
		try{
			List<ExclusionModel> listaResultados = excluNomContratoService.obtenerExclusiones(estatus);
			
			Assert.assertNotNull(listaResultados);
			
			if(listaResultados != null){
				logger.debug("listaResultados.size(): "+listaResultados.size());
				
				for(ExclusionModel exclusion : listaResultados){
					logger.debug("\nid: "+exclusion.getIdExclusion());
					logger.debug("descripcion: "+exclusion.getDescripcionExclusion());
					logger.debug("activo: "+exclusion.getActivo()+"\n");
				}
			}
		}catch(ServiceException serviceException){
			logger.error("Error en obtenerExclusionesActivasTest(estatus): "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void obtenerExclusionesTodasTest(){
		logger.debug("obtenerExclusionesActivasTodasTest");
		
		try{
			List<ExclusionModel> listaResultados = excluNomContratoService.obtenerExclusiones();
			
			Assert.assertNotNull(listaResultados);
			
			if(listaResultados != null){
				logger.debug("listaResultados.size(): "+listaResultados.size());
			}
		}catch(ServiceException serviceException){
			logger.error("Error en obtenerExclusionesActivasTodasTest: "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void obtenerExclusionPorNombreTest(){
		logger.debug("obtenerExclusionPorNombreTest");
		
		String nombreExclusion = "BANCO";
		
		try{
			List<ExclusionModel> listaResultados = excluNomContratoService.obtenerExclusionPorNombre(nombreExclusion);
			
			Assert.assertNotNull(listaResultados);
			
			if(listaResultados != null){
				logger.debug("listaResultados.size(): "+listaResultados.size());
			}
		}catch(ServiceException serviceException){
			logger.error("Error en obtenerExclusionPorNombreTest: "+serviceException.getLocalizedMessage());
		}
	}
	
	@Test
	public void obtenerExclusionPorIdTest(){
		logger.debug("obtenerExclusionPorNombreTest");
		
		Integer idExclusion = 5;
		
		try{
			ExclusionModel resultado = excluNomContratoService.obtenerExclusionPorId(idExclusion);
			
			Assert.assertNotNull(resultado);
			
			if(resultado != null){
				logger.debug("listaResultados.getId(): "+resultado.getIdExclusion());
				logger.debug("listaResultados.getDescripcion(): "+resultado.getDescripcionExclusion());
				logger.debug("listaResultados.getActivo(): "+resultado.getActivo());
			}
		}catch(ServiceException serviceException){
			logger.error("Error en obtenerExclusionPorNombreTest: "+serviceException.getLocalizedMessage());
		}
	}
}