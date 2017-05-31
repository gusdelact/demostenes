package com.gfi.bin.admctasweb.operativos.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.catalogos.service.ParametrosService;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;
import com.gfi.bin.admctasweb.operativos.service.RespuestaOficioService;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Controlador para manejar la respuesta de oficios
 * @author ESS3VAVC
 *
 */
@Controller
public class RespuestaOficioController {

	@Autowired
	RespuestaOficioService respuestaOficioService;
	
	@Autowired
	ParametrosService parametrosService;
	
	private static final String CLAVE_GRUPO_PARAM = "RES_OFI";
	private static final String CLAVE_PARAM = "APOD";
	
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RespuestaOficioController.class);
	
	/**
	 * Método que obtiene los datos de la respuesta de un oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerRespuestaOficio(@RequestBody RespuestaOficioModel vo) throws Exception{
		LOGGER.info("En RespuestaOficioController.obtenerRespuestaOficio");
		RespuestaOficioModel type = respuestaOficioService.obtenerRespuestaOficioPorId(vo);
		
		//Se complementa obteniendo listas para combos
		List<ItemModel> requerimientos = respuestaOficioService.obtenerRequerimientos(type.getTipoOficio());		
		List<ItemModel> solicitudes = respuestaOficioService.obtenerSolicitudes(type.getTipoOficio(), type.getTipoRequerimiento());
		//List<ItemModel> direcciones = respuestaOficioService.obtenerDireccionesPorTipoOficio(type.getTipoOficio());
		
		type.setRequerimientosList(requerimientos);
		type.setSolicitudesList(solicitudes);
		//type.setDireccionesList(direcciones);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}

	/**
	 * Método para guardar respuesta de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/create.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg guardarDestinatario(@RequestBody RespuestaOficioModel vo)throws Exception {
		
		vo.setCveUsuAlta(Util.usuarioSesion());
		vo.setFhAlta(new Date());
		
		List<ItemModel> solicitudes = respuestaOficioService.obtenerSolicitudes(vo.getTipoOficio(), vo.getTipoRequerimiento());
		
		if(!solicitudes.isEmpty() && StringUtils.isBlank(vo.getTipoSolicitud()))
			throw new Exception("Debe Seleccionar una Solicitud");
		
		vo.setObservaciones(adecuarObservaciones(vo.getObservaciones()));
		RespuestaOficioModel respuesta = respuestaOficioService.guardarRespuestaOficio(vo);
		
		List<ItemModel> requerimientos = respuestaOficioService.obtenerRequerimientos(vo.getTipoOficio());		
		//List<ItemModel> direcciones = respuestaOficioService.obtenerDireccionesPorTipoOficio(vo.getTipoOficio());
		
		respuesta.setRequerimientosList(requerimientos);
		respuesta.setSolicitudesList(solicitudes);
		//respuesta.setDireccionesList(direcciones);
		
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(respuesta, new String("Registro de respuesta de Oficio creado correctamente!!!"));
		return response;
	
	}
	
	/**
	 * Método para modificar respuesta de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/update.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg modificarDestinatario(@RequestBody RespuestaOficioModel vo) throws Exception{
		
		vo.setCveUsuMod(Util.usuarioSesion());
		vo.setFhUltMod(new Date());
		
		List<ItemModel> requerimientos = respuestaOficioService.obtenerRequerimientos(vo.getTipoOficio());		
		List<ItemModel> solicitudes = respuestaOficioService.obtenerSolicitudes(vo.getTipoOficio(), vo.getTipoRequerimiento());
		//List<ItemModel> direcciones = respuestaOficioService.obtenerDireccionesPorTipoOficio(vo.getTipoOficio());

		if(!solicitudes.isEmpty() && StringUtils.isBlank(vo.getTipoSolicitud()))
			throw new Exception("Debe Seleccionar una Solicitud");
		
		
		vo.setRequerimientosList(requerimientos);
		vo.setSolicitudesList(solicitudes);
		//vo.setDireccionesList(direcciones);

		vo.setObservaciones(adecuarObservaciones(vo.getObservaciones()));
		respuestaOficioService.modificarRespuestaOficio(vo);
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, new String("El registro ha sido actualizado exitosamente!!!"));

		return response;
	}
	
	/**
	 * Actualiza el estatus del Oficio a ENviado
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/situacionenvio.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg cambiarOficioEnviado(@RequestBody RespuestaOficioModel vo) throws Exception{
		
		vo.setCveUsuMod(Util.usuarioSesion());
		vo.setFhUltMod(new Date());
		vo.setRespuestaEnviada("V");
		
		respuestaOficioService.actualizarOficioEnviado(vo);
		
		List<ItemModel> requerimientos = respuestaOficioService.obtenerRequerimientos(vo.getTipoOficio());		
		List<ItemModel> solicitudes = respuestaOficioService.obtenerSolicitudes(vo.getTipoOficio(), vo.getTipoRequerimiento());
		//List<ItemModel> direcciones = respuestaOficioService.obtenerDireccionesPorTipoOficio(vo.getTipoOficio());

		vo.setRequerimientosList(requerimientos);
		vo.setSolicitudesList(solicitudes);
		//vo.setDireccionesList(direcciones);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo, new String("El registro ha sido actualizado exitosamente!!!"));

		return response;
	}
	
	
	/**
	 * Obtiene lista de requerimientos para combo
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/obtenerrequerimientos.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerRequerimientos(@RequestBody RespuestaOficioModel vo) throws Exception{
		
		List<ItemModel> requerimientos = respuestaOficioService.obtenerRequerimientos(vo.getTipoOficio());
		vo.setRequerimientosList(requerimientos);		
		
		//List<ItemModel> direcciones = respuestaOficioService.obtenerDireccionesPorTipoOficio(vo.getTipoOficio());
		//vo.setDireccionesList(direcciones);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		return response;
	}
	
	/**
	 * Obtiene lista de solicitudes de acuerdo al requerimiento seleccionado
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/obtenersolicitudes.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerSolicitudes(@RequestBody RespuestaOficioModel vo) throws Exception{
		
		List<ItemModel> solicitudes = respuestaOficioService.obtenerSolicitudes(vo.getTipoOficio(), vo.getTipoRequerimiento());
		vo.setSolicitudesList(solicitudes);
		
		//Se agrega para precargar datos del oficio en campo de descripción de la vista
		String datosOficio = "";
		String apoderado = "";
		if(solicitudes.isEmpty())
		{
			
			try {
				datosOficio = respuestaOficioService.armarCadenaDatosOficio(vo);
				
				//Se agrega para obtener apoderado
				ParametrosModel parametrosModel = new ParametrosModel();
				parametrosModel.setCveGpoParam(CLAVE_GRUPO_PARAM);
				parametrosModel.setCveParam(CLAVE_PARAM);
				parametrosModel = parametrosService.consultarParametroPorClave(parametrosModel);
				apoderado = parametrosModel.getDescParam();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		vo.setObservaciones(datosOficio);
		vo.setApoderado(apoderado);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		return response;
	}
	
	/**
	 * Para evento de selección de Solicitud y enviar cadena de respuesta
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/obtenerdescripcion.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerDescripcion(@RequestBody RespuestaOficioModel vo) throws Exception{
		
		String datosOficio = "";
		String apoderado = "";

		try {
			datosOficio = respuestaOficioService.armarCadenaDatosOficio(vo);
			
			//Se agrega para obtener apoderado
			ParametrosModel parametrosModel = new ParametrosModel();
			parametrosModel.setCveGpoParam(CLAVE_GRUPO_PARAM);
			parametrosModel.setCveParam(CLAVE_PARAM);
			parametrosModel = parametrosService.consultarParametroPorClave(parametrosModel);
			apoderado = parametrosModel.getDescParam();			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//if(StringUtils.isNotBlank(datosOficio))
		vo.setObservaciones(datosOficio);
		vo.setApoderado(apoderado);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		return response;
	}
	
	/**
	 * Método para generar reporte de respuesta de Oficio
	 * Se recibe la petición mediante GET desde un IFrame en la vista para que pueda recibir el archivo generado
	 * @param numOficio
	 * @param tipoOficio
	 * @param idRespuesta
	 * @param httpServletResponse
	 * @throws IOException
	 */
	@RequestMapping(value = "/operativos/respuestasOficios/reporte.htm", method = RequestMethod.GET)
	@ResponseBody
	public void crearReporteTest(@RequestParam(required = true) String numOficio, 
								@RequestParam(required = true) String tipoOficio,
								@RequestParam(required = true) Integer idRespuesta,
			HttpServletResponse httpServletResponse )throws IOException{

		byte[] pdfImage = null;
		try {
			pdfImage = respuestaOficioService.generarReporte(numOficio, tipoOficio, idRespuesta);
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
	 * Adecuación de campo de Observaciones antes de insertar o modificar en la BD
	 * Se requiere porque el editor html en ocaciones no pone bien el nombre de la fuente y puede generar error al crear reportes con jasper
	 * @param observaciones
	 * @return
	 */
	private String adecuarObservaciones(String observaciones)
	{
		return StringUtils.replace(observaciones, "tahoma", "Tahoma");
	}
	
}
