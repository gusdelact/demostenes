package com.gfi.bin.admctasweb.util;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Clase que contiene metodos genericos para las reglas de negocio de admctas
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
public class AdmctasUtil {
	
	public static String obtenerDescTipoOficio(String claveTipo){
		String descripcion = "";
		if(claveTipo.equals(Constantes.TIPO_OFICIO_JU)){
			descripcion = Constantes.TIPO_OFICIO_JUDICIAL;
		}else if(claveTipo.equals(Constantes.TIPO_OFICIO_AS)){
			descripcion = Constantes.TIPO_OFICIO_ASEGURAMIENTO;
		}else if(claveTipo.equals(Constantes.TIPO_OFICIO_HA)){
			descripcion = Constantes.TIPO_OFICIO_HACENDARIO;
		}else if(claveTipo.equals(Constantes.TIPO_OFICIO_OPERACIONES_ILICITAS)){
			descripcion = Constantes.TIPO_OFICIO_OPERACIONES_ILICITAS_COMPLETO;
		}else if(claveTipo.equals(Constantes.TIPO_OFICIO_PLD)){
			descripcion = Constantes.TIPO_OFICIO_PLD;
		}
		return descripcion;
	}	
	
	/**
	 * Obtiene el path del logo que se inserta en los reportes Jasper
	 * @param request
	 * @return
	 */
	public static String obtenerPathImagen(HttpServletRequest request) {
		// Path de la imagen de Logo.
		String absolutePath = "";
		ServletContext servletContext = request.getSession().getServletContext();
		String relativePath = "resources/images/logo_gfinan.jpg";
		absolutePath = servletContext.getRealPath(relativePath);
		return absolutePath;
	}

}
