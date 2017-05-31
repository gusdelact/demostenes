package com.gfi.bin.admctasweb.operativos.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.gfi.bin.admctasweb.catalogos.model.OficioModel;
import com.gfi.bin.admctasweb.catalogos.model.PersonaModel;
import com.gfi.bin.admctasweb.catalogos.service.DireccionesSolicitantesService;
import com.gfi.bin.admctasweb.catalogos.service.OficioService;
import com.gfi.bin.admctasweb.catalogos.service.PersonaService;
import com.gfi.bin.admctasweb.comun.dao.ComunDao;
import com.gfi.bin.admctasweb.comunes.enums.SituacionOficioEnum;
import com.gfi.bin.admctasweb.comunes.enums.TipoOficioEnum;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.operativos.dao.RespuestaOficioDAO;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;
import com.gfi.bin.admctasweb.operativos.service.RespuestaOficioService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.RespuestaOficioUtil;
import com.gfi.bin.admctasweb.util.Util;

/**
 * Implementación de servicio para respuestas de Oficio
 * @author ESS3VAVC
 *
 */
@Service
public class RespuestaOficioServiceImpl implements RespuestaOficioService {

	@Autowired
	RespuestaOficioDAO respuestaOficioDAO;
	
	@Autowired
	private OficioService oficioService;
	
	@Autowired
	PersonaService personaService;
	
	@Autowired
	private DireccionesSolicitantesService direccionesSolicitantesService;

	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	@Autowired
	JasperService jasperService;
	
	@Autowired
	private ConfigParams configParams;
	
	@Autowired
	private ComunDao comunDao;

	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	
	@Autowired
	private MailService mailService;
	
	
	
	private static final String NOMBRE_PLANTILLA_OFICIO = "respuestaOficio";

	/**
	 * Servicio para guardar respuesta de un oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	public RespuestaOficioModel guardarRespuestaOficio(RespuestaOficioModel respuestaOficioModel) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return respuestaOficioDAO.guardarRespuestaOficio(respuestaOficioModel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Modifica la respuesta de un Oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	public boolean modificarRespuestaOficio(RespuestaOficioModel respuestaOficioModel) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return respuestaOficioDAO.modificarRespuestaOficio(respuestaOficioModel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Obtiene la respuesta de Oficio por Id
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	public RespuestaOficioModel obtenerRespuestaOficioPorId(RespuestaOficioModel respuestaOficioModel) throws ServiceException {
		// TODO Auto-generated method stub	
		try {
			return respuestaOficioDAO.obtenerRespuestaOficioPorId(respuestaOficioModel);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene los requerimientos por Oficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public List<ItemModel> obtenerRequerimientos(String tipoOficio)throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return respuestaOficioDAO.obtenerRequerimientos(tipoOficio);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Obtiene tipo de Solicitudes de un Requerimiento
	 * @param tipoOficio
	 * @param idRequerimiento
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public List<ItemModel> obtenerSolicitudes(String tipoOficio, String idRequerimiento) throws ServiceException {
		// TODO Auto-generated method stub
		try {
			return respuestaOficioDAO.obtenerSolicitudes(tipoOficio, idRequerimiento);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	/**
	 * Actualiza la situación del oficio a enviado
	 * Se actualiza la tabla de respuesta de Oficio y tabla de Oficio
	 * @return
	 * @throws ServiceException
	 */	
	@Transactional(value = "corpTxManager", readOnly = false, propagation = Propagation.REQUIRES_NEW, rollbackFor = { ServiceException.class })
	public boolean actualizarOficioEnviado(RespuestaOficioModel respuestaOficioModel) throws ServiceException 
	{	
		boolean modificoRespuesta = false;
		boolean modificoOficio = false;
		
		String numOficio = respuestaOficioModel.getNumOficio();
		String tipoOficio = respuestaOficioModel.getTipoOficio();
		
		//Se verifica que se haya completado la impresión de dictamen jurídico
		OficioModel oficioModel = oficioService.buscarOficioPorLlave(numOficio, tipoOficio);		
		String estatus = oficioModel.getCveEstatus();
		if(!Constantes.IMPRESION_DICTAMEN_COMP.equals(estatus))
			throw new ServiceException("No se ha completado la IMPRESION DEL DICTAMEN JURIDICO. Favor de verificar.");		
		
		modificoRespuesta = this.modificarRespuestaOficio(respuestaOficioModel);
		modificoOficio = oficioService.modificarSituacionOficio(numOficio, tipoOficio, 
												SituacionOficioEnum.ENVIADO.getValor());
		
		if(!(modificoRespuesta && modificoOficio))
			throw new ServiceException("Error al modificar estatus del Oficio");
		
		//Se modifica para agregar cambio de estatus
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		seguimiento.setNumOficio(numOficio);
		seguimiento.setTipoOficio(tipoOficio);
		seguimiento.setCveEstatus(Constantes.IMPRESION_RESPUESTA_COMP);
		
		bitacoraSeguimientoService.guardar(seguimiento);
		
		//Se agrega envío de notificación
		enviarNotificacionOficioEnviado(numOficio, tipoOficio);
		
		
		return modificoRespuesta||modificoOficio;
	}

