package com.gfi.bin.admctasweb.operativos.dao;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/applicationContextTest.xml"})
public class RespuestaDaoTest {

	@Autowired
	RespuestaDao respuestaDao;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGuardarRespuesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testEliminarRespuesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testBuscarRespuestasPorOficio() {
		fail("Not yet implemented");
	}

	@Test
	public void testExisteRespuesta() {
		fail("Not yet implemented");
	}

	@Test
	public void testMarcarTipoOficio() {
		String numOficio = "214-1-168672/2007";
		String tipoOficio = "AS";
		int resultado = 0;
		try {
			resultado = respuestaDao.marcarTipoOficio(numOficio, tipoOficio);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue(resultado == 0);
	}

}
