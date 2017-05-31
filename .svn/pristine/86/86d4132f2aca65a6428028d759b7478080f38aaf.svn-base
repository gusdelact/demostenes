package com.gfi.bin.admctasweb.operativos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;
/**
 *Clase persistente para la tabla de la base de datos "CNBV_OFICIO". 
 *Utilizada para la creacion del archivo para la CNBV
 *  @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ArhivoCnbvModel implements Serializable{

	private static final long serialVersionUID = 1L;
	//campos que ocupa el buscador
	
	private Date fhVencimientoIni;
	private Date fhVencimientoFin;
	private Long idEmpresa;
	private String nombre;
	private String sitOficio;
	private String tipoOficio;	
	private String ruta;
	private List<OficioCnbvModel> listaOficiosResult;
	private List<OficioCnbvModel> listaOficiosResultGrid;
	
	
	
	public ArhivoCnbvModel(){
		
	}
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhVencimientoIni() {
		return fhVencimientoIni;
	}
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhVencimientoIni(Date fhVencimientoIni) {
		this.fhVencimientoIni = fhVencimientoIni;
	}
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhVencimientoFin() {
		return fhVencimientoFin;
	}
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhVencimientoFin(Date fhVencimientoFin) {
		this.fhVencimientoFin = fhVencimientoFin;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSitOficio() {
		return sitOficio;
	}

	public void setSitOficio(String sitOficio) {
		this.sitOficio = sitOficio;
	}

	public String getTipoOficio() {
		return tipoOficio;
	}

	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	public List<OficioCnbvModel> getListaOficiosResult() {
		return listaOficiosResult;
	}

	public void setListaOficiosResult(List<OficioCnbvModel> listaOficiosResult) {
		this.listaOficiosResult = listaOficiosResult;
	}
	public List<OficioCnbvModel> getListaOficiosResultGrid() {
		return listaOficiosResultGrid;
	}
	public void setListaOficiosResultGrid(
			List<OficioCnbvModel> listaOficiosResultGrid) {
		this.listaOficiosResultGrid = listaOficiosResultGrid;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
}
