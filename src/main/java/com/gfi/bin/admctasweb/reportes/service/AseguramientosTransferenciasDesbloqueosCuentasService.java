/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gfi.bin.admctasweb.reportes.service;

import java.util.List;
import java.util.Map;

import com.gfi.bin.admctasweb.reportes.model.BitacoraReporteR29;
import com.gfi.bin.admctasweb.reportes.model.ErroresRegistrosReporteR29;
import com.gfi.bin.admctasweb.reportes.model.IntentoReporteR29;
import com.gfi.bin.admctasweb.reportes.model.Periodo;

/**
 *
 * @author COOO4524
 */
public interface AseguramientosTransferenciasDesbloqueosCuentasService {

	  public Map<String, Object> generaIntento(String idEmpresa, String cvePeriodo, String cveUsuario);

	    public List<IntentoReporteR29> obtenIntentosRealizados(String idEmpresa, String cvePeriodo);
	    
	    public List<Periodo> obtenPeriodos(String idEmpresa);

	    public Map<String, Object> integraInformacion(String idEmpresa, String cvePeriodo, String fechaIni, String fechaFin, String numIntento);

	    public Map<String, Object> validaInformacion(String idEmpresa, String cvePeriodo, String numIntento, String valorCommit, String cveUsuario);

	    public Map<String, Object> cierraPeriodo(String idEmpresa, String cvePeriodo, String numIntento, String cveUsuario);

	    public List<BitacoraReporteR29> generaArchivo(String idEmpresa, String cvePeriodo, String numIntento);
	    
	    public List<ErroresRegistrosReporteR29> generaArchivoValidaciones(String idEmpresa, String cvePeriodo, String numIntento, String idGrupo);
	    
	    public int actualizaRegistroCaracteristicasCotitular(String valorCampoCaracteristicasCotitular, String idEmpresa, String cvePeriodo, String numIntento, String numOficio, String tipoOficio, String idCotitular, String idCuenta);

}
