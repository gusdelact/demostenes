/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gfi.bin.admctasweb.reportes.dao.impl;

import com.gfi.bin.admctasweb.reportes.dao.AseguramientosTransferenciasDesbloqueosCuentasDao;

import com.gfi.bin.admctasweb.reportes.mapper.BitacoraReporteR29Mapper;
import com.gfi.bin.admctasweb.reportes.mapper.ErroresRegistrosReporteR29Mapper;
import com.gfi.bin.admctasweb.reportes.mapper.IntentoReporteR29Mapper;
import com.gfi.bin.admctasweb.reportes.mapper.PeriodoMapper;
import com.gfi.bin.admctasweb.reportes.model.BitacoraReporteR29;
import com.gfi.bin.admctasweb.reportes.model.ErroresRegistrosReporteR29;
import com.gfi.bin.admctasweb.reportes.model.IntentoReporteR29;
import com.gfi.bin.admctasweb.reportes.model.Periodo;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 *
 * @author COOO4524
 */

@Component
@Repository
public class AseguramientosTransferenciasDesbloqueosCuentasDaoImpl implements AseguramientosTransferenciasDesbloqueosCuentasDao{

	
  private static final Logger LOGGER = LoggerFactory.getLogger(AseguramientosTransferenciasDesbloqueosCuentasDaoImpl.class);
	
  @Autowired
	private JdbcTemplate jdbcTemplateCorp;
  
   @Autowired                     
	private SimpleJdbcCall generaIntento;
   
   @Autowired                     
	private SimpleJdbcCall validaInformacion;
   
   @Autowired                     
	private SimpleJdbcCall integraInformacion;
   
   @Autowired                     
	private SimpleJdbcCall cierraPeriodo;

		 public Map<String, Object> generaIntento(String idEmpresa, String cvePeriodo, String cveUsuario) {
		 
		 LOGGER.info("Inicia metodo generaIntento capa dao");
		 
		 Map<String, Object> out = new HashMap<String, Object>();
		 
		 /*Parametros de entrada para procedimiento almacenado*/
		 
		 SqlParameterSource spParams = new MapSqlParameterSource()
		                 .addValue("PE_ID_EMPRESA",  idEmpresa,  Types.VARCHAR)
		                 .addValue("PE_CVE_PERIODO", cvePeriodo, Types.INTEGER)
		                 .addValue("PE_CVE_USUARIO", cveUsuario, Types.VARCHAR);
		 
		 out = generaIntento.declareParameters(
		             new SqlParameter("PE_ID_EMPRESA",      Types.VARCHAR),
		             new SqlParameter("PE_CVE_PERIODO",     Types.INTEGER),
		             new SqlParameter("PE_CVE_USUARIO",     Types.VARCHAR),
		             new SqlOutParameter("PS_NUM_INTENTO",  Types.INTEGER),
		             new SqlOutParameter("PS_COD_RESP",     Types.INTEGER),
		             new SqlOutParameter("PS_DESC_PROCESO", Types.VARCHAR)
		     ).withoutProcedureColumnMetaDataAccess().execute(spParams);
		                 
		 LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SALIDA DE MAPA OUT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ out);
		 