	/**
	 * Genera reporte de respuesta de un oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public byte[] generarReporte(String numOficio, String tipoOficio, Integer idRespuesta) throws ServiceException {
		
		RespuestaOficioModel respuestaOficioModel = new RespuestaOficioModel();
		respuestaOficioModel.setNumOficio(numOficio);
		respuestaOficioModel.setTipoOficio(tipoOficio);
		respuestaOficioModel.setIdRespuesta(idRespuesta);
		//Se obtiene la respuesta del Oficio
		respuestaOficioModel = this.obtenerRespuestaOficioPorId(respuestaOficioModel);
		
		if(respuestaOficioModel == null)
			throw new ServiceException("No se pudo recuperar la respuesta del Oficio");
		
		//Se consulta oficio
		OficioModel oficioModel = oficioService.buscarOficioPorLlave(respuestaOficioModel.getNumOficio(), 
																	respuestaOficioModel.getTipoOficio());
		
		if(oficioModel == null)
			throw new ServiceException("No se pudo recuperar el Oficio");
				
		String vicepresidencia = "";
		if(!tipoOficio.equals(TipoOficioEnum.PLD.getClave()))
			vicepresidencia = RespuestaOficioUtil.VICEPRESIDENCIA;
		
		
		String fechaReporte = Util.dateToString(new Date(), Constantes.FORMATO_MMMMM_DD_YYYY);
		
		//Se generan los parámetros del reporte
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("vicepresidencia", vicepresidencia);
		parametros.put("direccion", oficioModel.getTxDireccion());
		parametros.put("gerencia", oficioModel.getTxGerencia());
		parametros.put("nombre", oficioModel.getTxAtnNom());
		parametros.put("puesto", oficioModel.getTxAtnPue());		
		parametros.put("descripcion", respuestaOficioModel.getObservaciones());		
		parametros.put("usuario", respuestaOficioModel.getCveUsuAlta().replaceAll("\\d",""));
		parametros.put("fecha", StringUtils.capitalize(fechaReporte));
		parametros.put("apoderado", respuestaOficioModel.getApoderado());

		//Se genera el reporte
		return jasperService.generarReporteBean(configParams.getRutaPlantillas() + NOMBRE_PLANTILLA_OFICIO, 
										FormatoReporte.PDF,
										null,
										parametros);
	}

	/**
	 * Obtiene catalogo de Direcciones por tipo de Oficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public List<ItemModel> obtenerDireccionesPorTipoOficio(String tipoOficio) throws ServiceException {
		// TODO Auto-generated method stub
		return direccionesSolicitantesService.obtenerDireccionesPorTipoOficio(tipoOficio);
	}

	/**
	 * Se encarga de generar cadena de respuesta con datos del oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 * @throws ServiceException
	 */	
	@Override
	public String armarCadenaDatosOficio(RespuestaOficioModel respuestaOficioModel) throws ServiceException {
		
		try {
			//Cuando es evento de modificación se verifica con un id válido y no se retorna nada para no afectar lo que se registró
			if(respuestaOficioModel.getIdRespuesta() != null && respuestaOficioModel.getIdRespuesta() > 0)
				return null;
			
			//Se consulta oficio
			OficioModel oficioModel = oficioService.buscarOficioPorLlave(respuestaOficioModel.getNumOficio(), 
																		respuestaOficioModel.getTipoOficio());
			//Se consulta requerimiento
			ItemModel requerimiento = respuestaOficioDAO.obtenerRequerimientoPorId(respuestaOficioModel.getTipoOficio(), 
															respuestaOficioModel.getTipoRequerimiento());

			//Se consultan las personas del Oficio
			List<PersonaModel> personas = personaService.buscarPersonasPorOficio(respuestaOficioModel.getNumOficio(), 
																		respuestaOficioModel.getTipoOficio());
			
			//Se obtiene la empresa del oficio
			ItemModel empresa = comunDao.obtenerEmpresaOficio(respuestaOficioModel.getNumOficio(), 
											respuestaOficioModel.getTipoOficio());		
			
			
			//Se genera mapa para la Utilería
			Map<String, Object> mapa = new HashMap<String, Object>();
			
			mapa.put("numOficio", oficioModel.getNumOficio());
			mapa.put("numFolio", StringUtils.trimToEmpty(oficioModel.getNumFolio()));
			mapa.put("tipoOficio", oficioModel.getTipoOficio());
			mapa.put("numRegistro", StringUtils.trimToEmpty(oficioModel.getNumRegistro()));//
			mapa.put("expediente", StringUtils.trimToEmpty(oficioModel.getNumExped()));
			mapa.put("autoridad", StringUtils.trimToEmpty(oficioModel.getCveAutoridad()));
			mapa.put("empresa", empresa.getDescripcion());
			mapa.put("cveRequerimiento", respuestaOficioModel.getTipoRequerimiento());
			mapa.put("cveSolicitud", respuestaOficioModel.getTipoSolicitud());
			
			String anio = "";
			try {
				anio = Util.dateToString(oficioModel.getFhOficio(), "yyyy") ;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mapa.put("anio", anio);
			mapa.put("fecha", oficioModel.getFhOficio());
			mapa.put("requerimiento", requerimiento.getDescripcion().toUpperCase());
			MultiValueMap mapaPersonas = new MultiValueMap();
			for(PersonaModel persona : personas)
			{
				StringBuffer nombreBuffer = new StringBuffer(persona.getNombre());
				if(persona.getApPaterno()!=null)
					nombreBuffer.append(" " + persona.getApPaterno());
				if(persona.getApMaterno() != null)
					nombreBuffer.append(" " + persona.getApMaterno());
										
				mapaPersonas.put(StringUtils.deleteWhitespace(persona.getRfc()== null?"":persona.getRfc()), 
													StringUtils.trimToEmpty(nombreBuffer.toString()));
			}
			mapa.put("personas", mapaPersonas);
			
			return RespuestaOficioUtil.generaCadenaRespuesta(mapa);
			
			
		} 
		catch(ServiceException e)
		{
			throw e;
		}
		catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}		
	}	
	
	/**
	 * Envía notificación cuando se concluye el cambio de estatus del oficio a enviado
	 * @param numOficio
	 * @param tipoOficio
	 */
	private void enviarNotificacionOficioEnviado(String numOficio, String tipoOficio)
	{
		String templateVelocity = "mail/notificacionOficioEnviado.vm";
		
		String txTipoOficio = "";
		
		txTipoOficio = Util.obtenerDescTipoOficio(tipoOficio);
		
		try {
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("numOficio", numOficio);
			model.put("tipoOficio", txTipoOficio);
			
			velocityEmailMessageProvider.fillblanks(templateVelocity, null, model);
			velocityEmailMessageProvider.changeSubject("Envío de Oficio: " + numOficio);

			mailService.send(velocityEmailMessageProvider, Constantes.IMPRESION_RESPUESTA_COMP, new AttachmentMail[]{});
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		
	}
	
	
	
}
