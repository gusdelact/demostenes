/**
 * 
 */
package com.gfi.bin.admctasweb.reporte_r29.controller;

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
import com.gfi.bin.admctasweb.reporte_r29.service.ControlService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;


/**
 * @author LUGL4884
 * Controlador para Control
 */
@Controller
public class ControlController {
	private final Logger log = LoggerFactory.getLogger(ControlController.class);
	
	@Autowired
	private ControlService controlService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
//	@Autowired
//	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	@RequestMapping(value = "/reporte_r29/control/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarcontrol(@RequestBody ControlModel vo) throws Exception {
		log.info("Entro al Controller Control" + vo);
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		String mensaje="";
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		//Guardamos el Periodo
	
			
		List<String> messages = new ArrayList<String>();
		
		mensaje = this.controlService.guardarControl(vo);
				
		if(mensaje == ""){
			mensaje="El Control se guardó correctamente.";
		}
				
		messages.add(mensaje);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setMessages(messages);
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/control/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateControl(@RequestBody ControlModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		
		this.controlService.modificarControl(vo);
				
		//Consultamos el Control Acutalizado.
		ControlModel type = this.controlService.buscarControlPorLlave(vo.getIdEmpresaCx(), vo.getCvePeriodoCx(),
				vo.getNumIntentoCx());
		 
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String("El Control se modificó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/control/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deleteControl(@RequestBody ControlModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.controlService.eliminarControl(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("El control se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/control/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readControl(@RequestBody ControlModel vo) throws Exception {
		log.info("Entro al metodo read" + vo);
		
		vo.setIdEmpresaCx(vo.getIdEmpresa());
		vo.setCvePeriodoCx(vo.getCvePeriodo());
		vo.setNumIntentoCx(vo.getNumIntento());
		
		ControlModel type = this.controlService.buscarControlPorLlave(vo.getIdEmpresaCx(), vo.getCvePeriodoCx(), vo.getNumIntentoCx());    
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}


}
