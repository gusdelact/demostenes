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
import com.gfi.bin.admctasweb.reporte_r29.model.CapturaManualModel;
import com.gfi.bin.admctasweb.reporte_r29.service.CapturaManualService;
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
public class CapturaManualController {
	private final Logger log = LoggerFactory.getLogger(CapturaManualController.class);
	
	@Autowired
	private CapturaManualService capturaManualService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimiento;
	
//	@Autowired
//	private EstatusSeguimientoService estatusSeguimientoService;
	
	
	@RequestMapping(value = "/reporte_r29/manual/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg guardarManual(@RequestBody CapturaManualModel vo) throws Exception {
		
		log.info("Entro al metodo guardarManual de capa controller");
		log.info("Valores para el objto CapturaManualModel: "+vo);
		
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		//Guardamos el Oficio
		this.capturaManualService.guardarCapturaManual(vo);
			
		List<String> messages = new ArrayList<String>();
		messages.add("El Oficio se guardó correctamente.");
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setMessages(messages);
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/manual/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateManual(@RequestBody CapturaManualModel vo) throws Exception {
		log.info("Entro al metodo update" + vo);
		
		String valorDefaultTipoOficio = "NA";
		vo.setTipoOficio(valorDefaultTipoOficio);
		
		this.capturaManualService.modificarCapturaManual(vo);
		
		
		//Consultamos el Manual Acutalizado.
		CapturaManualModel type = this.capturaManualService.buscarCapturaManualPorLlave(vo.getNumOficio(), 
																						vo.getTipoOficio(), 
																						vo.getIdCotitular(),
																						vo.getIdEmpresa(), 
																						vo.getCvePeriodo(), 
																						vo.getNumIntento());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type, new String("El Oficio se modificó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/manual/delete.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg deleteManual(@RequestBody CapturaManualModel vo) throws Exception {
		log.info("Entro al metodo delete" + vo);
		
		this.capturaManualService.eliminarCapturaManual(vo);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, new String("El Oficio se eliminó correctamente."));
		
		return response;
	}
	
	@RequestMapping(value = "/reporte_r29/manual/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg readCapturaManual(@RequestBody CapturaManualModel vo) throws Exception {
		log.info("Entro al metodo read capa controller " + vo);
		
		CapturaManualModel type = this.capturaManualService.buscarCapturaManualPorLlave(vo.getNumOficio(),vo.getTipoOficio(), vo.getIdCotitular(), vo.getIdEmpresa(), vo.getCvePeriodo(), vo.getNumIntento());
		
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}
	
//	@RequestMapping(value = "/reporte_r29/manual/contrato.htm",method = RequestMethod.POST)
//	public  @ResponseBody ExtjsResponseMsg readCapturaManualContrato(@RequestBody CapturaManualModel vo) throws Exception {
//		log.info("Entro al metodo read" + vo);
//		
//		CapturaManualModel type = this.capturaManualService.buscarCapturaManualContrato(vo.getIdCuenta(), vo.getIdEmpresa());
//		
//		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
//		
//		return response;
//	}
	
	@RequestMapping(value = "/reporte_r29/manual/buscarcontrato.htm", method = RequestMethod.POST)
	public @ResponseBody
//	Map<String, Object> readCapturaManualContrato(@RequestBody String idCuenta, String idEmpresa) throws Exception {
	Map<String, Object> readCapturaManualContrato(@RequestParam(required = false) String idCuenta,
                                                  @RequestParam(required = false) String idEmpresa) throws Exception {
		
		log.info("En metodo buscar Contrato capa controller");
		Map<String, Object> map = new HashMap<String, Object>();
		CapturaManualModel type =  this.capturaManualService.buscarCapturaManualContrato(idCuenta, idEmpresa);
		map.put("success", true);
		map.put("info", type);
		return map;
	}

}
