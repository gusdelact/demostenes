/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gfi.bin.admctasweb.reportes.controller;

import com.gfi.bin.admctasweb.reportes.model.BitacoraReporteR29;
import com.gfi.bin.admctasweb.reportes.model.ErroresRegistrosReporteR29;
import com.gfi.bin.admctasweb.reportes.model.IntentoReporteR29;
import com.gfi.bin.admctasweb.reportes.model.Periodo;
import com.gfi.bin.admctasweb.reportes.service.AseguramientosTransferenciasDesbloqueosCuentasService;
import com.gfi.corp.model.ExtjsResponseMsg;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gfi.security.exception.SecurityContextInspectionException;
import com.gfi.security.util.SecurityContextInspector;

/**
 *
 * @author COOO4524
 */
@Controller
public class AseguramientosTransferenciasDesbloqueosCuentasController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AseguramientosTransferenciasDesbloqueosCuentasController.class);
    
    @Autowired
	private AseguramientosTransferenciasDesbloqueosCuentasService aseguramientosTransferenciasDesbloqueosCuentasService;
    

    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaIntento.htm")
    public @ResponseBody
    ExtjsResponseMsg generaIntento(
            @RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo) throws Exception {
        
        String cveUsuario = SecurityContextInspector.getPrincipal().getUsername();
        
        LOGGER.info("Inicia metodo generaIntento capa controller");
        LOGGER.info("Id  Empresa: "+idEmpresa);
        LOGGER.info("Cve Periodo: "+cvePeriodo);
        LOGGER.info("Cve Usuario: "+cveUsuario);
        
        Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasService.generaIntento(idEmpresa, cvePeriodo, cveUsuario);
        
        String mensajeRespuesta = (String) mapaResultados.get("PS_DESC_PROCESO");
        
        LOGGER.info("Resultado de variable mapaResultados: "+mapaResultados);
        
        ExtjsResponseMsg response = new ExtjsResponseMsg(null, mensajeRespuesta);
        
        return response;
    }

    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/obtenIntentosRealizados.htm")
    public @ResponseBody Map<String, Object> obtenIntentosRealizados(
    		@RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo) {
        
        LOGGER.info("Inicia metodo obtenIntentosRealizados capa controller");
        LOGGER.info("Id  Empresa: "+idEmpresa);
        LOGGER.info("Cve Periodo: "+cvePeriodo);
        
        Map<String, Object> response = new HashMap<String, Object>();
        
        List<IntentoReporteR29> listaIntentosRealizados;
        
        listaIntentosRealizados = aseguramientosTransferenciasDesbloqueosCuentasService.obtenIntentosRealizados(idEmpresa, cvePeriodo);
        
        response.put("data", listaIntentosRealizados);
                
        return response;
    }
    
    
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/obtenPeriodos.htm")
    public @ResponseBody Map<String, Object> obtenPeriodos(
    		@RequestParam(value = "idEmpresa",    required = false) String idEmpresa) {
        
        LOGGER.info("Inicia metodo obtenPeriodos capa controller");
        LOGGER.info("Valor para idEmpresa: "+idEmpresa);
        
        Map<String, Object> response = new HashMap<String, Object>();
        
        List<Periodo> listaPeriodos;
        
        listaPeriodos = aseguramientosTransferenciasDesbloqueosCuentasService.obtenPeriodos(idEmpresa);
        
        response.put("data", listaPeriodos);
        
        LOGGER.info("Termina metodo obtenPeriodos capa controller");
                
        return response;
    }
    
    
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/buscaRegistroEnBitacora.htm")
    public @ResponseBody Map<String, Object> buscaRegistroEnBitacora(@RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
                                             						 @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo,
                                             						 @RequestParam(value = "numIntento",   required = false) String numIntento) {
        
        LOGGER.info("Inicia metodo buscaRegistroEnBitacora capa controller");
        LOGGER.info("Id  Empresa: "+idEmpresa);
        LOGGER.info("Cve Periodo: "+cvePeriodo);
        LOGGER.info("Num Intento: "+numIntento);
        
        List<BitacoraReporteR29> listaRegistrosArchivoR29 = new ArrayList<BitacoraReporteR29>();
        
         listaRegistrosArchivoR29 = aseguramientosTransferenciasDesbloqueosCuentasService.generaArchivo(idEmpresa, cvePeriodo, numIntento);
        
         Map<String, Object> response = new HashMap<String, Object>();
         
         response.put("data", listaRegistrosArchivoR29);
                
        return response;
    }
    
    
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/actualizaRegistroCaracteristicasCotitular.htm", method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg actualizaRegistroCaracteristicasCotitular( 
            @RequestParam(value = "valorCampoCaracteristicasCotitular",    required = false) String valorCampoCaracteristicasCotitular,
            @RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo,
            @RequestParam(value = "numIntento",   required = false) String numIntento,
            @RequestParam(value = "numOficio",   required = false) String numOficio,
            @RequestParam(value = "tipoOficio",   required = false) String tipoOficio,
            @RequestParam(value = "idCotitular",   required = false) String idCotitular,
            @RequestParam(value = "idCuenta",   required = false) String idCuenta){	
            
            LOGGER.info("Inicia metodo actualizaRegistroCaracteristicasCotitular capa controller");
            
            LOGGER.info("Valor nuevo para el campo  Caracteristicas Cotitular : "+valorCampoCaracteristicasCotitular);
            LOGGER.info("Id Empresa: "+idEmpresa);
            LOGGER.info("Cve Periodo: "+cvePeriodo);            
            LOGGER.info("Numero de Intento : "+numIntento);   
            LOGGER.info("Numero de Oficio  : "+numOficio);   
            LOGGER.info("Tipo de Oficio    : "+tipoOficio);   
            LOGGER.info("Id Cotitular      : "+idCotitular);   
            LOGGER.info("Id Cuenta         : "+idCuenta);   
            
            String mensajeRespuesta = "";
            
            
            int valorResultadoActualizacion = aseguramientosTransferenciasDesbloqueosCuentasService.actualizaRegistroCaracteristicasCotitular(valorCampoCaracteristicasCotitular, idEmpresa, cvePeriodo, numIntento, numOficio, tipoOficio, idCotitular, idCuenta);
            
            mensajeRespuesta = valorResultadoActualizacion != 0 ? "Actualización de registro exitosa." : "El proceso de actualizacion del registro fallo.";
            
            ExtjsResponseMsg response = new ExtjsResponseMsg(null, mensajeRespuesta);
            
            
            LOGGER.info("Termina metodo actualizaRegistroCaracteristicasCotitular capa controller");
            
            return response;
	}    
    
    
    
    
    
    

    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/integraInformacion.htm")
    public @ResponseBody
    ExtjsResponseMsg integraInformacion(
            @RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo,            
            @RequestParam(value = "fechaInicial",     required = false) String fechaInicial,            
            @RequestParam(value = "fechaFinal",     required = false) String fechaFinal,            
            @RequestParam(value = "numIntento",   required = false) String numIntento) {
        
        LOGGER.info("Inicia metodo integraInformacion capa controller");
        LOGGER.info("Id  Empresa: "+idEmpresa);
        LOGGER.info("Cve Periodo: "+cvePeriodo);
        LOGGER.info("Fecha Inicial: "+fechaInicial);
        LOGGER.info("Fecha Final: "+fechaFinal);
        LOGGER.info("Numero de Intento : "+numIntento);        
        
        Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasService.integraInformacion(idEmpresa, cvePeriodo, fechaInicial, fechaFinal, numIntento);
        
        String mensajeRespuesta = (String) mapaResultados.get("PS_DESC_PROCESO");
        
        LOGGER.info("Resultado de variable mapaResultados: "+mapaResultados);
        
        ExtjsResponseMsg response = new ExtjsResponseMsg(null, mensajeRespuesta);
        
        return response;
    }
    
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/validaInformacion.htm")
	public  @ResponseBody ExtjsResponseMsg validaInformacion( 
            @RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo,
            @RequestParam(value = "numIntento",   required = false) String numIntento,
            @RequestParam(value = "bCommit",   required = false) String bCommit) throws Exception{
    	
    		String cveUsuario = SecurityContextInspector.getPrincipal().getUsername();
            
            LOGGER.info("Inicia metodo validaInformacion capa controller");
            LOGGER.info("Id Empresa: "+idEmpresa);
            LOGGER.info("Cve Periodo: "+cvePeriodo);            
            LOGGER.info("Numero de Intento : "+numIntento);   
            LOGGER.info("Valor de Commit : "+bCommit);   
            LOGGER.info("Clave de Usuario : "+cveUsuario);
            
            Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasService.validaInformacion(idEmpresa, cvePeriodo, numIntento, bCommit, cveUsuario);            
            
            String mensajeRespuesta = (String) mapaResultados.get("PS_DESC_PROCESO");
            
            LOGGER.info("Resultado de variable mapaResultados: "+mapaResultados);
            
            ExtjsResponseMsg response = new ExtjsResponseMsg(null, mensajeRespuesta);
            
            return response;
	}
        
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/cierraPeriodo.htm")
	public  @ResponseBody ExtjsResponseMsg cierraPeriodo(
            @RequestParam(value = "idEmpresa",    required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo",   required = false) String cvePeriodo,
            @RequestParam(value = "numIntento",   required = false) String numIntento) throws Exception{
    	

    		String cveUsuario = SecurityContextInspector.getPrincipal().getUsername();
    		
    		 LOGGER.info("Inicia Proceso Cierre de periodo");
    		
    		  LOGGER.info("Inicia metodo cierraPeriodo capa controller");
              LOGGER.info("Id Empresa: "+idEmpresa);
              LOGGER.info("Cve Periodo: "+cvePeriodo);            
              LOGGER.info("Numero de Intento : "+numIntento);   
              LOGGER.info("Clave Usuario : "+cveUsuario);
             
    		 Map<String, Object> mapaResultados = aseguramientosTransferenciasDesbloqueosCuentasService.cierraPeriodo(idEmpresa, cvePeriodo, numIntento, cveUsuario);
            
    		 String mensajeRespuesta = (String) mapaResultados.get("PS_DESC_PROCESO");
             
             LOGGER.info("Resultado de variable mapaResultados: "+mapaResultados);
             
             ExtjsResponseMsg response = new ExtjsResponseMsg(null, mensajeRespuesta);
             
             LOGGER.info("Termina Proceso Cierre de periodo");
             
             return response;
	}
        
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaArchivo.htm")
    public void generaArchivo(
            HttpServletResponse response,
            @RequestParam(value = "idEmpresa", required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo", required = false) String cvePeriodo,
            @RequestParam(value = "numIntento", required = false) String numIntento,
            @RequestParam(value = "nombreReporte", required = false) String nombreReporte) throws IOException {

        List<BitacoraReporteR29> listaRegistrosArchivoR29 = new ArrayList<BitacoraReporteR29>();
        listaRegistrosArchivoR29 = aseguramientosTransferenciasDesbloqueosCuentasService.generaArchivo(idEmpresa, cvePeriodo, numIntento);

        String nombreArchivo = nombreReporte;

        File fileEntrada = new File(System.getProperty("user.home") + System.getProperty("file.separator") + nombreArchivo);

        /*Archivo no existe se crea*/
        if (!fileEntrada.exists()) {
            fileEntrada.createNewFile();
        } else {
            /*Archivo existe borra y crea nuevamente*/
            fileEntrada.delete();
            fileEntrada.createNewFile();
        }

        FileWriter w = new FileWriter(fileEntrada);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        
        
//        sb.append("CLAVE PERIODO: ");
//        sb.append(objErroresRegistrosReporteR29.getCvePeriodo());
//        sb.append("\t");
//        sb.append("NUMERO INTENTO: ");
//        sb.append(objErroresRegistrosReporteR29.getNumIntento());
//        sb.append("\t");
//        sb.append("NUMERO OFICIO: ");
//        sb.append(objErroresRegistrosReporteR29.getNumOficio());
//        sb.append("\t");
//        sb.append("DESCRIPCION: ");
//        sb.append(objErroresRegistrosReporteR29.getDescripcionError());
//
//        wr.write(sb.toString() + "\r\n");
        
        
        if(nombreArchivo.equalsIgnoreCase("INFORMACION_REGISTROS.txt")){
        	
        	for (BitacoraReporteR29 objBitacoraReporteR29 : listaRegistrosArchivoR29) {
        		
    		 /*Cargamos todos los tipo int a variables string para su validacion*/
            String reporte = Integer.toString(objBitacoraReporteR29.getReporte());
            String medioSol = Integer.toString(objBitacoraReporteR29.getMedioSol());
            String fechaSolicitud = Integer.toString(objBitacoraReporteR29.getFechaSolicitud());
            String montoSolicitado = Long.toString(objBitacoraReporteR29.getMontoSolicitado());
            String persoTitular = Integer.toString(objBitacoraReporteR29.getPersoTitular());
            String persoCotitular = Integer.toString(objBitacoraReporteR29.getPersoCotitular());
            String estado = Integer.toString(objBitacoraReporteR29.getEstado());
            String localidad = Long.toString(objBitacoraReporteR29.getLocalidad());
            String modalidad = Integer.toString(objBitacoraReporteR29.getModalidad());
            String tipoNivel = Integer.toString(objBitacoraReporteR29.getTipoNivel());
            String producto = Integer.toString(objBitacoraReporteR29.getProducto());
            String monedaCuenta = Integer.toString(objBitacoraReporteR29.getMonedaCuenta());
            String montoInicial = Long.toString(objBitacoraReporteR29.getMontoInicial());
            String operacion = Integer.toString(objBitacoraReporteR29.getOperacion());
            String fRequermiento = Integer.toString(objBitacoraReporteR29.getfRequerimiento());
            String fAplicacion = Integer.toString(objBitacoraReporteR29.getfAplicacion());
            String montoAplicacion = Long.toString(objBitacoraReporteR29.getMontoAplicacion());
            String monedaOperacion = Integer.toString(objBitacoraReporteR29.getMonedaOperacion());
            String saldoOperacion = Integer.toString(objBitacoraReporteR29.getSaldoOperacion());
        	
            StringBuilder sb = new StringBuilder();
        	sb.append("REPORTE:");
        	sb.append(this.validaNulo(reporte)+" ");
            sb.append("\t");
            sb.append("MEDIO SOLICITUD:");
            sb.append(this.validaNulo(medioSol)+" ");
            sb.append("\t");
            sb.append("AUTORIDAD:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getAutoridad()+" "));
            sb.append("\t");
            sb.append("DESCRIPCIÓN:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getDescripcion()+" "));
            sb.append("\t");
            sb.append("NO. OFICIO:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getNumOficio()+" "));
            sb.append("\t");
            sb.append("FECHA SOLICITUD:");
            sb.append(this.validaNulo(fechaSolicitud)+" ");
            sb.append("\t");
            sb.append("FOLIO SIARA:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getFolioSiara()+" "));
            sb.append("\t");
            sb.append("MONTO SOLICITADO:");
            sb.append(this.validaNulo(montoSolicitado)+" ");
            sb.append("\t");
            sb.append("PERSONA TITULAR:");
            sb.append(this.validaNulo(persoTitular)+" ");
            sb.append("\t");
            sb.append("CARACTÉR DE TITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getCaracTitular()+" "));
            sb.append("\t");
            sb.append("RFC TITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRfcTitular()+" "));
            sb.append("\t");
            sb.append("RAZON TITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRazonTitular()+" "));
            sb.append("\t");
            sb.append("NOMBRE TITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getNombreTitular()+" "));
            sb.append("\t");
            sb.append("APE. PATERNO TITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getPaternoTitular()+" "));
            sb.append("\t");
            sb.append("APE. MATERNO TITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getMaternoTitular()+" "));
            sb.append("\t");
            sb.append("PERSONA COTITULAR:");
            sb.append(this.validaNulo(persoCotitular)+" ");
            sb.append("\t");
            sb.append("CARACTÉR DE COTITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getCaracCotitular()+" "));
            sb.append("\t");
            sb.append("RFC COTITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRfcCotitular()+" "));
            sb.append("\t");
            sb.append("RAZON COTITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRazonCotitular()+" "));
            sb.append("\t");
            sb.append("NOMBRE COTITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getNombreCotitular()+" "));
            sb.append("\t");
            sb.append("APE. PATERNO COTITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getPaternoCotitular()+" "));
            sb.append("\t");
            sb.append("APE. MATERNO COTITULAR:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getMaternoCotitular()+" "));
            sb.append("\t");
            sb.append("ID. SUCURSAL:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getIdSucursal()+" "));
            sb.append("\t");
            sb.append("ESTADO:");
            sb.append(this.validaNulo(estado)+" ");
            sb.append("\t");
            sb.append("LOCALIDAD:");
            sb.append(this.validaNulo(localidad)+" ");
            sb.append("\t");
            sb.append("CODIGO POSTAL:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getCodigoPostal()+" "));
            sb.append("\t");
            sb.append("MODALIDAD:");
            sb.append(this.validaNulo(modalidad)+" ");
            sb.append("\t");
            sb.append("TIPO NIVEL:");
            sb.append(this.validaNulo(tipoNivel)+" ");
            sb.append("\t");
            sb.append("ID CUENTA:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getIdCuenta()+" "));
            sb.append("\t");
            sb.append("PRODUCTO:");
            sb.append(this.validaNulo(producto)+" ");
            sb.append("\t");
            sb.append("MONEDA CUENTA:");
            sb.append(this.validaNulo(monedaCuenta)+" ");
            sb.append("\t");
            sb.append("MONTO INICIAL:");
            sb.append(this.validaNulo(montoInicial)+" ");
            sb.append("\t");
            sb.append("OPERACION:");
            sb.append(this.validaNulo(operacion)+" ");
            sb.append("\t");
            sb.append("OFICIO REQ. OPERACIÓN:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getOficioReqOpe()+" "));
            sb.append("\t");
            sb.append("FECHA REQUERIMIENTO:");
            sb.append(this.validaNulo(fRequermiento)+" ");
            sb.append("\t");
            sb.append("SIARA INDIRECTO:");
            sb.append(this.validaNulo(objBitacoraReporteR29.getSiaraIndirecto()+" "));
            sb.append("\t");
            sb.append("FECHA APLICACIÓN:");
            sb.append(this.validaNulo(fAplicacion)+" ");
            sb.append("\t");
            sb.append("MONTO OPERACIÓN:");
            sb.append(this.validaNulo(montoAplicacion)+" ");
            sb.append("\t");
            sb.append("MONEDA OPERACIÓN:");
            sb.append(this.validaNulo(monedaOperacion)+" ");
            sb.append("\t");
            sb.append("SALDO DE OPERACIÓN:");
            sb.append(this.validaNulo(saldoOperacion)+" ");

            wr.write(sb.toString() + "\r\n");
        	
        	}
        	
        }else{
        	
        for (BitacoraReporteR29 objBitacoraReporteR29 : listaRegistrosArchivoR29) {

            StringBuilder sb = new StringBuilder();

            /*Cargamos todos los tipo int a variables string para su validacion*/
            String reporte = Integer.toString(objBitacoraReporteR29.getReporte());
            String medioSol = Integer.toString(objBitacoraReporteR29.getMedioSol());
            String fechaSolicitud = Integer.toString(objBitacoraReporteR29.getFechaSolicitud());
            String montoSolicitado = Long.toString(objBitacoraReporteR29.getMontoSolicitado());
            String persoTitular = Integer.toString(objBitacoraReporteR29.getPersoTitular());
            String persoCotitular = Integer.toString(objBitacoraReporteR29.getPersoCotitular());
            String estado = Integer.toString(objBitacoraReporteR29.getEstado());
            String localidad = Long.toString(objBitacoraReporteR29.getLocalidad());
            String modalidad = Integer.toString(objBitacoraReporteR29.getModalidad());
            String tipoNivel = Integer.toString(objBitacoraReporteR29.getTipoNivel());
            String producto = Integer.toString(objBitacoraReporteR29.getProducto());
            String monedaCuenta = Integer.toString(objBitacoraReporteR29.getMonedaCuenta());
            String montoInicial = Long.toString(objBitacoraReporteR29.getMontoInicial());
            String operacion = Integer.toString(objBitacoraReporteR29.getOperacion());
            String fRequermiento = Integer.toString(objBitacoraReporteR29.getfRequerimiento());
            String fAplicacion = Integer.toString(objBitacoraReporteR29.getfAplicacion());
            String montoAplicacion = Long.toString(objBitacoraReporteR29.getMontoAplicacion());
            String monedaOperacion = Integer.toString(objBitacoraReporteR29.getMonedaOperacion());
            String saldoOperacion = Integer.toString(objBitacoraReporteR29.getSaldoOperacion());

            sb.append(this.validaNulo(reporte));
            sb.append(";");
            sb.append(this.validaNulo(medioSol));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getAutoridad()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getDescripcion()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getNumOficio()));
            sb.append(";");
            sb.append(this.validaNulo(fechaSolicitud));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getFolioSiara()));
            sb.append(";");
            sb.append(this.validaNulo(montoSolicitado));
            sb.append(";");
            sb.append(this.validaNulo(persoTitular));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getCaracTitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRfcTitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRazonTitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getNombreTitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getPaternoTitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getMaternoTitular()));
            sb.append(";");
            sb.append(this.validaNulo(persoCotitular));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getCaracCotitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRfcCotitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getRazonCotitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getNombreCotitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getPaternoCotitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getMaternoCotitular()));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getIdSucursal()));
            sb.append(";");
            sb.append(this.validaNulo(estado));
            sb.append(";");
            sb.append(this.validaNulo(localidad));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getCodigoPostal()));
            sb.append(";");
            sb.append(this.validaNulo(modalidad));
            sb.append(";");
            sb.append(this.validaNulo(tipoNivel));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getIdCuenta()));
            sb.append(";");
            sb.append(this.validaNulo(producto));
            sb.append(";");
            sb.append(this.validaNulo(monedaCuenta));
            sb.append(";");
            sb.append(this.validaNulo(montoInicial));
            sb.append(";");
            sb.append(this.validaNulo(operacion));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getOficioReqOpe()));
            sb.append(";");
            sb.append(this.validaNulo(fRequermiento));
            sb.append(";");
            sb.append(this.validaNulo(objBitacoraReporteR29.getSiaraIndirecto()));
            sb.append(";");
            sb.append(this.validaNulo(fAplicacion));
            sb.append(";");
            sb.append(this.validaNulo(montoAplicacion));
            sb.append(";");
            sb.append(this.validaNulo(monedaOperacion));
            sb.append(";");
            sb.append(this.validaNulo(saldoOperacion));

            wr.write(sb.toString() + "\r\n");

          }
        
        }// fin de else

        wr.close();
        bw.close();

        File fileSalida = new File(System.getProperty("user.home") + System.getProperty("file.separator") + nombreArchivo);

        FileInputStream fis = new FileInputStream(fileSalida);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }

        byte[] bytes = bos.toByteArray();

        if (bytes != null) {

            ServletOutputStream out = null;
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");

            out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();

        }

    }
    
    
    @RequestMapping(value = "/reportes/AseguramientosTransferenciasDesbloqueosCuentas/generaArchivoValidaciones.htm")
    public void generaArchivoValidaciones(
            HttpServletResponse response,
            @RequestParam(value = "idEmpresa",  required = false) String idEmpresa,
            @RequestParam(value = "cvePeriodo", required = false) String cvePeriodo,
            @RequestParam(value = "numIntento", required = false) String numIntento,
            @RequestParam(value = "idGrupo",    required = false) String idGrupo) throws IOException {
    	
    	 LOGGER.info("Inicia metodo generaArchivoValidaciones capa controller");

        List<ErroresRegistrosReporteR29> listaErroresRegistroReporteR29 = new ArrayList<ErroresRegistrosReporteR29>();
        listaErroresRegistroReporteR29 = aseguramientosTransferenciasDesbloqueosCuentasService.generaArchivoValidaciones(idEmpresa, cvePeriodo, numIntento, idGrupo);

        String nombreArchivo = "Archivo_ValidacionesPeriodo"+cvePeriodo+"_"+numIntento+"_"+idGrupo+".txt";

        File fileEntrada = new File(System.getProperty("user.home") + System.getProperty("file.separator") + nombreArchivo);

        /*Archivo no existe se crea*/
        if (!fileEntrada.exists()) {
            fileEntrada.createNewFile();
        } else {
            /*Archivo existe borra y crea nuevamente*/
            fileEntrada.delete();
            fileEntrada.createNewFile();
        }

        FileWriter w = new FileWriter(fileEntrada);
        BufferedWriter bw = new BufferedWriter(w);
        PrintWriter wr = new PrintWriter(bw);
        
        if(listaErroresRegistroReporteR29.size() > 0){
        	
        	 for (ErroresRegistrosReporteR29 objErroresRegistrosReporteR29 : listaErroresRegistroReporteR29) {

                 StringBuilder sb = new StringBuilder();
                 
                 sb.append("CLAVE PERIODO: ");
                 sb.append(objErroresRegistrosReporteR29.getCvePeriodo());
                 sb.append("\t");
                 sb.append("NUMERO INTENTO: ");
                 sb.append(objErroresRegistrosReporteR29.getNumIntento());
                 sb.append("\t");
                 sb.append("NUMERO OFICIO: ");
                 sb.append(objErroresRegistrosReporteR29.getNumOficio());
                 sb.append("\t");
                 sb.append("DESCRIPCION: ");
                 sb.append(objErroresRegistrosReporteR29.getDescripcionError());

                 wr.write(sb.toString() + "\r\n");

             }// final de for
        	
        }else{
        	
        	 StringBuilder sb = new StringBuilder();
        	 
        	 sb.append("EL PERIODO SELECCIONADO NO CONTIENE ERRORES EN LOS REGISTROS ASOCIADOS, PUEDE PROCEDER AL CIERRE DEL PERIODO.");
        	 
        	 wr.write(sb.toString() + "\r\n");
        	
        }
        

       

        wr.close();
        bw.close();

        File fileSalida = new File(System.getProperty("user.home") + System.getProperty("file.separator") + nombreArchivo);

        FileInputStream fis = new FileInputStream(fileSalida);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];

        for (int readNum; (readNum = fis.read(buf)) != -1;) {
            bos.write(buf, 0, readNum);
        }

        byte[] bytes = bos.toByteArray();

        if (bytes != null) {

            ServletOutputStream out = null;
            response.setContentType("text/plain");
            response.setHeader("Content-Disposition", "attachment;filename=\"" + nombreArchivo + "\"");

            out = response.getOutputStream();
            out.write(bytes);
            out.flush();
            out.close();

        }

    }
    
    
    private String validaNulo(String valor) {

        String vacio = "";

        if (valor == null) {
            return vacio;
        } else {
            return valor;
        }

    }

}
