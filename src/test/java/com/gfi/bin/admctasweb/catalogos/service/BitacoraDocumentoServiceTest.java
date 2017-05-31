package com.gfi.bin.admctasweb.catalogos.service;

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

import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.BitacoraDoctosEliminadosModel;
import com.gfi.bin.admctasweb.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class BitacoraDocumentoServiceTest {
	
	@Autowired
	private BitacoraDocumentoService bitacoraDocumentoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraDocumentoServiceTest.class);
		
	@Test
	@Transactional
	public void guardarTest() {
		LOGGER.info("GuardarTest iniciado");
		
		try{
			DocumentoEliminadoModel documentoEliminado = new DocumentoEliminadoModel();
			
			documentoEliminado.setNumOficio("000-0/DDD-000000/2020");
			documentoEliminado.setTipoOficio("HA");
			documentoEliminado.setCveDocto("OF");
			documentoEliminado.setNomDocto("NOMBRE DOCUMENTO PRUEBA");
			documentoEliminado.setFhElim(new Date());
			documentoEliminado.setCveUsuElim("PUDE");
			
			bitacoraDocumentoService.guardar(documentoEliminado);
			LOGGER.info("GuardarTest terminado");
		}catch(ServiceException serviceException){
			LOGGER.error(serviceException.getLocalizedMessage());
		}
	}

	@Test
	public void consultarDocsEliminadosTest() {
		LOGGER.info("consultarDocsEliminadosTest() - Inicia");
		
		List<DocumentoEliminadoModel> docsEliminadosList = null;
		
		try {
			
			BitacoraDoctosEliminadosModel parametros = new BitacoraDoctosEliminadosModel();			
			parametros.setNumOficio("220-1/2137194/2013");			
			LOGGER.info("ConsultarDocsEliminados por N\u00famero de Oficio: " + parametros.getNumOficio());
			docsEliminadosList = bitacoraDocumentoService.consultarDocsEliminados(parametros);
			this.imprimirAtributos(docsEliminadosList);

			parametros = new BitacoraDoctosEliminadosModel();
			parametros.setTipoOficio("AS");
			LOGGER.info("ConsultarDocsEliminados por Tipo de Oficio: " + parametros.getTipoOficio());			
			docsEliminadosList = bitacoraDocumentoService.consultarDocsEliminados(parametros);
			this.imprimirAtributos(docsEliminadosList);
			
			parametros = new BitacoraDoctosEliminadosModel();
			parametros.setfInicio(Util.stringToDate("01/01/2013", "dd/MM/yyyy"));
			parametros.setfFin(Util.stringToDate("31/05/2014", "dd/MM/yyyy"));
			LOGGER.info("ConsultarDocsEliminados por Rango de Fechas: " + parametros.getfInicio() + " y " + parametros.getfFin());
			docsEliminadosList = bitacoraDocumentoService.consultarDocsEliminados(parametros);
			this.imprimirAtributos(docsEliminadosList);
						
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("consultarDocsEliminadosTest() - Termina");
	}
	
	private void imprimirAtributos(List<DocumentoEliminadoModel> docsEliminadosList) {
		if ( docsEliminadosList!= null && !docsEliminadosList.isEmpty()) {
			for (DocumentoEliminadoModel docEliminadoModel: docsEliminadosList) {
				LOGGER.info(docEliminadoModel.toString());
			}
		}
		
	}
	
	
}
