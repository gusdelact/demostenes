package com.gfi.bin.admctasweb.operativos.service;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/applicationContextTest.xml"})
public class PersonaCorporativaServiceTest {
	
	@Autowired
	PersonaCorporativaService personaCorporativaService;
	
	
	final Logger log = LoggerFactory.getLogger(PersonaCorporativaServiceTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testObtenerPersonasNombreRFC() {
		
		String rfc = "CUVE2906161E4";
		String nombre = "MANUEL REYES CASTELLANOS";
		Integer similaridad = 90;
		
		List<PersonaCorporativaModel> personas = null;
		try {
			personas = personaCorporativaService.obtenerPersonas(rfc, nombre, similaridad);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(personas);
		for(PersonaCorporativaModel personaCorporativaModel : personas)
		{
			log.debug("nombre:" + personaCorporativaModel.getNombre());
		}		
	}

	@Test
	public void testObtenerPersonasNombre() {
		
		String rfc = null;
		String nombre = "MANUEL REYES CASTELLANOS";
		Integer similaridad = 90;
		
		List<PersonaCorporativaModel> personas = null;
		try {
			personas = personaCorporativaService.obtenerPersonas(rfc, nombre, similaridad);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(personas);
		for(PersonaCorporativaModel personaCorporativaModel : personas)
		{
			log.debug("nombre:" + personaCorporativaModel.getNombre());
		}		
	}
	
	
	@Test
	public void testObtenerPersonasPorRFC() {
		
		String rfc = "CUVE2906161E4";
		Integer similaridad = 90;
		
		List<PersonaCorporativaModel> personas = null;
		try {
			personas = personaCorporativaService.obtenerPersonas(rfc, similaridad);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertNotNull(personas);
		for(PersonaCorporativaModel personaCorporativaModel : personas)
		{
			log.debug("nombre:" + personaCorporativaModel.getNombre());
		}
		
	}
	
	@Test
	public void obtenerSimilaridad()
	{
		
		SimilaridadModel similaridad = null;
		try {
			similaridad = personaCorporativaService.obtenerSimilaridad();
		} 
		catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(similaridad != null);
		
		log.debug(similaridad.getSimilaridad().toString());
	}

	
	@Test
	public void modificarSimilaridadTest()
	{
		SimilaridadModel similaridadModel = new SimilaridadModel();
		similaridadModel.setSimilaridad(80);
		similaridadModel.setFhUltMod(new Date());
		similaridadModel.setCveUsuMod("cvazquez");
		
		boolean modifico = false;
		
		
		try {
			modifico = personaCorporativaService.modificarSimilaridad(similaridadModel);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertTrue(modifico);
	}
	
	
	@Test
	public void testObtenerPersonasNombreConExclusiones() {
		
		String rfc = null;
		String nombre = "FINSAGO, S.A DE C.V.";
		Integer similaridad = 85;
		
		List<PersonaCorporativaModel> personas = null;
		try {
			personas = personaCorporativaService.obtenerPersonas(rfc, nombre, similaridad);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		Assert.assertNotNull(personas);
		for(PersonaCorporativaModel personaCorporativaModel : personas)
		{
			log.debug("nombre:" + personaCorporativaModel.getNombre());
		}
	}
}