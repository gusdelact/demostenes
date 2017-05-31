/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;

/**
 * @author lugl4884
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"/applicationContextTest.xml"}
)
public class RespuestaServiceTest {
	final Logger log = LoggerFactory.getLogger(RespuestaServiceTest.class);
	
	@Autowired
	RespuestaService respuestaService;
	
	//@Test
	public void guardarRespuesta(){
		
		RespuestaModel respuesta = new RespuestaModel();
		
		respuesta.setNumOficio("OFICIO TEST");
		respuesta.setTipoOficio("JU");
		respuesta.setNumConsec(Long.valueOf(3));
		respuesta.setIdContrato(Long.valueOf(302094186));
		respuesta.setTipoRespuesta(Long.valueOf(1));
		respuesta.setTipoCaso("PO");
		respuesta.setSitEnvio("NE");
		respuesta.setbMedioElec("F");
		respuesta.setNomTitular("PRUEBA TIRULAR");
		respuesta.setTipoTitular(Long.valueOf(1));
		respuesta.setSitCuenta(Long.valueOf(1));
		respuesta.setFhAlta(new Date());
		respuesta.setCveUsuAlta("LUGL4884");
		
		try {
			this.respuestaService.guardarRespuesta(respuesta);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void eliminarRespuesta(){
		
		RespuestaModel respuesta = new RespuestaModel();
		
		respuesta.setNumOficio("OFICIO TEST");
		respuesta.setTipoOficio("JU");
		respuesta.setNumConsec(Long.valueOf(3));
		respuesta.setIdContrato(Long.valueOf(302094186));
		respuesta.setTipoRespuesta(Long.valueOf(1));
		respuesta.setTipoCaso("PO");
		respuesta.setSitEnvio("NE");
		respuesta.setbMedioElec("F");
		respuesta.setNomTitular("PRUEBA TIRULAR");
		respuesta.setTipoTitular(Long.valueOf(1));
		respuesta.setSitCuenta(Long.valueOf(1));
		respuesta.setFhAlta(new Date());
		respuesta.setCveUsuAlta("LUGL4884");
		
		try {
			this.respuestaService.eliminarRespuesta(respuesta);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	//@Test
	public void buscarRespuestasPorOficio() {
		
		List<RespuestaModel> lista = new ArrayList<RespuestaModel>();
		RespuestaModel resp = new RespuestaModel();
		
		try {
			lista = this.respuestaService.buscarRespuestasPorOficio("OFICIO TEST", "JU");
			
			log.debug("Tamaño de la Lista:" + String.valueOf(lista.size()));
			
			Iterator<RespuestaModel> it = lista.iterator();
			while (it.hasNext()) {
				resp = it.next();
				
				log.debug(resp.getNumOficio());
				log.debug(resp.getTipoOficio());
				log.debug(resp.getNumConsec().toString());
				log.debug(resp.getIdContrato().toString());
				log.debug(resp.getTipoCaso());
			}
			
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void generaObjetoRespuesta() {
		RespuestaModel objetoResp = new RespuestaModel();
		
		try {
			RespuestaModel respuesta = new RespuestaModel();
			
			respuesta.setNumOficio("214-1-168672/2007");
			respuesta.setTipoOficio("AS");
			respuesta.setNumConsec(Long.valueOf(1));
			respuesta.setIdContrato(Long.valueOf(100030125));
			respuesta.setTipoRespuesta(Long.valueOf(1));
			respuesta.setTipoBusqueda("DPVISTA");
			
			objetoResp = this.respuestaService.generaObjetoRespuesta(respuesta, "POSITIVA");
			
			Assert.assertNotNull(objetoResp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
