package com.gfi.bin.admctasweb.catalogos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoListModel;
import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.catalogos.service.DocumentoService;
import com.gfi.bin.admctasweb.util.SFTPUtil;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * @author LUGL4884
 * Controlador para Documentos.
 */
@Controller
public class DocumentoController {
	final Logger logger = LoggerFactory.getLogger(DocumentoController.class);
	
	@Autowired
	private DocumentoService documentoService;
	
	
	@RequestMapping(value = "catalogos/documentoView/read.htm" , method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerDocumento(@RequestBody DocumentoModel vo) throws Exception{
		logger.info("Inicia ejecución de DocumentoController");

		DocumentoModel documento = this.documentoService.buscarDocumentoPorId(vo.getNumOficio(), vo.getTipoOficio(), vo.getNumDocto());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(documento);
		
		return response;
	}
	
	@RequestMapping(value = "catalogos/documento/read.htm" , method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerListaDocumentos(@RequestBody DocumentoListModel vo) throws Exception{
		logger.info("Inicia ejecución de DocumentoController");

		List<DocumentoModel> doctoList = this.documentoService.buscarDocumentosPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		
		DocumentoListModel documentos = new DocumentoListModel();
		documentos.setNumOficio(vo.getNumOficio());
		documentos.setTipoOficio(vo.getTipoOficio());
		documentos.setDocumentoList(doctoList);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(documentos);
		
		return response;
	}
	
	@RequestMapping(value = "catalogos/documento/carga.htm" , method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg cargarDocumento(DocumentoModel documento) throws Exception{
		logger.info("Inicia ejecución de DocumentoController - cargarDocumento");
		String msg = "";
		String ruta ="";
		
		if(this.documentoService.existeDocumento(documento.getNumOficio(), documento.getTipoOficio(), documento.getNumDocto())) {
			documento.setCveUsuMod(Util.usuarioSesion());
			documento.setFhUltMod(new Date());
			
			//Modificamos el registro en la BD.
			this.documentoService.modificarDocumento(documento);
			msg = "El documento se actualizó correctamente";
		} else {
			documento.setNumDocto(this.documentoService.obtenerConsecutivo(documento.getNumOficio(), documento.getTipoOficio()));
			documento.setCveUsuAlta(Util.usuarioSesion());
			documento.setFhAlta(new Date());
			
			//Guardamos registro en la BD.
			this.documentoService.guardarDocumento(documento);
			msg = "El documento se guardó correctamente";
		}
		
		//Armamos ruta de archivo.
		ruta = this.documentoService.armaRutaDocumento(documento.getNumOficio(), documento.getTipoOficio(), null);
		logger.debug("Ruta de borrado de documentos: " + ruta);
		
		//Creamos la carpeta del repositorio si no existe.
		if (!SFTPUtil.existeDirectorio(ruta)) {
			SFTPUtil.crearDirectorioRemoto(ruta);
		}

		//Armamos la ruta del documento.
		ruta = ruta + "/" + documento.getFile().getOriginalFilename();
		
		//Copiamos el documento al Repositorio Final.
		SFTPUtil.subirArchivo(documento.getFile().getInputStream(), ruta);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(msg);
		
		return response;
	}
	
	@RequestMapping(value = "catalogos/documento/update.htm" , method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg actualizarDocumento(@RequestBody DocumentoListModel vo) throws Exception{
		logger.info("Inicia ejecución de actualizarDocumento");
		
		List<DocumentoModel> lista = new ArrayList<DocumentoModel>();
		DocumentoListModel documentoList = new DocumentoListModel();
		String msg = "Se ha actualizado la lista de Documentos";
		
		//Consultamos la lista de documentos actualizada.
		lista = this.documentoService.buscarDocumentosPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		
		documentoList.setNumOficio(vo.getNumOficio());
		documentoList.setTipoOficio(vo.getTipoOficio());
		documentoList.setDocumentoList(lista);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(documentoList, msg);
		
		return response;
	}
	
	@RequestMapping(value = "catalogos/documento/delete.htm" , method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg eliminarDocumento(@RequestBody DocumentoModel vo) throws Exception{
		logger.info("Inicia ejecución de DocumentoController - eliminarDocumento");
		String ruta = "";
		
		//Consultamos el documento por su Id.
		DocumentoModel documento = this.documentoService.buscarDocumentoPorId(vo.getNumOficio(), vo.getTipoOficio(), vo.getNumDocto());
		
		//Eliminamos registro de Documento de la BD.
		this.documentoService.eliminarDocumento(documento);
		
		//Armamos la ruta del documento.
		ruta = this.documentoService.armaRutaDocumento(documento.getNumOficio(), documento.getTipoOficio(), documento.getNomDocto());
		logger.debug("Ruta de borrado de documentos: " + ruta);
		
		//Borramos el archivo del Repositorio.
		SFTPUtil.borrarArchivo(ruta);

		//Recuperamos lista de documentos actualizada.
		List<DocumentoModel> doctoList = this.documentoService.buscarDocumentosPorOficio(documento.getNumOficio(), documento.getTipoOficio());
		
		DocumentoListModel documentos = new DocumentoListModel();
		documentos.setNumOficio(documento.getNumOficio());
		documentos.setTipoOficio(documento.getTipoOficio());
		documentos.setDocumentoList(doctoList);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(documentos, "El Documento se eliminó correctamente");
		
		return response;
	}
	
	@RequestMapping(value = "catalogos/documento/visualizar.htm", method = RequestMethod.GET)
	@ResponseBody
	public void visualizarDocumento(HttpServletRequest request, HttpServletResponse httpServletResponse) throws Exception {
		
		logger.info("Inicia ejecución de DocumentoController - visualizarDocumento");
		String numOficio = request.getParameter("numOficio");
		String tipoOficio = request.getParameter("tipoOficio");
		String nomDocto = request.getParameter("nomDocto");
		
		//Armamos la ruta del documento.
		String ruta = this.documentoService.armaRutaDocumento(numOficio, tipoOficio, nomDocto);
		logger.debug("Ruta de visualización de documentos: " + ruta);
		
		String contentType = "";
		
		//Validamos el ContentType
		if (nomDocto.endsWith(".doc")) {
			contentType = "application/msword";
		}else if (nomDocto.endsWith("tif")){
			contentType = "image/tiff";
		}else if (nomDocto.endsWith("xml")){
			contentType = "application/xml";
		}

		byte[] content = null;
		
		try {
			//content = FileUtil.obtenArchivoPdf(ruta);
			content = SFTPUtil.descargarArchivoBytes(ruta);
		} catch (IOException e) {
			Log.error("Error al Visualizar Documento: "	+ e.getLocalizedMessage());
		}
		
		if (content != null) {
			httpServletResponse.setContentType(contentType);
			httpServletResponse.setHeader("Content-Disposition","attachment; filename=" + nomDocto);
			httpServletResponse.setHeader("Pragma", "no-cache");
			httpServletResponse.setContentLength(content.length);

			ServletOutputStream out = httpServletResponse.getOutputStream();
			out.write(content);
			out.flush();
			out.close();
		}
	}
	
}
