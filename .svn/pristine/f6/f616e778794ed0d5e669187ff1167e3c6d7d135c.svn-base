/**
 * 
 */
package com.gfi.bin.admctasweb.operativos.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoModel;
import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.catalogos.service.DocumentoService;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.catalogos.service.ParametrosService;
import com.gfi.bin.admctasweb.catalogos.service.PersonaService;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.operativos.dao.RespuestaDao;
import com.gfi.bin.admctasweb.operativos.model.ContratoCambiosModel;
import com.gfi.bin.admctasweb.operativos.model.ContratoModel;
import com.gfi.bin.admctasweb.operativos.model.PersonaCorporativaModel;
import com.gfi.bin.admctasweb.operativos.model.RespuestaModel;
import com.gfi.bin.admctasweb.operativos.service.ContratoService;
import com.gfi.bin.admctasweb.operativos.service.PersonaCorporativaService;
import com.gfi.bin.admctasweb.operativos.service.RespuestaService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.SFTPUtil;
import com.gfi.bin.admctasweb.util.Util;

/**
 * @author lugl4884
 *
 */
@Component
@Service
public class RespuestaServiceImpl implements RespuestaService {
	final Logger log = LoggerFactory.getLogger(RespuestaServiceImpl.class);
	
	@Autowired
	private RespuestaDao respuestaDao;
	
	@Autowired
	private PersonaCorporativaService personaCorporativaService;
	
	@Autowired
	private PersonaService personaService;
	
	@Autowired
	private ContratoService contratoService;
	
	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private DocumentoService documentoService;
	
	@Autowired
	private OficioService oficioService;
	
