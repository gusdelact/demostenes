package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jxls.exception.ParsePropertyException;
import net.sf.jxls.transformer.XLSTransformer;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.aspectj.util.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.model.DatosEmail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaOficioModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaPersonaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.NotificacionCargaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.NotificacionDocumentoModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.NotificacionOficioModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.NotificacionPersonaModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientoDescargadoModel;
import com.gfi.bin.admctasweb.procesoautomatico.model.RequerimientosDescargadosModel;
import com.gfi.bin.admctasweb.procesoautomatico.service.NotificacionProcesoAutomaticoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class NotificacionProcesoAutomaticoServiceImpl implements NotificacionProcesoAutomaticoService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(NotificacionProcesoAutomaticoServiceImpl.class);
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	
	/**
	 * Envía correo a usuarios configurados notificando el resumen del proceso automático
	 * 
	 * @param listaBitacoraCargaAutomatica
	 * @throws ServiceException
	 */
	@Override
	public void enviarNotificacion(List<BitacoraCargaAutomaticaModel> listaBitacoraCargaAutomatica) throws ServiceException {
		LOGGER.debug("Comienza enviarNotificacion");
		
		//Se obtendrá resumen a enviar
		List<NotificacionCargaModel> resumenNotificar = obtenerResumen(listaBitacoraCargaAutomatica);
		
		LOGGER.debug("resumenNotificar: "+resumenNotificar);
		//Se enviará mail
		enviarMailCargaAutomatica(resumenNotificar);
	}
	
	/**
	 * Obtiene un resumen de la carga automática que se enviará por correo electrónico
	 * 
	 * @param listaBitacoraCargaAutomatica
	 * @return List<NotificacionCargaModel>
	 */
	private List<NotificacionCargaModel> obtenerResumen(List<BitacoraCargaAutomaticaModel> listaBitacoraCargaAutomatica){
		LOGGER.debug("Comienza obtenerResumen");
		List<NotificacionCargaModel> listaNotificacionCarga = obtenerOficios(listaBitacoraCargaAutomatica);
		LOGGER.debug("listaNotificacionCarga: "+listaNotificacionCarga);
		
		return listaNotificacionCarga;
	}
	
	/**
	 * Obtiene oficios por empresa (Con y sin XML), junto con sus documentos y personas y documentos 
	 * 
	 * @param listaBitacoraCargaAutomatica
	 * @return List<NotificacionCargaModel>
	 */
	private List<NotificacionCargaModel> obtenerOficios(List<BitacoraCargaAutomaticaModel> listaBitacoraCargaAutomatica){
		LOGGER.debug("Comienza obtenerOficios");
		List<NotificacionCargaModel> listaNotificacionCarga = new ArrayList<NotificacionCargaModel>();
		List<NotificacionOficioModel> listaOficios = null;
		
		NotificacionCargaModel notificacionCargaEmpresa = null;
		
		for(BitacoraCargaAutomaticaModel cargaAutomaticaEmpresa : listaBitacoraCargaAutomatica){
			notificacionCargaEmpresa = new NotificacionCargaModel();
			notificacionCargaEmpresa.setIdCarga(cargaAutomaticaEmpresa.getIdCarga());
			notificacionCargaEmpresa.setIdEmpresa(cargaAutomaticaEmpresa.getIdEmpresa());
			if(Constantes.ID_EMPRESA_CASA == cargaAutomaticaEmpresa.getIdEmpresa()){
				notificacionCargaEmpresa.setEmpresa(Constantes.EMPRESA_CASA);
			}
			else if(Constantes.ID_EMPRESA_BANCO == cargaAutomaticaEmpresa.getIdEmpresa()){
				notificacionCargaEmpresa.setEmpresa(Constantes.EMPRESA_BANCO);
			}else{
				notificacionCargaEmpresa.setEmpresa(Constantes.EMPRESA_ASEGURADORA);
			}
			
			//Obtener los oficios de la empresa actual que sí tuvieron XML 
			listaOficios = obtenerOficios(cargaAutomaticaEmpresa.getListaOficios(), cargaAutomaticaEmpresa.getListaRequerimientosDescargados(), notificacionCargaEmpresa.getEmpresa());
			
			//Agregar folio a oficios
			int folio = 0;
			for(NotificacionOficioModel oficio : listaOficios){
				folio = folio+1;
				oficio.setFolio(folio);
			}
			
			notificacionCargaEmpresa.setListaOficios(listaOficios);
			listaNotificacionCarga.add(notificacionCargaEmpresa);
		}
		return listaNotificacionCarga;
	}
	
	/**
	 * Obtiene los oficios de que no tuvieron XML por empresa, se obtienen también sus personas y documentos 
	 * 
	 * @param listaOficiosConXML
	 * @param requerimientosDescargados
	 * @param empresa
	 * @return List<NotificacionOficioModel>
	 */
	private List<NotificacionOficioModel> obtenerOficios(List<BitacoraCargaAutomaticaOficioModel> listaOficiosConXML, List<RequerimientosDescargadosModel> requerimientosDescargados, String empresa){
		LOGGER.debug("Comienza obtenerOficiosConXML");
		List<NotificacionOficioModel> listaNotificacionOficioRespuesta = new ArrayList<NotificacionOficioModel>();
		List<NotificacionPersonaModel> listaNotificacionPersona = null;
		NotificacionPersonaModel notificacionPersona = null;
		
		try{
			NotificacionOficioModel notificacionOficio = null;
			
			if(listaOficiosConXML!=null)
				for(BitacoraCargaAutomaticaOficioModel cargaAutomaticaOficio : listaOficiosConXML){
					notificacionOficio = new NotificacionOficioModel();
					notificacionOficio.setNumOficio(cargaAutomaticaOficio.getNumeroOficio());
					
					if(Constantes.TIPO_OFICIO_AS.equals(cargaAutomaticaOficio.getTipoOficio().trim()))
						notificacionOficio.setTipoOficio(Constantes.TIPO_OFICIO_ASEGURAMIENTO);
					else if(Constantes.TIPO_OFICIO_HA.equals(cargaAutomaticaOficio.getTipoOficio().trim()))
						notificacionOficio.setTipoOficio(Constantes.TIPO_OFICIO_HACENDARIO);
					else if(Constantes.TIPO_OFICIO_JU.equals(cargaAutomaticaOficio.getTipoOficio().trim()))
						notificacionOficio.setTipoOficio(Constantes.TIPO_OFICIO_JUDICIAL);
					else if(Constantes.TIPO_OFICIO_PLD_PERSISTIR.equals(cargaAutomaticaOficio.getTipoOficio().trim()))
						notificacionOficio.setTipoOficio(Constantes.TIPO_OFICIO_OPERACIONES_ILICITAS_COMPLETO);
					notificacionOficio.setEmpresa(empresa);
					
					if(cargaAutomaticaOficio.getExisteXML().equals("N")){
						notificacionOficio.setTieneXML(Constantes.MENSAJE_NO_TIENE_XML.trim());
					}else{
						notificacionOficio.setTieneXML("");
					}
					notificacionOficio.setNumeroFolio(cargaAutomaticaOficio.getNumeroFolio());
//					notificacionOficio.setReferencia(cargaAutomaticaOficio.getReferencia()!=null? cargaAutomaticaOficio.getReferencia() : "");
					notificacionOficio.setReferencia(cargaAutomaticaOficio.getReferencia());
					
					notificacionOficio.setPlazoRespuesta(cargaAutomaticaOficio.getDiasPlazo());
					notificacionOficio.setObservaciones(cargaAutomaticaOficio.getObservaciones()==null? "" : cargaAutomaticaOficio.getObservaciones());
					
					//Se obtienen datos de personas que hayan tenido algun problema al insertar
					if(cargaAutomaticaOficio.getListaPersonas()!=null){
						listaNotificacionPersona = new ArrayList<NotificacionPersonaModel>();
						for(BitacoraCargaAutomaticaPersonaModel personaActual : cargaAutomaticaOficio.getListaPersonas()){
							notificacionPersona = new NotificacionPersonaModel();
							
							notificacionPersona.setNumeroConsecutivo(personaActual.getNumeroConsecutivoPersona());
							notificacionPersona.setObservaciones(personaActual.getObservaciones());
							notificacionPersona.setCuenta(personaActual.getCuenta());
							
							listaNotificacionPersona.add(notificacionPersona);
						}
						notificacionOficio.setListaPersonas(listaNotificacionPersona);
					}
					else{
						notificacionOficio.setListaPersonas(new ArrayList<NotificacionPersonaModel>());
					}
					
					//Se obtienen datos de documentos que hayan tenido problemas al insertar
					List<NotificacionDocumentoModel> notificacionesDoctos = new ArrayList<NotificacionDocumentoModel>();
					if(!"".equals(notificacionOficio.getObservaciones()) && requerimientosDescargados != null){
						RequerimientoDescargadoModel requerimiento = obtenerRequerimientoDescargado(notificacionOficio.getNumOficio(), requerimientosDescargados);
						String mensajeObservaciones = "El documento se está repitiendo debido a que el oficio también está repetido en algúna otra carpeta de archivos";
						
						notificacionesDoctos = new ArrayList<NotificacionDocumentoModel>();
						NotificacionDocumentoModel notifDocto = new NotificacionDocumentoModel();
						
						if(!Constantes.MENSAJE_NO_TIENE_XML.trim().equals(notificacionOficio.getTieneXML())){
							notifDocto.setNomDocto(requerimiento.getXmlFileName().trim());
							notifDocto.setObservaciones(mensajeObservaciones);
							notificacionesDoctos.add(notifDocto);
						}
						
						notifDocto = new NotificacionDocumentoModel();
						notifDocto.setNomDocto(requerimiento.getOficioFileName().trim());
						notifDocto.setObservaciones(mensajeObservaciones);
						notificacionesDoctos.add(notifDocto);
						
						notifDocto = new NotificacionDocumentoModel();
						notifDocto.setNomDocto(requerimiento.getRequerimientoFileName().trim());
						notifDocto.setObservaciones(mensajeObservaciones);
						notificacionesDoctos.add(notifDocto);
					}
					notificacionOficio.setListaDocumentos(notificacionesDoctos);
					
					listaNotificacionOficioRespuesta.add(notificacionOficio);
				}
		}catch(Exception exc){
			exc.printStackTrace();
		}
		return listaNotificacionOficioRespuesta;
	}
	
	/**
	 * Obtiene un objeto de tipo RequerimientoDescargadoModel a partir de un numero de oficio y una lista de requerimientos descargados
	 * 
	 * @param numeroOficio
	 * @param requerimientosDescargados
	 * @return RequerimientoDescargadoModel
	 */
	private RequerimientoDescargadoModel obtenerRequerimientoDescargado(String numeroOficio, List<RequerimientosDescargadosModel> requerimientosDescargados){
		LOGGER.debug("Comienza obtenerRequerimientoDescargado");
		
		for(RequerimientosDescargadosModel requerimientos : requerimientosDescargados){
			if(requerimientos.getListaRequerimientosDescargados()!=null){
					for(RequerimientoDescargadoModel requerimiento : requerimientos.getListaRequerimientosDescargados()){
						if(numeroOficio.equals(requerimiento.getNoOficio())){
							return requerimiento;
						}
					}
			}
		}
		return null;
	}
	
	/**
	 * Envía mail de notificación de carga automática
	 * 
	 * @param resumenNotificar
	 */
	private void enviarMailCargaAutomatica(List<NotificacionCargaModel> resumenNotificar){
		LOGGER.debug("Comienza enviarMailCargaAutomatica");
		
		try {
			LOGGER.debug(" mailService -> "+mailService);
			LOGGER.debug("velocityEmailMessageProvider -> "+velocityEmailMessageProvider);
			
			DatosEmail datosEmail = new DatosEmail("", Constantes.CLAVE_GRUPO_CORREO_PROCESOAUTOMATICO);
			String templateVelocity = "mail/cargaAutomatica.vm";
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("datosEmail", datosEmail);
			model.put("operacion", "Carga automática");
			model.put("numeroCarga", resumenNotificar.get(0).getIdCarga());
			model.put("listaEmpresas", resumenNotificar);
			
			velocityEmailMessageProvider.fillblanks(templateVelocity, datosEmail, model);
			velocityEmailMessageProvider.changeSubject("Carga automática");
			
			File resumenCargaAutomatica = generarArchivoResumen(resumenNotificar, 
				LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_TEMPORAL_CARGA_AUTOMATICA)
				+File.separator+
				LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_NOMBRE_PLANTILLA_RESUMEN_CARGA_AUTOMATICA),
				
				LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_TEMPORAL_CARGA_AUTOMATICA)
				+File.separator+
				LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_NOMBRE_RESUMEN_CARGA_AUTOMATICA)
						);
			
			AttachmentMail attachmentMail = new AttachmentMail();
			
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
				simpleDateFormat.applyPattern(Constantes.FORMATO_FECHA_RESUMEN_CARGAAUTOMATICA);
				Date date = new Date();
				
			attachmentMail.setName(resumenCargaAutomatica.getName()+"-"+simpleDateFormat.format(date)+Constantes.EXTENSION_XLS.toLowerCase());
			attachmentMail.setAttachmentBytes(FileUtil.readAsByteArray(resumenCargaAutomatica));
			
			mailService.send(velocityEmailMessageProvider, Constantes.CLAVE_GRUPO_CORREO_PROCESOAUTOMATICO, attachmentMail);
			
			//Eliminar archivo que se adjuntó de la carpeta temporal
			if(resumenCargaAutomatica.delete()){
				LOGGER.debug("El archivo XLS temporal ha sido eliminado despues de enviar mail...");
			}else{
				LOGGER.warn("El archivo XLS temporal no se pudo eliminar...");
			}
			
		} catch (Exception e) {
			final String msg = "Excepción en el envío de emails con recursos incrustados.";
			LOGGER.warn(msg, e);
		}
	}
	
	/**
	 * Genera el archivo de resumen que se enviará adjunto en el correo electrónico de notificación
	 * 
	 * @param notificaciones
	 * @param plantilla
	 * @param destinoXLS
	 * @return File
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public File generarArchivoResumen(List<NotificacionCargaModel> notificaciones, String plantilla, String destinoXLS){
		Map mapa = new HashMap();
		mapa.put("notificaciones", notificaciones);
		XLSTransformer transformer = new XLSTransformer();

		try {
			transformer.transformXLS(plantilla, mapa, destinoXLS);
		} catch (ParsePropertyException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new File(destinoXLS);
	}
}