		 return out;
		}


	    public List<IntentoReporteR29> obtenIntentosRealizados(String idEmpresa, String cvePeriodo) {
		 
		         LOGGER.info("Inicia metodo obtenIntentosRealizados capa dao");
		 
		         List<IntentoReporteR29> listaIntentosRealizados = null;
		 
		         StringBuilder sb = new StringBuilder();
		         sb.append("select ccp.CVE_USU_ALTA, ccp.ID_EMPRESA, ccp.CVE_PERIODO, ccp.NUM_INTENTO, cp.ESTATUS as SITUACION, cp.F_INICIAL, cp.F_FINAL, ccp.ID_GRUPO ");
		         sb.append("from CNBV_CTRL_PERIODO ccp, CNBV_PERIODO cp ");
		         sb.append("where ccp.ID_EMPRESA = cp.ID_EMPRESA ");
		         sb.append("and ccp.CVE_PERIODO = cp.CVE_PERIODO ");
		         sb.append("and ccp.ID_EMPRESA = ");
		         sb.append(idEmpresa);
		         sb.append("and ccp.CVE_PERIODO = ");
		         sb.append(cvePeriodo);                
		 
		         LOGGER.info("Consulta a ejecutar: "+sb.toString());
		         
		         listaIntentosRealizados = jdbcTemplateCorp.query(sb.toString(), new IntentoReporteR29Mapper());
		 
		         LOGGER.info("Tamaño de la lista: " + listaIntentosRealizados.size());
		 
		         return listaIntentosRealizados;
		 
		     }
     

    public Map<String, Object> integraInformacion(String idEmpresa, String cvePeriodo, String fechaIni, String fechaFin, String numIntento) {
        
        LOGGER.info("Inicia metodo integraInformacion capa dao");
        
        /*Parseamos fechas tipo String a tipo Date*/
        Date fechaIniDate = null;
        Date fecheFinDate = null;
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        try {
			fechaIniDate = df.parse(fechaIni);
			fecheFinDate = df.parse(fechaFin);
			
		} catch (ParseException e) {
			LOGGER.error("Error al parsear las fechas en el metodo integraInformacion");
		}
        
        
        
        Map<String, Object> out = new HashMap<String, Object>();
        
        /*Parametros de entrada para procedimiento almacenado*/
        
        SqlParameterSource spParams = new MapSqlParameterSource()
                        .addValue("PE_ID_EMPRESA",  idEmpresa,  Types.VARCHAR)
                        .addValue("PE_CVE_PERIODO", cvePeriodo, Types.INTEGER)
                        .addValue("PE_FECHA_INI",   fechaIniDate,   Types.DATE)
                        .addValue("PE_FECHA_FIN",   fecheFinDate,   Types.DATE)
                        .addValue("PE_NUM_INTENTO", numIntento, Types.INTEGER);
        
        out = integraInformacion.declareParameters(
                    new SqlParameter("PE_ID_EMPRESA",      Types.VARCHAR),
                    new SqlParameter("PE_CVE_PERIODO",     Types.INTEGER),
                    new SqlParameter("PE_FECHA_INI",       Types.DATE),
                    new SqlParameter("PE_FECHA_FIN",       Types.DATE),
                    new SqlParameter("PE_NUM_INTENTO",     Types.INTEGER),
                    new SqlOutParameter("PS_COD_RESP",     Types.INTEGER),
                    new SqlOutParameter("PS_DESC_PROCESO", Types.VARCHAR)
            ).withoutProcedureColumnMetaDataAccess().execute(spParams);
                        
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SALIDA DE MAPA OUT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ out);
        
        return out;
    }

    public Map<String, Object> validaInformacion(String idEmpresa, String cvePeriodo, String numIntento, String valorCommit, String cveUsuario) {
        
        LOGGER.info("Inicia metodo validaInformacion capa dao");
        
        Map<String, Object> out = new HashMap<String, Object>();
        
        /*Parametros de entrada para procedimiento almacenado*/
        
        SqlParameterSource spParams = new MapSqlParameterSource()
                        .addValue("PE_ID_EMPRESA",  idEmpresa,   Types.VARCHAR)
                        .addValue("PE_CVE_PERIODO", cvePeriodo,  Types.INTEGER)
                        .addValue("PE_NUM_INTENTO", numIntento,  Types.INTEGER)
                        .addValue("PE_B_COMMIT",    valorCommit, Types.VARCHAR)
                        .addValue("PE_CVE_USUARIO", cveUsuario,  Types.VARCHAR);
        
        out = validaInformacion.declareParameters(
                    new SqlParameter("PE_ID_EMPRESA",      Types.VARCHAR),
                    new SqlParameter("PE_CVE_PERIODO",     Types.INTEGER),
                    new SqlParameter("PE_NUM_INTENTO",     Types.INTEGER),
                    new SqlParameter("PE_B_COMMIT",        Types.VARCHAR),
                    new SqlParameter("PE_CVE_USUARIO",     Types.VARCHAR),
                    new SqlOutParameter("PS_ID_GRUPO",     Types.INTEGER),
                    new SqlOutParameter("PS_COD_RESP",     Types.INTEGER),
                    new SqlOutParameter("PS_DESC_PROCESO", Types.VARCHAR)
            ).withoutProcedureColumnMetaDataAccess().execute(spParams);
                        
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SALIDA DE MAPA OUT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ out);
        
        return out;
        
    }

        public Map<String, Object> cierraPeriodo(String idEmpresa, String cvePeriodo, String numIntento, String cveUsuario) {
        
        
        LOGGER.info("Inicia metodo cierraPeriodo capa dao");
        
        Map<String, Object> out = new HashMap<String, Object>();
        
        /*Parametros de entrada para procedimiento almacenado*/
        
        SqlParameterSource spParams = new MapSqlParameterSource()
                        .addValue("PE_ID_EMPRESA",  idEmpresa,   Types.VARCHAR)
                        .addValue("PE_CVE_PERIODO", cvePeriodo,  Types.INTEGER)
                        .addValue("PE_NUM_INTENTO", numIntento,  Types.INTEGER)
                        .addValue("PE_CVE_USUARIO", cveUsuario,  Types.VARCHAR);
        
        out = cierraPeriodo.declareParameters(
                    new SqlParameter("PE_ID_EMPRESA",      Types.VARCHAR),
                    new SqlParameter("PE_CVE_PERIODO",     Types.INTEGER),
                    new SqlParameter("PE_NUM_INTENTO",     Types.INTEGER),
                    new SqlParameter("PE_CVE_USUARIO",     Types.VARCHAR),
                    new SqlOutParameter("PS_COD_RESP",     Types.INTEGER),
                    new SqlOutParameter("PS_DESC_PROCESO", Types.VARCHAR)
            ).withoutProcedureColumnMetaDataAccess().execute(spParams);
                        
        LOGGER.info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<< SALIDA DE MAPA OUT>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ out);
        
        return out;
        
    }

    public List<BitacoraReporteR29> generaArchivo(String idEmpresa, String cvePeriodo, String numIntento) {
        
        LOGGER.info("Inicia metodo generaArchivo capa dao");
        
        List<BitacoraReporteR29> listaRegistrosArchivoR29 = new ArrayList<BitacoraReporteR29>();
        
        StringBuilder sb = new StringBuilder();
        
        sb.append("SELECT ID_EMPRESA, CVE_PERIODO, NUM_INTENTO, TIPO_OFICIO, ID_COTITULAR, REPORTE, MEDIO_SOL, AUTORIDAD, DESCRIPCION, NUM_OFICIO, ");
        sb.append("F_SOLICITUD, FOLIO_SIARA, MONTO_SOLICITADO, PERSO_TITULAR, CARAC_TITULAR, RFC_TITULAR, RAZON_TITULAR, ");
        sb.append("NOMBRE_TITULAR, PATERNO_TITULAR, MATERNO_TITULAR, PERSO_COTI, CARAC_COTI, RFC_COTITULAR, RAZON_COTI, ");
        sb.append("NOMBRE_COTI, PATERNO_COTI, MATERNO_COTI, ID_SUCURSAL, ESTADO, LOCALIDAD, CODIGO_POSTAL, ");
        sb.append("MODALIDAD, TIPO_NIVEL, ID_CUENTA, PRODUCTO, MONEDA_CUENTA, MONTO_INICIAL, OPERACION, ");
        sb.append("OFICIO_REQ_OPE, F_REQUERIMIENTO, SIARA_INDIRECTO, F_APLICACION, MONTO_OPERCION, MONEDA_OPERACION, SALDO_OPERACION ");
        sb.append("FROM cnbv_bitacora ");
        sb.append(" WHERE ID_EMPRESA = ");
        sb.append(idEmpresa);
        sb.append(" AND CVE_PERIODO = ");
        sb.append(cvePeriodo);
        sb.append(" AND NUM_INTENTO = ");
        sb.append(numIntento);
        sb.append(" order by num_oficio");
        
        LOGGER.info("Consulta a ejecutar: "+sb.toString());
        
        listaRegistrosArchivoR29 = jdbcTemplateCorp.query(sb.toString(), new BitacoraReporteR29Mapper());
        
        return listaRegistrosArchivoR29;
        
    }


 public int actualizaRegistroCaracteristicasCotitular(String valorCampoCaracteristicasCotitular, String idEmpresa, String cvePeriodo, String numIntento, String numOficio, String tipoOficio, String idCotitular, String idCuenta) {
      
	    LOGGER.info("Inicia metodo actualizaRegistroCaracteristicasCotitular capa dao");
	 
	 
        int valorResultadoActualizacion = 0;
        
        StringBuilder sb = new StringBuilder();
        sb.append("update CNBV_BITACORA cb ");
        sb.append("set cb.CARAC_COTI = '");
        sb.append(valorCampoCaracteristicasCotitular);
        sb.append("' ");
        sb.append("where cb.ID_EMPRESA =");
        sb.append(idEmpresa);
        sb.append(" and cb.CVE_PERIODO = ");
        sb.append(cvePeriodo);
        sb.append(" and cb.NUM_INTENTO = ");
        sb.append(numIntento);
        sb.append(" and cb.NUM_OFICIO = '");
        sb.append(numOficio);
        sb.append("'");
        sb.append(" and cb.TIPO_OFICIO ='");
        sb.append(tipoOficio);
        sb.append("'");
        sb.append(" and cb.ID_COTITULAR =");
        sb.append(idCotitular);
        sb.append(" and cb.ID_CUENTA = '");
        sb.append(idCuenta);
        sb.append("'");
        
        LOGGER.info("Consulta a ejecutar: "+sb.toString());
        
        valorResultadoActualizacion = jdbcTemplateCorp.update(sb.toString());
        
        return valorResultadoActualizacion;
        
    }


