/**
 * 
 */
package com.gfi.corp.component.genericgridman.dto;

/**
 * Objeto con los parametros de una Columna de exportación de lo Grids.
 * @author LUGL4884
 *
 */
public class ColumnaExport {
	
	private String titulo;
	private String campo;
	private String tipo;
	
	
	/**
	 * @return the titulo
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo the titulo to set
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	/**
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}
	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	} 

}
