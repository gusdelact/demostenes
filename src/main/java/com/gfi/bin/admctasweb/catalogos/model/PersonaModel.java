package com.gfi.bin.admctasweb.catalogos.model;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.gfi.bin.admctasweb.comunes.model.BaseModel;


/**
 * Clase persistente de la tabla CNBV_PERSONA
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PersonaModel extends BaseModel implements Serializable {
		private static final long serialVersionUID = 1L;

		//LLaves primarias
		private String numOficio;
		private String tipoOficio;
		private Long numConsec;

		private Long idPersona;
		private String tipoPersona;
		private String nombre;
		private String apMaterno;
		private String apPaterno;
		private String rfc;
		private String numDictamen;
		private String sitRespuesta;
		private String cuenta;
		
		//JAPL 
		private String caracter;
		
		/**
		 * @return the caracter
		 */
		public String getCaracter() {
			return caracter;
		}
		/**
		 * @param numOficio the caracter to set
		 */
		public void setCaracter(String caracter) {
			this.caracter = caracter;
		}
		
		//JAPL 
		
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
		 * @return the numConsec
		 */
		public Long getNumConsec() {
			return numConsec;
		}
		/**
		 * @param numConsec the numConsec to set
		 */
		public void setNumConsec(Long numConsec) {
			this.numConsec = numConsec;
		}
		/**
		 * @return the idPersona
		 */
		public Long getIdPersona() {
			return idPersona;
		}
		/**
		 * @param idPersona the idPersona to set
		 */
		public void setIdPersona(Long idPersona) {
			this.idPersona = idPersona;
		}
		/**
		 * @return the tipoPersona
		 */
		public String getTipoPersona() {
			return tipoPersona;
		}
		/**
		 * @param tipoPersona the tipoPersona to set
		 */
		public void setTipoPersona(String tipoPersona) {
			this.tipoPersona = tipoPersona;
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
		 * @return the apMaterno
		 */
		public String getApMaterno() {
			return apMaterno;
		}
		/**
		 * @param apMaterno the apMaterno to set
		 */
		public void setApMaterno(String apMaterno) {
			this.apMaterno = apMaterno;
		}
		/**
		 * @return the apPaterno
		 */
		public String getApPaterno() {
			return apPaterno;
		}
		/**
		 * @param apPaterno the apPaterno to set
		 */
		public void setApPaterno(String apPaterno) {
			this.apPaterno = apPaterno;
		}
		/**
		 * @return the rfc
		 */
		public String getRfc() {
			return rfc;
		}
		/**
		 * @param rfc the rfc to set
		 */
		public void setRfc(String rfc) {
			this.rfc = rfc;
		}
		/**
		 * @return the numDictamen
		 */
		public String getNumDictamen() {
			return numDictamen;
		}
		/**
		 * @param numDictamen the numDictamen to set
		 */
		public void setNumDictamen(String numDictamen) {
			this.numDictamen = numDictamen;
		}
		/**
		 * @return the sitRespuesta
		 */
		public String getSitRespuesta() {
			return sitRespuesta;
		}
		/**
		 * @param sitRespuesta the sitRespuesta to set
		 */
		public void setSitRespuesta(String sitRespuesta) {
			this.sitRespuesta = sitRespuesta;
		}
		/**
		 * @return the cuenta
		 */
		public String getCuenta() {
			return cuenta;
		}
		/**
		 * @param cuenta the cuenta to set
		 */
		public void setCuenta(String cuenta) {
			this.cuenta = cuenta;
		}
		
}
