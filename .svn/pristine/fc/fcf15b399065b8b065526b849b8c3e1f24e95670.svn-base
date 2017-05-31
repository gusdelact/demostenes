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

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.catalogos.service.BitacoraDocumentoService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraDoctosEliminadosModel;
import com.gfi.bin.admctasweb.util.AdmctasUtil;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Controller
public class BitacoraDoctosEliminadosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraDoctosEliminadosController.class);
	
	@Autowired 
	private BitacoraDocumentoService bitacoraDoctosEliminadosService;

	@RequestMapping(value = "/reportes/bitDocsElim/imprimir.htm", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirBitacoraDoctosEliminados(HttpServletRequest request, HttpServletResponse response ) throws IOException{
		LOGGER.info("Imprimir Bit\u00e1cora de Documentos Eliminados - Inicia");
		
		BitacoraDoctosEliminadosModel vo = new BitacoraDoctosEliminadosModel();
		vo.setNumOficio(request.getParameter("numOficio"));
		vo.setTipoOficio(request.getParameter("tipoOficio"));
		vo.setfInicio(Util.stringToDate(request.getParameter("fInicio"), Constantes.FORMATO_DDMMYYYY));
		vo.setfFin(Util.stringToDate(request.getParameter("fFin"), Constantes.FORMATO_DDMMYYYY));
		LOGGER.info(vo.toString());
		
		try {
			vo = this.validarParametros(vo);
			LOGGER.info(vo.toString());

			byte[] reporte = this.bitacoraDoctosEliminadosService.generarReporte(vo,
																				 Constantes.PLANTILLA_JASPER_BITACORA_DOCTOS_ELIMINADOS,
																				 AdmctasUtil.obtenerPathImagen(request),
																				 Util.usuarioSesion());
			if(reporte != null){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=" + Constantes.PLANTILLA_JASPER_BITACORA_DOCTOS_ELIMINADOS + Constantes.EXTENSION_PDF.toLowerCase());
				response.setHeader("Pragma", "no-cache");
				response.setContentLength(reporte.length);
				
			    ServletOutputStream out;
				out = response.getOutputStream();
				out.write(reporte);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			throw new IOException(e);
		}
		LOGGER.info("Imprimir Bit\u00e1cora de Documentos Eliminados - Finaliza");
	}	

	@RequestMapping(value = "/reportes/bitDocsElim/buscar.htm", method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg leerBitacoraDoctosEliminados(@RequestBody BitacoraDoctosEliminadosModel vo) throws Exception{
		LOGGER.info("Buscar Bit\u00e1cora de Documentos Eliminados - Inicia" + vo.toString());
		
		ExtjsResponseMsg response = this.consultarBitacoraDocumentosEliminados(vo);
		
		LOGGER.info("Buscar Bit\u00e1cora de Documentos Eliminados - Finaliza");
		return response;
	}
	
	private ExtjsResponseMsg consultarBitacoraDocumentosEliminados(BitacoraDoctosEliminadosModel vo) {
		LOGGER.info("Consultar Bit\u00e1cora de Documentos Eliminados - Inicia" + vo.toString());
		String mensaje = "";
		List<DocumentoEliminadoModel> docsElimList = null;
		if (vo != null) {
			LOGGER.info(vo.toString());
			
			try {
				vo = this.validarParametros(vo);
				LOGGER.info("validarParametros: " + vo);
				docsElimList = this.bitacoraDoctosEliminadosService.consultarDocsEliminados(vo);
				if (docsElimList != null && !docsElimList.isEmpty()) {
					LOGGER.info("docsElimList size: " + docsElimList.size());
					LOGGER.info("Elemento 1: " + docsElimList.get(0).toString());
					LOGGER.info("Elemento " + (docsElimList.size()) + ": " + docsElimList.get(docsElimList.size() - 1).toString());
					vo.setDocumentoEliminadoList(docsElimList);
				} else {
					mensaje = "No existen registros en la bitácora de documentos eliminados con los filtros proporcionados";
					LOGGER.info(mensaje);
				}
				//LOGGER.info(docsElimList.toString());
			} catch (Exception e) {
				mensaje = e.getMessage();
				LOGGER.error(mensaje);
			}
		}

		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, mensaje);
		
		LOGGER.info("Consultar Bit\u00e1cora de Documentos Eliminados - Finaliza");
		return response;
		
	}
	
	private BitacoraDoctosEliminadosModel validarParametros (BitacoraDoctosEliminadosModel bitDoctosElimModel) throws Exception {
		LOGGER.info("Validar Parametros de Bit\u00e1cora de Documentos Eliminados - Inicia" + bitDoctosElimModel.toString());
		String mensaje = "";
		
		if (bitDoctosElimModel != null) {
		
			//Valida que exista al menos un parametro
			if ((bitDoctosElimModel.getNumOficio() != null && StringUtils.isNotBlank(bitDoctosElimModel.getNumOficio())) || 
				(bitDoctosElimModel.getTipoOficio() != null && StringUtils.isNotBlank(bitDoctosElimModel.getTipoOficio()) ) || 
				bitDoctosElimModel.getfInicio() != null ||
				bitDoctosElimModel.getfFin() != null) {
	
				this.validarFechas(bitDoctosElimModel.getfInicio(), bitDoctosElimModel.getfFin());
				//Si existe el numero de oficio, se eliminan los demás filtros
//				if (bitDoctosElimModel.getNumOficio() != null && StringUtils.isNotBlank(bitDoctosElimModel.getNumOficio())) {
//					
//					bitDoctosElimModel.setTipoOficio(null);
//					bitDoctosElimModel.setfInicio(null);
//					bitDoctosElimModel.setfFin(null);
//					bitDoctosElimModel.setDocumentoEliminadoList(null);
//					
//				//Si digitaron la fecha de inicio o fecha fin
//				} else {
//					LOGGER.info("Validar Rango de Fechas");
//					this.validarFechas(bitDoctosElimModel.getfInicio(), bitDoctosElimModel.getfFin());
//				}
			} else {
				mensaje = "Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda";
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
			}
		}
		LOGGER.info("Validar Parametros de Bit\u00e1cora de Documentos Eliminados - Finaliza");
		return bitDoctosElimModel;
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
	private String obtenerWhere(BitacoraDoctosEliminadosModel doctosElim) {
		LOGGER.info("obtenerWhere - Inicia" + doctosElim.toString());
		StringBuilder where = new StringBuilder();
		if (doctosElim != null) {
			if (doctosElim.getNumOficio() != null && StringUtils.isNotBlank(doctosElim.getNumOficio())) {
				where.append(" AND NUM_OFICIO = '" + doctosElim.getNumOficio() + "'");
			}
			if (doctosElim.getTipoOficio() != null && StringUtils.isNotBlank(doctosElim.getTipoOficio())) {
				where.append(" AND TIPO_OFICIO = '" + doctosElim.getTipoOficio() + "'");
			}
			if (doctosElim.getfInicio() != null && doctosElim.getfFin() != null) {
				where.append(" AND FH_ELIMINA BETWEEN TO_DATE('" + Util.dateToString(doctosElim.getfInicio(), Constantes.FORMATO_YYYYMMDD) + "','" + Constantes.FORMATO_YYYYMMDD + "')");
				where.append(" AND TO_DATE('" + Util.dateToString(doctosElim.getfFin(), Constantes.FORMATO_YYYYMMDD) +  "','" + Constantes.FORMATO_YYYYMMDD + "')");
			}
		}
		LOGGER.info("WHERE: " + where.toString());
		LOGGER.info("obtenerWhere - Finaliza");
		return (where.toString() != null && StringUtils.isNotBlank(where.toString())) ? where.toString() : "";
	}
}
