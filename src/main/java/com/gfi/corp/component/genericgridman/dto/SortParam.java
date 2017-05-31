/**
 * 
 */
package com.gfi.corp.component.genericgridman.dto;

/**
 * Clase con los atributos del Objeto de Ordenamiento. 
 * @author LUGL4884
 * 
 */
public class SortParam {
	
	private String property;
	private String direction;
	private String type;
	
	
	/**
	 * @return the property
	 */
	public String getProperty() {
		return property;
	}
	/**
	 * @param property the property to set
	 */
	public void setProperty(String property) {
		this.property = property;
	}
	/**
	 * @return the direction
	 */
	public String getDirection() {
		return direction;
	}
	/**
	 * @param direction the direction to set
	 */
	public void setDirection(String direction) {
		this.direction = direction;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

}
