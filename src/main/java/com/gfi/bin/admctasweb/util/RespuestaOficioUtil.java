package com.gfi.bin.admctasweb.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.map.MultiValueMap;
import com.gfi.bin.admctasweb.comunes.enums.RequerimientoEnum;
import com.gfi.bin.admctasweb.comunes.enums.SolicitudEnum;
import com.gfi.bin.admctasweb.comunes.enums.TipoOficioEnum;

/**
 * Clase de Utileria para generar la respuesta de Oficios dependiendo del tipo de requerimiento, solicitud y oficio.
 * El formato de salida de la cadena debe ser html ya que en la vista se utiliza un editor html
 * @author ESS3VAVC
 *
 */
public final class RespuestaOficioUtil {

	private static final String CADENA_NUM_OFICIO = "En atenci&oacute;n a su Oficio No.";
	private static final String CADENA_NUM_FOLIO = "Folio:";
	private static final String CADENA_NUM_REGISTRO = "Registro No.";
	private static final String CADENA_NUM_EXP = "Exp.:";
	private static final String CADENA_ANIO = "A&ntilde;o:";
	private static final String CADENA_FECHA = "de fecha";
	private static final String CADENA_SOLICITUD = "mediante el cual nos solicitan";
	private static final String CADENA_CON_RFC = "con R.F.C.";
	private static final String CADENA_INFORMACION = "Les informamos que hemos revisado nuestros archivos de";
	
	
	private static final String CADENA_DESINMOVILIZACION = "la DESINMOVILIZACI&Oacute;N de los recursos de las cuentas a nombre de:";	
	private static final String CADENA_INMOVILIZACION = "la INMOVILIZACI&Oacute;N de los recursos de las cuentas a nombre de:";
	private static final String CADENA_INMOVILIZACION_PARCIAL = "Hasta por la cantidad de $0.00 ([Indique cantidad con letra])";
	private static final String CADENA_INMOVILIZACION_DOC = "As&iacute; como diversa DOCUMENTACI&Oacute;N";
	private static final String CADENA_INMOVILIZACION_DOC_PARCIAL = "Hasta por la cantidad de $0.00 ([Indique cantidad con letra]), as&iacute; como diversa DOCUMENTACI&Oacute;N";
	private static final String CADENA_INMOVILIZACION_SOLICITUD = "Derivada de la solicitud de";
	private static final String CADENA_INMOVILIZACION_DOC_SOLICITUD = "derivada de la solicitud del";
	
	private static final String CADENA_TRASPASO = "EL TRASPASO DE FONDOS de la(s) cuenta(s) bancaria(s) dada(s) de alta en esta institución a nombre de:";
	
	private static final String CADENA_DOCUMENTACION = "DIVERSA DOCUMENTACI&Oacute;N de la(s) persona(s):";	
	private static final String CADENA_INF = "INFORMACI&Oacute;N de la(s) persona(s):";
	private static final String CADENA_INF_DOC = "INFORMACI&Oacute;N Y DOCUMENTACI&Oacute;N de la(s) persona(s):";
	
	
	private static final String CADENA_DESINMOVILIZACION_INSTRUCCION = "Al respecto, tomamos nota de su instrucci&oacute;n";
	private static final String CADENA_INSTRUCCION = "localizando";
	
	//private static final String EMPTY_STRING = "";
	private static final String BLANK_SPACE = " ";
	private static final String COMMA_STRING = ",";
	private static final String NEW_LINE = "<br>";
	private static final String DOT_STRING = ".";

	
	public static final String VICEPRESIDENCIA = "VICEPRESIDENCIA DE SUPERVISIÓN DE PROCESOS PREVENTIVOS";
	
	private static final String OBSERVACIONES = "[INGRESE AQUI SUS OBSERVACIONES]";
	private static final String DESPEDIDA = "Esperando que la informaci&oacute;n proporcionada sea de su conformidad, quedamos a sus &oacute;rdenes";
	
