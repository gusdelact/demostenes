/**
 * 
 */
package com.gfi.corp.component.genericgridman.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Objeto con los campos para implementar la busqueda General.
 * @author LUGL4884
 *
 */
public class Campos {
	
	private List<Map<String, String>> campos;
	
	/**
	 * @return the campos
	 */
	public List<Map<String, String>> getCampos() {
		return campos;
	}
	/**
	 * @param campos the campos to set
	 */
	public void setCampos(List<Map<String, String>> campos) {
		this.campos = campos;
	}
	
	/**
	 * Metodo que agrega registros a la lista de campos del query.
	 */
	public void addCampo(String nombreCampo, String tipo){
		if(getCampos() == null){
			setCampos(new ArrayList<Map<String,String>>());
		}
		
		Map<String, String> campo= new HashMap<String, String>();
		campo.put("CAMPO", nombreCampo);
		campo.put("TIPO", tipo);
		campos.add(campo);	
	}

}
