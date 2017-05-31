/**
 * 
 */
package com.gfi.corp.component.genericgridman.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gfi.corp.component.genericgridman.dto.ExportarGridReq;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;
import com.gfi.corp.component.genericgridman.service.ExporterService;

/**
 * Controlador para las peticiones del exporter del Grid Manual.
 * @author LUGL4884
 *
 */
@Controller
@RequestMapping(value = "/exporterman")
public class ExporterController {
	private static Logger logger = LoggerFactory.getLogger(ExporterController.class);
	
	@Autowired
	private ExporterService exporterService;
	
	@RequestMapping(value = "/exportar")
	public void generarExportacion(
			HttpServletResponse response,
			@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String filter,
			@RequestParam(required = false) String tipo,
			@RequestParam(required = false) String nombre,
			@RequestParam(required = false) String cveGrid,
			@RequestParam(required = false) String extParam) throws Exception {			
		logger.debug("ExporterController.generarReporte");
		String nombreRep = "";
		
		GenericGridReq gridReq = new GenericGridReq();
		gridReq.setFilter(filter);
		gridReq.setSort(sort);
		gridReq.setLimit(limit);
		gridReq.setPage(page);		
		
		ExportarGridReq req = new ExportarGridReq();
		req.setGridParams(gridReq);
		req.setNombre(nombre);
		req.setTipo(tipo);
		req.setCveGrid(cveGrid);
		req.setExtParam(extParam);
		
		byte [] respuestaWS = this.exporterService.generarExportacion(req);

		if(tipo.equalsIgnoreCase("PDF")){
			nombreRep = nombre + ".pdf";
		}else{
			nombreRep = nombre + ".xlsx";
		}
		
		response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment; filename=" + nombreRep);

        ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
        baos.write(respuestaWS);
        baos.writeTo(response.getOutputStream());
        response.flushBuffer();
	}

}
