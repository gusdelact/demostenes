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
import com.gfi.bin.admctasweb.reporte_r29.model.MapeoModel;
import com.gfi.bin.admctasweb.reporte_r29.service.MapeoService;
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
public class MapeoController {
	private final Logger log = LoggerFactory.getLogger(MapeoController.class);
	
	@Autowired
	private MapeoService mapeoService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
//	@Autowired
//	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	@RequestMapping(value = "/reporte_r29/mapeo/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarMapeo(@RequestBody MapeoModel vo) throws Exception {
		log.info("Entro al Controller Mapeo" + vo);
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		String mensaje ="";
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		//Guardamos el Oficio
		List<String> messages = new ArrayList<String>();
		
		mensaje = this.mapeoService.guardarMapeo(vo);
				
		if(mensaje == ""){
			mensaje="El mapeo se guardó correctamente.";
		}
				
		messages.add(mensaje);

		ExtjsResponseMsg response = new ExtjsResponseMsg();

		response.setMessages(messages);
				
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/mapeo/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateMapeo(@RequestBody MapeoModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		
		this.mapeoService.modificarMapeo(vo);
				
		//Consultamos el Mapeo Acutalizado.
		MapeoModel type = this.mapeoService.buscarMapeoPorLlave(vo.getIdCatalogo(), vo.getCveCorporativa() );
		 
		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String("El Mapeo se modificó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/mapeo/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deleteMapeo(@RequestBody MapeoModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.mapeoService.eliminarMapeo(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("El Mapeo se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/mapeo/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readMapeo(@RequestBody MapeoModel vo) throws Exception {
		log.info("Entro al metodo read" + vo);
		
		MapeoModel type = this.mapeoService.buscarMapeoPorLlave(vo.getIdCatalogo(), vo.getCveCorporativa());
		
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}


}
