package com.gfi.bin.admctasweb.operativos.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.catalogos.dao.PersonaDao;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.jasper.ConfigParams;
import com.gfi.bin.admctasweb.jasper.FormatoReporte;
import com.gfi.bin.admctasweb.jasper.service.JasperService;
import com.gfi.bin.admctasweb.mail.model.AttachmentMail;
import com.gfi.bin.admctasweb.mail.model.DatosEmail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.operativos.dao.CnbvDictamenDao;
import com.gfi.bin.admctasweb.operativos.mapper.CnbvDictamenModel;
import com.gfi.bin.admctasweb.operativos.service.CnbvDictamenService;
import com.gfi.bin.admctasweb.reportes.model.BitacoraSeguimientoModel;
import com.gfi.bin.admctasweb.reportes.service.BitacoraSeguimientoService;
import com.gfi.bin.admctasweb.util.AdmctasUtil;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.DateUtil;
import com.gfi.bin.admctasweb.util.Response;
import com.gfi.bin.admctasweb.util.Util;

/**
 * Implementacion de los servicios encargados de gestionar operaciones relacionadas al
 * "Registro de dictamen Juridico" interfaz CnbvDictamenService
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Service(value = "cnbvDictamenService")
public class CnbvDictamenServiceImpl implements CnbvDictamenService {
	private final Logger log = LoggerFactory.getLogger(CnbvDictamenServiceImpl.class);
	
	@Autowired
	private CnbvDictamenDao cnbvDictamenDao;
	@Autowired
	private PersonaDao personaDao;
	
	@Autowired
	private MailService mailService;
	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	private static final String CVE_EMAIL = Constantes.DICTAMEN_JURIDICO_COMP;
	private static final AttachmentMail[] NO_ATTACHMENTS = null;
	
	private static final String NOMBRE_PLANTILLA_JASPER = "dictamenJuridico";
	
	@Autowired
	private ConfigParams configParams;
	@Autowired
	BasicDataSource corpDS;
	@Autowired
	JasperService jasperService;
	
	@Autowired
	private BitacoraSeguimientoService bitacoraSeguimientoService;
	
	/**
	 * Busca todos los dictamenes juridicos existentes de la tabla CNBV_DICTAMEN
	 * @return
	 * @throws ServiceException 
	 */
	public Response getAllCnbvDictamen() throws ServiceException {
		Response resp = new Response(true);
		List<CnbvDictamenModel> result =  new ArrayList<CnbvDictamenModel>();		
		result = this.cnbvDictamenDao.getAllCnbvDictamen();
		resp.setData(result);
		return resp;
	}
	
	/**
	 * Metodo que obtiene el id_persona para le dictamen
	 * @param cnbvDictamen
	 * @return
	 */
	public Long obtenerIdPersona(CnbvDictamenModel cnbvDictamen){
		Integer idPersona = null;
		try{
			idPersona = personaDao.obtenerIDPersona(cnbvDictamen.getNumOficio(), cnbvDictamen.getTipoOficio(), cnbvDictamen.getNumConsec());
		} catch (Exception e) {
			log.error("Ocurrio un error al obtener el id de la persona " + e.getLocalizedMessage());			
			
		}
		if(idPersona==null){
			return null;
		}
		return idPersona.longValue();
	}

	/**
	 * Guarda en la tabla CNBV_DICTAMEN, llave primaria numOficio,tipoOficio,numConsec
	 * @param cnbvDictamen
	 * @return response
	 * @throws ServiceException
	 */
	public Response guardarCnbvDictamen(CnbvDictamenModel cnbvDictamen) throws ServiceException {
		Response resp = new Response(true);
		if(existeCnbvDictamen(cnbvDictamen.getNumOficio(), cnbvDictamen.getTipoOficio(), cnbvDictamen.getNumConsec())){
			throw new ServiceException("Ya existe un registro del oficio "+cnbvDictamen.getNumOficio() );
		}		
    	cnbvDictamen.setIdPersona(obtenerIdPersona(cnbvDictamen));
    	cnbvDictamen.setImpresoPorContraloria(Constantes.IMPRESO_POR_CONTRALORIA_FALSO);
    	cnbvDictamenDao.guardarCnbvDictamen(cnbvDictamen);		
    	
    	try{
    		cnbvDictamen.setRespFaltantes(obtenerRespuestasFaltantesDeRegistro(cnbvDictamen.getNumOficio()));
		}catch(Exception e){
			log.error("Ocurrio un error al obtener el numero de respuestas faltantes de registro"+e.getMessage());
			e.printStackTrace();
		}
    	
		resp.setData(cnbvDictamen);
		
		resp.setMsg("El registro se guardo correctamente ");		
		
		byte[] reporte = null;		
		reporte = generarReporte(cnbvDictamen,  NOMBRE_PLANTILLA_JASPER );
		if(reporte==null){
			resp.setMsg("El registro se guardo correctamente, pero ocurrio un error al generar el reporte. ");
		}
		
		log.debug(">"+reporte);				
		log.debug("Genero el archivo pdf satisfactoriamente");			
		
		
		try{			
			AttachmentMail[] fileAttachment = NO_ATTACHMENTS;
			if(reporte!=null){
				fileAttachment =  new AttachmentMail[1];
				fileAttachment[0] = new AttachmentMail( ("ReporteJuridico-"+cnbvDictamen.getNumOficio()),reporte );
			}						
			enviarEmailSeguimiento(cnbvDictamen, Constantes.OPERACION_INSERTAR,fileAttachment);
		}catch(Exception e){
			resp.setMsg("El registro fue exitoso, no se pudo enviar el email de notificación");
			e.printStackTrace();
		}		
		
		try{
			//inserta en la bitacora de seguimiento
			if(oficiosRegistrados(cnbvDictamen.getNumOficio())){
				insertarBitacoraSeguimiento(cnbvDictamen.getNumOficio(), cnbvDictamen.getTipoOficio());
				log.info(" *-*-* INSERTO EN BITACORA DE SEGUIMIENTO -*-*-* ");
			}
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException("Registro exitoso, no se pudo actualizar la bitacora de seguimiento "+cnbvDictamen.getNumOficio() );
		}
		
		return resp;
	}
	/**
	 * Actualiza en la tabla CNBV_DICTAMEN, llave primaria numOficio,tipoOficio,numConsec
	 * @param cnbvDictamen
	 * @return
	 * @throws ServiceException
	 */
	public Response actualizarCnbvDictamen(CnbvDictamenModel cnbvDictamen) throws ServiceException {
		Response resp = new Response(true);			
			String impresoContraloria  = cnbvDictamenDao.buscarFlagContraloriaCnbvDictamen(cnbvDictamen);
			if(impresoContraloria!=null && impresoContraloria.equals(Constantes.FLAG_IMPRESO_CONTRALORIA)){
				throw new ServiceException("El registro ya fue impreso por contraloria, no es posible editarlo");
			} 
			// Se agrega la bandera de Impreso por contraloria ya que no la trae de la Forma.
			cnbvDictamen.setImpresoPorContraloria(Constantes.IMPRESO_POR_CONTRALORIA_FALSO);
			
			//Se actualiza Dictamen Jurídico.
			cnbvDictamenDao.actualizarCnbvDictamen(cnbvDictamen);
			log.debug("Actualizo el dictamen satisfactoriamente");
			resp.setData(cnbvDictamen);	
			resp.setSuccess(true);
			resp.setMsg("El registro se actualizo correctamente ");
			
			byte[] reporte = null;		
			reporte = generarReporte(cnbvDictamen,  NOMBRE_PLANTILLA_JASPER );
			if(reporte==null){
				resp.setMsg("El registro se guardo correctamente, pero ocurrio un error al generar el reporte. ");
			}
			
			log.debug(">"+reporte);				
			log.debug("Genero el archivo pdf satisfactoriamente");			
			
			try{			
				AttachmentMail[] fileAttachment = NO_ATTACHMENTS;
				
					fileAttachment =  new AttachmentMail[1];
					fileAttachment[0] = new AttachmentMail( ("ReporteJuridico-"+cnbvDictamen.getNumOficio()),reporte );
				
							
				enviarEmailSeguimiento(cnbvDictamen, Constantes.OPERACION_ACTUALIZAR,fileAttachment);
			}catch(Exception e){
				resp.setMsg("La actualizacion fue exitosa, no se pudo enviar el email de notificación");
				e.printStackTrace();
			}		
		return resp;
	}

	/**
	 * Consulta si existe un dictamen juridico de acuerdo a su PK.
	 * @param numOficio
	 * @param tipoOficio
	 * @return Boolean
	 * @throws ServiceException 
	 */
	public boolean existeCnbvDictamen(String numOficio, String tipoOficio,
			Long numConsec) throws ServiceException {
		Boolean existe = false;		
		try {
			if ( this.cnbvDictamenDao.existeCnbvDictamen(numOficio, tipoOficio, numConsec) > 0 ) {
				existe = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}		
		return existe;
	}
	
	/**
	 * Obtiene el siguiente consecutivo del dictamen juridico.
	 * @param numOficio
	 * @param tipoOficio
	 * @return int
	 * @throws ServiceException 
	 */
	public int obtenerConsecutivo(String numOficio, String tipoOficio) throws ServiceException {
		try {
			return this.cnbvDictamenDao.obtenerConsecutivo(numOficio, tipoOficio);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
	
	/**
	 * Consulta si existe un dictamen juridico de acuerdo a su PK.Incluye los datos de la cabezera del oficio
	 * @param numOficio
	 * @param tipoOficio
	 * @return Boolean
	 * @throws ServiceException 
	 */
	public Response buscarDatosOficioAndRegistro(CnbvDictamenModel vo) throws ServiceException {
		Response resp = new Response(true);
		CnbvDictamenModel dictamen = new CnbvDictamenModel();
		
		try {
			//Consultamos información del Dictamen
			dictamen = cnbvDictamenDao.buscarDatosOficioAndRegistro(vo);

			//Seteamos ictamen en Objeto de Respuesta
			resp.setData(dictamen);
		} catch (Exception e) {
			resp.setSuccess(false);
			e.printStackTrace();
			throw new ServiceException(e);
		}
		return resp;
	}
	
	/**
	 * Metodo que invoca al servicio de envio de correo, usa la plantilla registroDictamenJuridico
	 * atachment del registro de dictamen juridico pdf
	 * @param dictamen
	 * @param operacion
	 * @param attachments
	 */
	private void enviarEmailSeguimiento(CnbvDictamenModel dictamen,String operacion, AttachmentMail[] attachments){
		try {						
			dictamen.setDescTipoOficio(AdmctasUtil.obtenerDescTipoOficio(dictamen.getTipoOficio()));
			DatosEmail datosEmail = new DatosEmail("", CVE_EMAIL);
			String templateVelocity = "mail/registroDictamenJuridico.vm";
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("datosEmail", datosEmail);
			model.put("operacion", operacion);
			model.put("numeroDeOficio", dictamen.getNumOficio());
			model.put("numeroDeFolio", dictamen.getNumFolio());
			model.put("descTipoOficio", dictamen.getDescTipoOficio());
			model.put("numConsecutivo", dictamen.getNumConsec());
			//model.put("idPersona", dictamen.getIdPersona() == null ? "" : dictamen.getIdPersona());
			model.put("fhAlta", dictamen.getFhAlta() == null ? "" : dictamen.getFhAlta());
			
			velocityEmailMessageProvider.fillblanks(templateVelocity, datosEmail, model);
			velocityEmailMessageProvider.changeSubject(operacion+" "+dictamen.getNumOficio());
		
			mailService.send(velocityEmailMessageProvider,CVE_EMAIL, attachments);
			
		} catch (Exception e) {			
			final String msg = "Excepción en el envío de emails con recursos incrustados.";
			log.error(msg, e);
			e.printStackTrace();
			
		}
	}
	
	/**
	 * Generación del reporte metodo que se ocupa para vizualisar el reporte en pantalla
	 * @param modelo
	 * @param nombrePlantilla
	 * @return
	 */
	@Override
	public byte[] generarReporte(CnbvDictamenModel modelo, String nombrePlantilla){
		byte[] reporte = null;
		try{
		String plantilla =  configParams.getRutaPlantillas() + nombrePlantilla;
		
		CnbvDictamenModel nuevo = modelo;
		cambiaModelo(nuevo);
		evaluaEmpresa(nuevo);
		
		nuevo.setDescTipoOficio(AdmctasUtil.obtenerDescTipoOficio(nuevo.getTipoOficio()));
		nuevo.setRecJurAudi(modelo.getCveUsuAlta());
		nuevo.setFecRecJurAudi( DateUtil.obtenerDatoFechaString(modelo.getFhAlta(), "dd/MM/yyyy" ) );
		nuevo.setHoraRecJurAudi( DateUtil.getHoraFecha(modelo.getFhAlta() ) );
		nuevo.setCveUsuMod(Util.usuarioSesion() );
		nuevo.setRecAudiJur(modelo.getCveUsuImpresion());
		
		if( modelo.getFhImpresion() != null ){
			nuevo.setFecRecAudiJur(DateUtil.obtenerDatoFechaString(modelo.getFhImpresion(), "dd/MM/yyyy" ));
			nuevo.setHoraRecAudiJur( DateUtil.getHoraFecha(modelo.getFhImpresion() ) );
		}
		
		if(nuevo.getMonedaEmbargoParcial()!=null && nuevo.getMonedaEmbargoParcial().intValue() == 0 ){
			nuevo.setMonedaEmbargoParcial(null);
		}
		
		if(nuevo.getMonedaLevParcialEmb()!=null && nuevo.getMonedaLevParcialEmb().intValue() == 0 ){
			nuevo.setMonedaLevParcialEmb(null);
		}
		
		List<CnbvDictamenModel> listaLlenado = new ArrayList<CnbvDictamenModel>();
		listaLlenado.add(nuevo);
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("logo", modelo.getLogo() );
	
		//reporte = service.generarReporteSqlJasper(plantilla, archivo, FormatoReporte.PDF, parametros, corpDS.getConnection());
		reporte = jasperService.generarReporteBean(plantilla, FormatoReporte.PDF, listaLlenado, parametros);
		log.debug("Genero el reporte satisfactoriamente");			
		
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return reporte;
	}
	
	/**
	 *Metodo que se ocupa para cambiar los valores de la tabla CNBV_DICTAMEN para el reporte PDF, cambia el valor 1 = selecccionado 
	 *en el check de la vista por X = a seleccionado en el reporte
	 * @param cnbvDictamenModel
	 * @return
	 */
	private CnbvDictamenModel cambiaModelo(CnbvDictamenModel cnbvDictamenModel){
		try {          
            Field properties[] = cnbvDictamenModel.getClass().getDeclaredFields();
            for (int i = 0; i < properties.length; i++) {
                Field field = properties[i];
                if( field.getType().isAssignableFrom(String.class) ){
                	//System.out.println(field.getName() +" > "+field.getType()+" > " +field.getType().getName());
                	String evaluar = BeanUtils.getProperty(cnbvDictamenModel, field.getName());
                    if( evaluar!=null && evaluar.equals("1") ){
                    	BeanUtils.setProperty(cnbvDictamenModel,  field.getName(), "X");
                    }
                }
                
            }

        } catch (Exception ex) {
        	ex.printStackTrace();
        }
		return cnbvDictamenModel;
	}
	
	/**
	 * Metodo que se ocupa para marcar con una X la empresa seleccionada para el reporte PDF
	 * @param cnbvDictamenModel
	 */
	private void evaluaEmpresa(CnbvDictamenModel cnbvDictamenModel){
		try {          
			if(cnbvDictamenModel.getIdEmpresa() != null){
				if(cnbvDictamenModel.getIdEmpresa().equals(Constantes.ID_EMPRESA_BANCO.longValue())){					
					cnbvDictamenModel.setxBanco("X");
					cnbvDictamenModel.setxCasa("");
					cnbvDictamenModel.setxSociedad("");
					cnbvDictamenModel.setxAseguradora("");
				}else if(cnbvDictamenModel.getIdEmpresa().equals(Constantes.ID_EMPRESA_CASA.longValue())){					
					cnbvDictamenModel.setxBanco("");
					cnbvDictamenModel.setxCasa("X");
					cnbvDictamenModel.setxSociedad("");
					cnbvDictamenModel.setxAseguradora("");
				} if(cnbvDictamenModel.getIdEmpresa().equals(Constantes.ID_EMPRESA_ASEGURADORA.longValue())){					
					cnbvDictamenModel.setxBanco("");
					cnbvDictamenModel.setxCasa("");
					cnbvDictamenModel.setxSociedad("X");
					cnbvDictamenModel.setxAseguradora("");
				}
				
			}
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
	}
	
	/**
	 * Metodo que regresa una bandera que indica si todas las respuestas del oficio fueron registradas
	 * si la bandera es verdadera en el metodo de guardarCnbvDictamen se invoca el servicio de insercion de bitacora
	 * @param numOficio
	 * @return
	 */
	private boolean oficiosRegistrados(String numOficio){
		boolean flag = false;
		int rs = cnbvDictamenDao.obtenerFlagRegistroJur(numOficio);
		if(rs==0){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * Metodo de insercion de bitacora se seguimiento 
	 * @param numOficio
	 * @param tipoOficio
	 * @throws ServiceException
	 */
	private void insertarBitacoraSeguimiento(String numOficio, String tipoOficio) throws ServiceException{
		BitacoraSeguimientoModel seguimiento = new BitacoraSeguimientoModel();
		seguimiento.setNumOficio(numOficio);
		seguimiento.setTipoOficio(tipoOficio);
		seguimiento.setCveEstatus(Constantes.DICTAMEN_JURIDICO_COMP);		
		this.bitacoraSeguimientoService.guardar(seguimiento);
	}

	/**
	 * Obtiene el numero de resspuestas faltantes de registro lo manda al controller para presentacion en la vista
	 */
	@Override
	public int obtenerRespuestasFaltantesDeRegistro(String numOficio)
			throws ServiceException {		
		int numResp =  cnbvDictamenDao.obtenerFlagRegistroJur(numOficio);
		return  numResp;
	}
	
	public void setCnbvDictamenDao(CnbvDictamenDao cnbvDictamenDao) {
		this.cnbvDictamenDao = cnbvDictamenDao;
	}

}
