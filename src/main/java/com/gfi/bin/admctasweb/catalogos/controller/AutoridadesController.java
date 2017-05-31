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

import com.gfi.bin.admctasweb.catalogos.model.AutoridadesModel;
import com.gfi.bin.admctasweb.catalogos.service.AutoridadesService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Controller
public class AutoridadesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AutoridadesController.class);
	
	@Autowired
	private AutoridadesService autoridadesService;

	/**
	 * Genera una nueva autoridad en catálogo. Se valida que no exista con identificador similar.
	 * @param AutoridadesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/autoridades/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg insertarAutoridades(@RequestBody AutoridadesModel vo) throws Exception{
		LOGGER.info("Insertar Autoridades - Inicia" + vo.toString());
		
		String mensaje = "";
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		if (!this.autoridadesService.existeAutoridad(vo)) {
			
			LOGGER.info("Insertar: " + vo.toString());
			boolean validacion = this.autoridadesService.insertarAutoridad(vo);
			
			mensaje = (validacion ? "Se" : "No se") + " insert\u00f3 la informaci\u00f3n en la tabla de Autoridades";			
		} else {
			throw new Exception("La informaci\u00f3n ya existe en la tabla de Autoridades");// Se cambia a excepción para que no se complete la acción en la vista
			//mensaje = "La informaci\u00f3n ya existe en la tabla de Autoridades";
		}
		LOGGER.info(mensaje);
		
//		AutoridadesModel type = this.autoridadesService.consultarAutoridadPorClave(vo);
//		LOGGER.info("Consultar Autoridad por Clave: " + type);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, mensaje);//Se debe devolver el mismo objeto que se inserta con las fechas y usuario de creación
																	  //No es necesario ir a consultar de nuevo a la BD
		
		LOGGER.info("Insertar Autoridades - Finaliza");
		return response;
	}

	/**
	 * Actualiza los datos de una Autoridad
	 * @param AutoridadesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/autoridades/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg actualizarAutoridades(@RequestBody AutoridadesModel vo) throws Exception{
		LOGGER.info("Actualizar Autoridades - Inicia" + vo.toString());
		
		String mensaje = "";
		vo.setCveUsuMod(Util.usuarioSesion());
		vo.setFhUltMod(new Date());
		
		
		LOGGER.info("Actualizar" + vo.toString());
		boolean validacion = this.autoridadesService.actualizarAutoridad(vo);

		mensaje = (validacion ? "Se" : "No se") + " actualiz\u00f3 la informaci\u00f3n en la tabla de Autoridades";
		LOGGER.info(mensaje);
		
//		AutoridadesModel type = this.autoridadesService.consultarAutoridadPorClave(vo);
//		LOGGER.info("Consultar Autoridad por Clave: " + type);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, mensaje);//Se debe devolver el mismo objeto que se modifica con las fechas y usuario de modificación
		 															  //No es necesario ir a consultar de nuevo a la BD
		LOGGER.info("Actualizar Autoridades - Finaliza");
		return response;
	}
	
	/**
	 * Elimina Autoridad de catálogo
	 * @param AutoridadesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/autoridades/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg eliminarAutoridades(@RequestBody AutoridadesModel vo) throws Exception{
		LOGGER.info("Eliminar Autoridades - Inicia" + vo.toString());
		
		boolean validacion = this.autoridadesService.eliminarAutoridad(vo);
		
		String mensaje = (validacion ? "Se" : "No se") + " elimin\u00f3 la informaci\u00f3n en la tabla de Autoridades";
		LOGGER.info(mensaje);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String(mensaje));
		LOGGER.info("Eliminar Autoridades - Finaliza");
		return response;
	}
	
	/**
	 * Obtiene los datos de Autoridad por id
	 * @param AutoridadesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/autoridades/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg consultarAutoridades(@RequestBody AutoridadesModel vo) throws Exception{
		LOGGER.info("Consultar Autoridades - Inicia" + vo);
		
		AutoridadesModel type = this.autoridadesService.consultarAutoridadPorClave(vo);
		LOGGER.info("Consultar Autoridad por Clave: " + type.toString());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		LOGGER.info("Consultar Autoridades - Finaliza");
		return response;
	}
}
