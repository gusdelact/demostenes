package com.gfi.bin.admctasweb.operativos.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
import com.gfi.bin.admctasweb.operativos.service.CnbvDictamenService;
import com.gfi.bin.admctasweb.util.AdmctasUtil;
import com.gfi.bin.admctasweb.util.Response;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Controlador que cacha las peticiones de la vista de registro de dictamen juridico
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Controller
public class RegistroDictamenJurController {
	final Logger log = LoggerFactory.getLogger(RegistroDictamenJurController.class);
	
	@Autowired
	CnbvDictamenService cnbvDictamenService;
	
	private static final String NOMBRE_PLANTILLA_JASPER = "dictamenJuridico";
	/**
	 * Crea un registro en la tabla  CNBV_DICTAMEN
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/regdictamenjuri/create.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg crearRegistroDictamen(@RequestBody CnbvDictamenModel vo,HttpServletRequest request) throws Exception{	
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, "");
		Response resp = new Response();
		try{
			 vo.setFhAlta(new Date());
			 vo.setCveUsuAlta(Util.usuarioSesion());
			 vo.setLogo(AdmctasUtil.obtenerPathImagen(request));
			 resp = cnbvDictamenService.guardarCnbvDictamen(vo);			
			 response = new ExtjsResponseMsg(resp.getData(), resp.getMsg());
		}catch(ServiceException se){
			response = new ExtjsResponseMsg(vo, se.getMessage());
			se.printStackTrace();
		}catch(Exception e){
			String msg = "Ocurrio un error al tratar de guardar el registro. ";
			log.error(msg + e.getLocalizedMessage());							
			response = new ExtjsResponseMsg(vo, msg);
			e.printStackTrace();
		}		
		return response;
	}	
	/**
	 * Actualiza un registro en la tabla  CNBV_DICTAMEN
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/regdictamenjuri/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg actualizarRegistroDictamen(@RequestBody CnbvDictamenModel vo,HttpServletRequest request) throws ServiceException{
		ExtjsResponseMsg response = new ExtjsResponseMsg(null, "");
		Response resp = new Response();
		try{			
			 vo.setFhUltMod(new Date());
			 vo.setCveUsuMod(Util.usuarioSesion());
			 vo.setLogo(AdmctasUtil.obtenerPathImagen(request));
			 resp = cnbvDictamenService.actualizarCnbvDictamen(vo);
			 response = new ExtjsResponseMsg(resp.getData(), resp.getMsg());
		}catch(ServiceException se){
			response = new ExtjsResponseMsg(vo, se.getMessage());
			se.printStackTrace();
		}catch(Exception e){
			String msg = "Ocurrio un error al tratar de actualizar el registro. ";	
			log.error(msg +e.getLocalizedMessage());			
			response = new ExtjsResponseMsg(vo, msg);
			e.printStackTrace();
		}		
		return response;
	}	
	
	/**
	 * Obtiene registro por id
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/regdictamenjuri/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg buscarRegistroDictamen(@RequestBody CnbvDictamenModel vo) throws Exception{
		CnbvDictamenModel dictamen = (CnbvDictamenModel) cnbvDictamenService.buscarDatosOficioAndRegistro(vo).getData();
		try{
			dictamen.setRespFaltantes(cnbvDictamenService.obtenerRespuestasFaltantesDeRegistro(dictamen.getNumOficio()));
		}catch(Exception e){
			log.error("Ocurrio un error al obtener el numero de respuestas faltantes de registro");
			e.printStackTrace();
		}
		ExtjsResponseMsg response = new ExtjsResponseMsg(dictamen);
		
		return response;
	}
	
	/**
	 * Recibe la peticion del boton visualizar, es encargado de invocar al servicio de reportes, manda el documento
	 * pdf del registro del dictamen juridico a la vista
	 * @param numOficio
	 * @param tipoOficio
	 * @param numConsec
	 * @param httpServletResponse
	 * @throws IOException
	 */
	@RequestMapping(value = "/operativos/regdictamenjuri/reporte.htm", method = RequestMethod.GET)
	@ResponseBody
	public void crearReporteTest(@RequestParam(required = true) String numOficio, 
								@RequestParam(required = true) String tipoOficio,
								@RequestParam(required = true) Long numConsec,
			HttpServletResponse httpServletResponse )throws IOException{

		byte[] pdfImage = null;
		try {
			CnbvDictamenModel modelo = (CnbvDictamenModel) cnbvDictamenService.buscarDatosOficioAndRegistro(
															new CnbvDictamenModel(numOficio, tipoOficio, numConsec)	).getData();
			pdfImage = cnbvDictamenService.generarReporte(modelo, NOMBRE_PLANTILLA_JASPER);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(pdfImage != null){
			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + numOficio + ".pdf");
			httpServletResponse.setHeader("Pragma", "no-cache");
			httpServletResponse.setContentLength(pdfImage.length);
			
		    ServletOutputStream out = httpServletResponse.getOutputStream();
		    out.write(pdfImage);
		    out.flush();
		    out.close();
		}
	}
	
	/**
	 * Recibe la peticion de la vista, invoca al servicio que obtiene el numero de respuestas faltantantes de registro.
	 * Busca por numero de oficio.
	 * @param vo CnbvDictamenModel
	 * @return RespFaltantes
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/regdictamenjuri/buscarRegFaltantes.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg buscarRegFaltantes(@RequestBody CnbvDictamenModel vo) throws Exception{
		log.debug("buscarRegFaltantes para el buscador");
		try{
			if(vo!=null && vo.getNumOficio()!=null){
				int respFaltantes = cnbvDictamenService.obtenerRespuestasFaltantesDeRegistro(vo.getNumOficio());
				log.info("respFaltantes ->" + respFaltantes);
				vo.setRespFaltantes(respFaltantes);
			}
		}catch(Exception e){
			log.error("Ocurrio un error al obtener el numero de respuestas faltantes de registro");
			e.printStackTrace();
		}
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);		
		return response;
	}
	
}
