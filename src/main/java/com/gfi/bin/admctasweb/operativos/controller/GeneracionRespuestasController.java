package com.gfi.bin.admctasweb.operativos.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.catalogos.model.SimilaridadModel;
import com.gfi.bin.admctasweb.catalogos.service.PersonaService;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaBusquedaModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaListModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;
import com.gfi.bin.admctasweb.operativos.service.ContratoService;
import com.gfi.bin.admctasweb.operativos.service.PersonaCorporativaService;
import com.gfi.bin.admctasweb.operativos.service.RespuestaService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Response;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * Controlador para generar respuestas de las personas de un Oficio
 * @author ESS3VAVC
 *
 */
@Controller
public class GeneracionRespuestasController {
	
	@Autowired
	PersonaCorporativaService personaCorporativaService;
	
	@Autowired
	RespuestaService respuestaService;
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	ContratoService contratoService;
	
	private final Logger logger = LoggerFactory.getLogger(GeneracionRespuestasController.class);
	
	/**
	 * Realiza la búsqueda de personas en la BD Corporativa
	 * La búsqueda se realiza por Nombre, RFC y porcentaje de similitud
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/buscarpersonas.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerListaPersonas(@RequestBody PersonaBusquedaModel vo) throws Exception{
				
		List<PersonaCorporativaModel> personaList = personaCorporativaService.obtenerPersonas(vo.getRfc(), generaNombre(vo), vo.getSimilaridad());
		
		vo.setPersonaList(personaList);
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(vo);
		
		return response;
	}
	
	/**
	 * Realiza la búsqueda de los Contratos o Cuentas asociados a una Persona corporativa
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/buscarcontratos.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg obtenerContratos(@RequestBody PersonaBusquedaModel vo) throws Exception{
		logger.debug("obtenerContratos; vo.getIdPersona(): "+vo.getIdPersona()+", vo.getIdContrato(): "+vo.getIdContrato());
		
		List<ContratoModel> contratos = contratoService.obtenerContratos(vo.getIdPersona(), vo.getIdContrato());
		vo.setContratosList(contratos);
		
		List<ContratoCambiosModel> contratosCambios = null;
		
		if(vo.getIdContrato() == 0){
			logger.debug("Se va a consultar contratosCambios...");
			contratosCambios = contratoService.obtenerContratosCambios(vo.getIdPersona());
			vo.setContratosCambiosList(contratosCambios);
		}
				
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(vo);
		
		return response;
	}
	
	/**
	 * Asocia un id de Persona corporativa a la Persona del Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/asociaroficio.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg asociarPersonaOficio(@RequestBody PersonaModel vo) throws Exception{
		
		RespuestaModel respuesta = new RespuestaModel();
		respuesta.setNumOficio(vo.getNumOficio());
		respuesta.setTipoOficio(vo.getTipoOficio());
		respuesta.setNumConsec(vo.getNumConsec());
		
		//Se agrega validación para verificar si ya tiene registrada una respuesta
		if (respuestaService.existeRespuesta(respuesta)) 
			throw new Exception("La persona ya tiene registrada una Respuesta");
		
		//El objeto vo solo trae la llave y el id de la persona corporativa, 
		//por lo que se realiza una consulta para  obtener la persona completa
		Response response = personaService.buscarPersonaId(vo);
		PersonaModel personaModel = (PersonaModel) response.getData();
		
		//Verifica si ya tiene id de persona asociado
		String idPersona = personaModel.getIdPersona()==null?null:personaModel.getIdPersona().toString();
		
		if(StringUtils.isNotBlank(idPersona))
			throw new Exception("La persona ya tiene asociado un Identificador");
					
		personaModel.setIdPersona(vo.getIdPersona());
		personaModel.setCveUsuMod(Util.usuarioSesion());
		personaModel.setFhUltMod(new Date());
		
		personaService.actualizarPersona(personaModel);
		
		ExtjsResponseMsg responseExtjs = new ExtjsResponseMsg(new String[]{"El registro ha sido actualizado exitosamente!!!"});
		//responseExtjs.setInfo(personaModel));
		return responseExtjs;
	}
	
	/**
	 * Desasocia un id de Persona corporativa de la Persona de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/cambiarcliente.htm",method = RequestMethod.POST)
	public @ResponseBody ExtjsResponseMsg cambiarCliente(@RequestBody PersonaModel vo) throws Exception{
		
		RespuestaModel respuesta = new RespuestaModel();
		respuesta.setNumOficio(vo.getNumOficio());
		respuesta.setTipoOficio(vo.getTipoOficio());
		respuesta.setNumConsec(vo.getNumConsec());
		
		//Se agrega validación para verificar si ya tiene registrada una respuesta
		if (respuestaService.existeRespuesta(respuesta)) 
			throw new Exception("La persona ya tiene registrada una Respuesta");
		
		//El objeto vo solo trae la llave y el id de la persona corporativa, 
		//por lo que se realiza una consulta para  obtener la persona completa
		Response response = personaService.buscarPersonaId(vo);
		PersonaModel personaModel = (PersonaModel) response.getData();
		
					
		personaModel.setIdPersona(null);//Se elimina persona asociada
		personaModel.setCveUsuMod(Util.usuarioSesion());
		personaModel.setFhUltMod(new Date());
		
		personaService.actualizarPersona(personaModel);
		
		ExtjsResponseMsg responseExtjs = new ExtjsResponseMsg(new String[]{"El registro ha sido actualizado exitosamente!!!"});
		//responseExtjs.setInfo(personaModel));
		return responseExtjs;
	}
	
	
	/**
	 * Método privado para concatener nombres y apellidos de una persona
	 * @param vo
	 * @return
	 */
	private String generaNombre(PersonaBusquedaModel vo)
	{
		StringBuffer nombre = new StringBuffer(vo.getNombre());
				
		if(StringUtils.isNotBlank(vo.getPaterno())){
			nombre.append(" ");
			nombre.append(vo.getPaterno());
		}
		
		if(StringUtils.isNotBlank(vo.getMaterno())){
			nombre.append(" ");
			nombre.append(vo.getMaterno());
		}
		
		return nombre.toString();
	}
	
