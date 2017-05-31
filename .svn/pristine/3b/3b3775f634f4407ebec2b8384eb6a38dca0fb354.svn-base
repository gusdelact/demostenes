package com.gfi.bin.admctasweb.procesoautomatico.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gfi.bin.admctasweb.catalogos.model.ParametrosModel;
import com.gfi.bin.admctasweb.catalogos.service.ParametrosService;
import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.procesoautomatico.model.BitacoraCargaAutomaticaModel;
import com.gfi.bin.admctasweb.procesoautomatico.service.BitacoraCargaAutomaticaService;
import com.gfi.bin.admctasweb.procesoautomatico.service.GestionArchivosXMLService;
import com.gfi.bin.admctasweb.procesoautomatico.service.GestionArchivosZipService;
import com.gfi.bin.admctasweb.procesoautomatico.service.NotificacionProcesoAutomaticoService;
import com.gfi.bin.admctasweb.procesoautomatico.service.RespaldoCargaAutomatica;
import com.gfi.bin.admctasweb.procesoautomatico.service.ValidacionCargaAutomatica;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.model.ExtjsResponseMsg;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Controller
public class GestionArchivosController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(GestionArchivosController.class);
	
	@Autowired
	private GestionArchivosZipService gestionArchivosService;
	
	@Autowired
	private GestionArchivosXMLService gestionArchivosXMLService;
	
	@Autowired
	private BitacoraCargaAutomaticaService bitCargaAutomaticaService;
	
	@Autowired
	private NotificacionProcesoAutomaticoService notificacionProcesoAutomaticoService;
	
	@Autowired
	private ValidacionCargaAutomatica validacionCargaAutomatica;
	
	@Autowired
	private RespaldoCargaAutomatica respaldoCargaAutomatica;
	
	@Autowired
	private ParametrosService parametrosService;
	
	private static final String GRUPO_PARAMETRO = "CARGAAUT";
	private static final String CLAVE_PARAMETRO = "BEJEC";
	private static final String SIT_PARAMETRO_ACTIVO = "AC";
	private static final String PROCESO_EN_EJECUCION = "V";
	private static final String PROCESO_NO_INICIADO = "F";
	
	
	/**
	 * Se encarga de la comunicación con la capa de vista a la capa de negocios que lleva a cabo las siguientes tareas:
	 *    Validación de precondiciones
	 *    Descompresión archivos ZIP
	 *    Lectura de archivos XML y extracción de información
	 *    Persistencia de bitácora de carga automática
	 *    Respaldo de archivos XML
	 *    Depuración de directorios de archivos comprimidos y descomprimidos
	 * 
	 * @return ExtjsResponseMsg
	 * @throws Exception
	 */
	@RequestMapping(value = "/procesoautomatico/carga/ejecuta.htm", method = RequestMethod.GET)
	public @ResponseBody ExtjsResponseMsg procesarCargaAutomatica() throws Exception{
		
		ParametrosModel parametrosModel = new ParametrosModel();
		parametrosModel.setCveGpoParam(GRUPO_PARAMETRO);
		parametrosModel.setCveParam(CLAVE_PARAMETRO);
				
		parametrosModel = parametrosService.consultarParametroPorClave(parametrosModel);
		
		if(parametrosModel == null)
			throw new Exception("No se pudo recuperar parámetro para ejecución de proceso");
		
		if(parametrosModel.getDescParam().equals(PROCESO_EN_EJECUCION))
			throw new Exception("Actualmente ya se encuentra un proceso en Ejecución");
		
		final String usuario = Util.usuarioSesion();
		
		Thread t = new Thread() {
		    public void run() {
		    	
				ParametrosModel parametrosModel = new ParametrosModel();
				try{
					parametrosModel.setCveGpoParam(GRUPO_PARAMETRO);
					parametrosModel.setCveParam(CLAVE_PARAMETRO);							
					parametrosModel.setCveUsuMod(usuario);
					parametrosModel.setSitParam(SIT_PARAMETRO_ACTIVO);
					parametrosModel.setFhUltMod(new Date());
					parametrosModel.setDescParam(PROCESO_EN_EJECUCION);
					parametrosService.actualizarParametro(parametrosModel);

					LOGGER.debug("Inicia ejecución de proceso automático");
					
					//Se descargan archivos del servidor SFTP
					LOGGER.debug("Se descargan archivos del servidor SFTP");
					gestionArchivosService.descargarArchivos();
					
					if(validacionCargaAutomatica.validarPrecondiciones()){
						
						//Se descomprime
						LOGGER.debug("Se descomprimen archivos ZIP");
						gestionArchivosService.descomprimirArchivos();
						
						//Se realiza eliminación de archivos ZIP del directorio de comprimidos
						LOGGER.debug("Se eliminan archivos");
						gestionArchivosService.eliminarArchivos();
						
						
						
						//Se realiza la lectura, procesamiento y persistencia de datos
						LOGGER.debug("Se leen archivos XML");
						List<BitacoraCargaAutomaticaModel> listaCargaAutomatica = gestionArchivosXMLService.leerArchivosXMLRepositorio();
						
						//Se realiza el procesamiento y persistencia de la bitácora
						LOGGER.debug("Se persiste bitácora de carga automática");
						listaCargaAutomatica = bitCargaAutomaticaService.guardarCargaAutomatica(listaCargaAutomatica);
						
						//Se realiza respaldo y limpieza de directorio de archivos XML
						LOGGER.debug("Se respaldan archivos XML");
						respaldoCargaAutomatica.respaldarArchivosXML();
						
						//Se envía la notificación de carga automática completa
						LOGGER.debug("Se envía correo de notificación a destinatarios configurados");
						notificacionProcesoAutomaticoService.enviarNotificacion(listaCargaAutomatica);
					}
					else{
						LOGGER.warn("No se cumplen las precondiciones para iniciar la carga automatica");
					}
				}catch(ServiceException serviceException){
					LOGGER.error("Error en descomprimirArchivosRepositorio: "+serviceException.getLocalizedMessage());
					serviceException.printStackTrace();
				}
				finally
				{
					parametrosModel.setDescParam(PROCESO_NO_INICIADO);					
					try {
						parametrosService.actualizarParametro(parametrosModel);
					} catch (ServiceException e) {
						e.printStackTrace();
					}
				}
		    }
		};
		t.start();		
		
		ExtjsResponseMsg response = new ExtjsResponseMsg(new String[]{"Se inició el proceso de Carga Automática de manera exitosa!<br/>Se le enviará una notificación a su correo electrónico con el resultado del proceso."});		
		return response;
	}
}