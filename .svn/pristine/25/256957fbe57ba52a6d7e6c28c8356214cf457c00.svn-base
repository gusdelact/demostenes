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

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesListModel;
import com.gfi.bin.admctasweb.reportes.model.CasosEspecialesModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.AdmctasUtil;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Controller
public class CasosEspecialesController {
	private static final Logger LOGGER = LoggerFactory.getLogger(CasosEspecialesController.class);
	
	@Autowired
	private OficioService oficioService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	

	@RequestMapping(value = "/reportes/casosEspeciales/imprimir.htm", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirBitacoraDoctosEliminados(HttpServletRequest request, HttpServletResponse response )throws IOException{
		LOGGER.info("Imprimir Estad\u00edstico de Casos Especiales - Inicia");
		
		CasosEspecialesListModel vo = new CasosEspecialesListModel();
		vo.setIdEmpresa(StringUtils.isNotBlank(request.getParameter("idEmpresa")) ? Integer.parseInt(request.getParameter("idEmpresa")) : null);
		vo.setNumOficio(request.getParameter("numOficio"));
		vo.setCveAutoridad(request.getParameter("cveAutoridad"));
		String tipo = request.getParameter("tipoOficio");
		if(  StringUtils.isNotBlank(tipo) && !tipo.equals("undefined") ){
			vo.setTipoOficio(tipo);
		}else{
			vo.setTipoOficio(null);
		}
		
		vo.setfIniRecepcion(Util.stringToDate(request.getParameter("fIniRecepcion"), Constantes.FORMATO_DDMMYYYY));
		vo.setfFinRecepcion(Util.stringToDate(request.getParameter("fFinRecepcion"), Constantes.FORMATO_DDMMYYYY));
		vo.setfIniRespuesta(Util.stringToDate(request.getParameter("fIniRespuesta"), Constantes.FORMATO_DDMMYYYY));
		vo.setfFinRespuesta(Util.stringToDate(request.getParameter("fFinRespuesta"), Constantes.FORMATO_DDMMYYYY));
		vo.setNumConsec( StringUtils.isNotBlank(request.getParameter("numConsec")) ? Integer.parseInt(request.getParameter("numConsec")) : null );

		LOGGER.info(vo.toString());

		try {
			this.validarParametros(vo);
			LOGGER.info(vo.toString());

			byte[] reporte = this.oficioService.generarReporteCasosEspeciales(vo,
					 												 		  Constantes.PLANTILLA_JASPER_ESTADISTICO_CASOS_ESPECIALES,
					 												 		  AdmctasUtil.obtenerPathImagen(request),
					 												 		  Util.usuarioSesion(),FormatoReporte.XLS);
			if(reporte != null){
				response.setContentType("application/vnd.ms-excel");
				response.setHeader("Content-Disposition", "inline; filename=" + Constantes.PLANTILLA_JASPER_ESTADISTICO_CASOS_ESPECIALES + Constantes.EXTENSION_XLS.toLowerCase());
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
		LOGGER.info("Imprimir Estad\u00edstico de Casos Especiales - Finaliza");
	}

	@RequestMapping(value = "/reportes/casosEspeciales/buscar.htm", method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg leerCasosEspeciales(@RequestBody CasosEspecialesListModel vo) throws Exception{
		LOGGER.info("Buscar Estad\u00edstico de Casos Especiales - Inicia" + vo.toString());
		
		ExtjsResponseMsg response = this.consultarOficiosCasosEspeciales(vo);
		
		LOGGER.info("Buscar Estad\u00edstico de Casos Especiales - Finaliza");
		return response;
	}
	
	private ExtjsResponseMsg consultarOficiosCasosEspeciales(CasosEspecialesListModel vo) {
		LOGGER.info("Consultar Estad\u00edstico de Casos Especiales - Inicia" + vo.toString());
		String mensaje = "";
		List<CasosEspecialesModel> casosEspecialesList = null;
		if (vo != null) {
			LOGGER.info(vo.toString());			
			try {
				
				this.validarParametros(vo);
				
				LOGGER.info("OficioService.buscarOficiosCasosEspeciales()");
				casosEspecialesList = this.oficioService.buscarOficiosCasosEspeciales(vo);
				if (casosEspecialesList != null && !casosEspecialesList.isEmpty()) {
					LOGGER.info("casosEspeciales size: " + casosEspecialesList.size());
					LOGGER.info("Elemento 1: " + casosEspecialesList.get(0).toString());
					LOGGER.info("Elemento " + (casosEspecialesList.size()) + ": " + casosEspecialesList.get(casosEspecialesList.size() - 1).toString());
					vo.setCasoEspecialList(casosEspecialesList);
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
		
		LOGGER.info("Consultar Estad\u00edstico de Casos Especiales - Finaliza");
		return response;		
	}

	private void validarParametros(CasosEspecialesListModel casosEspecialesListModel) throws Exception {
		LOGGER.info("Validar Parametros de Bit\u00e1cora de Documentos Eliminados - Inicia" + casosEspecialesListModel.toString());
		String mensaje = "";
		
		//Valida que exista al menos un parametro
		if (casosEspecialesListModel != null) {
			if (casosEspecialesListModel.getfIniRecepcion() != null || casosEspecialesListModel.getfFinRecepcion() != null || 
				casosEspecialesListModel.getfIniRespuesta() != null || casosEspecialesListModel.getfFinRespuesta() !=null ||
			    casosEspecialesListModel.getIdEmpresa() != null ||
			    (casosEspecialesListModel.getCveAutoridad() != null && StringUtils.isNotBlank(casosEspecialesListModel.getCveAutoridad())) || 
			    (casosEspecialesListModel.getNumOficio() != null && StringUtils.isNotBlank(casosEspecialesListModel.getNumOficio())) || 
			    (casosEspecialesListModel.getTipoOficio() != null && StringUtils.isNotBlank(casosEspecialesListModel.getTipoOficio())) ||
			    (casosEspecialesListModel.getNumConsec() != null )
			    
					) {
				
				//valida fecha de recepcion
				LOGGER.info("Validar Fechas de Recepci\u00f3n");
				this.validarFechas(casosEspecialesListModel.getfIniRecepcion(),casosEspecialesListModel.getfFinRecepcion(), "recepci\u00f3n"); 
	
				//valida fecha de respuesta
				LOGGER.info("Validar Fechas de Respuesta");
				this.validarFechas(casosEspecialesListModel.getfIniRespuesta(),casosEspecialesListModel.getfFinRespuesta(), "respuesta");
				
			} else {
				mensaje = "Debe de seleccionar alg\u00fan filtro para poder realizar la b\u00fasqueda";
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
				
			}
		}
		LOGGER.info("Validar Parametros de Bit\u00e1cora de Documentos Eliminados - Finaliza");
	}

	private void validarFechas(Date fInicio, Date fFin, String tipoFecha) throws Exception{
		String mensaje = "";
		
		//Si digitaron la fecha de inicio o fecha fin
		if (fInicio != null || fFin != null) {
			
			//Existe la fecha inicial pero no existe la fecha fin
			if (fInicio != null && fFin == null) {
				mensaje = "Falta digitar la fecha fin de " + tipoFecha;
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
				
			//Existe la fecha fin pero no existe la fecha inicial
			} else if (fInicio == null && fFin != null) {
				mensaje = "Falta digitar la fecha inicial de " + tipoFecha;
				LOGGER.info(mensaje);
				throw new Exception(mensaje);
				
			//Si la fecha inicial y fecha fin no son nulas
			} else if (fInicio != null && fFin != null) {
				
				//Si la fecha inicial es mayo a la fecha fin
				if (fInicio.after(fFin)) {
					mensaje = "La fecha fin de " + tipoFecha + " debe ser mayor o igual a la fecha inicio de " + tipoFecha;
					LOGGER.info(mensaje);
					throw new Exception(mensaje);
				}
			}
		}
	}
	
	@RequestMapping(value = "/reportes/casosEspeciales/guardar.htm", method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarCasosEspeciales(@RequestBody CasosEspecialesListModel vo) throws Exception{
		LOGGER.info("Guardar Estad\u00edstico de Casos Especiales - Inicia" + vo.toString());
		String mensaje = "";
		if (vo != null) {
			//valida que la lista de modificados contenga elementos
			if (vo.getCasoEspecialModList()!= null && !vo.getCasoEspecialModList().isEmpty()) {
				
				for(CasosEspecialesModel casoEspecial: vo.getCasoEspecialModList()) {
					OficioModel oficio = this.oficioService.buscarOficioPorLlave(casoEspecial.getNumOficio(), casoEspecial.getTipoOficio()); 
					if (oficio != null) {
						
						oficio.setTieneAcuse(casoEspecial.getTieneAcuse());
						oficio.setFhUltMod(new Date());
						oficio.setCveUsuMod(Util.usuarioSesion());
						
						//Se Guarda la bandera de Acuse en los Oficios para los casos Especiales (Positivos).
						this.oficioService.modificarOficio(oficio);
						
						//Generamos el seguimiento del Oficio.
						BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
						seguimiento.setNumOficio(oficio.getNumOficio());
						seguimiento.setTipoOficio(oficio.getTipoOficio());
						seguimiento.setCveEstatus(Constantes.REGISTRO_ACUSE_COMP);
						
						this.bitacoraSeguimiento.guardar(seguimiento);
					}
				}
				mensaje = "Se guardaron los cambios exitosamente";
			} else {
				mensaje = "No existen elementos modificados para guardarse";
			}
		}
		
		ExtjsResponseMsg response = this.consultarOficiosCasosEspeciales(vo);
		List<String> mensajes = null;
		if (mensaje != null && StringUtils.isNotBlank(mensaje)){
			if (response != null ) {
				mensajes = response.getMessages();
			}
			mensajes.add(mensaje);
			response.setMessages(mensajes);
		}
		LOGGER.info("Guardar Estad\u00edstico de Casos Especiales - Finaliza");
		return response;
	}
	
	@RequestMapping(value = "/reportes/casosEspeciales/impPdf.htm", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirBitacoraDoctosEliminadosPDF(HttpServletRequest request, HttpServletResponse response )throws IOException{
		LOGGER.info("Imprimir Estad\u00edstico de Casos Especiales impPdfimpPdfimpPdf - Inicia");
		
		CasosEspecialesListModel vo = new CasosEspecialesListModel();
		vo.setIdEmpresa(StringUtils.isNotBlank(request.getParameter("idEmpresa")) ? Integer.parseInt(request.getParameter("idEmpresa")) : null);
		vo.setNumOficio(request.getParameter("numOficio"));
		vo.setCveAutoridad(request.getParameter("cveAutoridad"));
		
		String tipo = request.getParameter("tipoOficio");
		if(  StringUtils.isNotBlank(tipo) && !tipo.equals("undefined") ){
			vo.setTipoOficio(tipo);
		}else{
			vo.setTipoOficio(null);
		}
		
		vo.setfIniRecepcion(Util.stringToDate(request.getParameter("fIniRecepcion"), Constantes.FORMATO_DDMMYYYY));
		vo.setfFinRecepcion(Util.stringToDate(request.getParameter("fFinRecepcion"), Constantes.FORMATO_DDMMYYYY));
		vo.setfIniRespuesta(Util.stringToDate(request.getParameter("fIniRespuesta"), Constantes.FORMATO_DDMMYYYY));
		vo.setfFinRespuesta(Util.stringToDate(request.getParameter("fFinRespuesta"), Constantes.FORMATO_DDMMYYYY));
		
		vo.setNumConsec( StringUtils.isNotBlank(request.getParameter("numConsec")) ? Integer.parseInt(request.getParameter("numConsec")) : null );
		
		LOGGER.info(vo.toString());

		try {
			this.validarParametros(vo);
			LOGGER.info(vo.toString());

			byte[] reporte = this.oficioService.generarReporteCasosEspeciales(vo,
					 												 		  Constantes.PLANTILLA_JASPER_ESTADISTICO_CASOS_ESPECIALES,
					 												 		  AdmctasUtil.obtenerPathImagen(request),
					 												 		  Util.usuarioSesion(),FormatoReporte.PDF);
			if(reporte != null){
				response.setContentType("application/pdf");
				response.setHeader("Content-Disposition", "inline; filename=" + Constantes.PLANTILLA_JASPER_ESTADISTICO_CASOS_ESPECIALES + Constantes.EXTENSION_PDF.toLowerCase());
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
		LOGGER.info("Imprimir Estad\u00edstico de Casos Especiales  PDF- Finaliza");
	}
}
