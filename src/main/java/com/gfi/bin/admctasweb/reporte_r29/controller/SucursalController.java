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
import com.gfi.bin.admctasweb.reporte_r29.model.SucursalModel;
import com.gfi.bin.admctasweb.reporte_r29.service.SucursalService;
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
public class SucursalController {
	private final Logger log = LoggerFactory.getLogger(SucursalController.class);
	
	@Autowired
	private SucursalService sucursalService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
//	@Autowired
//	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	@RequestMapping(value = "/reporte_r29/sucursal/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarMSucursal(@RequestBody SucursalModel vo) throws Exception {
		log.info("Entro al Controller CM" + vo);
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		String mensaje= "";
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		//Guardamos la sucursal
		
		List<String> messages = new ArrayList<String>();
		
		mensaje = this.sucursalService.guardarSucursal(vo);
				
		if(mensaje == ""){
			mensaje="La Sucursal se guardó correctamente.";
		}
				
		messages.add(mensaje);

		ExtjsResponseMsg response = new ExtjsResponseMsg();

		response.setMessages(messages);
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/sucursal/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateSucursal(@RequestBody SucursalModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		
		this.sucursalService.modificarSucursal(vo);
		
		
		//Consultamos la sucursal Acutalizado.
		SucursalModel type = this.sucursalService.buscarSucursalPorLlave(vo.getIdEmpresa(), vo.getIdSucursal());
		 
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String("La Sucursal se modificó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/sucursal/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deleteSucursal(@RequestBody SucursalModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.sucursalService.eliminarSucursal(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("La Sucursal se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/sucursal/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readSucursal(@RequestBody SucursalModel vo) throws Exception {
		log.info("Entro al metodo read" + vo);
		
		SucursalModel type = this.sucursalService.buscarSucursalPorLlave(vo.getIdEmpresa(),vo.getIdSucursal());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}


}
