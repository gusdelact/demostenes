/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.EstatusSeguimientoService;
import com.gfi.bin.admctasweb.reporte_r29.model.ControlModel;
import com.gfi.bin.admctasweb.reporte_r29.model.PeriodoModel;
import com.gfi.bin.admctasweb.reporte_r29.service.PeriodoService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;
import com.tangosol.coherence.component.net.Message;


/**
 * @author LUGL4884
 * Controlador para Periodo
 */
@Controller
public class PeriodoController {
	private final Logger log = LoggerFactory.getLogger(PeriodoController.class);
	
	@Autowired
	private PeriodoService periodoService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
//	@Autowired
//	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	@RequestMapping(value = "/reporte_r29/periodo/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarPeriodo(@RequestBody PeriodoModel vo) throws Exception {
		log.info("Entro al Controller Mapeo" + vo);
		String mensaje ="";
		
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
				
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());

		// Guardamos el Periodo
		List<String> messages = new ArrayList<String>();
		
		mensaje = this.periodoService.guardarPeriodo(vo);
				
		if(mensaje == ""){
			mensaje="El Periodo se guardó correctamente.";
		}
				
		messages.add(mensaje);

		ExtjsResponseMsg response = new ExtjsResponseMsg();

		response.setMessages(messages);
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/periodo/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updatePeriodo(@RequestBody PeriodoModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		
		List<String> messages = new ArrayList<String>();
		
		this.periodoService.modificarPeriodo(vo);
		
		//Consultamos el Periodo Acutalizado.
	    PeriodoModel type = this.periodoService.buscarPeriodoPorLlave(vo.getIdEmpresaP(), vo.getCvePeriodoP() );
		 
		messages.add("El Periodo se modificó correctamente.");

		response = new ExtjsResponseMsg(type, new String(messages.toString()));
		
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/periodo/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deletePeriodo(@RequestBody PeriodoModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.periodoService.eliminarPeriodo(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("El Periodo se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/periodo/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readPeriodo(@RequestBody PeriodoModel vo) throws Exception {
		log.info("Entro al metodo read" + vo);
		
		vo.setIdEmpresaP(vo.getIdEmpresa());
		vo.setCvePeriodoP(vo.getCvePeriodo());
		PeriodoModel type = this.periodoService.buscarPeriodoPorLlave(vo.getIdEmpresaP(), vo.getCvePeriodoP());
				
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}
}
