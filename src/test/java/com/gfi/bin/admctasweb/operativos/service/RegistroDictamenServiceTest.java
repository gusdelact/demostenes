package com.gfi.bin.admctasweb.operativos.service;

import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
import com.gfi.bin.admctasweb.util.Response;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/applicationContextTest.xml"})
@Transactional
public class RegistroDictamenServiceTest {
	
	@Autowired
	CnbvDictamenService cnbvDictamenService;
	
	final Logger log = LoggerFactory.getLogger(RegistroDictamenServiceTest.class);
	
	@BeforeTransaction
	public void verificarEstadoInicial(){
		System.out.println("<!------------------------Antes de la transaccion------------------------>");
	}
	
	@AfterTransaction
	public void verificarEstadoFinal(){
		System.out.println("<!***********************Despues de la transaccion************************>");
	}
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testConsultarTodas() {
		try {
			Response result = cnbvDictamenService.getAllCnbvDictamen();
			log.info("Consulta exitosa --> "  + result.isSuccess() );
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testConsultarPorId() {
		try {
			CnbvDictamenModel cnbvDictamen = new CnbvDictamenModel("214-1-458037/2006", "AS", 1L);
			Response result = cnbvDictamenService.buscarDatosOficioAndRegistro(cnbvDictamen);
			log.info("Consulta exitosa --> "  + result.isSuccess() );
			if(result.getData()!=null){
				log.info("Datos --> "  +	( (CnbvDictamenModel)result.getData()).getNumOficio() );
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testInsertar() {
		try {
			CnbvDictamenModel cnbvDictamen = new CnbvDictamenModel("214-1-458037/2006", "AS", 1L);
			cnbvDictamen.setCveUsuAlta("MANU05");
			cnbvDictamen.setFhAlta(new Date());
			Response result = cnbvDictamenService.guardarCnbvDictamen( cnbvDictamen );
			log.info("Insercion exitosa --> "  + result.isSuccess() );
			if(result.getData()!=null){
				log.info("Datos --> "  +	( (CnbvDictamenModel)result.getData()).getNumOficio() );
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testActualizar() {
		try {
			CnbvDictamenModel cnbvDictamen = new CnbvDictamenModel("214-1-458037/2006", "AS", 1L);
			cnbvDictamen.setIdPersona(1L);
			cnbvDictamen.setCveUsuMod("MANU05");
			cnbvDictamen.setFhUltMod(new Date());
			Response result = cnbvDictamenService.actualizarCnbvDictamen( cnbvDictamen );
			log.info("Actualizacion exitosa --> "  + result.isSuccess() );
			if(result.getData()!=null){
				log.info("Datos --> "  +	( (CnbvDictamenModel)result.getData()).getNumOficio() );
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExiste() {
		try {
			CnbvDictamenModel cnbvDictamen = new CnbvDictamenModel("214-1-458037/2006", "AS", 1L);
		
			boolean result = cnbvDictamenService.existeCnbvDictamen(
					cnbvDictamen.getNumOficio(), 
					cnbvDictamen.getTipoOficio(),
					cnbvDictamen.getNumConsec()
					);
			log.info("Existe --> "  + result );
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}

}
