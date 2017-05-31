package com.gfi.bin.admctasweb.procesoautomatico.model;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author ESS3ESPP
 *
 */
public class BitacoraCargaAutomaticaModel {
	private Integer idCarga;
	private Integer idEmpresa;
	private String excelConciliador;
	private Date fechaHoraRegistro;
	
	/**
	 * Guarda la lista de objetos RequerimientosDescargados que contendrá la información de los archivos que se leen por empresa
	 */
	private List<RequerimientosDescargadosModel> listaRequerimientosDescargados;
	
	private List<BitacoraCargaAutomaticaOficioModel> listaOficios;
	
	public Integer getIdCarga() {
		return idCarga;
	}
	public void setIdCarga(Integer idCarga) {
		this.idCarga = idCarga;
	}
	public Integer getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Integer idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public String getExcelConciliador() {
		return excelConciliador;
	}
	public void setExcelConciliador(String excelConciliador) {
		this.excelConciliador = excelConciliador;
	}
	public Date getFechaHoraRegistro() {
		return fechaHoraRegistro;
	}
	public void setFechaHoraRegistro(Date fechaHoraRegistro) {
		this.fechaHoraRegistro = fechaHoraRegistro;
	}
	public List<RequerimientosDescargadosModel> getListaRequerimientosDescargados() {
		return listaRequerimientosDescargados;
	}
	public void setListaRequerimientosDescargados(
			List<RequerimientosDescargadosModel> listaRequerimientosDescargados) {
		this.listaRequerimientosDescargados = listaRequerimientosDescargados;
	}
	public List<BitacoraCargaAutomaticaOficioModel> getListaOficios() {
		return listaOficios;
	}
	public void setListaOficios(
			List<BitacoraCargaAutomaticaOficioModel> listaOficios) {
		this.listaOficios = listaOficios;
	}
}