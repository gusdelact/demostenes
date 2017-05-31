/**
 * 
 */
package com.gfi.corp.component.genericgridman.service;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Service;

import com.gfi.corp.component.genericgridman.dto.Campos;
import com.gfi.corp.component.genericgridman.dto.Filter;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;
import com.gfi.corp.component.genericgridman.dto.SortParam;

/**
 * Clase de servicio para los metodos del Grid Generico.
 * @author LUGL4884
 *
 */
@Service("GenericGridService")
public class GenericGridService {
	private final static Logger logger = Logger.getLogger(GenericGridService.class);

	/**
	 * Método que arma el query a ejecutar en base a los parametros del Grid.
	 * @param gridReq - Objeto que contiene los parametros necesarios para armar el query del grid.
	 * @return String 
	 *
	 */
	public String generaQuery(GenericGridReq gridReq, Campos campos) throws Exception {
		
		//Obtenemos los filtros y sorters enviados por el grid.
		List<Filter> filtros = this.obtieneFiltros(gridReq.getFilter());
		List<SortParam> sorters = this.obtieneOrden(gridReq.getSort());
		
		StringBuilder sql = new StringBuilder(gridReq.getSqlBase());
		StringBuilder busquedaGeneral = new StringBuilder();
		StringBuilder strfiltros = new StringBuilder();
		StringBuilder strOrden = new StringBuilder();
		
		long start = ((gridReq.getPage() * gridReq.getLimit()) - gridReq.getLimit() + 1);
		long limit = gridReq.getLimit() * gridReq.getPage();
		
		//Generamos Filtros si vienen parametros.
		if(!filtros.isEmpty()){
			
			Filter filtro = new Filter();
			Iterator<Filter> it = filtros.iterator();
			while(it.hasNext()) {
				filtro = it.next();
				String operador = filtro.getOperator();
				
				//Validamos si el filtro viene del campo de busqueda General.
				if(filtro.getProperty().equals("BUSQUEDA_GRAL")) {
					
					busquedaGeneral.append(" and (");
					
					Iterator<Map<String, String>> itCampos = campos.getCampos().iterator();
					while(itCampos.hasNext()){
						Map<String, String> campo = itCampos.next();
						busquedaGeneral.append("(upper(\"" + campo.get("CAMPO") + "\") like upper('%'||'" + filtro.getValue() + "'||'%')) OR ");
					}
					
					busquedaGeneral.replace(busquedaGeneral.lastIndexOf("OR"),  busquedaGeneral.lastIndexOf("OR") + 2, "");
					busquedaGeneral.append(")");
				}else {
					
					//Validamos el operador.
					if(operador.equals("eq")) {
						operador = "=";
					} else if(operador.equals("gt")) {
						operador = ">";
					} else if(operador.equals("lt")) {
						operador = "<";
					}
					
					//Generamos la cadena de filtros del query.
					if(filtro.getType().toUpperCase().equals("STRING")) {
						strfiltros.append(" AND upper(\"" + filtro.getProperty() + "\")" + " like upper( '%'||'" + filtro.getValue() + "'||'%' ) "); 
					}else if(filtro.getType().toUpperCase().equals("NUMERIC")) {
						strfiltros.append(" AND \"" + filtro.getProperty() + "\"" + operador + filtro.getValue());
					}else if(filtro.getType().toUpperCase().equals("DATE")) {
						strfiltros.append(" AND trunc(\"" + filtro.getProperty() +"\")"+ operador + "to_date('" + filtro.getValue() + "','dd/mm/yyyy')");
					}	
				}
			}
			
			//Agregamos los filtros al query.
			sql.append(strfiltros.toString());
			sql.append(busquedaGeneral.toString());
		}
		
		//Generamos ordenamiento si vienen parametros.
		if(!sorters.isEmpty()) {
			
			SortParam orden = new SortParam();
			Iterator<SortParam> it = sorters.iterator();
			while(it.hasNext()){
				orden = it.next();
				
				if(orden.getType() != null) {
					if(orden.getType().toUpperCase().equals("NUMBER")) {
						strOrden.append("  order by \"" + orden.getProperty() + "\" *1 " + orden.getDirection());
					}else if(orden.getType().toUpperCase().equals("DATE")){
						strOrden.append("  order by " + "to_date(\"" + orden.getProperty() + "\",'dd/mm/yyyy')" + orden.getDirection());  
					}
				}else{
					strOrden.append("  order by \"" + orden.getProperty() + "\" " + orden.getDirection());
				}
			}
			
			//Agregamos el ordenamiento al query.
			sql.append(strOrden.toString());
		}
		
		//Generamos el query con la paginación.
		StringBuilder query = new StringBuilder();
	    query.append(" select * from ( ");
	    query.append(" select rowNum numRecord, count(*) over () recordCount, a.*  From ( ");
	    query.append(sql.toString());
	    query.append(" ) a ");
	    query.append(" ) Where 1 = 1 ");     
	    query.append(" And numRecord between " + start + " and " + limit);
	    query.append(" order by numRecord asc ");
	    
	    logger.info("Query a ejecutar :: " + query.toString());
		
		return query.toString();
	}
	
	
	/**
	 * Obtiene la lista de Filtros del JSON enviado por el grid.
	 */
	private List<Filter> obtieneFiltros(String filter){
		List<Filter> filterList = new ArrayList<Filter>();
		for(Filter f : this.decodeJSONString(filter, new Filter())){
			filterList.add(f);
		}
		
		return filterList;
	}
	
	/**
	 * Obtiene la lista de Sorters del JSON enviado por el grid.
	 */
	private List<SortParam> obtieneOrden(String orden){
		List<SortParam> sortList = new ArrayList<SortParam>();
		for(SortParam f : this.decodeJSONString(orden, new SortParam())){
			sortList.add(f);
		}
		
		return sortList;
	}
		
	/**
	 * Decodifica una cadena JSON
	 */
	@SuppressWarnings("unchecked")
	private <T> T[] decodeJSONString(String JSONString, T tipo){
		T[] arreglo = (T[]) Array.newInstance(tipo.getClass(), 0);
		
		if (JSONString != null) {
			ObjectMapper mapper = new ObjectMapper();
			try {
				arreglo = (T[]) mapper.readValue(JSONString, arreglo.getClass());
			} catch (Exception e) {				
				//e.printStackTrace();
			}
		}
		
		return arreglo;
	}
	
}
