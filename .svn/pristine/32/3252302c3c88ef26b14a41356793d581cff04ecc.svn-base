package com.gfi.bin.admctasweb.reportes.controller;

import java.io.IOException;
import java.util.List;

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
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoListModel;
import com.gfi.bin.admctasweb.reportes.model.OficioSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Clase de Control para Bitácora de Seguimiento.
 * @author LUGL4884
 *
 */
@Controller
public class BitacoraSeguimientoController {
	private static final Logger log = LoggerFactory.getLogger(BitacoraSeguimientoController.class);
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	

	@RequestMapping(value = "/reportes/bitacoraseguimiento/buscarseguimiento.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg buscarOficiosSeguimiento(@RequestBody BitacoraSeguimientoListModel vo) throws Exception{
		log.info("En el Metodo buscarOficiosSeguimiento");
		
		//Buscamos los Oficios de acuerdo a los Filtros de Busqueda.
		List<OficioSeguimientoModel> oficioList = this.bitacoraSeguimientoService.buscarOficiosSeguimiento(vo);
		 
		vo.setOficioList(oficioList);
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(vo);
		
		return response;
	}
	
	@RequestMapping(value = "/reportes/bitacoraseguimiento/buscarbitacora.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg buscarSeguimiento(@RequestBody BitacoraSeguimientoListModel vo) throws Exception{
		log.info("En el Metodo buscarSeguimientoOficios");
		
		//Consultamos los registros en la Bitacora de Seguimiento del Oficio seleccionado.
		vo.setBitacoraList(this.bitacoraSeguimientoService.buscarSeguimientoPorOficio(vo.getNumOficio(), vo.getTipoOficio()));

		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(vo);
		
		return response;
	}
	
	@RequestMapping(value = "/reportes/bitacoraseguimiento/reporte.htm", method = RequestMethod.GET)
	@ResponseBody
	public void crearReporteTest(HttpServletRequest request, HttpServletResponse httpServletResponse )throws IOException{

		BitacoraSeguimientoListModel vo = new BitacoraSeguimientoListModel();
		vo.setNumOficio(request.getParameter("numOficio"));
		vo.setTipoOficio(request.getParameter("tipoOficio"));
		vo.setCveEstatus(request.getParameter("cveEstatus"));
		vo.setFhRecepIni(Util.stringToDate(request.getParameter("fhRecepIni"),Constantes.FORMATO_DDMMYYYY));
		vo.setFhRecepFin(Util.stringToDate(request.getParameter("fhRecepFin"),Constantes.FORMATO_DDMMYYYY));
		vo.setFhEnvIni(Util.stringToDate(request.getParameter("fhEnvIni"),Constantes.FORMATO_DDMMYYYY));
		vo.setFhEnvFin(Util.stringToDate(request.getParameter("fhEnvFin"),Constantes.FORMATO_DDMMYYYY));
		vo.setFhVenIni(Util.stringToDate(request.getParameter("fhVenIni"),Constantes.FORMATO_DDMMYYYY));
		vo.setFhVenFin(Util.stringToDate(request.getParameter("fhVenFin"),Constantes.FORMATO_DDMMYYYY));
		
		//Path de la imagen de Logo.
		ServletContext servletContext = request.getSession().getServletContext();
		String relativePath = "resources/images/logo_gfinan.jpg";
		String absolutePath = servletContext.getRealPath(relativePath);

		
		byte[] pdfImage = null;
		try {
			pdfImage = this.bitacoraSeguimientoService.generarReporteSeguimiento(vo, absolutePath);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(pdfImage != null){
			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + "test" + ".pdf");
			httpServletResponse.setHeader("Pragma", "no-cache");
			httpServletResponse.setContentLength(pdfImage.length);
			
		    ServletOutputStream out = httpServletResponse.getOutputStream();
		    out.write(pdfImage);
		    out.flush();
		    out.close();
		}
	}
}