	/**
	 * Método principal para generar cadena de respuesta de un oficio
	 * @param campos
	 * @return
	 */
	public static String generaCadenaRespuesta(Map<String, Object> campos)
	{
		StringBuffer respuesta = new StringBuffer(CADENA_NUM_OFICIO);
		respuesta.append(BLANK_SPACE);
		respuesta.append(campos.get("numOficio"));
		respuesta.append(BLANK_SPACE);
		respuesta.append(CADENA_NUM_FOLIO);
		respuesta.append(BLANK_SPACE);
		respuesta.append(campos.get("numFolio"));
		respuesta.append(BLANK_SPACE);
		//Oficios AS se pone registro pero no expediente
		if(campos.get("tipoOficio").equals(TipoOficioEnum.ASEGURAMIENTO.getClave())){
			respuesta.append(CADENA_NUM_REGISTRO);
			respuesta.append(campos.get("numRegistro"));
		}
		else{
			respuesta.append(CADENA_NUM_EXP);
			respuesta.append(campos.get("expediente"));
		}
		respuesta.append(BLANK_SPACE);
		respuesta.append(CADENA_ANIO);
		respuesta.append(BLANK_SPACE);
		respuesta.append(campos.get("anio"));
		respuesta.append(BLANK_SPACE);
		respuesta.append(CADENA_FECHA);
		respuesta.append(BLANK_SPACE);
		String fecha = "";
		try {
			fecha = Util.dateToString((Date)campos.get("fecha"), "dd 'de' MMMMM 'de' yyyy");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		respuesta.append(fecha);
		respuesta.append(COMMA_STRING);
		respuesta.append(BLANK_SPACE);

		respuesta.append(CADENA_SOLICITUD);
		respuesta.append(BLANK_SPACE);
		
		respuesta.append(generaCadenaRequerimiento(campos));
		
		respuesta.append(NEW_LINE);
		respuesta.append(NEW_LINE);
		
		respuesta.append(DESPEDIDA);
		respuesta.append(DOT_STRING);
		
		return respuesta.toString();
	}
	
	/**
	 * Método privado para generar parte de la cadena de respuesta
	 * @param campos
	 * @return
	 */
	private static String generaCadenaRequerimiento(Map<String, Object> campos)
	{
		String requerimiento = (String) campos.get("cveRequerimiento");
		String solicitud = (String) campos.get("cveSolicitud");
		MultiValueMap personas = (MultiValueMap) campos.get("personas");
		String autoridad = (String) campos.get("autoridad");
		String tipoOficio = (String) campos.get("tipoOficio");
		String empresa = (String) campos.get("empresa");
		
		//Se reemplazan ya que su clave no corresponde con su descripcion
		if("INFONA".equals(autoridad))
			autoridad = "INFONAVIT";
		else if("TRIBU".equals(autoridad))
			autoridad = "TRIBUNALES";		
		else if("JUZGAD".equals(autoridad))
			autoridad = "JUZGADOS";
		
		
		
		StringBuffer cadena = new StringBuffer("");
		
		if(RequerimientoEnum.DESINMOVILIZACION.getClave().equals(requerimiento)){
			cadena.append(CADENA_DESINMOVILIZACION);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, true));
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(DOT_STRING);
			cadena.append(BLANK_SPACE);			
			cadena.append(CADENA_DESINMOVILIZACION_INSTRUCCION);
			cadena.append(DOT_STRING);
		}
		else if(RequerimientoEnum.INMOVILIZACION.getClave().equals(requerimiento) 
				&& SolicitudEnum.TOTAL.getClave().equals(solicitud)){
			cadena.append(CADENA_INMOVILIZACION);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, true));		
			cadena.append(NEW_LINE);
			
			if(TipoOficioEnum.ASEGURAMIENTO.getClave().equals(tipoOficio))
			{
				cadena.append(CADENA_INMOVILIZACION_SOLICITUD);
				cadena.append(BLANK_SPACE);
				cadena.append(autoridad);
				cadena.append(DOT_STRING);
				cadena.append(NEW_LINE);
				cadena.append(NEW_LINE);
			}
			
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);
			cadena.append(OBSERVACIONES);
			
			
		}
		else if(RequerimientoEnum.INMOVILIZACION.getClave().equals(requerimiento) 
				&& SolicitudEnum.PARCIAL.getClave().equals(solicitud)){
			cadena.append(CADENA_INMOVILIZACION);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, true));		
			cadena.append(NEW_LINE);

			cadena.append(CADENA_INMOVILIZACION_PARCIAL);
			
			if(TipoOficioEnum.ASEGURAMIENTO.getClave().equals(tipoOficio))
			{
				cadena.append(COMMA_STRING);
				cadena.append(BLANK_SPACE);

				cadena.append(CADENA_INMOVILIZACION_DOC_SOLICITUD);
				cadena.append(BLANK_SPACE);
				cadena.append(autoridad);
				cadena.append(DOT_STRING);
			}
			else{
				cadena.append(DOT_STRING);
			}
			
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);				

			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);
			cadena.append(OBSERVACIONES);
			

		}
		else if(RequerimientoEnum.INM_DOC.getClave().equals(requerimiento) 
				&& SolicitudEnum.TOTAL.getClave().equals(solicitud)){
			cadena.append(CADENA_INMOVILIZACION);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, true));		
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INMOVILIZACION_DOC);
			if(TipoOficioEnum.JUDICIAL.getClave().equals(tipoOficio))
			{
				cadena.append(DOT_STRING);
			}
			else
			{
				cadena.append(COMMA_STRING);
				cadena.append(BLANK_SPACE);
				cadena.append(CADENA_INMOVILIZACION_DOC_SOLICITUD);
				cadena.append(autoridad);
				cadena.append(DOT_STRING);				
			}			
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);
			cadena.append(OBSERVACIONES);

		}
		else if(RequerimientoEnum.INM_DOC.getClave().equals(requerimiento) 
				&& SolicitudEnum.PARCIAL.getClave().equals(solicitud)){
			cadena.append(CADENA_INMOVILIZACION);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, true));		
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INMOVILIZACION_DOC_PARCIAL);
			if(TipoOficioEnum.JUDICIAL.getClave().equals(tipoOficio))
			{
				cadena.append(DOT_STRING);
			}
			else
			{
				cadena.append(COMMA_STRING);
				cadena.append(BLANK_SPACE);
				cadena.append(CADENA_INMOVILIZACION_DOC_SOLICITUD);
				cadena.append(autoridad);
				cadena.append(DOT_STRING);
			}
			
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);			
			cadena.append(OBSERVACIONES);

		}
		else if(RequerimientoEnum.TRASPASO_FONDOS.getClave().equals(requerimiento) ){
			cadena.append(CADENA_TRASPASO);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, true));		
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);						
			cadena.append(OBSERVACIONES);

		}
		else if(RequerimientoEnum.INFORMACION.getClave().equals(requerimiento) ){
			cadena.append(CADENA_INF);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);			
			cadena.append(obtenerListaPersonas(personas, !TipoOficioEnum.PLD.getClave().equals(tipoOficio)));		
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);									
			cadena.append(OBSERVACIONES);

		}
		else if(RequerimientoEnum.DOCUMENTACION.getClave().equals(requerimiento) ){
			cadena.append(CADENA_DOCUMENTACION);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			cadena.append(obtenerListaPersonas(personas, !TipoOficioEnum.PLD.getClave().equals(tipoOficio)));			
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);												
			cadena.append(OBSERVACIONES);
			
		}
		else if(RequerimientoEnum.INF_DOC.getClave().equals(requerimiento) ){
			cadena.append(CADENA_INF_DOC);
			cadena.append(NEW_LINE);
			cadena.append(NEW_LINE);
			
			cadena.append(obtenerListaPersonas(personas, !TipoOficioEnum.PLD.getClave().equals(tipoOficio)));
			
			cadena.append(NEW_LINE);
			cadena.append(CADENA_INFORMACION);
			cadena.append(BLANK_SPACE);
			cadena.append(empresa);
			cadena.append(COMMA_STRING);
			cadena.append(BLANK_SPACE);
			cadena.append(CADENA_INSTRUCCION);
			cadena.append(BLANK_SPACE);															
			cadena.append(OBSERVACIONES);			
		}
						
		return cadena.toString();
	}
	
	/**
	 * Genera la lista de personas a mostrar en la respuesta
	 * @param listaPersonas
	 * @param incluirRFC Indica si se debe incluir el RFC junto a la persona
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private static String obtenerListaPersonas(MultiValueMap listaPersonas, boolean incluirRFC)
	{
		StringBuffer personas = new StringBuffer();
		List list;
		Set entrySet = listaPersonas.entrySet();		
		Iterator it = entrySet.iterator();		
		
		while (it.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) it.next();
			list = (List) listaPersonas.get(mapEntry.getKey());
			for (int j = 0; j < list.size(); j++) {	
				if(incluirRFC)
					personas.append(list.get(j) + COMMA_STRING + BLANK_SPACE +  CADENA_CON_RFC + BLANK_SPACE + mapEntry.getKey());
				else
					personas.append(list.get(j));
				personas.append(NEW_LINE);
			}	
		}			
		
		return personas.toString();
	}
	
}