	/**
	 * Obtiene las respuestas de las Personas de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/read.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerListaRespuestas(@RequestBody RespuestaListModel vo) throws Exception{
				
		List<RespuestaModel> respuestaList = this.respuestaService.buscarRespuestasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		
		vo.setRespuestaList(respuestaList);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(vo);
		
		return response;
	}
	
	/**
	 * Elimina la respuesta de una Persona de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/update.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg updateListaRespuestas(@RequestBody RespuestaModel vo) throws Exception {
		
		this.respuestaService.eliminarRespuesta(vo);
		
		RespuestaListModel respuesta = new RespuestaListModel();
		//Recuperamos Respuestas.
		List<RespuestaModel> respuestaList = this.respuestaService.buscarRespuestasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		respuesta.setRespuestaList(respuestaList);
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(respuesta);
		
		return response;
	}
	
	/**
	 * Registra respuesta Positiva para Persona de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/positiva.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg generaRespuestaPositiva(@RequestBody RespuestaModel vo) throws Exception {
		List <String> msg = new ArrayList<String>();
		RespuestaListModel respList = new RespuestaListModel();
		RespuestaModel respuesta = new RespuestaModel();
		PersonaModel persona = new PersonaModel();
		
		//Validamos que la respuesta no haya sido registrada.
		if (!this.respuestaService.existeRespuesta(vo)) {
			respuesta = this.respuestaService.generaObjetoRespuesta(vo, Constantes.RESPUESTA_POSITIVA);
			
			if (this.respuestaService.guardarRespuesta(respuesta)) {
				persona.setNumOficio(vo.getNumOficio());
				persona.setTipoOficio(vo.getTipoOficio());
				persona.setNumConsec(vo.getNumConsec());

				persona = (PersonaModel) this.personaService.buscarPersonaId(persona).getData();
				persona.setSitRespuesta(Constantes.TIPO_CASO_POSITIVO);
				
				this.personaService.actualizarPersona(persona);
				
				msg.add("La Respuesta fue generada correctamente.");
			}
		}else {//Se lanza excepción para evitar que se complete acción
			throw new Exception("El contrato ya ha sido registrado con anterioridad o la Persona ya tiene registrada una Respuesta. Favor de validar.");
			//msg.add("El contrato ya ha sido registrado con anterioridad o la Persona ya tiene registrada una Respuesta. Favor de validar.");
		}
		//Con la recarga del buscador del oficio no es necesario mandar las respuestas
		//List<RespuestaModel> respuestaList = this.respuestaService.buscarRespuestasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		//respList.setRespuestaList(respuestaList);
		respList.setNumOficio(vo.getNumOficio());
		respList.setTipoOficio(vo.getTipoOficio());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(respList);
		response.setMessages(msg);
		
		return response;
	}
	
	/**
	 * Registra respuesta Negativa para Persona de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/negativa.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg generaRespuestaNegativa(@RequestBody RespuestaModel vo) throws Exception {
		List <String> msg = new ArrayList<String>();
		RespuestaListModel respList = new RespuestaListModel();
		RespuestaModel respuesta = new RespuestaModel();
		PersonaModel persona = new PersonaModel();
		
		//Validamos que la respuesta no haya sido registrada.
		if (!this.respuestaService.existeRespuesta(vo)) {
			respuesta = this.respuestaService.generaObjetoRespuesta(vo, Constantes.RESPUESTA_NEGATIVA);
			
			if (this.respuestaService.guardarRespuesta(respuesta)) {
				persona.setNumOficio(vo.getNumOficio());
				persona.setTipoOficio(vo.getTipoOficio());
				persona.setNumConsec(vo.getNumConsec());
				persona = (PersonaModel) this.personaService.buscarPersonaId(persona).getData();
				
				persona.setSitRespuesta(Constantes.TIPO_CASO_NEGATIVO);
				
				this.personaService.actualizarPersona(persona);
				
				msg.add("La Respuesta fue generada correctamente.");
			}
		}else {
			throw new Exception("La Respuesta ya ha sido registrada con anterioridad. Favor de validar.");//Se lanza excepción para evitar que se complete acción
			//msg.add("La Respuesta ya ha sido registrada con anterioridad. Favor de validar.");
		}
		//Con la recarga del buscador del oficio no es necesario mandar las respuestas
		//List<RespuestaModel> respuestaList = this.respuestaService.buscarRespuestasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		//respList.setRespuestaList(respuestaList);
		respList.setNumOficio(vo.getNumOficio());
		respList.setTipoOficio(vo.getTipoOficio());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(respList);
		response.setMessages(msg);
		
		return response;
	}
	
	/**
	 * Registra respuesta Positiva a Cliente para Persona de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/cliente.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg generaRespuestaCliente(@RequestBody RespuestaModel vo) throws Exception {
		List <String> msg = new ArrayList<String>();
		RespuestaListModel respList = new RespuestaListModel();
		RespuestaModel respuesta = new RespuestaModel();
		PersonaModel persona = new PersonaModel();
		
		//Validamos que la respuesta no haya sido registrada.
		if (!this.respuestaService.existeRespuesta(vo)) {
			respuesta = this.respuestaService.generaObjetoRespuesta(vo, Constantes.RESPUESTA_CLIENTE);
			
			if (this.respuestaService.guardarRespuesta(respuesta)) {
				persona.setNumOficio(vo.getNumOficio());
				persona.setTipoOficio(vo.getTipoOficio());
				persona.setNumConsec(vo.getNumConsec());

				persona = (PersonaModel) this.personaService.buscarPersonaId(persona).getData();
				persona.setSitRespuesta(Constantes.TIPO_CASO_POSITIVO);
				
				this.personaService.actualizarPersona(persona);
				
				msg.add("La Respuesta fue generada correctamente.");
			}
		}else {
			throw new Exception("La Respuesta ya ha sido registrada con anterioridad. Favor de validar.");//Se lanza excepción para evitar que se complete acción
			//msg.add("La Respuesta ya ha sido registrada con anterioridad. Favor de validar.");
		}
		
		//Con la recarga del buscador del oficio no es necesario mandar las respuestas
		//List<RespuestaModel> respuestaList = this.respuestaService.buscarRespuestasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		//respList.setRespuestaList(respuestaList);
		respList.setNumOficio(vo.getNumOficio());
		respList.setTipoOficio(vo.getTipoOficio());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(respList);
		response.setMessages(msg);
		
		return response;
	}
	
	/**
	 * Registra respuesta Positiva a Cuenta para Persona de un Oficio
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/cuenta.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg generaRespuestaCuenta(@RequestBody RespuestaModel vo) throws Exception {
		List <String> msg = new ArrayList<String>();
		RespuestaListModel respList = new RespuestaListModel();
		RespuestaModel respuesta = new RespuestaModel();
		PersonaModel persona = new PersonaModel();
				
		//Validamos que la respuesta no haya sido registrada.
		if (!this.respuestaService.existeRespuesta(vo)) {
			respuesta = this.respuestaService.generaObjetoRespuesta(vo, Constantes.RESPUESTA_CUENTA);
			
			if (this.respuestaService.guardarRespuesta(respuesta)) {
				persona.setNumOficio(vo.getNumOficio());
				persona.setTipoOficio(vo.getTipoOficio());
				persona.setNumConsec(vo.getNumConsec());
				
				persona = (PersonaModel) this.personaService.buscarPersonaId(persona).getData();
				persona.setSitRespuesta(Constantes.TIPO_CASO_POSITIVO);
				
				this.personaService.actualizarPersona(persona);
				
				msg.add("La Respuesta fue generada correctamente.");
			}
		}else {
			throw new Exception("El contrato ya ha sido registrado con anterioridad. Favor de validar.");//Se lanza excepción para evitar que se complete acción
			//msg.add("El contrato ya ha sido registrado con anterioridad. Favor de validar.");
		}
		//Con la recarga del buscador del oficio no es necesario mandar las respuestas
		//List<RespuestaModel> respuestaList = this.respuestaService.buscarRespuestasPorOficio(vo.getNumOficio(), vo.getTipoOficio());
		//respList.setRespuestaList(respuestaList);
		respList.setNumOficio(vo.getNumOficio());
		respList.setTipoOficio(vo.getTipoOficio());
		
		ExtjsResponseMsg response = new ExtjsResponseMsg();
		response.setInfo(respList);
		response.setMessages(msg);
		
		return response;
	}
	
	/**
	 * Obtiene % Similitud configurado en la BD que se utiliza para realizar la búsqueda de Personas corporativas
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/operativos/respuesta/obtenersimilaridad.htm",method = RequestMethod.POST)
	public  @ResponseBody ExtjsResponseMsg obtenerSimilitud(@RequestBody SimilaridadModel vo) throws Exception{
		
		vo = personaCorporativaService.obtenerSimilaridad();
		ExtjsResponseMsg response = new ExtjsResponseMsg(vo);
		
		return response;
	}
	
	@RequestMapping(value = "/operativos/respuesta/reportecontratos.htm", method = RequestMethod.GET)
	@ResponseBody
	public void crearReporteContratos(HttpServletRequest request,
			HttpServletResponse httpServletResponse )throws IOException{
		
		Long idPersona = null;
		String parametro = request.getParameter("idPersona");
		if(StringUtils.isNumeric(parametro))
			idPersona = Long.valueOf(parametro);
		logger.debug("parametro(idPersona):"+parametro+", idPersona: "+idPersona);
		
		Long idContrato = new Long(0);
		String parametro2 = request.getParameter("idContrato");
		if(!StringUtils.isEmpty(parametro2) && StringUtils.isNumeric(parametro2))
			idContrato = Long.valueOf(parametro2);
		logger.debug("parametro2(idContrato):"+parametro2+", idContrato: "+idContrato);
		
		byte[] pdfImage = null;
		try {
			ServletContext servletContext = request.getSession().getServletContext();
			String relativePath = "resources/images/logo_gfinan.jpg";
			String absolutePath = servletContext.getRealPath(relativePath);
						
			pdfImage = contratoService.generarReporte(idPersona, idContrato, absolutePath);
			
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(pdfImage != null){
			httpServletResponse.setContentType("application/pdf");
			httpServletResponse.setHeader("Content-Disposition", "inline; filename=" + idPersona + ".pdf");
			httpServletResponse.setHeader("Pragma", "no-cache");
			httpServletResponse.setContentLength(pdfImage.length);
			
		    ServletOutputStream out = httpServletResponse.getOutputStream();
		    out.write(pdfImage);
		    out.flush();
		    out.close();
		}
	}
	
}
