package com.gfi.bin.admctasweb.catalogos.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"/applicationContextTest.xml"})
@Transactional
public class OficioServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OficioServiceTest.class);
	
	@Autowired
	private OficioService oficioService;
	
	@Test
	public void guardaOficioTest() throws ParseException, ServiceException{
		LOGGER.debug("Metodo guardarOficioTest");
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");
		OficioModel oficio = new OficioModel();
		
		oficio.setNumOficio("TEST OFICIO");
		oficio.setTipoOficio("HA");
		oficio.setNumFolio("22222");
		oficio.setNumExped("33333");
		oficio.setNumRegistro("111111");
		oficio.setCveAutoridad("IMSS");
		oficio.setIdEmpresa(2);
		oficio.setFhOficio(formatter.parse("05/05/2014"));
		oficio.setFhRecepcion(formatter.parse("05/05/2014"));
		oficio.setNumDiasPzo(10);
		oficio.setTxDireccion("DIRECCION");
		oficio.setTxGerencia("GERENCIA");
		oficio.setTxSubgeren("SUBGERENCIA");
		oficio.setTxAtnNom("ATENCION");
		oficio.setTxAtnPue("PUESTO");
		oficio.setbTurAudit("F");
		oficio.setSitOficio("PE");
		oficio.setFhEnvio(null);
		oficio.setCveUsuEnvio(null);
		oficio.setFhAlta(null);
		oficio.setCveUsuAlta(null);
		oficio.setFhUltMod(null);
		oficio.setCveUsuMod(null);
		oficio.setTxNomArch(null);
		oficio.setTxNomAcu(null);
		oficio.setCveUsuAcu(null);
		oficio.setFhRegAcu(null);
		oficio.setFhVencimiento(null);
		
		this.oficioService.guardarOficio(oficio);
	}
	
	@Test
	public void modificaOficioTest() throws ParseException, ServiceException{
		LOGGER.debug("Metodo modificaOficioTest");
		
		OficioModel oficio = this.oficioService.buscarOficioPorLlave("TEST OFICIO", "HA");
		oficio.setNumFolio("XXXX");
		
		this.oficioService.modificarOficio(oficio);
		
		OficioModel oficioNuevo = this.oficioService.buscarOficioPorLlave(oficio.getNumOficio(),oficio.getTipoOficio());
		LOGGER.debug(oficioNuevo.getNumFolio());
	}
	
	@Test
	public void eliminaOficioTest() throws ParseException, ServiceException{
		LOGGER.debug("Metodo eliminaOficioTest");
		
		OficioModel oficio = this.oficioService.buscarOficioPorLlave("TEST OFICIO", "HA");
		this.oficioService.eliminarOficio(oficio);	
	}
	
	@Test
	public void buscaOficioPorLlave() throws ParseException, ServiceException{
		LOGGER.debug("Metodo buscaOficioPorLlave");
		
		OficioModel oficio = this.oficioService.buscarOficioPorLlave("TEST OFICIO", "HA");
		Assert.assertNotNull(oficio);
	}
}