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
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.reporte_r29.model.ConversionModel;
import com.gfi.bin.admctasweb.reporte_r29.service.ConversionService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;


/**
 * @author LUGL4884
 * Controlador para Oficios
 */
@Controller
public class ConversionController {
	private final Logger log = LoggerFactory.getLogger(ConversionController.class);
	
	@Autowired
	private ConversionService conversionService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
//	@Autowired
//	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	@RequestMapping(value = "/reporte_r29/conversion/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarMConversion(@RequestBody ConversionModel vo) throws Exception {
		log.info("Entro al Controller Conversion" + vo);
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		String mensaje=""; 
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		List<String> messages = new ArrayList<String>();
		
		mensaje = this.conversionService.guardarConversion(vo);
		
		if(mensaje == ""){
			mensaje="La Conversion se guardó correctamente.";
		}
		
		messages.add(mensaje);

		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setMessages(messages);
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/conversion/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateConversion(@RequestBody ConversionModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		
		this.conversionService.modificarConversion(vo);
		
		
		//Consultamos el Manual Acutalizado.
		ConversionModel type = this.conversionService.buscarConversionPorLlave(vo.getIdCatalogo());
		 
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String("La Conversion se modificó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/conversion/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deleteConversion(@RequestBody ConversionModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.conversionService.eliminarConversion(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("La Conversion se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/conversion/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readConversion(@RequestBody ConversionModel vo) throws Exception {
		log.info("Entro al metodo read" + vo);
		
		ConversionModel type = this.conversionService.buscarConversionPorLlave(vo.getIdCatalogo());
		
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}


}
