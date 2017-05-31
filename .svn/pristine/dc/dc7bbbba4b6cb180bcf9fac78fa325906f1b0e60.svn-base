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

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.catalogos.service.ParametrosService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Controller
public class ParametrosController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ParametrosController.class);
	
	@Autowired
	private ParametrosService parametrosService;

	/**
	 * Genera un nuevo parámetro. Se valida que no exista un identificador igual.
	 * @param ParametrosModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/parametros/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg insertarParametros(@RequestBody ParametrosModel vo) throws Exception{
		LOGGER.info("Insertar Par\u00e1metros - Inicia" + vo.toString());
		
		String mensaje = "";

		if (!this.parametrosService.existeParametro(vo)) {
			
			vo.setCveUsuAlta(Util.usuarioSesion());
			vo.setFhAlta(new Date());
		
			LOGGER.info("Insertar: " + vo.toString());
			boolean validacion = this.parametrosService.insertarParametro(vo);
			
			mensaje = (validacion ? "Se" : "No se") + " insert\u00f3 la informaci\u00f3n en la tabla de Par\u00e1metros";
		} else {
			//mensaje = "La informaci\u00f3n ya existe en la tabla de Par\u00e1metros";
			throw new Exception("La informaci\u00f3n ya existe en la tabla de Par\u00e1metros");
		}
		LOGGER.info(mensaje);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, new String(mensaje));
		
		LOGGER.info("Insertar Par\u00e1metros - Finaliza");
		return response;
	}

	/**
	 * Actualiza los datos de un parámetro
	 * @param ParametrosModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/parametros/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg actualizarParametros(@RequestBody ParametrosModel vo) throws Exception{
		LOGGER.info("Actualizar Par\u00e1metros - Inicia" + vo.toString());
		String mensaje = "";
		
		vo.setCveUsuMod(Util.usuarioSesion());
		vo.setFhUltMod(new Date());
		
		LOGGER.info("Actualizar" + vo.toString());
		boolean validacion = this.parametrosService.actualizarParametro(vo);

		mensaje = (validacion ? "Se" : "No se") + " actualiz\u00f3 la informaci\u00f3n en la tabla de Par\u00e1metros";
		LOGGER.info(mensaje);

		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, new String(mensaje));
		
		LOGGER.info("Actualizar Par\u00e1metros - Finaliza");
		return response;
	}
	
	/**
	 * Elimina un parámetro
	 * @param ParametrosModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	
	@RequestMapping(value = "/catalogos/parametros/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg eliminarParametros(@RequestBody ParametrosModel vo) throws Exception{
		LOGGER.info("Eliminar Par\u00e1metros - Inicia" + vo.toString());
		
		boolean validacion = this.parametrosService.eliminarParametro(vo);
		
		String mensaje = (validacion ? "Se" : "No se") + " elimin\u00f3 la informaci\u00f3n en la tabla de Par\u00e1metros";
		LOGGER.info(mensaje);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String(mensaje));
		LOGGER.info("Eliminar Par\u00e1metros - Finaliza");
		return response;
	}
	
	/**
	 * Consulta los datos de un parámetro por id
	 * @param ParametrosModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/parametros/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg consultarParametros(@RequestBody ParametrosModel vo) throws Exception{
		LOGGER.info("Consultar Par\u00e1metros - Inicia" + vo.toString());
		
		ParametrosModel type = this.parametrosService.consultarParametroPorClave(vo);
		LOGGER.info("Consultar Par\u00e1metro por Clave: " + type.toString());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		LOGGER.info("Consultar Par\u00e1metros - Finaliza");
		return response;
	}
}
