/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.bin.admctasweb.catalogos.model.DocumentoEliminadoModel;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BitacoraDoctosEliminadosModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String numOficio;
	private String tipoOficio;
	private Date fInicio;
	private Date fFin;
	private List<DocumentoEliminadoModel> documentoEliminadoList;

	/**
	 * @return the numOficio
	 */
	public String getNumOficio() {
		return numOficio;
	}

	/**
	 * @param numOficio the numOficio to set
	 */
	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
	}

	/**
	 * @return the tipoOficio
	 */
	public String getTipoOficio() {
		return tipoOficio;
	}

	/**
	 * @param tipoOficio the tipoOficio to set
	 */
	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	/**
	 * @return the fInicio
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfInicio() {
		return fInicio;
	}

	/**
	 * @param fInicio the fInicio to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfInicio(Date fInicio) {
		this.fInicio = fInicio;
	}

	/**
	 * @return the fFin
	 */
	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getfFin() {
		return fFin;
	}

	/**
	 * @param fFin the fFin to set
	 */
	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setfFin(Date fFin) {
		this.fFin = fFin;
	}

	/**
	 * @return the documentosEliminadosList
	 */
	public List<DocumentoEliminadoModel> getDocumentoEliminadoList() {
		return documentoEliminadoList;
	}

	/**
	 * @param documentosEliminadosList the documentosEliminadosList to set
	 */
	public void setDocumentoEliminadoList(
			List<DocumentoEliminadoModel> documentoEliminadoList) {
		this.documentoEliminadoList = documentoEliminadoList;
	}

	@Override
	public String toString() {
		StringBuilder value = new StringBuilder();
		
		value.append(String.format("%nnumOficio :[%s]%n", this.numOficio));
		value.append(String.format("tipoOficio:[%s]%n", this.tipoOficio));
		value.append(String.format("fInicio   :[%s]%n", Util.dateToString(this.fInicio, Constantes.FORMATO_DDMMYYYY)));
		value.append(String.format("fFin      :[%s]%n", Util.dateToString(this.fFin, Constantes.FORMATO_DDMMYYYY)));
		value.append(String.format("documentosEliminadosList: [size: %d]%n", this.documentoEliminadoList!=null ? this.documentoEliminadoList.size() : 0));
		return value.toString();
	}
	
}