	@Autowired
	private ParametrosService parametrosService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean guardarRespuesta(RespuestaModel respuesta)
			throws ServiceException {
		Boolean result = false;
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		
		try{
			//Guardamos la Respuesta
			log.info("Inicia Generacion de respuesta Posible ERROR");
			log.info("Oficio: " + respuesta.getNumOficio());
			log.info("Tipo Oficio: " + respuesta.getTipoOficio());
			log.info("Persona: " + respuesta.getNomTitular());
			result = this.respuestaDao.guardarRespuesta(respuesta);
			
			//Ejecuta store para verificar si debe marcar oficio
			// 0 - Aun hay Personas Pendientes, No se marca el Oficio
			// 1 - Se marca el oficio como POSITIVO.
			// 2 - Se Marca el Oficio como NEGATIVO.
			int res = respuestaDao.marcarTipoOficio(respuesta.getNumOficio(), respuesta.getTipoOficio());
			log.info("Resultado de Procedure que marca al Oficio: " + res);
			
			//Si el Oficio se Marca como Positivo se  envia la Notificación a Juridico.
			if (res == 1) {
				seguimiento.setTipoCaso(Constantes.TIPO_CASO_POSITIVO);
				
				//Buscamos Todas las Respuestas Positivas del Oficio.
				List<RespuestaModel> listResp = new ArrayList<RespuestaModel>();
				listResp = this.respuestaDao.buscarRespuestasPorCaso(
						respuesta.getNumOficio(), respuesta.getTipoOficio(),
						Constantes.TIPO_CASO_POSITIVO);

				//Generamos Notificación para Area de Jurídico.
				this.enviarEmailSeguimiento(listResp, "Solicitud de Dictamen Jurídico: ");
				
			}else if (res == 2) {
				//Guardamos seguimiento del Oficio.
				seguimiento.setTipoCaso(Constantes.TIPO_CASO_NEGATIVO);
				
				//Se quita Notificación de Correo para casos Negativos a Petición del Usuario
				//this.enviarEmailSeguimientoNeg(respuesta.getNumOficio(), respuesta.getTipoOficio(), "Notificación Oficio Negativo: ");
			}
			
			if (res == 1 || res == 2) {
				//Guardamos Seguimiento del Oficio.
				seguimiento.setNumOficio(respuesta.getNumOficio());
				seguimiento.setTipoOficio(respuesta.getTipoOficio());
				seguimiento.setCveEstatus(Constantes.BUSQUEDA_OFICIO_COMP);
				
				this.bitacoraSeguimientoService.guardar(seguimiento);
			}
					
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean eliminarRespuesta(RespuestaModel respuesta)
			throws ServiceException {
		Boolean result = false;
		
		try{
			result = this.respuestaDao.eliminarRespuesta(respuesta);
		}catch(DAOException e) {
			throw new ServiceException(e);
		}
		
		return result;
	}

	public List<RespuestaModel> buscarRespuestasPorOficio(String numOficio,
			String tipoOficio) throws ServiceException {
		try{
			return this.respuestaDao.buscarRespuestasPorOficio(numOficio, tipoOficio);
		}catch(DAOException e){
			log.error(e.getLocalizedMessage());
			throw new ServiceException(e);
		}
	}

	public boolean existeRespuesta(RespuestaModel respuesta)
			throws ServiceException {
		Boolean existe = false;
		
		try {
			if(this.respuestaDao.existeRespuesta(respuesta) > 0){
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
		
		return existe;
	}

	public RespuestaModel generaObjetoRespuesta(RespuestaModel resp, String tipoResp)
			throws Exception {
		
		PersonaCorporativaModel personaCorp = new PersonaCorporativaModel();
		PersonaModel persona = new PersonaModel();
		ContratoModel contrato = new ContratoModel();
		ContratoCambiosModel contratoCambios = new ContratoCambiosModel();
		
		//Obtenemos la Persona del Oficio.
		persona.setNumOficio(resp.getNumOficio());
		persona.setTipoOficio(resp.getTipoOficio());
		persona.setNumConsec(resp.getNumConsec());
		persona = (PersonaModel) this.personaService.buscarPersonaId(persona).getData();
				
		//Armamos el Objeto de Respuesta que se va a persistir.
		RespuestaModel respuesta = new RespuestaModel();
		
		respuesta.setNumOficio(resp.getNumOficio());
		respuesta.setTipoOficio(resp.getTipoOficio());
		respuesta.setNumConsec(resp.getNumConsec());
		respuesta.setNomTitular(persona.getNombre() + " " + StringUtils.defaultIfEmpty(persona.getApPaterno(), "") + " " + StringUtils.defaultIfEmpty(persona.getApMaterno(), ""));
		respuesta.setFhAlta(new Date());
		respuesta.setCveUsuAlta(Util.usuarioSesion());
		
		if(tipoResp.equals(Constantes.RESPUESTA_POSITIVA)) {
			
			//Obtenemos el registro de Persona Corporativa.
			//Se modifica para obtener la persona con el id proporcionado desde la vista
			personaCorp = this.personaCorporativaService.obtenerPersonaPorId(resp.getIdPersona());
			
			Long tipoTitular = new Long(0);
			Long sitCuenta = new Long(0);
			
			Long idContrato = new Long(0);;
			String cveContratante = "";
			String sitContrato = "";
			
			if(resp.getTipoBusqueda().equals("CCAMBIOS")){
				//Obtenemos el Contrato CCAMBIOS.
				contratoCambios = this.contratoService.obtenerContratoCambiosPorId(resp.getIdContrato());
				
				idContrato = contratoCambios.getIdContrato();
				sitContrato = contratoCambios.getSituacionContrato();
			}else{
				//Obtenemos el Contrato DPVISTA, BURSATIL, FIDEICOMISOS y CREDITOS.
				contrato = this.contratoService.obtenerContratoPorId(resp.getIdContrato(), personaCorp.getIdPersona());
				
				idContrato = contrato.getIdContrato();
				cveContratante = contrato.getCveContratante();
				sitContrato = contrato.getSituacionContrato();
				
				// Validación de Tipo de Titular.
				if (StringUtils.isNotBlank(persona.getRfc())){
					if (persona.getRfc().equals(personaCorp.getRfc())) {
						if (cveContratante.equals("C001") || cveContratante.equals("C022") || cveContratante.equals("C023")) {
							tipoTitular = Long.valueOf(1);
						} else if (cveContratante.equals("C003")) {
							tipoTitular = Long.valueOf(2);
						} else {
							tipoTitular = Long.valueOf(3);
						}
					} else {
						tipoTitular = Long.valueOf(5);
					}
				}else{
					tipoTitular = Long.valueOf(4);
				}
			}
			
			//Validación de Situación de la Cuenta.
			if (sitContrato.equals("AC") || sitContrato.equals("BL")) {
				sitCuenta = Long.valueOf(1);
			}else if (sitContrato.equals("CA")) {
				sitCuenta = Long.valueOf(2);
			}
			
			respuesta.setIdContrato(idContrato);
			respuesta.setTipoRespuesta(resp.getTipoRespuesta());
			respuesta.setTipoCaso(Constantes.TIPO_CASO_POSITIVO);
			respuesta.setSitEnvio(Constantes.SITUACION_ENVIO_NEGATIVA);
			respuesta.setbMedioElec(Constantes.MEDIO_ELECTRONICO_NEGATIVO);
			respuesta.setTipoTitular(tipoTitular);
			respuesta.setSitCuenta(sitCuenta);
		}else if (tipoResp.equals(Constantes.RESPUESTA_NEGATIVA)) {
			respuesta.setIdContrato(Long.valueOf(0));
			respuesta.setTipoRespuesta(resp.getTipoRespuesta());
			respuesta.setTipoCaso(Constantes.TIPO_CASO_NEGATIVO);
			respuesta.setSitEnvio(Constantes.SITUACION_ENVIO_NEGATIVA);
			respuesta.setbMedioElec(Constantes.MEDIO_ELECTRONICO_NEGATIVO);
			respuesta.setTipoTitular(Long.valueOf(0));
			respuesta.setSitCuenta(Long.valueOf(0));
		}else if (tipoResp.equals(Constantes.RESPUESTA_CLIENTE)){
			respuesta.setIdContrato(Long.valueOf(0));
			respuesta.setTipoRespuesta(resp.getTipoRespuesta());
			respuesta.setTipoCaso(Constantes.TIPO_CASO_POSITIVO);
			respuesta.setSitEnvio(Constantes.SITUACION_ENVIO_NEGATIVA);
			respuesta.setbMedioElec(Constantes.MEDIO_ELECTRONICO_NEGATIVO);
			respuesta.setTipoTitular(Long.valueOf(4));
			respuesta.setSitCuenta(Long.valueOf(0));
		}else if (tipoResp.equals(Constantes.RESPUESTA_CUENTA)){
			respuesta.setIdContrato(resp.getIdContrato());
			respuesta.setTipoRespuesta(resp.getTipoRespuesta());
			respuesta.setTipoCaso(Constantes.TIPO_CASO_POSITIVO);
			respuesta.setSitEnvio(Constantes.SITUACION_ENVIO_NEGATIVA);
			respuesta.setbMedioElec(Constantes.MEDIO_ELECTRONICO_NEGATIVO);
			respuesta.setTipoTitular(Long.valueOf(0));
			respuesta.setSitCuenta(Long.valueOf(0));
		}
		
		return respuesta;
	}
	
	//Metodo para la Generación de Notificaciones al Area de Jurídico.
	private void enviarEmailSeguimiento(List<RespuestaModel> listResp, String operacion) {
		String templateVelocity = "mail/solicitudDictamenJuridico.vm";
		StringBuffer msgEncMail = new StringBuffer();
		String numOficio = listResp.get(0).getNumOficio();
		String tipoOficio = listResp.get(0).getTipoOficio();
		String txTipoOficio = "";
		List<DocumentoModel> listDoc = new ArrayList<DocumentoModel>();
		List<File> attachments = new ArrayList<File>();
		File archivoDoc = null;
		File archivoTif = null;
		File archivoPdf = null;
		FileOutputStream fos = null;
		
		txTipoOficio = Util.obtenerDescTipoOficio(tipoOficio);
		
		try {
			//Consultamos el Oficio para obtener dias de Plazo.
			OficioModel oficio = this.oficioService.buscarOficioPorLlave(numOficio, tipoOficio);
			
			//Consultamos la URL del Sistema desde la Tabla de parámetros.
			ParametrosModel parametro = new ParametrosModel();
			parametro.setCveGpoParam(Constantes.GPO_PARAMETRO_URL_ADMCTASWEB);
			parametro.setCveParam(Constantes.CVE_PARAMETRO_URL_ADMCTASWEB);
			parametro = this.parametrosService.consultarParametroPorClave(parametro);
			
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("listResp", listResp);
			model.put("numOficio", numOficio);
			model.put("tipoOficio", txTipoOficio);
			model.put("diasPzo", oficio.getNumDiasPzo());
			model.put("url", parametro.getDescParam());
			
			//Si el Plazo es menor o igual a 3 dias el mensaje se manda como Urgente.
			if (oficio.getNumDiasPzo() <= Constantes.DIAS_PLAZO_URGENTE){
				msgEncMail.append(Constantes.MSG_PLAZO_URGENTE);
				msgEncMail.append(" ");
			}
			msgEncMail.append(operacion);
			msgEncMail.append(" ");
			msgEncMail.append(numOficio);
			
			//Seteamos los datos de la plantilla.
			velocityEmailMessageProvider.fillblanks(templateVelocity, null, model);
			velocityEmailMessageProvider.changeSubject(msgEncMail.toString());
			
			//Consultamos los Documentos asociados al Oficio.
			listDoc = this.documentoService.buscarDocumentosPorOficio(numOficio, tipoOficio);
			
			DocumentoModel documento = new DocumentoModel();
			Iterator<DocumentoModel> it = listDoc.iterator();
			while (it.hasNext()) {
				documento = it.next();
				if (documento.getNomDocto().endsWith(".doc")){
					//archivoDoc = new File (this.documentoService.armaRutaDocumento(numOficio, tipoOficio, documento.getNomDocto()));
					archivoDoc = new File(documento.getNomDocto());
			        fos = new FileOutputStream(archivoDoc);
			        fos.write(SFTPUtil.descargarArchivoBytes(this.documentoService.armaRutaDocumento(numOficio, tipoOficio, documento.getNomDocto())));
			        fos.flush();
			        fos.close();
				}else if (documento.getNomDocto().endsWith(".tif")) {
					//archivoTif = new File (this.documentoService.armaRutaDocumento(numOficio, tipoOficio, documento.getNomDocto()));
					archivoTif = new File(documento.getNomDocto());
			        fos = new FileOutputStream(archivoTif);
			        fos.write(SFTPUtil.descargarArchivoBytes(this.documentoService.armaRutaDocumento(numOficio, tipoOficio, documento.getNomDocto())));
			        fos.flush();
			        fos.close();
				}else if (documento.getNomDocto().endsWith(".pdf")) {
					//archivoPdf = new File (this.documentoService.armaRutaDocumento(numOficio, tipoOficio, documento.getNomDocto()));
					archivoPdf = new File(documento.getNomDocto());
			        fos = new FileOutputStream(archivoPdf);
			        fos.write(SFTPUtil.descargarArchivoBytes(this.documentoService.armaRutaDocumento(numOficio, tipoOficio, documento.getNomDocto())));
			        fos.flush();
			        fos.close();
				}
			}
			
			if (archivoDoc != null){
				attachments.add(archivoDoc);
			}
			
			if (archivoTif != null){
				attachments.add(archivoTif);
			}
			
			if (archivoPdf != null){
				attachments.add(archivoPdf);
			}

			mailService.send(velocityEmailMessageProvider, Constantes.BUSQUEDA_OFICIO_COMP, attachments);
			
		} catch (Exception e) {
			final String msg = "Excepción en el envío de emails con recursos incrustados.";
			log.warn(msg, e);
		}
	}
	
	//Metodo para la Generación de Notificacion cuando el Oficio se marca como negativo.
	@SuppressWarnings("unused")
	private void enviarEmailSeguimientoNeg(String numOficio, String tipoOficio, String operacion) {
		String templateVelocity = "mail/notificacionOficioNeg.vm";
		AttachmentMail[] fileAttachment = null;
		String txTipoOficio = "";
		
		txTipoOficio = Util.obtenerDescTipoOficio(tipoOficio);
		
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("numOficio", numOficio);
			model.put("tipoOficio", txTipoOficio);
			
			velocityEmailMessageProvider.fillblanks(templateVelocity, null, model);
			velocityEmailMessageProvider.changeSubject(operacion + " " + numOficio);

			mailService.send(velocityEmailMessageProvider, Constantes.BUSQUEDA_OFICIO_COMP, fileAttachment);
			
		} catch (Exception e) {
			final String msg = "Excepción en el envío de mail de Notificación.";
			log.warn(msg, e);
		}
	}
	
	
}
