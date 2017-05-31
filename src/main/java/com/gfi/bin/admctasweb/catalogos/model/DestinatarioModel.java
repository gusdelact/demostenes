package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;

/**
 * Clase para almacenar datos de Destinatario
 * @author ESS3VAVC
 *
 */
public class DestinatarioModel extends BaseModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int idDestinatario;
	private String cveEstatus;
	private String nombre;
	private String correo;
	private String situacion;
	
	public  DestinatarioModel() {
	
	}

	/**
	 * @return the idDestinatario
	 */
	public int getIdDestinatario() {
		return idDestinatario;
	}

	/**
	 * @param idDestinatario the idDestinatario to set
	 */
	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	/**
	 * @return the cveEstatus
	 */
	public String getCveEstatus() {
		return cveEstatus;
	}

	/**
	 * @param cveEstatus the cveEstatus to set
	 */
	public void setCveEstatus(String cveEstatus) {
		this.cveEstatus = cveEstatus;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the correo
	 */
	public String getCorreo() {
		return correo;
	}

	/**
	 * @param correo the correo to set
	 */
	public void setCorreo(String correo) {
		this.correo = correo;
	}

	/**
	 * @return the situacion
	 */
	public String getSituacion() {
		return situacion;
	}

	/**
	 * @param situacion the situacion to set
	 */
	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%nidDestinatario:[%s]%n", this.idDestinatario));
		value.append(String.format("cveEstatus    :[%s]%n", this.cveEstatus));
		value.append(String.format("nombre        :[%s]%n", this.nombre));
		value.append(String.format("correo        :[%s]%n", this.correo));
		value.append(String.format("situacion     :[%s]%n", this.situacion));
		value.append(String.format("fhAlta        :[%s]%n", this.getFhAlta()));
		value.append(String.format("cveUsuAlta    :[%s]%n", this.getCveUsuAlta()));
		value.append(String.format("fhUltMod      :[%s]%n", this.getFhUltMod()));
		value.append(String.format("cveUsuMod     :[%s]%n", this.getCveUsuMod()));

		return value.toString();
	}
	
}
