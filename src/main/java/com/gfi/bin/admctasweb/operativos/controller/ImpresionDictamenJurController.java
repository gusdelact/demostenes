/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
import com.gfi.bin.admctasweb.operativos.model.ImpresionDictamenJurListModel;
import com.gfi.bin.admctasweb.operativos.service.CnbvDictamenService;
import com.gfi.bin.admctasweb.operativos.service.ImpresionDictamenJurService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Controller para el Módulo de Impresión de Dictamen Jurídico.
 * @author LUGL4884
 *
 */
@Controller
public class ImpresionDictamenJurController {
	final Logger log = LoggerFactory.getLogger(ImpresionDictamenJurController.class);
	
	@Autowired
	private ImpresionDictamenJurService impresionDictamenJurService;
	
	@Autowired
	private CnbvDictamenService cnbvDictamenService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	
	@RequestMapping(value = "/operativos/impresiondictamenjur/read.htm", method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg buscarRegistroDictamen(@RequestBody ImpresionDictamenJurListModel vo) throws Exception {
		
		//Consultamos los dictamenes Jurídicos del Oficio.
		vo.setImpresionDictamenList(this.impresionDictamenJurService.buscarDictamenJur(vo.getNumOficio(), vo.getTipoOficio()));
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		
		return response;
	}
	
	@RequestMapping(value = "/operativos/impresiondictamenjur/visualizar.htm", method = RequestMethod.GET)
	@ResponseBody
	public void visualizarRegistroDictamen(HttpServletRequest request, HttpServletResponse httpServletResponse )throws IOException, ServiceException {
		
		String numOficio = request.getParameter("numOficio");
		String tipoOficio = request.getParameter("tipoOficio");
		Long numConsec = Long.valueOf(request.getParameter("numConsec"));
		
		CnbvDictamenModel dictamen = new CnbvDictamenModel();
		dictamen.setNumOficio(numOficio);
		dictamen.setTipoOficio(tipoOficio);
		dictamen.setNumConsec(numConsec);
		
		//Path de la imagen de Logo.
		ServletContext servletContext = request.getSession().getServletContext();
		String relativePath = "resources/images/logo_gfinan.jpg";
		String absolutePath = servletContext.getRealPath(relativePath);
		
		try {
			dictamen = (CnbvDictamenModel) this.cnbvDictamenService.buscarDatosOficioAndRegistro(dictamen).getData();
			dictamen.setLogo(absolutePath);
		} catch (ServiceException ex) {
			log.error("Error al consultar Dictamen Jurídico: " + ex.getLocalizedMessage());
		}

		byte[] pdfImage = null;
		try {
			pdfImage = this.cnbvDictamenService.generarReporte(dictamen, "dictamenJuridico");
		} catch (Exception e) {
			log.error("Error al generar Reporte de Dictamen Jurídico: " + e.getLocalizedMessage());
		}

		if(pdfImage != null){
			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + request.getParameter("numOficio") + ".pdf");
			httpServletResponse.setHeader("Pragma", "no-cache");
			httpServletResponse.setContentLength(pdfImage.length);
			
		    ServletOutputStream out = httpServletResponse.getOutputStream();
		    out.write(pdfImage);
		    out.flush();
		    out.close();
		}
	}
	
	@RequestMapping(value = "/operativos/impresiondictamenjur/imprimir.htm", method = RequestMethod.GET)
	@ResponseBody
	public void imprimirRegistroDictamen(HttpServletRequest request, HttpServletResponse httpServletResponse )throws IOException, ServiceException {
		
		String numOficio = request.getParameter("numOficio");
		String tipoOficio = request.getParameter("tipoOficio");
		Long numConsec = Long.valueOf(request.getParameter("numConsec"));
		
		CnbvDictamenModel dictamen = new CnbvDictamenModel();
		dictamen.setNumOficio(numOficio);
		dictamen.setTipoOficio(tipoOficio);
		dictamen.setNumConsec(numConsec);
		
		//Path de la imagen de Logo.
		ServletContext servletContext = request.getSession().getServletContext();
		String relativePath = "resources/images/logo_gfinan.jpg";
		String absolutePath = servletContext.getRealPath(relativePath);
		
		//Ejecuta store para verificar si debe marcar el Dictamen Jurídico.
		// 0 - Hay Dictamenes Pendientes, No se marca el Dictamen Jurídico.
		// 1 - Se marca el Dictamen como Impreso por Contraloría.
		// 2 - El Dictamen ya estaba marcado como Impreso por Contraloría.
		int res = this.impresionDictamenJurService.marcarDictamenJur(numOficio, tipoOficio, numConsec, Util.usuarioSesion());
		log.info("Respuesta del Store de Marca de dictamen: " + res);
		
		if(res == 1) {	
			//Consultamos el registro del dictamen jurídico.
			dictamen = (CnbvDictamenModel) this.cnbvDictamenService.buscarDatosOficioAndRegistro(dictamen).getData();
			dictamen.setLogo(absolutePath);
				
			//Bitácora de Seguimiento del Oficio.
			if (this.impresionDictamenJurService.buscarDictamenSinImp(numOficio, tipoOficio) == 0) {
				BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
				seguimiento.setNumOficio(numOficio);
				seguimiento.setTipoOficio(tipoOficio);
				seguimiento.setCveEstatus(Constantes.IMPRESION_DICTAMEN_COMP);
				
				this.bitacoraSeguimientoService.guardar(seguimiento);
				
				//Envia Notificación de la Impresión de Dictamenes Jurídicos del Oficio.
				this.impresionDictamenJurService.enviarEmailSeguimientoImpDic(numOficio, tipoOficio, "Impresión de Dictamenes Jurídicos:");
			}

			byte[] pdfImage = null;
			try {
				pdfImage = this.cnbvDictamenService.generarReporte(dictamen, "dictamenJuridico");
			} catch (Exception e) {
				log.error("Error al generar Reporte de Dictamen Jurídico: " + e.getLocalizedMessage());
			}

			if(pdfImage != null) {
				httpServletResponse.setContentType("application/pdf");
				httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + request.getParameter("numOficio") + ".pdf");
				httpServletResponse.setHeader("Pragma", "no-cache");
				httpServletResponse.setContentLength(pdfImage.length);
				
			    ServletOutputStream out = httpServletResponse.getOutputStream();
			    out.write(pdfImage);
			    out.flush();
			    out.close();
			}
		}else if (res == 2){
			httpServletResponse.setContentType("text/html;charset=UTF-8"); 
			
			ServletOutputStream out = httpServletResponse.getOutputStream(); 
			
			out.println("<html>");
		    out.println("<body bgcolor=\"white\">");
		    out.println("El Dictamen Jur&iacute;dico ya fue Impreso con anterioridad, tiene la opci&oacute;n de Visualizarlo: " + numOficio + "</br>");
		    out.println("</br>Favor de Validar. </br>");
		    out.println("</body>");
		    out.println("</html>");
		    
			out.close();
		}else if (res == 0){
			httpServletResponse.setContentType("text/html;charset=UTF-8"); 
			
			ServletOutputStream out = httpServletResponse.getOutputStream(); 
			
			out.println("<html>");
		    out.println("<body bgcolor=\"white\">");
		    out.println("Existen Dictamenes Jur&iacute;dicos pendientes de generar para el Oficio: " + numOficio + "</br>");
		    out.println("</br>Favor de Validar. </br>");
		    out.println("</body>");
		    out.println("</html>");
		    
			out.close();
		}
	}

}
