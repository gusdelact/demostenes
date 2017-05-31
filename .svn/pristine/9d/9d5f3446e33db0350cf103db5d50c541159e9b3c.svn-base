/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.service;

import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/applicationContextTest.xml"})
public class DireccionesSolicitantesServiceTest extends TestCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DireccionesSolicitantesServiceTest.class);
	
	@Autowired
	private DireccionesSolicitantesService dsService;
	
	
	@Test
	public void testConsultarDireccionesSolicitantes() {
		LOGGER.info("testConsultarDireccionesSolicitantes");
		
		List<DireccionesSolicitantesModel> dsModelList = null;
		try {
			dsModelList = this.dsService.consultarDireccionesSolicitantes(new DireccionesSolicitantesModel());
			if (dsModelList != null && !dsModelList.isEmpty()) {
				for (DireccionesSolicitantesModel ds: dsModelList) {
					LOGGER.info(ds.toString());
				}
			}			
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}
	
	
	@Test
	public void insertarDireccionesSolicitantes() {
		LOGGER.info("insertarDireccionesSolicitantes");
		
		DireccionesSolicitantesModel dsModel = new DireccionesSolicitantesModel();
		
		dsModel.setTipoOficio("AS");
		dsModel.setDireccion("DIRECCION PRUEBA FMD");
		dsModel.setGerencia("GERENCIA PRUEBA FMD");
		dsModel.setSubgerencia("SUBGERENCIA PRUEBA FMD");
		dsModel.setNombreAtencion("NOMBRE PRUEBA FMD");
		dsModel.setPuestoAtencion("PUESTO PRUEBA FMD");
		dsModel.setSituacion("IN");
		dsModel.setNivel(1);
//		dsModel.setFhAlta(new Date());
//		dsModel.setCveUsuAlta("FMUNIVE1");

		LOGGER.info(dsModel.toString());
		
		try {
			this.dsService.insertarDireccionSolicitante(dsModel);
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}

	
	@Test
	public void actualizarDireccionesSolicitantes() {
		LOGGER.info("actualizarDireccionesSolicitantes");
		
		try {			
			int id = 8;
			DireccionesSolicitantesModel dsModel = new DireccionesSolicitantesModel();
			dsModel.setIdConfiguracion(id);
			
			dsModel = this.dsService.consultarDireccionSolicitantePorID(dsModel);
			if (dsModel != null) {
				dsModel.setIdConfiguracion(id);
				dsModel.setTipoOficio("AS");
				dsModel.setDireccion("DIRECCION PRUEBA FMD 2");
				dsModel.setGerencia("GERENCIA PRUEBA FMD 2");
				dsModel.setSubgerencia("SUBGERENCIA PRUEBA FMD 2");
				dsModel.setNombreAtencion("NOMBRE PRUEBA FMD 2");
				dsModel.setPuestoAtencion("PUESTO PRUEBA FMD 2");
				dsModel.setSituacion("IN");
				dsModel.setNivel(1);
//				dsModel.setFhUltMod(new Date());
//				dsModel.setCveUsuMod("FMUNIVE2");
	
				LOGGER.info(dsModel.toString());
				
				this.dsService.actualizarDireccionSolicitante(dsModel);
			}
		} catch (ServiceException e) {
			LOGGER.info(e.getMessage());
			
		}
	}
	
	
	@Test
	public void eliminarDireccionesSolicitantes() {
		LOGGER.info("eliminarDireccionesSolicitantes");
		
		try {
			
			int id = 8;
			DireccionesSolicitantesModel dsModel = new DireccionesSolicitantesModel(); 
			dsModel.setIdConfiguracion(id);
			dsModel = this.dsService.consultarDireccionSolicitantePorID(dsModel);			
			if (dsModel != null) {				
				LOGGER.info(dsModel.toString());
				this.dsService.eliminarDireccionSolicitante(dsModel);
			}		
		} catch (ServiceException e) {
			LOGGER.error(e.getMessage());
		}
	}
		
	@Test
	public void obtenerDireccionesPorTipoOficioTest() {
		
		List<ItemModel> direcciones = null;
		try {
			direcciones = dsService.obtenerDireccionesPorTipoOficio("PL");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertNotNull(direcciones);
		for(ItemModel direccion : direcciones)
			LOGGER.info(direccion.getClave() + "--" + direccion.getDescripcion());
	}
	
	
	
	
}
