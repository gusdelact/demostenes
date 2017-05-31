package com.gfi.bin.admctasweb.procesoautomatico.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContextTest.xml"})
public class BitacoraCargaAutomaticaServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraCargaAutomaticaServiceTest.class);
	
	@Autowired
	BitacoraCargaAutomaticaService bitacoraCargaAutomaticaService;
	
	@Test
	@Transactional
	public void guardarCargaAutomaticaTest(){
		LOGGER.debug("Comienza guardarCargaAutomaticaTest");
		
		List<BitacoraCargaAutomaticaModel> listaCargaAutomatica = new ArrayList<BitacoraCargaAutomaticaModel>();
		
		//cargaAutomaticaCasa
		BitacoraCargaAutomaticaModel cargaAutomaticaCasa = new BitacoraCargaAutomaticaModel();
		cargaAutomaticaCasa.setExcelConciliador("excelConciliadorCasa.xls");
		cargaAutomaticaCasa.setFechaHoraRegistro(new Date());
		cargaAutomaticaCasa.setIdEmpresa(1);
			List<BitacoraCargaAutomaticaOficioModel> listaOficiosCgaAut = new ArrayList<BitacoraCargaAutomaticaOficioModel>();
				BitacoraCargaAutomaticaOficioModel oficio1 = new BitacoraCargaAutomaticaOficioModel();
				oficio1.setIdEmpresa(1);
				oficio1.setNumeroOficio("NUM_OFI_1");
				oficio1.setTipoOficio("HA");
				oficio1.setExisteXML("S");
					List<BitacoraCargaAutomaticaPersonaModel> listaPersonasCgaAut = new ArrayList<BitacoraCargaAutomaticaPersonaModel>();
					BitacoraCargaAutomaticaPersonaModel persona1 = new BitacoraCargaAutomaticaPersonaModel();
					persona1.setIdEmpresa(1);
					persona1.setNumeroOficio("NUM_OFI_1");
					persona1.setTipoOficio("HA");
					persona1.setNumeroConsecutivoPersona(1);
					persona1.setObservaciones("No se puede insertar, RFC con longitud 15");
					
					BitacoraCargaAutomaticaPersonaModel persona2 = new BitacoraCargaAutomaticaPersonaModel();
					persona2.setIdEmpresa(1);
					persona2.setNumeroOficio("NUM_OFI_1");
					persona2.setTipoOficio("HA");
					persona2.setNumeroConsecutivoPersona(2);
					persona2.setObservaciones("No se puede insertar, no hay nombre que es obligatorio");
					
					listaPersonasCgaAut.add(persona1);
					listaPersonasCgaAut.add(persona2);
				oficio1.setListaPersonas(listaPersonasCgaAut);
				
			listaOficiosCgaAut.add(oficio1);
		cargaAutomaticaCasa.setListaOficios(listaOficiosCgaAut);
		
		
		//cargaAutomaticaBanco
		BitacoraCargaAutomaticaModel cargaAutomaticaBanco = new BitacoraCargaAutomaticaModel();
		cargaAutomaticaBanco.setExcelConciliador("excelConciliadorBanco.xls");
		cargaAutomaticaBanco.setFechaHoraRegistro(new Date());
		cargaAutomaticaBanco.setIdEmpresa(2);
		cargaAutomaticaBanco.setListaOficios(null);
		
		
		//cargaAutomaticaISOSI
		BitacoraCargaAutomaticaModel cargaAutomaticaISOSI = new BitacoraCargaAutomaticaModel();
		cargaAutomaticaISOSI.setExcelConciliador("excelConciliadorISOSI.xls");
		cargaAutomaticaISOSI.setFechaHoraRegistro(new Date());
		cargaAutomaticaISOSI.setIdEmpresa(8);
		cargaAutomaticaISOSI.setListaOficios(null);
		
		listaCargaAutomatica.add(cargaAutomaticaCasa);
		listaCargaAutomatica.add(cargaAutomaticaBanco);
		listaCargaAutomatica.add(cargaAutomaticaISOSI);
		try{
			bitacoraCargaAutomaticaService.guardarCargaAutomatica(listaCargaAutomatica);
			
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
			serviceException.printStackTrace();
		}
	}
}