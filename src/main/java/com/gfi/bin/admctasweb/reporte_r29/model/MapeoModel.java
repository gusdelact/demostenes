package com.gfi.bin.admctasweb.reporte_r29.model;

import java.io.Serializable;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * Clase Modelo para el Objeto Oficio.
 * @author LUGL4884
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapeoModel extends BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
	private int idCatalogo;
	private String cveCorporativa;
	private String cveSiti;
	private String descripcion;
	
	public int getIdCatalogo() {
		return idCatalogo;
	}
	public void setIdCatalogo(int idCatalogo) {
		this.idCatalogo = idCatalogo;
	}
	public String getCveCorporativa() {
		return cveCorporativa;
	}
	public void setCveCorporativa(String cveCorporativa) {
		this.cveCorporativa = cveCorporativa;
	}
	public String getCveSiti() {
		return cveSiti;
	}
	public void setCveSiti(String cveSiti) {
		this.cveSiti = cveSiti;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
}
