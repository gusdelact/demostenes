package com.gfi.bin.admctasweb.catalogos.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.operativos.service.PersonaCorporativaService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Clase de control para Catálogo de Similaridad o Coincidencia para personas
 * @author ESS3VAVC
 *
 */
@Controller
public class SimilaridadController {

	
	@Autowired
	PersonaCorporativaService personaCorporativaService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SimilaridadController.class);
	
	@RequestMapping(value = "/catalogos/similaridad/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerDestinatario(@RequestBody SimilaridadModel vo) throws Exception{
		LOGGER.debug("En el método obtenerDestinatario del Controller de Similaridad");
		
		vo = personaCorporativaService.obtenerSimilaridad();
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		
		return response;
	}
	
	@RequestMapping(value = "/catalogos/similaridad/update.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg modificarDestinatario(@RequestBody SimilaridadModel vo) throws Exception{
		
		//Validaciones
		if(vo.getSimilaridad() == null || vo.getSimilaridad() == 0)
			throw new Exception("El campo % Coincidencia es requerido");
			
		if(!(vo.getSimilaridad() > 0 && vo.getSimilaridad() < 100))
			throw new Exception("El valor del campo % Coincidencia debe estar entre 1 y 100");
		
		vo.setCveUsuMod(Util.usuarioSesion());
		vo.setFhUltMod(new Date());
		
		personaCorporativaService.modificarSimilaridad(vo);
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, new String("El registro ha sido actualizado exitosamente!!!"));

		return response;
	
	}
	
	
}
