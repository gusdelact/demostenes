package com.gfi.bin.admctasweb.catalogos.service;

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
import com.gfi.bin.admctasweb.comunes.model.ItemModel;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"/applicationContextTest.xml"}
)
public class EstatusSeguimientoServiceTest {

	@Autowired
	EstatusSeguimientoService estatusSeguimientoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EstatusSeguimientoServiceTest.class);

	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testObtenerEstatusPorClave() {
		ItemModel estatus = null;
		try {
			estatus = estatusSeguimientoService.obtenerEstatusPorClave("ENVRESC");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertNotNull(estatus);
		LOGGER.info(estatus.getDescripcion());
	}

}
