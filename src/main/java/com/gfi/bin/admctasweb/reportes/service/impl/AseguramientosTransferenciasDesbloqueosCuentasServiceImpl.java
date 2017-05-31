/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gfi.bin.admctasweb.reportes.service.impl;

import com.gfi.bin.admctasweb.reportes.dao.AseguramientosTransferenciasDesbloqueosCuentasDao;
import com.gfi.bin.admctasweb.reportes.model.BitacoraReporteR29;
import com.gfi.bin.admctasweb.reportes.model.ErroresRegistrosReporteR29;
import com.gfi.bin.admctasweb.reportes.model.IntentoReporteR29;
import com.gfi.bin.admctasweb.reportes.model.Periodo;
import com.gfi.bin.admctasweb.reportes.service.AseguramientosTransferenciasDesbloqueosCuentasService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author COOO4524
 */

@Component
@Service(value = "AseguramientosTransferenciasDesbloqueosCuentasService")
public class AseguramientosTransferenciasDesbloqueosCuentasServiceImpl implements AseguramientosTransferenciasDesbloqueosCuentasService{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(AseguramientosTransferenciasDesbloqueosCuentasServiceImpl.class);

	@Autowired
	private AseguramientosTransferenciasDesbloqueosCuentasDao aseguramientosTransferenciasDesbloqueosCuentasDao;

	public Map<String, Object> generaIntento(String idEmpresa, String cvePeriodo, String cveUsuario) {

		LOGGER.info("Inicia metodo generaIntento capa service");

		Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasDao.generaIntento(idEmpresa, cvePeriodo, cveUsuario);

		return mapaResultados;

	}

	public List<IntentoReporteR29> obtenIntentosRealizados(String idEmpresa, String cvePeriodo) {

		LOGGER.info("Inicia metodo obtenIntentosRealizados capa service");

		List<IntentoReporteR29> listaIntentosRealizados;

		listaIntentosRealizados = aseguramientosTransferenciasDesbloqueosCuentasDao.obtenIntentosRealizados(idEmpresa, cvePeriodo);

		return listaIntentosRealizados;
	}

    public Map<String, Object> integraInformacion(String idEmpresa, String cvePeriodo, String fechaIni, String fechaFin, String numIntento) {
        LOGGER.info("Inicia metodo integraInformacion capa service");
        
        Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasDao.integraInformacion(idEmpresa, cvePeriodo, fechaIni, fechaFin, numIntento);
        
        return mapaResultados;

    }

    public Map<String, Object> validaInformacion(String idEmpresa, String cvePeriodo, String numIntento, String valorCommit, String cveUsuario) {
        LOGGER.info("Inicia metodo validaInformacion capa service");
        
        Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasDao.validaInformacion(idEmpresa, cvePeriodo, numIntento, valorCommit, cveUsuario);
        
        return mapaResultados;
        
        
    }

    public Map<String, Object> cierraPeriodo(String idEmpresa, String cvePeriodo, String numIntento, String cveUsuario) {
        LOGGER.info("Inicia metodo cierraPeriodo capa service");
        
         Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasDao.cierraPeriodo(idEmpresa, cvePeriodo, numIntento, cveUsuario);
         
         return mapaResultados;
    }

    public List<BitacoraReporteR29> generaArchivo(String idEmpresa, String cvePeriodo, String numIntento) {
        
        
        LOGGER.info("Inicia metodo generaArchivo capa service");
        
        List<BitacoraReporteR29> listaRegistrosArchivoR29 = new ArrayList<BitacoraReporteR29>();
        
        listaRegistrosArchivoR29 = aseguramientosTransferenciasDesbloqueosCuentasDao.generaArchivo(idEmpresa, cvePeriodo, numIntento);
        
        return listaRegistrosArchivoR29;
        
        
    }

    public int actualizaRegistroCaracteristicasCotitular(String valorCampoCaracteristicasCotitular, String idEmpresa, String cvePeriodo, String numIntento, String numOficio, String tipoOficio, String idCotitular, String idCuenta) {
        
   	 LOGGER.info("Inicia metodo actualizaRegistroCaracteristicasCotitular capa service");
   	
       int valorResultadoActualizacion = aseguramientosTransferenciasDesbloqueosCuentasDao.actualizaRegistroCaracteristicasCotitular(valorCampoCaracteristicasCotitular, idEmpresa, cvePeriodo, numIntento, numOficio, tipoOficio, idCotitular, idCuenta);
       
       return valorResultadoActualizacion;
       
   }

	public List<ErroresRegistrosReporteR29> generaArchivoValidaciones(
			String idEmpresa, String cvePeriodo, String numIntento, String idGrupo) {
		
		  LOGGER.info("Inicia metodo generaArchivoValidaciones capa service");
	        
	        List<ErroresRegistrosReporteR29> listaErroresRegistrosReporteR29 = new ArrayList<ErroresRegistrosReporteR29>();
	        
	        listaErroresRegistrosReporteR29 = aseguramientosTransferenciasDesbloqueosCuentasDao.generaArchivoValidaciones(idEmpresa, cvePeriodo, numIntento, idGrupo);
	        
	        return listaErroresRegistrosReporteR29;
	}

	public List<Periodo> obtenPeriodos(String idEmpresa) {
		  
		LOGGER.info("Inicia metodo obtenPeriodos capa service");
		
		List<Periodo> listaPeriodos = new ArrayList<Periodo>();
		
		listaPeriodos = aseguramientosTransferenciasDesbloqueosCuentasDao.obtenPeriodos(idEmpresa);
		
		return listaPeriodos;
	}
    
}
