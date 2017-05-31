/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.service;

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

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;
import com.gfi.bin.admctasweb.util.Util;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class CasosEspecialesServiceTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(CasosEspecialesServiceTest.class);
	
	@Autowired
	private OficioService oficioService;
		
	
	//@Test
	public void consultarOficiosCasosEspeciales() {
		LOGGER.info("consultarOficiosCasosEspeciales - Inicia");
		
		try {
		
			List<CasosEspecialesModel> casosEspecialesList = null;
			
			Date fInicio = Util.stringToDate("01/08/2013", "dd/MM/yyyy");
			Date fFin = Util.stringToDate("31/05/2014", "dd/MM/yyyy");
			LOGGER.info("consultarCasosEspeciales: ==>");
			//casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(fIniRecepcion, fFinRecepcion, fIniRespuesta, fFinRespuesta, idEmpresa, cveAutoridad, tipoOficio);
			
			CasosEspecialesListModel parametros = new CasosEspecialesListModel();
			parametros.setfIniRecepcion(fInicio);
			parametros.setfFinRecepcion(fFin);
			casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(parametros);
			if (casosEspecialesList != null) {
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales 1: ==> size[" + casosEspecialesList.size() + "]");
			}
			
			parametros = new CasosEspecialesListModel();
			parametros.setfIniRespuesta(fInicio);
			parametros.setfFinRespuesta(fFin);
			casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(parametros);
			if (casosEspecialesList != null) {
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales 2: ==> size[" + casosEspecialesList.size() + "]");
			}

			parametros = new CasosEspecialesListModel();
			parametros.setIdEmpresa(2);
			casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(parametros);
			if (casosEspecialesList != null) {
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales 3: ==> size[" + casosEspecialesList.size() + "]");
			}

			parametros = new CasosEspecialesListModel();
			parametros.setCveAutoridad("IMSS");
			casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(parametros);
			if (casosEspecialesList != null) {
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales 4: ==> size[" + casosEspecialesList.size() + "]");
			}
			
			parametros = new CasosEspecialesListModel();
			parametros.setTipoOficio("AS");
			casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(parametros);
			if (casosEspecialesList != null) {
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales 5: ==> size[" + casosEspecialesList.size() + "]");
			}

			parametros = new CasosEspecialesListModel();
			casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(parametros);
			if (casosEspecialesList != null) {
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales 6: ==> size[" + casosEspecialesList.size() + "]");
			}			
			
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("consultarOficiosCasosEspeciales - Termina");
	}
	
	@Test
	@Transactional
	public void guardarTieneAcuse() {
		LOGGER.info("guardarTieneAcuse - Inicia");		
		try {
			String numOficio = "214-1-1445929/2014";
			String tipoOficio = "AS";
			
			OficioModel oficio = this.oficioService.buscarOficioPorLlave(numOficio, tipoOficio);
			if (oficio != null) {
				LOGGER.info("Caso Especial Original: numOficio [" + oficio.getNumOficio() + "], tipoOficio [" + oficio.getTipoOficio() + "], tieneAcuse [" + oficio.getTieneAcuse() + "]");
				
				oficio.setTieneAcuse(!oficio.getTieneAcuse());
				oficio.setCveUsuMod(Util.usuarioSesion());
				oficio.setFhUltMod(new Date());
				
				this.oficioService.guardarOficio(oficio);
				oficio = this.oficioService.buscarOficioPorLlave(numOficio, tipoOficio);
				if (oficio != null) {
					LOGGER.info("Caso Especial Modificado: numOficio [" + oficio.getNumOficio() + "], tipoOficio [" + oficio.getTipoOficio() + "], tieneAcuse [" + oficio.getTieneAcuse() + "]");
				}
			}
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
		LOGGER.info("guardarTieneAcuse - Termina");
	}
	
}
