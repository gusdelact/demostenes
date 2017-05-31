package com.gfi.bin.admctasweb.operativos.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.operativos.model.ExclusionModel;
import com.gfi.bin.admctasweb.operativos.service.ExcluNomContratoService;
import com.gfi.corp.model.ExtjsResponseMsg;

@Controller
public class ExcluNomContratoController {
	
	private final Logger logger = LoggerFactory.getLogger(ExcluNomContratoController.class);
	
	@Autowired
	private ExcluNomContratoService excluNomContratoService;
	
	/**
	 * Método que obtiene una exclusion por Id
	 * 
	 * @param exclusionModel
	 * @return @ResponseBody
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/exclusiones/read.htm", method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerExclusionPorId(@RequestBody ExclusionModel exclusionModel) throws Exception{
		logger.debug("obtenerExclusionPorId: "+exclusionModel+", vo.getId(): "+exclusionModel.getIdExclusion());
		ExclusionModel exclusion = excluNomContratoService.obtenerExclusionPorId(exclusionModel.getIdExclusion());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(exclusion);
		
		return response;
	}
	
	/**
	 * Método que actualiza un registro por medio de su Id
	 * 
	 * @param exclusionModel
	 * @return @ResponseBody
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/exclusiones/update.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg actualizarExclusion(@RequestBody ExclusionModel exclusionModel) throws Exception {
		logger.debug("actualizarExclusion: "+exclusionModel+", vo.getId(): "+exclusionModel.getIdExclusion());
		String mensaje = "";
		
		Boolean resultado = excluNomContratoService.actualizarExclusion(exclusionModel);
		mensaje = resultado ? "¡El registro se actualizó exitosamente!" : "Hubo un problema al actualizar el registro";
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(exclusionModel, mensaje);
		
		return response;
	}
	
	/**
	 * Método que guarda una nueva exclusión
	 * 
	 * @param exclusionModel
	 * @return @ResponseBody
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/exclusiones/create.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg guardarExclusion(@RequestBody ExclusionModel exclusionModel)throws Exception {
		logger.debug("guardarExclusion: "+exclusionModel+", id: "+exclusionModel.getIdExclusion()+", activo: "+exclusionModel.getActivo());
		String mensaje = "";
		
		Boolean resultado = excluNomContratoService.insertarExclusion(exclusionModel);
		mensaje = resultado ? "¡El registro se guardó exitosamente!" : "Hubo un problema al guardar el registro";
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(exclusionModel, mensaje);
		
		return response;
	}
	
	/**
	 * Método que elimina una exclusión por Id
	 * 
	 * @param exclusionModel
	 * @return @ResponseBody
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/exclusiones/delete.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg eliminarExclusion(@RequestBody ExclusionModel exclusionModel) throws Exception {
		logger.debug("eliminarExclusion: "+exclusionModel+", id: "+exclusionModel.getIdExclusion()+", activo: "+exclusionModel.getActivo());
		String mensaje = "";
		
		Boolean resultado = excluNomContratoService.eliminarExclusion(exclusionModel.getIdExclusion());
		mensaje = resultado ? "¡El registro se eliminó exitosamente!" : "Hubo un problema al eliminar el registro";
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(exclusionModel, mensaje);
		
		return response;
	}
}