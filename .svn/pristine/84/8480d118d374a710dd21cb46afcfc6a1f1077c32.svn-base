package com.gfi.bin.admctasweb.catalogos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.RespContrato;
import com.gfi.bin.admctasweb.catalogos.service.RespContratoService;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;
import com.gfi.security.util.SecurityContextInspector;

@Controller
@RequestMapping(value = "catalogos/respContrato")
public class RespContratoController {
	private static Logger logger = LoggerFactory.getLogger(RespContratoController.class);

	@Autowired
	private RespContratoService respContratoService;
	
	@RequestMapping(value = "/read")
	public @ResponseBody Map<String,Object> consultaContratosRespuesta(
			@RequestParam(required = false) String cbSituacion ,
			@RequestParam(required = false) Integer limit,
			@RequestParam(required = false) Integer page,
			@RequestParam(required = false) String sort,
			@RequestParam(required = false) String filter,
			@RequestParam(required = false) String numOficio) throws Exception{
		logger.debug("RespContratoController.consultaContratosRespuesta");
		Map<String,Object> map = new HashMap<String, Object>();
		
		GenericGridReq gridReq = new GenericGridReq();
		gridReq.setFilter(filter);
		gridReq.setLimit(limit);
		gridReq.setPage(page);
		gridReq.setSort(sort);
		
		List<RespContrato> list = new ArrayList<RespContrato>();
		
		list = this.respContratoService.consultaContratosRespuesta(gridReq, numOficio);
		
		map.put("success", true);
		map.put("data", list);
		map.put("totalCount", list.isEmpty()? 0:list.get(0).getRecordCount());
		
		return map;
	}
	
	@RequestMapping(value = "/create")
	public @ResponseBody Map<String,Object> insertarContratoRespuesta(@RequestBody List<RespContrato> respContratos)throws Exception {
		logger.debug("RespContratoController.insertarContratoRespuesta");
		Map<String,Object> map = new HashMap<String, Object>();
		String cveUsuario = SecurityContextInspector.getPrincipal().getUsername();
		
		boolean respuesta = this.respContratoService.insertarContratoRespuesta(respContratos, cveUsuario);
		
		map.put("success", respuesta);
		
		return map;
	}
	
	@RequestMapping(value = "/update")
	public @ResponseBody Map<String,Object> actualizarContratoRespuesta(@RequestBody List<RespContrato> respContratos) throws Exception {
		logger.debug("RespContratoController.actualizarContratoRespuesta");
		Map<String,Object> map = new HashMap<String, Object>();
		String cveUsuario = SecurityContextInspector.getPrincipal().getUsername();
		
		boolean respuesta = this.respContratoService.actualizarContratoRespuesta(respContratos, cveUsuario);
		
		map.put("success", respuesta);
		
		return map;
	}
	
	@RequestMapping(value = "/delete")
	public @ResponseBody Map<String,Object> eliminarContratoRespuesta(@RequestBody List<RespContrato> respContratos)
			throws Exception {
		logger.debug("ValidaHeaderController.eliminarValidaHeader");
		Map<String,Object> map = new HashMap<String, Object>();
		
		boolean respuesta = this.respContratoService.eliminarContratoRespuesta(respContratos);
		
		map.put("success", respuesta);
		
		return map;
	}
}