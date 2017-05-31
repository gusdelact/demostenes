/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraExcepcionService;
import com.gfi.bin.admctasweb.util.AdmctasUtil;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Controller
public class BitacoraExcepcionController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraExcepcionController.class);
	
	@Autowired
	private BitacoraExcepcionService bitExcepcionService;

	@RequestMapping(value = "/reportes/bitacoraExcepcion/imprimir.htm", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirBitacoraExcepcion(HttpServletRequest request, HttpServletResponse response )throws IOException{
		LOGGER.info("Imprimir Bit\u00e1cora de Excepciones - Inicia");
		
		BitacoraExcepcionListModel vo = new BitacoraExcepcionListModel();
		vo.setIdEmpresa(request.getParameter("idEmpresa")!=null && StringUtils.isNotBlank(request.getParameter("idEmpresa")) ? Integer.parseInt(request.getParameter("idEmpresa")) : null);
		vo.setIdContrato(request.getParameter("idContrato")!=null && StringUtils.isNotBlank(request.getParameter("idContrato")) ? Long.parseLong(request.getParameter("idContrato")) : null);
		vo.setCveTipoExcepcion(request.getParameter("cveTipoExcepcion"));
		vo.setfInicio(Util.stringToDate(request.getParameter("fInicio"), Constantes.FORMATO_DDMMYYYY));
		vo.setfFin(Util.stringToDate(request.getParameter("fFin"), Constantes.FORMATO_DDMMYYYY));
		LOGGER.info(vo.toString());

		try {
			this.validarParametros(vo);
			LOGGER.info(vo.toString());
			ExtjsResponseMsg res = this.consultarBitacoraExcepcion(vo);
			BitacoraExcepcionListModel  bitacora  = (BitacoraExcepcionListModel) res.getInfo();

			byte[] reporte = this.bitExcepcionService.generarReporteBean(bitacora.getBitExcepcionList(), Constantes.PLANTILLA_JASPER_BITACORA_EXCEPCIONES, AdmctasUtil.obtenerPathImagen(request), Util.usuarioSesion());
			
			if(reporte != null){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=" + Constantes.PLANTILLA_JASPER_BITACORA_EXCEPCIONES + Constantes.EXTENSION_PDF.toLowerCase());
				response.setHeader("Pragma", "no-cache");
				response.setContentLength(reporte.length);
				
			    ServletOutputStream out = response.getOutputStream();
			    out.write(reporte);
			    out.flush();
			    out.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new IOException(e);
		}
		LOGGER.info("Imprimir Bit\u00e1cora de Excepciones - Finaliza");
	}	

	@RequestMapping(value = "/reportes/bitacoraExcepcion/buscar.htm", method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg leerBitacoraExcepcion(@RequestBody BitacoraExcepcionListModel vo) throws Exception{
		LOGGER.info("Buscar Bit\u00e1cora de Excepciones - Inicia" + vo.toString());
		
		ExtjsResponseMsg response = this.consultarBitacoraExcepcion(vo);
		
		LOGGER.info("Buscar Bit\u00e1cora de Excepciones - Finaliza");
		return response;
	}
	
	private ExtjsResponseMsg consultarBitacoraExcepcion(BitacoraExcepcionListModel vo) {
		LOGGER.info("Consultar Bit\u00e1cora de Excepciones - Inicia" + vo.toString());
		String mensaje = "";
		List<BitacoraExcepcionModel> bitacoraExcepcionList = null;
		if (vo != null) {
			LOGGER.info(vo.toString());			
			try {
				
				this.validarParametros(vo);
				
				LOGGER.info("OficioService.buscarOficiosBitacoraExcepcion()");
				bitacoraExcepcionList = this.bitExcepcionService.consultarBitacoraExcepcion(vo);
				if (bitacoraExcepcionList != null && !bitacoraExcepcionList.isEmpty()) {
					LOGGER.info("bitacoraExcepcion size: " + bitacoraExcepcionList.size());
					LOGGER.info("Elemento 1: " + bitacoraExcepcionList.get(0).toString());
					LOGGER.info("Elemento " + (bitacoraExcepcionList.size()) + ": " + bitacoraExcepcionList.get(bitacoraExcepcionList.size() - 1).toString());
					vo.setBitExcepcionList(bitacoraExcepcionList);
				} else {
					mensaje = "No existen registros en el estad\u00edstico de casos especiales con los filtros proporcionados";
					LOGGER.info(mensaje);
				}
			} catch (Exception e) {
				mensaje = e.getMessage();
				LOGGER.error(mensaje);
			}
		}
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, mensaje);
		
		LOGGER.info("Consultar Bit\u00e1cora de Excepciones - Finaliza");
		return response;		
	}

	private void validarParametros(BitacoraExcepcionListModel bitacoraExcepcionListModel) throws Exception {
		LOGGER.info("Validar Parametros de Bit\u00e1cora de Documentos Eliminados - Inicia" + bitacoraExcepcionListModel.toString());
		String mensaje = "";
		
		//Valida que exista al menos un parametro
		if (bitacoraExcepcionListModel != null) {
			if (bitacoraExcepcionListModel.getfInicio() != null || bitacoraExcepcionListModel.getfFin() != null ||
			    bitacoraExcepcionListModel.getIdEmpresa() != null || bitacoraExcepcionListModel.getIdContrato() != null ||
				(bitacoraExcepcionListModel.getCveTipoExcepcion() != null && StringUtils.isNotBlank(bitacoraExcepcionListModel.getCveTipoExcepcion()))) {
				
				if (bitacoraExcepcionListModel.getCveTipoExcepcion() == null || StringUtils.isBlank(bitacoraExcepcionListModel.getCveTipoExcepcion())) {
					mensaje = "La clave de excepci\u00f3n es requerida";
					LOGGER.info(mensaje);
					throw new Exception(mensaje);
				} else {
					LOGGER.info("Validar Rango de Fechas");
					this.validarFechas(bitacoraExcepcionListModel.getfInicio(), bitacoraExcepcionListModel.getfFin());
				}
			} else {
				mensaje = "Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda";
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
			}
		}
		LOGGER.info("Validar Parametros de Bit\u00e1cora de Documentos Eliminados - Finaliza");
	}

	private void validarFechas(Date fInicio, Date fFin) throws Exception{
		String mensaje = "";
		
		//Si digitaron la fecha de inicio o fecha fin
		if (fInicio != null || fFin != null) {
			
			//Existe la fecha inicial pero no existe la fecha fin
			if (fInicio != null && fFin == null) {
				mensaje = "Falta digitar la fecha fin";
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
				
			//Existe la fecha fin pero no existe la fecha inicial
			} else if (fInicio == null && fFin != null) {
				mensaje = "Falta digitar la fecha inicial";
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
				
			//Si la fecha inicial y fecha fin no son nulas
			} else if (fInicio != null && fFin != null) {
				
				//Si la fecha inicial es mayo a la fecha fin
				if (fInicio.after(fFin)) {
					mensaje = "La fecha fin debe ser mayor o igual a la fecha inicio";
					LOGGER.info(mensaje);
					throw new Exception(mensaje);
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private String obtenerWhere(BitacoraExcepcionListModel bitExcepcion) {
		LOGGER.info("obtenerWhere - Inicia" + bitExcepcion.toString());
		StringBuilder where = new StringBuilder();

		if (bitExcepcion != null) {
			if (bitExcepcion.getIdEmpresa() != null) {
				where.append(" AND CTO.ID_EMPRESA = " + bitExcepcion.getIdEmpresa());
			}
			if (bitExcepcion.getIdContrato() != null) {
				where.append(" AND CTO.ID_CONTRATO = " + bitExcepcion.getIdContrato());
			}
			if (bitExcepcion.getfInicio() != null && bitExcepcion.getfFin() != null) {
				where.append(" AND BEC.F_EXCEPCION BETWEEN TO_DATE('" + Util.dateToString(bitExcepcion.getfInicio(), Constantes.FORMATO_YYYYMMDD) + "','" + Constantes.FORMATO_YYYYMMDD + "')");
				where.append(" AND TO_DATE('" + Util.dateToString(bitExcepcion.getfFin(), Constantes.FORMATO_YYYYMMDD) +  "','" + Constantes.FORMATO_YYYYMMDD + "')");
			}
			if (bitExcepcion.getCveTipoExcepcion() != null && StringUtils.isNotBlank(bitExcepcion.getCveTipoExcepcion())) {
				where.append(" AND BEC.CVE_TIP_EXCEP= '" + bitExcepcion.getCveTipoExcepcion() + "'");
			}
		}
		LOGGER.info("WHERE: " + where.toString());
		LOGGER.info("obtenerWhere - Finaliza");
		return (where.toString() != null && StringUtils.isNotBlank(where.toString())) ? where.toString() : "";
	}
}
