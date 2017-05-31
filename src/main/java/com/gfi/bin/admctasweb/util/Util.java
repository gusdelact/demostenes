/**
 * 
 */
package com.gfi.bin.admctasweb.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfi.security.exception.SecurityContextInspectionException;
import com.gfi.security.model.UserDetails;
import com.gfi.security.util.SecurityContextInspector;

/**
 * Clase de Utilería
 * 
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
public class Util {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Util.class);

	/**
	 * Obtiene el usuario de sesi&oacute;n
	 * @return String
	 */
	public static String usuarioSesion() {
		String usuario = "";
		try {
			UserDetails userDetails = (UserDetails) SecurityContextInspector.getPrincipal();
			if (userDetails != null && userDetails.getUsername() != null) {
				usuario = userDetails.getUsername();
				LOGGER.info("USUARIO SESION: " + usuario);
			}
		} catch (SecurityContextInspectionException e) {
			LOGGER.error(e.getMessage());
			e.printStackTrace();
		}
		return usuario;
	}
	
    /**
     * Convierte una Fecha a Cadena con el patr&oacute;n enviado
     * @param Date fecha
     * @param String patron
     * @return String
     */
    public static String dateToString(Date fecha, String patron) {
    	String fechaString = "";
    	if (fecha != null && StringUtils.isNotBlank(patron)) {
	        Locale loc = new Locale("es", "ES");
	        DateFormat df = new SimpleDateFormat(patron, loc);
	        fechaString = df.format(fecha);
    	}
        return fechaString; 
    }

    /**
     * Convierte un String a un Date a partir del patr&oacute;n proporcionado
     * @param String fecha
     * @param String patron
     * @return
     */
    public static Date stringToDate(String fecha, String patron) {
    	Date today = null;
        if (fecha != null && patron != null && StringUtils.isNotBlank(patron)) {
            try {
            	SimpleDateFormat formato = new SimpleDateFormat(patron);
				today = formato.parse(fecha);
				LOGGER.debug("Fecha convertida = " + formato.format(today));
			} catch (ParseException e) {
				LOGGER.error(e.getMessage());
			}
        }
        return today;
    }
    
    /**
     * Devuelve el estatus pendiente correspondiente al estatus actual del Oficio.
     * @param String cveEstatus
     * @return String El estatus Pendiente.
     */
    public static String devuelveEstatusPend(String cveEstatus, String tipoCaso) {
    	String cveEstatusPend = "";
    	
    	if (cveEstatus.equals(Constantes.REGISTRO_OFICIO_INFO_COMP)){
    		cveEstatusPend = Constantes.BUSQUEDA_OFICIO_PEND;
		}else if (cveEstatus.equals(Constantes.REGISTRO_OFICIO_INFO_PEND)){
			cveEstatusPend = Constantes.REGISTRO_OFICIO_INFO_COMP;
		}else if (cveEstatus.equals(Constantes.BUSQUEDA_OFICIO_COMP)){
			if (tipoCaso.equals(Constantes.TIPO_CASO_POSITIVO)){
				cveEstatusPend = Constantes.DICTAMEN_JURIDICO_PEND;
			}else {
				cveEstatusPend = Constantes.GENERACION_ARCHIVO_CNBV_PEND;
			}
		}else if (cveEstatus.equals(Constantes.DICTAMEN_JURIDICO_COMP)){
			cveEstatusPend = Constantes.IMPRESION_DICTAMEN_PEND;
		}else if (cveEstatus.equals(Constantes.IMPRESION_DICTAMEN_COMP)){
			cveEstatusPend = Constantes.IMPRESION_RESPUESTA_PEND;
		}else if (cveEstatus.equals(Constantes.IMPRESION_RESPUESTA_COMP)){
			cveEstatusPend = Constantes.REGISTRO_ACUSE_PEND;
		}else if (cveEstatus.equals(Constantes.GENERACION_ARCHIVO_CNBV_COMP)){
			cveEstatusPend = Constantes.REGISTRO_ACUSE_PEND;
		}else if (cveEstatus.equals(Constantes.REGISTRO_ACUSE_COMP)){
			cveEstatusPend = Constantes.OFICIO_TERMINADO;
		}
    	
    	return cveEstatusPend;
    }
	
	/**
	 * Retorna el contenido de un archivo en arreglo de bytes
	 * 
	 * @param inputStream
	 * @return
	 */
	public static byte[] getByteArrayFile(InputStream inputStream) {

		ByteArrayOutputStream baos = null;
		try {

			byte[] buffer = new byte[1024 * 8];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return baos.toByteArray();
	}
		
    /**
     * Devuelve el numero de mes solicitado en formato MM
     * 
     * @param mes
     * @return String
     */
    public static String obtenerMesDosDigitos(Integer mes){
    	String mesCadena = null;
    	
    	switch(mes){
    	case 1: mesCadena = "01";
    		break;
    	case 2: mesCadena = "02";
    		break;
    	case 3: mesCadena = "03";
    		break;
    	case 4: mesCadena = "04";
    		break;
    	case 5: mesCadena = "05";
    		break;
    	case 6: mesCadena = "06";
    		break;
    	case 7: mesCadena = "07";
    		break;
    	case 8: mesCadena = "08";
    		break;
    	case 9: mesCadena =  "09";
    		break;
    	case 10: mesCadena = "10";
    		break;
    	case 11: mesCadena = "11";
    		break;
    	case 12: mesCadena = "12";
    		break;
    	default: mesCadena = null;
    		break;
    	}
    	
    	return mesCadena;
    }
    
    /**
     * Devuelve el nombre de la Carpeta donde se ubican los archivos en base a la Fecha de Alta.
     * @param fhAlta
     * @return String
     */
    public static String obtenerCarpetaDoc(String fhAlta) {
		String nomCarpeta = "";
		
		String mes = fhAlta.substring(3, 5);
		String anio = fhAlta.substring(6, 10);
		
		nomCarpeta = anio + mes;
		
		return nomCarpeta;
	}
    
    /**
     * Devuelve la descripción del Tipo de oficio en base a su Clave.
     * @param tipoOficio
     * @return String
     */
    public static String obtenerDescTipoOficio(String tipoOficio) {
		String txTipoOficio = "";
		
		if (tipoOficio.equals(Constantes.TIPO_OFICIO_HA)) {
			txTipoOficio = Constantes.TIPO_OFICIO_HACENDARIO;
		}else if (tipoOficio.equals(Constantes.TIPO_OFICIO_JU)) {
			txTipoOficio = Constantes.TIPO_OFICIO_JUDICIAL;
		}else if (tipoOficio.equals(Constantes.TIPO_OFICIO_AS)) {
			txTipoOficio = Constantes.TIPO_OFICIO_ASEGURAMIENTO;
		}else if (tipoOficio.equals(Constantes.TIPO_OFICIO_PLD_PERSISTIR)) {
			txTipoOficio = Constantes.TIPO_OFICIO_PLD;
		}
		
		return txTipoOficio;
	}
    
}