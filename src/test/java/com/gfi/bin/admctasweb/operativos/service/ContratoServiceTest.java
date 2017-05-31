package com.gfi.bin.admctasweb.operativos.service;

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

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/applicationContextTest.xml"})
public class ContratoServiceTest {

	@Autowired
	private ContratoService contratoService;
	
	final Logger log = LoggerFactory.getLogger(ContratoServiceTest.class);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	
	
	@Test
	public void testObtenerContratos() {
		
		Long idPersona = 5478l;
		List<ContratoModel> contratos = null;
		try {
			contratos = contratoService.obtenerContratos(idPersona, new Long(0));
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertNotNull(contratos);
		
		for(ContratoModel contrato : contratos)
		{
			log.info(contrato.getIdContrato() + "||" + contrato.getCliente());
		}
	}

	
	@Test
	public void testObtenerContratosCambios() {
		Long idPersona = 5478l;
		List<ContratoCambiosModel> contratos = null;
		
		try {
			contratos= contratoService.obtenerContratosCambios(idPersona);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertNotNull(contratos);
		for(ContratoCambiosModel contrato : contratos)
		{
			log.info(contrato.getIdContrato() + "||" + contrato.getSituacionContrato());
		}
	}

}
