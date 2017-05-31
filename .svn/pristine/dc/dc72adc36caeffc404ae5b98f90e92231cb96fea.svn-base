package com.gfi.bin.admctasweb.operativos.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ArhivoCnbvModel;
import com.gfi.bin.admctasweb.operativos.model.OficioCnbvModel;
import com.gfi.bin.admctasweb.operativos.service.ArchivoCnbvService;
import com.gfi.bin.admctasweb.util.FileUtil;
import com.gfi.bin.admctasweb.util.Response;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Controllador encargado de gestionar las peticiones de la vista para la generacion del archivo a CNBV
 * "Generacion de archivo CNBV" interfaz ArchivoCnbvService
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Controller
public class ArchivoCnbvController {
	final Logger log = LoggerFactory.getLogger(ArchivoCnbvController.class);
	
	@Autowired
	ArchivoCnbvService archivoCnbvService;
	File fDownload = null;
	
	/**
	 * Muestra el resultado de la busqueda de oficios
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operativos/archivoCnbv/buscar.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg buscarOficiosArchivoCnbv(@RequestBody ArhivoCnbvModel vo) throws Exception{
		List<OficioCnbvModel> listaOficiosResult = (List<OficioCnbvModel>) archivoCnbvService.buscarDatosOficioAndRegistro(vo).getData();
		vo.setListaOficiosResult(listaOficiosResult);
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);	
		if(listaOficiosResult == null || listaOficiosResult.size() ==0 ){
			response = new ExtjsResponseMsg(vo,"No se encontraron resultados");
		} 			
		return response;
	}
	
	/**
	 * Recibe la petición de generación de archivo para la CNBV de la vista invoca al servicio encargado de esta operación
	 * @param vo
	 * @param httpResponse
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/operativos/archivoCnbv/generarArchivo.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg generarArchivo(@RequestBody ArhivoCnbvModel vo,HttpServletResponse httpResponse) throws Exception{	
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		String msg = "";
		try{
	 		Response resp = archivoCnbvService.generarArchivoCnbv(vo.getListaOficiosResultGrid());
	 		log.info("Se creo el archivo " + resp.isSuccess()); 					
			vo.setRuta((String) resp.getMsg());
			fDownload = (File) resp.getData();
			msg =  "Se creo el archivo satisfactoriamente > " + vo.getRuta();
		}catch(ServiceException se){
			msg = se.getMessage();			
			se.printStackTrace();
		}catch(Exception e){
			msg = "Ocurrio un error al tratar de generar el archivo. ";
			log.error(msg + e.getLocalizedMessage());
			e.printStackTrace();
		}		
		List<OficioCnbvModel> listaOficiosResult = (List<OficioCnbvModel>) archivoCnbvService.buscarDatosOficioAndRegistro(vo).getData();
		vo.setListaOficiosResult(listaOficiosResult);
		response = new ExtjsResponseMsg(vo, msg);
		return response;
	}
	
	/**
	 * Recibe la petición de la vista de invocar el servicio de descarga del archivo.
	 * @param request
	 * @param httpResponse
	 * @throws IOException
	 */
	@RequestMapping(value = "/operativos/archivoCnbv/descargar.htm", method = RequestMethod.GET)
	@ResponseBody
	public void generarArchivoDownload( HttpServletRequest request, HttpServletResponse httpResponse )throws IOException{
		log.debug(">>"+ fDownload);
		if(fDownload!=null){
			byte[] pdfImage = FileUtil.converterFileToArray(fDownload);
			httpResponse.setContentType("application/pdf");
			httpResponse.setHeader("Content-Disposition", "attachment; filename="+fDownload.getName());
			httpResponse.setHeader("Pragma", "no-cache");
			httpResponse.setContentLength(pdfImage.length);	
		    ServletOutputStream out = httpResponse.getOutputStream();
		    out.write(pdfImage);
		    out.flush();
		    out.close();
		}
	}
	
}

