/**
 * 
 */
package com.gfi.bin.admctasweb.catalogos.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.EstatusSeguimientoService;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
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
public class OficioController {
	private final Logger log = LoggerFactory.getLogger(OficioController.class);
	
	@Autowired
	private OficioService oficioService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
	@Autowired
	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	
	@RequestMapping(value = "/catalogos/oficio/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarOficio(@RequestBody OficioModel vo) throws Exception {
		
		log.info("Entro al metodo guardarOficio capa controller");
		
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		
		//Se llenan estas propiedades que ya no se usan, pero son requeridas en la Tabla.
		vo.setbTurAudit(Constantes.TURNAR_AUDITORIA_FALSO);
		vo.setSitOficio(Constantes.SITUACION_OFICIO_PENDIENTE);
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		//Guardamos el Oficio
		this.oficioService.guardarOficio(vo);
		
		//Generamos registro en Bitacora de Seguimiento.
		seguimiento.setNumOficio(vo.getNumOficio());
		seguimiento.setTipoOficio(vo.getTipoOficio());
		seguimiento.setCveEstatus(Constantes.REGISTRO_OFICIO_INFO_COMP);
		
		this.bitacoraSeguimiento.guardar(seguimiento);
		
		List<String> messages = new ArrayList<String>();
		messages.add("El Oficio se guardó correctamente.");
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setMessages(messages);
		
		return response;
	}
	
	@RequestMapping(value = "/catalogos/oficio/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateOficio(@RequestBody OficioModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		
		//Se llenan estas propiedades que ya no se usan, pero son requeridas en la Tabla.
		vo.setbTurAudit(Constantes.TURNAR_AUDITORIA_FALSO);
		vo.setSitOficio(Constantes.SITUACION_OFICIO_PENDIENTE);
		vo.setCveUsuMod(Util.usuarioSesion());
		vo.setFhUltMod(new Date());
		
		this.oficioService.modificarOficio(vo);
		
		// Si el Oficio esta con Información Pendiente llamamos a la Bitacora para actualizar el Registro.
		if (vo.getCveEstatus().equals(Constantes.REGISTRO_OFICIO_INFO_PEND)) {
			//Generamos registro en Bitacora de Seguimiento.
			BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
			seguimiento.setNumOficio(vo.getNumOficio());
			seguimiento.setTipoOficio(vo.getTipoOficio());
			seguimiento.setCveEstatus(Constantes.REGISTRO_OFICIO_INFO_COMP);
			
			this.bitacoraSeguimiento.guardar(seguimiento);
		}
		
		//Consultamos el Oficio Acutalizado.
		OficioModel type = this.oficioService.buscarOficioPorLlave(vo.getNumOficio(), vo.getTipoOficio());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String("El Oficio se modificó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/catalogos/oficio/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deleteOficio(@RequestBody OficioModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.oficioService.eliminarOficio(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("El Oficio se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/catalogos/oficio/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readOficio(@RequestBody OficioModel vo) throws Exception {
		log.info("Entro al metodo read" + vo);
		
		OficioModel type = this.oficioService.buscarOficioPorLlave(vo.getNumOficio(),vo.getTipoOficio());
		//Se complementa descripción de estatus
		if(StringUtils.isNotBlank(type.getCveEstatus())){
			try {				
				type.setDescEstatus(estatusSeguimientoService.obtenerEstatusPorClave(type.getCveEstatus()).getDescripcion());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}

}
