package com.gfi.bin.admctasweb.procesoautomatico.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gfi.bin.admctasweb.comunes.exception.ServiceException;
import com.gfi.bin.admctasweb.mail.model.DatosEmail;
import com.gfi.bin.admctasweb.mail.service.EmailMessageProvider;
import com.gfi.bin.admctasweb.mail.service.MailService;
import com.gfi.bin.admctasweb.procesoautomatico.service.ValidacionCargaAutomatica;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.LectorPropertiesUtil;

/**
 * 
 * @author ESS3ESPP
 *
 */
@Service
public class ValidacionCargaAutomaticaImpl implements ValidacionCargaAutomatica {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ValidacionCargaAutomaticaImpl.class);
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private EmailMessageProvider velocityEmailMessageProvider;
	
	private Boolean existenArchivosNoZip;
	
	private Boolean existenArchivosComprimidos;
	
	/**
	 * Valida que existan las condiciones necesarias para ejecutar sin problemas la carga automática, 
	 * se valida que exista la plantilla para generar el resumen (archivo xls), si no existe, se envía
	 * mensaje de correo a un grupo de usuarios para informar del problema
	 * 
	 * @return Boolean
	 * @throws ServiceException
	 */
	@Override
	public Boolean validarPrecondiciones() throws ServiceException {
		LOGGER.debug("Inicia validarEjecucionCorrecta");
		Boolean resultado = false;
		
		String rutaPlantilla = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_TEMPORAL_CARGA_AUTOMATICA);
		String nombrePlantilla = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_NOMBRE_PLANTILLA_RESUMEN_CARGA_AUTOMATICA);
		String rutaComprimidos = LectorPropertiesUtil.obtenerValoresEtiqueta(Constantes.ARCHIVO_CARGAAUTOMATICA_PROPIEDADES, Constantes.KEY_RUTA_COMPRIMIDOS);
		
		existenArchivosNoZip = false;
		existenArchivosComprimidos = false;
		
		File plantillaResumen = new File(rutaPlantilla + File.separator + nombrePlantilla);
		
		try{
			existenArchivosNoZip(rutaComprimidos);
			existenArchivosComprimidos(rutaComprimidos);
		}catch(NullPointerException nullPointerException){
//			nullpointerException
			nullPointerException.printStackTrace();
			throw new ServiceException(nullPointerException);
//			return resultado;
		}
		
		if(plantillaResumen.exists() && existenArchivosNoZip==false && existenArchivosComprimidos){
			resultado = true;
			LOGGER.debug("Precondiciones correctas");
		}
		else{
			LOGGER.debug("Se enviará notificacion, error en precondiciones");
			notificarFaltaPlantillaResumen(rutaPlantilla, nombrePlantilla, rutaComprimidos);
		}
		return resultado;
	}
	
	/**
	 * Ejecuta el método para enviar la notificación de error al grupo de usuarios
	 * 
	 * @param rutaPlantilla
	 * @param nombrePlantilla
	 * @param rutaComprimidos
	 */
	private void notificarFaltaPlantillaResumen(String rutaPlantilla, String nombrePlantilla, String rutaComprimidos){
		LOGGER.debug("Inicia notificarFaltaPlantillaResumen");
		
		enviarMail(rutaPlantilla, nombrePlantilla, rutaComprimidos);
	}
	
	/**
	 * Envía el correo de notificación de error al grupo de usuarios configurados para ésto
	 * 
	 * @param rutaPlantillaExcel
	 * @param nombrePlantillaExcel
	 * @param rutaComprimidos
	 */
	private void enviarMail(String rutaPlantillaExcel, String nombrePlantillaExcel, String rutaComprimidos){
		try {
			LOGGER.debug("mailService -> "+mailService);
			LOGGER.debug("velocityEmailMessageProvider -> "+velocityEmailMessageProvider);
			
			DatosEmail datosEmail = new DatosEmail("", Constantes.CLAVE_GRUPO_CORREO_PROCESOAUTOMATICO);
			String templateVelocity = "mail/cargaAutomaticaErrorPlantillaResumen.vm";
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("datosEmail", datosEmail);
			model.put("operacion", "Plantilla Excel Carga automática");
			model.put("rutaArchivo", rutaPlantillaExcel);
			model.put("nombreArchivo", nombrePlantillaExcel);
			model.put("rutaComprimidos", rutaComprimidos);
			
			velocityEmailMessageProvider.fillblanks(templateVelocity, datosEmail, model);
			velocityEmailMessageProvider.changeSubject("Error plantilla de carga automática");
			
			mailService.send(velocityEmailMessageProvider, Constantes.CLAVE_GRUPO_CORREO_PROCESOAUTOMATICO);
			
		} catch (Exception e) {
			final String msg = "Excepción en el envío de emails con recursos incrustados.";
			LOGGER.warn(msg, e);
		}
	}
	
	/**
	 * Realiza la validación de que sólo existan archivos con extensión ZIP en el directorio de Comprimidos
	 * 
	 * @param rutaComprimidos
	 */
	private void existenArchivosNoZip(String rutaComprimidos){
		LOGGER.debug("===> Existen No Zip/XLS en ruta?: "+rutaComprimidos);
		
		File folderComprimidos = new File(rutaComprimidos);
		File[] listaArchivosDirectorio = folderComprimidos.listFiles();
		LOGGER.debug("folderComprimidos.listFiles(): "+folderComprimidos.listFiles());
		LOGGER.debug("listaArchivosDirectorio: "+listaArchivosDirectorio);
		
		for(int i=0; i<listaArchivosDirectorio.length; i++){
			File archivoActual = listaArchivosDirectorio[i];

			if(archivoActual.isFile() 
					&&(!archivoActual.getName().toUpperCase().endsWith(Constantes.EXTENSION_ZIP) 
						&& !archivoActual.getName().toUpperCase().endsWith(Constantes.EXTENSION_XLS)
						&& !archivoActual.getName().toUpperCase().endsWith(Constantes.EXTENSION_DB) ) ){
				 existenArchivosNoZip = true;
				 LOGGER.debug("Hay archivo no Zip o no XLS!!!");
			}
			else if(archivoActual.isDirectory()){
				existenArchivosNoZip(rutaComprimidos + File.separator + archivoActual.getName());
			}
		}
	}
	
	/**
	 * Realiza la validación de que exista al menos un archivo comprimido con extensión ZIP
	 * 
	 * @param rutaComprimidos
	 */
	private void existenArchivosComprimidos(String rutaComprimidos){
		LOGGER.debug("===> Existen archivos comprimidos en ruta?: "+rutaComprimidos);
		
		File folderComprimidos = new File(rutaComprimidos);
		File[] listaArchivosDirectorio = folderComprimidos.listFiles();
		LOGGER.debug("folderComprimidos.listFiles(): "+folderComprimidos.listFiles());
		LOGGER.debug("listaArchivosDirectorio: "+listaArchivosDirectorio);
		
		for(int i=0; i<listaArchivosDirectorio.length; i++){
			File archivoActual = listaArchivosDirectorio[i];

			LOGGER.debug("ArchivoActual: "+archivoActual.getName());
			if(archivoActual.isFile() 
					&&(archivoActual.getName().toUpperCase().endsWith(Constantes.EXTENSION_ZIP))){
				 existenArchivosComprimidos = true;
				 LOGGER.debug("Existe al menos un archivo comprimido (ZIP)...");
			}
			else if(archivoActual.isDirectory()){
				existenArchivosComprimidos(rutaComprimidos + File.separator + archivoActual.getName());
			}
		}
	}
}