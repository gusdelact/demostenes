package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.RespContratoDao;
import com.gfi.bin.admctasweb.catalogos.dao.util.RespContratoQuery;
import com.gfi.bin.admctasweb.catalogos.mapper.RespContratoMapper;
import com.gfi.bin.admctasweb.catalogos.model.RespContrato;
import com.gfi.corp.component.genericgridman.dto.Campos;
import com.gfi.corp.component.genericgridman.dto.GenericGridReq;
import com.gfi.corp.component.genericgridman.service.GenericGridService;

@Repository(value="respContratoDao")
public class RespContratoDaoImpl implements RespContratoDao{
	private final static Logger logger = Logger.getLogger(RespContratoDaoImpl.class);
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;
	
	@Autowired
	private GenericGridService genericGridService;
	

	public List<RespContrato> consultaContratosRespuesta(GenericGridReq gridReq, String numOficio)
			throws Exception {
		logger.debug("En el método: RespContratoDaoImpl.consultaContratosRespuesta");
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		gridReq.setSqlBase(RespContratoQuery.SELECT_RESP_CONTRATO_ORI);
		
		//Campos para busqueda General.
		Campos campos = new Campos();
		campos.addCampo("NUM_OFICIO", "STRING");
		campos.addCampo("TIPO_OFICIO", "STRING");
		campos.addCampo("ID_CONTRATO", "NUMBER");
		campos.addCampo("FH_ALTA", "DATE");
		campos.addCampo("CVE_USU_ALTA", "STRING");
		campos.addCampo("FH_ULT_MOD", "DATE");
		campos.addCampo("CVE_USU_MOD", "STRING");
		campos.addCampo("MONTO_INICIAL", "NUMBER");
		
		logger.debug("Consulta: " + gridReq.getSqlBase());
		
		parametros.put("NUM_OFICIO", numOficio);
		
		List<RespContrato> respContratos = corpNamedTemplate.query(genericGridService.generaQuery(gridReq, campos), parametros, new RespContratoMapper());
		
		return respContratos;
	}

	public boolean insertarContratoRespuesta(RespContrato respContrato, String cveUsuario) throws Exception {
		logger.debug("En el método: RespContratoDaoImpl.insertarContratoRespuesta");
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		String query = RespContratoQuery.INSERT_RESP_CONTRATO;
		
		//Parametros del query.
		parametros.put("NUM_OFICIO", respContrato.getNumOficio());
		parametros.put("TIPO_OFICIO", respContrato.getTipoOficio());
		parametros.put("ID_CONTRATO", respContrato.getIdContrato());
		parametros.put("MONTO_INICIAL", respContrato.getMontoInicial());
		parametros.put("CVE_USU_ALTA", cveUsuario);
		
		logger.debug("Consulta: " + query);
		
		boolean respuesta = corpNamedTemplate.update(query, parametros) > 0 ? true:false;
		
		return respuesta;
	}

	public boolean actualizarContratoRespuesta(RespContrato respContrato, String cveUsuario) throws Exception {
		logger.debug("En el método: RespContratoDaoImpl.actualizarContratoRespuesta");
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		String query = RespContratoQuery.UPDATE_RESP_CONTRATO;
		
		//Parametros del query.
		parametros.put("NUM_OFICIO", respContrato.getNumOficio());
		parametros.put("TIPO_OFICIO", respContrato.getTipoOficio());
		parametros.put("ID_CONTRATO", respContrato.getIdContrato());
		parametros.put("MONTO_INICIAL", respContrato.getMontoInicial());
		parametros.put("CVE_USU_MOD", cveUsuario);
		
		logger.debug("Consulta: " + query);
		
		boolean respuesta = corpNamedTemplate.update(query, parametros) > 0 ? true:false;
		
		return respuesta;
	}

	public boolean eliminarContratoRespuesta(RespContrato respContrato) throws Exception {
		logger.debug("En el método: RespContratoDaoImpl.eliminarContratoRespuesta");
		Map<String,Object> parametros = new HashMap<String, Object>();
		
		String query = RespContratoQuery.DELETE_RESP_CONTRATO;
		
		//Parametros del query.
		parametros.put("NUM_OFICIO", respContrato.getNumOficio());
		parametros.put("TIPO_OFICIO", respContrato.getTipoOficio());
		parametros.put("ID_CONTRATO", respContrato.getIdContrato());
		
		logger.debug("Consulta: " + query);
		
		boolean respuesta = corpNamedTemplate.update(query, parametros) > 0 ? true:false;
		
		return respuesta;
	}
}