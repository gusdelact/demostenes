package com.gfi.bin.admctasweb.operativos.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.UploadedFile;
import com.gfi.bin.admctasweb.operativos.service.OficioRespuestaNegativaService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Controller
public class OficiosRespuestasNegativasController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(OficiosRespuestasNegativasController.class);
	
	@Autowired
	private OficioRespuestaNegativaService oficioRespuestaNegativaService;
	
	@Autowired
	private OficioService oficioService;
	
	/**
	 * Realiza la consulta de un oficio
	 * 
	 * @param vo
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/oficiosrespuestasnegativas/read.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg readOficio(@RequestBody OficioModel vo) throws Exception {
		LOGGER.info("readOficio; vo:" + vo);
		
		OficioModel type = oficioService.buscarOficioPorLlave(vo.getNumOficio(), vo.getTipoOficio());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(type);
		
		return response;
	}
	
	/**
	 * Realiza la actualización de todos los oficios que contengan el mismo archivo TXT, se les actualiza 
	 * el numero de folio, se registra un nuevo documento para cada oficio y se agrega el archivo seleccionado
	 * al repositorio de acuerdo a la fecha de cada oficio
	 * 
	 * @param uploadedFile
	 * @param numeroOficio
	 * @param tipoOficio
	 * @param nombreArchivo
	 * @param nombreAcuse
	 * @param httpServletResponse
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/oficiosrespuestasnegativas/update.htm", method = RequestMethod.POST)
	@ResponseBody
	public void updateOficio(@ModelAttribute("file") UploadedFile uploadedFile, 
										 @ModelAttribute("numOficio") String numeroOficio,
										 @ModelAttribute("tipoOficio") String tipoOficio,
										 @ModelAttribute("txNomArch") String nombreArchivo,
										 @ModelAttribute("txNomAcu") String nombreAcuse, HttpServletResponse httpServletResponse) throws Exception {
		LOGGER.debug("en update oficio");
		LOGGER.debug("File es: "+uploadedFile+", numOficio:"+numeroOficio+", tipoOficio:"+tipoOficio+", nomArchivo:"+nombreArchivo+", nomAcuse(Folio):"+nombreAcuse);
		LOGGER.info("File de UploadedFile es:"+uploadedFile.getFile());
		
		Boolean resultado = true;
		
		MultipartFile multipartFile = uploadedFile.getFile();
		
		String mensaje = null;
		if(multipartFile == null){
			mensaje = "Debe seleccionar un archivo";
			resultado = false;
		}
		else if(!multipartFile.getOriginalFilename().toUpperCase().endsWith(Constantes.EXTENSION_PDF.toUpperCase())){
			mensaje = "El archivo a enviar debe tener extension pdf";
			resultado = false;
		}
		
		if(resultado){
			try{
				resultado = oficioRespuestaNegativaService.modificarOficiosRespuestasNegativas(nombreArchivo, nombreAcuse, multipartFile);
			}catch(ServiceException serviceException){
				LOGGER.error("Error en modificación de respuestas negativas");
				resultado = false;
				serviceException.printStackTrace();
			}
			
			if(resultado)
				mensaje = "Los oficios fueron actualizados y el archivo se transfirio exitosamente.";
			else
				mensaje = "Los oficios no fueron actualizados, \\n ocurrió un error";//para estos documentos ya existe clave de seguimiento 'REGACUC'
		}
		
		httpServletResponse.setContentType("application/json");
		PrintWriter out = httpServletResponse.getWriter();
        out.println("{");
        out.println("\"mensaje\": \""+mensaje+"\",");
        out.println("}");
        out.close();
	}
}