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

import com.gfi.bin.admctasweb.catalogos.model.DestinatarioModel;
import com.gfi.bin.admctasweb.catalogos.service.DestinatarioService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Clase de control para Destinatarios
 * @author ESS3VAVC
 *
 */
@Controller
public class DestinatarioController {
	
	@Autowired
	private DestinatarioService destinatarioService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DestinatarioController.class);
	
	/**
	 * Obtiene Destinatario por id
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/destinatario/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerDestinatario(@RequestBody DestinatarioModel vo) throws Exception{
		LOGGER.info("Entro al metodo read" + vo);
		
		DestinatarioModel type = destinatarioService.obtenerDestinatario(vo.getIdDestinatario());
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}
	
	
	/**
	 * Guarda el Destinatario capturado
	 * @param destinatarioModel
	 * @return
	 */
	@RequestMapping(value = "/catalogos/destinatario/create.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg guardarDestinatario(@RequestBody DestinatarioModel destinatarioModel)throws Exception {
		
		destinatarioModel.setCveUsuAlta(Util.usuarioSesion());
		destinatarioModel.setFhAlta(new Date());
		
		int llave = destinatarioService.guardarDestinatario(destinatarioModel);
		destinatarioModel.setIdDestinatario(llave);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(destinatarioModel, new String("Registro de Destinatario creado correctamente!!!"));
		
		return response;
	
	}
	
	/**
	 * Realiza actualización de un Destinatario
	 * @param destinatarioModel
	 * @return
	 */
	@RequestMapping(value = "/catalogos/destinatario/update.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg modificarDestinatario(@RequestBody DestinatarioModel destinatarioModel) throws Exception{
		
		destinatarioModel.setCveUsuMod(Util.usuarioSesion());
		destinatarioModel.setFhUltMod(new Date());

		destinatarioService.modificarDestinatario(destinatarioModel);
		ExtjsResponseMsg response = new ExtjsResponseMsg(destinatarioModel, new String("El registro ha sido actualizado exitosamente!!!"));

		return response;
	
	}
}
