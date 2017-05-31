package com.gfi.bin.admctasweb.operativos.mapper;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.gfi.corp.component.replicacion.annotation.Catalogo;
import com.gfi.corp.utils.ExtDateDeserializer;
import com.gfi.corp.utils.ExtDateSerializer;

/**
 *Clase persistente para la tabla de la base de datos "CNBV_DICTAMEN". 
 *Crea un registro para el dictamen juridico
 *  @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Catalogo("cnbvDictamenModel")
@JsonIgnoreProperties(ignoreUnknown = true)
public class CnbvDictamenModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	//Llave primaria compuesta
	
	private String numOficio;
	private String tipoOficio;
	private Long numConsec;

	//Campos de la tabla
	private String aplicaCtasMonedaExt;
	private String aplicaCtasMonedaNal;
	private String bloqueoAperturaCta;
	private String cajasSeguridad;
	private String caracterContrato;
	private String comentarios;
	private String copiaCertificada;
	private String copiaSimple;
	private String creditos;
	private String cuentaCheques;
	private String cveUsuAlta;
	private String cveUsuImpresion;
	private String cveUsuMod;
	private String docIdentifCuenta;
	private String embargoParcial;
	private String embargoParcialCta;
	private BigDecimal embargoParcialMonto;
	private BigDecimal embargoParcialPorc;
	private String embargoTotal;
	private String embargoTotalCta;
	private String especificar;
	private String especificar2;
	private String especificar3;
	private String estadosCuenta;
	private Date fhAlta;
	private Date fhImpresion;
	private Date fhUltMod;
	private String fideicomisos;
	private Long idPersona;
	private String impresoPorContraloria;
	private String infoGralCuenta;
	private String intermediacionBursatil;
	private String inversiones;
	private String levantamtoParcialEmb;
	private String levantamtoTotalEmb;
	private Long monedaEmbargoParcial;
	private Long monedaLevParcialEmb;
	private BigDecimal montoEmbargoParcial;
	private BigDecimal montoLevParcialEmb;
	private String operacionDivisas;
	private String operacionesInusuales;
	private String operacionesRelevantes;
	private String otros;
	private String otros2;
	private String otros3;
	private String reportesOperPld;
	private String tarjetaRegFirma;
	private String tipoFideicomisos;
	private String transfMonedaExt;
	private String transfMonedaNal;
	
	//Para la cabezera de oficio
	private Long idEmpresa;
	private String descEmpresa;
	private String nombre;
	private String numFolio;
	private String numExped;
	private String numRegistro;
	private Date fhOficio;
	private Date fhRecepcion;
	private Long numDiasPzo;
	private String nombreTitular;	  
	private String logo;
	private String descTipoOficio;
	private String rfc;
	
	private String xBanco;
	private String xCasa;
	private String xSociedad;
	private String xAseguradora;
	
	//variables para el uso del reporte jasper
	//RECIBIDO POR JURIDICO DE PARTE CONTRALORÍA
	private String recJurAudi;
	private String fecRecJurAudi;
	private String horaRecJurAudi;
	//RECIBIDO POR CONTRALORÍA DE PARTE DE JURIDICO
	private String recAudiJur;
	private String fecRecAudiJur;
	private String horaRecAudiJur;
	//RECIBIDO POR OPERACIONOES DE PARTE DE CONTRALORÍA
	private String recOperAudi;
	private String fecRecOperAudi;
	private String horaRecOperAudi;
	
	//Campo que se utiliza para indicar las respuestas faltantes de registrar  
	private Integer respFaltantes;
	private String descMonedaEmbargoParcial;
	private String descMonedaLevParcialEmb;
	
	public CnbvDictamenModel() {
	}

	public CnbvDictamenModel(String numOficio, String tipoOficio, Long numConsec) {
		super();
		this.numOficio = numOficio;
		this.tipoOficio = tipoOficio;
		this.numConsec = numConsec;
	}

	public String getNumOficio() {
		return numOficio;
	}

	public void setNumOficio(String numOficio) {
		this.numOficio = numOficio;
	}

	public String getTipoOficio() {
		return tipoOficio;
	}

	public void setTipoOficio(String tipoOficio) {
		this.tipoOficio = tipoOficio;
	}

	public Long getNumConsec() {
		return numConsec;
	}

	public void setNumConsec(Long numConsec) {
		this.numConsec = numConsec;
	}

	public String getAplicaCtasMonedaExt() {
		return aplicaCtasMonedaExt;
	}

	public void setAplicaCtasMonedaExt(String aplicaCtasMonedaExt) {
		this.aplicaCtasMonedaExt = aplicaCtasMonedaExt;
	}

	public String getAplicaCtasMonedaNal() {
		return aplicaCtasMonedaNal;
	}

	public void setAplicaCtasMonedaNal(String aplicaCtasMonedaNal) {
		this.aplicaCtasMonedaNal = aplicaCtasMonedaNal;
	}

	public String getBloqueoAperturaCta() {
		return bloqueoAperturaCta;
	}

	public void setBloqueoAperturaCta(String bloqueoAperturaCta) {
		this.bloqueoAperturaCta = bloqueoAperturaCta;
	}

	public String getCajasSeguridad() {
		return cajasSeguridad;
	}

	public void setCajasSeguridad(String cajasSeguridad) {
		this.cajasSeguridad = cajasSeguridad;
	}

	public String getCaracterContrato() {
		return caracterContrato;
	}

	public void setCaracterContrato(String caracterContrato) {
		this.caracterContrato = caracterContrato;
	}

	public String getComentarios() {
		return comentarios;
	}

	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}

	public String getCopiaCertificada() {
		return copiaCertificada;
	}

	public void setCopiaCertificada(String copiaCertificada) {
		this.copiaCertificada = copiaCertificada;
	}

	public String getCopiaSimple() {
		return copiaSimple;
	}

	public void setCopiaSimple(String copiaSimple) {
		this.copiaSimple = copiaSimple;
	}

	public String getCreditos() {
		return creditos;
	}

	public void setCreditos(String creditos) {
		this.creditos = creditos;
	}

	public String getCuentaCheques() {
		return cuentaCheques;
	}

	public void setCuentaCheques(String cuentaCheques) {
		this.cuentaCheques = cuentaCheques;
	}

	public String getCveUsuAlta() {
		return cveUsuAlta;
	}

	public void setCveUsuAlta(String cveUsuAlta) {
		this.cveUsuAlta = cveUsuAlta;
	}

	public String getCveUsuMod() {
		return cveUsuMod;
	}

	public void setCveUsuMod(String cveUsuMod) {
		this.cveUsuMod = cveUsuMod;
	}

	public String getDocIdentifCuenta() {
		return docIdentifCuenta;
	}

	public void setDocIdentifCuenta(String docIdentifCuenta) {
		this.docIdentifCuenta = docIdentifCuenta;
	}

	public String getEmbargoParcial() {
		return embargoParcial;
	}

	public void setEmbargoParcial(String embargoParcial) {
		this.embargoParcial = embargoParcial;
	}

	public String getEmbargoParcialCta() {
		return embargoParcialCta;
	}

	public void setEmbargoParcialCta(String embargoParcialCta) {
		this.embargoParcialCta = embargoParcialCta;
	}

	public BigDecimal getEmbargoParcialMonto() {
		return embargoParcialMonto;
	}

	public void setEmbargoParcialMonto(BigDecimal embargoParcialMonto) {
		this.embargoParcialMonto = embargoParcialMonto;
	}

	public BigDecimal getEmbargoParcialPorc() {
		return embargoParcialPorc;
	}

	public void setEmbargoParcialPorc(BigDecimal embargoParcialPorc) {
		this.embargoParcialPorc = embargoParcialPorc;
	}

	public String getEmbargoTotal() {
		return embargoTotal;
	}

	public void setEmbargoTotal(String embargoTotal) {
		this.embargoTotal = embargoTotal;
	}

	public String getEmbargoTotalCta() {
		return embargoTotalCta;
	}

	public void setEmbargoTotalCta(String embargoTotalCta) {
		this.embargoTotalCta = embargoTotalCta;
	}

	public String getEspecificar() {
		return especificar;
	}

	public void setEspecificar(String especificar) {
		this.especificar = especificar;
	}

	public String getEspecificar2() {
		return especificar2;
	}

	public void setEspecificar2(String especificar2) {
		this.especificar2 = especificar2;
	}

	public String getEspecificar3() {
		return especificar3;
	}

	public void setEspecificar3(String especificar3) {
		this.especificar3 = especificar3;
	}

	public String getEstadosCuenta() {
		return estadosCuenta;
	}

	public void setEstadosCuenta(String estadosCuenta) {
		this.estadosCuenta = estadosCuenta;
	}

	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhAlta() {
		return fhAlta;
	}

	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhAlta(Date fhAlta) {
		this.fhAlta = fhAlta;
	}

	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhUltMod() {
		return fhUltMod;
	}

	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhUltMod(Date fhUltMod) {
		this.fhUltMod = fhUltMod;
	}

	public String getFideicomisos() {
		return fideicomisos;
	}

	public void setFideicomisos(String fideicomisos) {
		this.fideicomisos = fideicomisos;
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}

	public String getImpresoPorContraloria() {
		return impresoPorContraloria;
	}

	public void setImpresoPorContraloria(String impresoPorContraloria) {
		this.impresoPorContraloria = impresoPorContraloria;
	}

	public String getInfoGralCuenta() {
		return infoGralCuenta;
	}

	public void setInfoGralCuenta(String infoGralCuenta) {
		this.infoGralCuenta = infoGralCuenta;
	}

	public String getIntermediacionBursatil() {
		return intermediacionBursatil;
	}

	public void setIntermediacionBursatil(String intermediacionBursatil) {
		this.intermediacionBursatil = intermediacionBursatil;
	}

	public String getInversiones() {
		return inversiones;
	}

	public void setInversiones(String inversiones) {
		this.inversiones = inversiones;
	}

	public String getLevantamtoParcialEmb() {
		return levantamtoParcialEmb;
	}

	public void setLevantamtoParcialEmb(String levantamtoParcialEmb) {
		this.levantamtoParcialEmb = levantamtoParcialEmb;
	}

	public String getLevantamtoTotalEmb() {
		return levantamtoTotalEmb;
	}

	public void setLevantamtoTotalEmb(String levantamtoTotalEmb) {
		this.levantamtoTotalEmb = levantamtoTotalEmb;
	}

	public Long getMonedaEmbargoParcial() {
		return monedaEmbargoParcial;
	}

	public void setMonedaEmbargoParcial(Long monedaEmbargoParcial) {
		this.monedaEmbargoParcial = monedaEmbargoParcial;
	}

	public Long getMonedaLevParcialEmb() {
		return monedaLevParcialEmb;
	}

	public void setMonedaLevParcialEmb(Long monedaLevParcialEmb) {
		this.monedaLevParcialEmb = monedaLevParcialEmb;
	}

	public BigDecimal getMontoEmbargoParcial() {
		return montoEmbargoParcial;
	}

	public void setMontoEmbargoParcial(BigDecimal montoEmbargoParcial) {
		this.montoEmbargoParcial = montoEmbargoParcial;
	}

	public BigDecimal getMontoLevParcialEmb() {
		return montoLevParcialEmb;
	}

	public void setMontoLevParcialEmb(BigDecimal montoLevParcialEmb) {
		this.montoLevParcialEmb = montoLevParcialEmb;
	}

	public String getOperacionDivisas() {
		return operacionDivisas;
	}

	public void setOperacionDivisas(String operacionDivisas) {
		this.operacionDivisas = operacionDivisas;
	}

	public String getOperacionesInusuales() {
		return operacionesInusuales;
	}

	public void setOperacionesInusuales(String operacionesInusuales) {
		this.operacionesInusuales = operacionesInusuales;
	}

	public String getOperacionesRelevantes() {
		return operacionesRelevantes;
	}

	public void setOperacionesRelevantes(String operacionesRelevantes) {
		this.operacionesRelevantes = operacionesRelevantes;
	}

	public String getOtros() {
		return otros;
	}

	public void setOtros(String otros) {
		this.otros = otros;
	}

	public String getOtros2() {
		return otros2;
	}

	public void setOtros2(String otros2) {
		this.otros2 = otros2;
	}

	public String getOtros3() {
		return otros3;
	}

	public void setOtros3(String otros3) {
		this.otros3 = otros3;
	}

	public String getReportesOperPld() {
		return reportesOperPld;
	}

	public void setReportesOperPld(String reportesOperPld) {
		this.reportesOperPld = reportesOperPld;
	}

	public String getTarjetaRegFirma() {
		return tarjetaRegFirma;
	}

	public void setTarjetaRegFirma(String tarjetaRegFirma) {
		this.tarjetaRegFirma = tarjetaRegFirma;
	}

	public String getTipoFideicomisos() {
		return tipoFideicomisos;
	}

	public void setTipoFideicomisos(String tipoFideicomisos) {
		this.tipoFideicomisos = tipoFideicomisos;
	}

	public String getTransfMonedaExt() {
		return transfMonedaExt;
	}

	public void setTransfMonedaExt(String transfMonedaExt) {
		this.transfMonedaExt = transfMonedaExt;
	}

	public String getTransfMonedaNal() {
		return transfMonedaNal;
	}

	public void setTransfMonedaNal(String transfMonedaNal) {
		this.transfMonedaNal = transfMonedaNal;
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

	public String getNumFolio() {
		return numFolio;
	}

	public void setNumFolio(String numFolio) {
		this.numFolio = numFolio;
	}

	public String getNumExped() {
		return numExped;
	}

	public void setNumExped(String numExped) {
		this.numExped = numExped;
	}

	public String getNumRegistro() {
		return numRegistro;
	}

	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhOficio() {
		return fhOficio;
	}

	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhOficio(Date fhOficio) {
		this.fhOficio = fhOficio;
	}

	@JsonSerialize(using = ExtDateSerializer.class)
	public Date getFhRecepcion() {
		return fhRecepcion;
	}

	@JsonDeserialize(using = ExtDateDeserializer.class)
	public void setFhRecepcion(Date fhRecepcion) {
		this.fhRecepcion = fhRecepcion;
	}

	public Long getNumDiasPzo() {
		return numDiasPzo;
	}

	public void setNumDiasPzo(Long numDiasPzo) {
		this.numDiasPzo = numDiasPzo;
	}

	public String getNombreTitular() {
		return nombreTitular;
	}

	public void setNombreTitular(String nombreTitular) {
		this.nombreTitular = nombreTitular;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getDescTipoOficio() {
		return descTipoOficio;
	}

	public void setDescTipoOficio(String descTipoOficio) {
		this.descTipoOficio = descTipoOficio;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getxBanco() {
		return xBanco;
	}

	public void setxBanco(String xBanco) {
		this.xBanco = xBanco;
	}

	public String getxCasa() {
		return xCasa;
	}

	public void setxCasa(String xCasa) {
		this.xCasa = xCasa;
	}

	public String getxSociedad() {
		return xSociedad;
	}

	public void setxSociedad(String xSociedad) {
		this.xSociedad = xSociedad;
	}

	public String getxAseguradora() {
		return xAseguradora;
	}

	public void setxAseguradora(String xAseguradora) {
		this.xAseguradora = xAseguradora;
	}

	public String getDescEmpresa() {
		return descEmpresa;
	}

	public void setDescEmpresa(String descEmpresa) {
		this.descEmpresa = descEmpresa;
	}

	public String getCveUsuImpresion() {
		return cveUsuImpresion;
	}

	public void setCveUsuImpresion(String cveUsuImpresion) {
		this.cveUsuImpresion = cveUsuImpresion;
	}

	public Date getFhImpresion() {
		return fhImpresion;
	}

	public void setFhImpresion(Date fhImpresion) {
		this.fhImpresion = fhImpresion;
	}

	public String getRecJurAudi() {
		return recJurAudi;
	}

	public void setRecJurAudi(String recJurAudi) {
		this.recJurAudi = recJurAudi;
	}

	public String getFecRecJurAudi() {
		return fecRecJurAudi;
	}

	public void setFecRecJurAudi(String fecRecJurAudi) {
		this.fecRecJurAudi = fecRecJurAudi;
	}

	public String getHoraRecJurAudi() {
		return horaRecJurAudi;
	}

	public void setHoraRecJurAudi(String horaRecJurAudi) {
		this.horaRecJurAudi = horaRecJurAudi;
	}

	public String getRecAudiJur() {
		return recAudiJur;
	}

	public void setRecAudiJur(String recAudiJur) {
		this.recAudiJur = recAudiJur;
	}

	public String getFecRecAudiJur() {
		return fecRecAudiJur;
	}

	public void setFecRecAudiJur(String fecRecAudiJur) {
		this.fecRecAudiJur = fecRecAudiJur;
	}

	public String getHoraRecAudiJur() {
		return horaRecAudiJur;
	}

	public void setHoraRecAudiJur(String horaRecAudiJur) {
		this.horaRecAudiJur = horaRecAudiJur;
	}

	public String getRecOperAudi() {
		return recOperAudi;
	}

	public void setRecOperAudi(String recOperAudi) {
		this.recOperAudi = recOperAudi;
	}

	public String getFecRecOperAudi() {
		return fecRecOperAudi;
	}

	public void setFecRecOperAudi(String fecRecOperAudi) {
		this.fecRecOperAudi = fecRecOperAudi;
	}

	public String getHoraRecOperAudi() {
		return horaRecOperAudi;
	}

	public void setHoraRecOperAudi(String horaRecOperAudi) {
		this.horaRecOperAudi = horaRecOperAudi;
	}

	public Integer getRespFaltantes() {
		return respFaltantes;
	}

	public void setRespFaltantes(Integer respFaltantes) {
		this.respFaltantes = respFaltantes;
	}

	public String getDescMonedaEmbargoParcial() {
		return descMonedaEmbargoParcial;
	}

	public void setDescMonedaEmbargoParcial(String descMonedaEmbargoParcial) {
		this.descMonedaEmbargoParcial = descMonedaEmbargoParcial;
	}

	public String getDescMonedaLevParcialEmb() {
		return descMonedaLevParcialEmb;
	}

	public void setDescMonedaLevParcialEmb(String descMonedaLevParcialEmb) {
		this.descMonedaLevParcialEmb = descMonedaLevParcialEmb;
	}

}
