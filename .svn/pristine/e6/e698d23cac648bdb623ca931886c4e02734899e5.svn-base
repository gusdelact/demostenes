package com.gfi.bin.admctasweb.catalogos.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.DireccionesSolicitantesModel;
import com.gfi.bin.admctasweb.catalogos.service.DireccionesSolicitantesService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */

@Controller
public class DireccionesSolicitantesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DireccionesSolicitantesController.class);

	@Autowired
	private DireccionesSolicitantesService direccionesSolicitantesService;

	/**
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/direccionesSolicitantes/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg insertarDireccionesSolicitantes(@RequestBody DireccionesSolicitantesModel vo) throws Exception{
		LOGGER.info("Insertar Direcciones Solicitantes - Inicia" + vo.toString());
		
		DireccionesSolicitantesModel type = null;
		LOGGER.info("Insertar: " + vo.toString());
		
		vo.setFhAlta(new Date());
		vo.setCveUsuAlta(Util.usuarioSesion());
		
		type = this.direccionesSolicitantesService.insertarDireccionSolicitante(vo);		

		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String[]{"Registro de Dirección creado correctamente!!!"});
		
		LOGGER.info("Insertar Direcciones Solicitantes - Finaliza");
		return response;
	}

	/**
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/direccionesSolicitantes/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg actualizarDireccionesSolicitantes(@RequestBody DireccionesSolicitantesModel vo) throws Exception{
		LOGGER.info("Actualizar Direcciones Solicitantes - Inicia" + vo.toString());

		String mensaje = "";
		vo.setFhUltMod(new Date());
		vo.setCveUsuMod(Util.usuarioSesion());
		
		try {
			//this.validarInformacion(vo);

			LOGGER.info("Actualizar: " + vo.toString());
			boolean validacion = this.direccionesSolicitantesService.actualizarDireccionSolicitante(vo);
			
			mensaje = (validacion ? "Se" : "No se") + " actualiz\u00f3 la informaci\u00f3n en la tabla de Direcciones Solicitantes";
			LOGGER.info(mensaje);
		} catch (Exception e) {
			mensaje = e.getMessage();
			LOGGER.error(mensaje);
		}

		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, mensaje);
		
		
		return response;
	}
	
	/**
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/direccionesSolicitantes/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg eliminarDireccionesSolicitantes(@RequestBody DireccionesSolicitantesModel vo) throws Exception{
		LOGGER.info("Eliminar Direcciones Solicitantes - Inicia" + vo.toString());
		
		boolean validacion = this.direccionesSolicitantesService.eliminarDireccionSolicitante(vo);

		String mensaje = (validacion ? "Se" : "No se") + " elimin\u00f3 la informaci\u00f3n en la tabla de Direcciones Solicitantes";
		LOGGER.info(mensaje);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String(mensaje));
		LOGGER.info("Eliminar Direcciones Solicitantes - Finaliza");
		return response;
	}
	
	/**
	 * 
	 * @param DireccionesSolicitantesModel
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/catalogos/direccionesSolicitantes/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg consultarDireccionesSolicitantes(@RequestBody DireccionesSolicitantesModel vo) throws Exception{
		LOGGER.info("Consultar Direcciones Solicitantes - Inicia" + vo.toString());
			
		DireccionesSolicitantesModel type = this.direccionesSolicitantesService.consultarDireccionSolicitantePorID(vo);
		LOGGER.info("Consultar Direcci\u00f3n Solicitante por ID: " + type.toString());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		LOGGER.info("Consultar Direcciones Solicitantes - Finaliza");
		return response;
	}
	
	@SuppressWarnings("unused")
	private void validarInformacion(DireccionesSolicitantesModel vo) throws Exception {
		String mensaje = null;
		if (vo != null) {
			if (vo.getTipoOficio() == null || StringUtils.isBlank(vo.getTipoOficio())) {
				mensaje = "El Tipo de Oficio es requerido";
			} else if (vo.getNivel() == null) {
				mensaje = "El Nivel es requerido";
			} else if (vo.getSituacion() == null || StringUtils.isBlank(vo.getSituacion())) {
				mensaje = "La Situaci\u00f3n es requerida";
			} else if ((vo.getDireccion() == null || StringUtils.isBlank(vo.getDireccion())) &&
					   (vo.getGerencia() == null || StringUtils.isBlank(vo.getGerencia())) &&
					   (vo.getSubgerencia() == null || StringUtils.isBlank(vo.getSubgerencia())) &&
					   (vo.getNombreAtencion() == null || StringUtils.isBlank(vo.getNombreAtencion())) &&
					   (vo.getPuestoAtencion() == null || StringUtils.isBlank(vo.getPuestoAtencion())) ) {
				mensaje = "Los campos se encuentran vac\u00edos";
			}
		} else {
			mensaje = "Debe existir al menos un campo lleno";
		}
		
		if (mensaje != null && StringUtils.isNotBlank(mensaje)) {
			LOGGER.error(mensaje);
			throw new Exception (mensaje);
		}
	}
}