public List<ErroresRegistrosReporteR29> generaArchivoValidaciones(
		String idEmpresa, String cvePeriodo, String numIntento, String idGrupo) {


	  LOGGER.info("Inicia metodo generaArchivoValidaciones capa dao");
      
      List<ErroresRegistrosReporteR29> listaErroresRegistrosReporteR29 = new ArrayList<ErroresRegistrosReporteR29>();
      
      StringBuilder sb = new StringBuilder();
      
      sb.append("select cbe.CVE_PERIODO, cbe.NUM_INTENTO, cbe.NUM_OFICIO, cbe.DESCRIPCION from CNBV_BIT_ERROR cbe");
      sb.append(" where cbe.ID_EMPRESA = '");
      sb.append(idEmpresa);
      sb.append("'");
      sb.append(" and cbe.CVE_PERIODO =");
      sb.append(cvePeriodo);
      sb.append(" and cbe.NUM_INTENTO =");
      sb.append(numIntento);
      sb.append(" and cbe.ID_GRUPO =");
      sb.append(idGrupo);
      
      LOGGER.info("Consulta a ejecutar: "+sb.toString());
      
      listaErroresRegistrosReporteR29 = jdbcTemplateCorp.query(sb.toString(), new ErroresRegistrosReporteR29Mapper());
      
      return listaErroresRegistrosReporteR29;
	
	
}


public List<Periodo> obtenPeriodos(String idEmpresa) {
	
	 LOGGER.info("Inicia metodo obtenPeriodos capa dao");
     
     List<Periodo> listaPeriodos = new ArrayList<Periodo>();
     
     StringBuilder sb = new StringBuilder();
     
     if(idEmpresa != null || !idEmpresa.isEmpty()){
    	 sb.append("select cp.cve_periodo from cnbv_periodo cp where cp.ID_EMPRESA ="+idEmpresa);
     }else{
    	 sb.append("select cp.cve_periodo from cnbv_periodo cp");
     }
     
     LOGGER.info("Consulta a ejecutar: "+sb.toString());
     
     listaPeriodos = jdbcTemplateCorp.query(sb.toString(), new PeriodoMapper());
     
     return listaPeriodos;
}
    
}
