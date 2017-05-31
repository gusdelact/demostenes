package com.gfi.bin.admctasweb.operativos.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
import com.gfi.bin.admctasweb.operativos.dao.RespuestaOficioDAO;
import com.gfi.bin.admctasweb.operativos.mapper.RequerimientoMapper;
import com.gfi.bin.admctasweb.operativos.mapper.RespuestaOficioMapper;
import com.gfi.bin.admctasweb.operativos.mapper.RespuestasOficioMap;
import com.gfi.bin.admctasweb.operativos.mapper.SolicitudMapper;
import com.gfi.bin.admctasweb.operativos.model.RespuestaOficioModel;

/**
 * Implementación de dao para respuestas de oficios
 * @author ESS3VAVC
 *
 */
@Repository
public class RespuestaOficioDAOImpl implements RespuestaOficioDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	
	/**
	 * Guarda respuesta de un oficio en BD
	 * @param respuestaOficioModel
	 * @return
	 * @throws DAOException
	 */	
	public RespuestaOficioModel guardarRespuestaOficio(RespuestaOficioModel respuestaOficioModel) throws DAOException {

		StringBuffer sql = new StringBuffer();
		
		sql.append("INSERT \n");
		sql.append("INTO CNBV_RESP_OFICIO \n");
		sql.append("  ( \n");
		sql.append("    NUM_OFICIO, \n");
		sql.append("    TIPO_OFICIO, \n");
		sql.append("    ID_RESP, \n");
		sql.append("    TX_OBSERVACION, \n");
		sql.append("    TIPO_REQ, \n");
		sql.append("    TIPO_SOL, \n");		
		sql.append("    RESP_ENVIO, \n");
		sql.append("    ID_CONFIG_DIR, \n");
		sql.append("    APODERADO, \n");
		sql.append("    FH_ALTA, \n");
		sql.append("    CVE_USU_ALTA \n");
		sql.append("  ) \n");
		sql.append("  VALUES \n");
		sql.append("  ( \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ?, \n");
		sql.append("    ? \n");
		sql.append("  )");

		try {
			
			int idRespuesta = obtenerConsecutivo(respuestaOficioModel.getNumOficio(), respuestaOficioModel.getTipoOficio());
			respuestaOficioModel.setIdRespuesta(idRespuesta);
			
			jdbcTemplateCorp.update(sql.toString(), new Object[] {respuestaOficioModel.getNumOficio(),
																	respuestaOficioModel.getTipoOficio(),
																	idRespuesta,
																	respuestaOficioModel.getObservaciones(),
																	respuestaOficioModel.getTipoRequerimiento(),
																	respuestaOficioModel.getTipoSolicitud(),
																	respuestaOficioModel.getRespuestaEnviada(),
																	respuestaOficioModel.getIdDireccion(),
																	respuestaOficioModel.getApoderado(),
																	respuestaOficioModel.getFhAlta(),
																	respuestaOficioModel.getCveUsuAlta()});
			return respuestaOficioModel;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		catch(DAOException e){
			throw e;
		}
	}

	/**
	 * Modifica la respuesta de un Oficio
	 * @param respuestaOficioModel
	 * @return
	 * @throws DAOException
	 */
	public boolean modificarRespuestaOficio(RespuestaOficioModel respuestaOficioModel) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE CNBV_RESP_OFICIO \n");
		sql.append("SET TX_OBSERVACION = ?, \n");
		sql.append("  TIPO_REQ         = ?, \n");
		sql.append("  TIPO_SOL         = ?, \n");
		sql.append("  RESP_ENVIO       = ?, \n");
		sql.append("  ID_CONFIG_DIR    = ?, \n");
		sql.append("  APODERADO    	   = ?, \n");
		sql.append("  FH_ULT_MOD       = ?, \n");
		sql.append("  CVE_USU_MOD      = ? \n");
		sql.append("WHERE NUM_OFICIO   = ? \n");
		sql.append("AND TIPO_OFICIO    = ? \n");
		sql.append("AND ID_RESP    = ?");
		
		try {
			int resultado = jdbcTemplateCorp.update(sql.toString(), new Object[]{respuestaOficioModel.getObservaciones(),
																			respuestaOficioModel.getTipoRequerimiento(),
																			respuestaOficioModel.getTipoSolicitud(),
																			respuestaOficioModel.getRespuestaEnviada(),
																			respuestaOficioModel.getIdDireccion(),
																			respuestaOficioModel.getApoderado(),
																			respuestaOficioModel.getFhUltMod(),
																			respuestaOficioModel.getCveUsuMod(),
																			respuestaOficioModel.getNumOficio(),
																			respuestaOficioModel.getTipoOficio(),
																			respuestaOficioModel.getIdRespuesta()});
			return resultado > 0;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		
	}

	/**
	 * Obtiene la respuesta de Oficio por Id
	 * @param respuestaOficioModel
	 * @return
	 * @throws DAOException
	 */	
	public RespuestaOficioModel obtenerRespuestaOficioPorId(RespuestaOficioModel respuestaOficioModel) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT A.*, \n");
		sql.append("  B.CVE_AUTORIDAD, \n");
		sql.append("  B.TIPO_CASO, \n");
		sql.append("  B.F_OFICIO, \n");
		sql.append("  B.TX_DIRECCION, \n");
		sql.append("  B.TX_GERENCIA \n");
		sql.append("FROM CNBV_RESP_OFICIO A, \n");
		sql.append("  CNBV_OFICIO B \n");
		sql.append("WHERE A.NUM_OFICIO = B.NUM_OFICIO \n");
		sql.append("AND A.TIPO_OFICIO  = B.TIPO_OFICIO \n");
		sql.append("AND A.NUM_OFICIO   = ? \n");
		sql.append("AND A.TIPO_OFICIO  = ? \n");
		sql.append("AND A.ID_RESP   = ?");
		
		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(),
												new Object[]{respuestaOficioModel.getNumOficio(), 
																respuestaOficioModel.getTipoOficio(), 
																respuestaOficioModel.getIdRespuesta()}, 
												new RespuestaOficioMapper());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene catálogo de Requerimientos
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */	
	@Override
	public List<ItemModel> obtenerRequerimientos(String tipoOficio)throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();		
		sql.append("SELECT ID_TIP_REQ, DESC_TIP_REQ FROM CNBV_TIP_REQ WHERE TIPO_OFICIO = ?");		
		
		try {
			return jdbcTemplateCorp.query(sql.toString(), new Object[]{tipoOficio}, new RequerimientoMapper());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		
	}

	/**
	 * Obtiene lista de Solicitudes por tipo de requerimiento y oficio
	 */
	@Override
	public List<ItemModel> obtenerSolicitudes(String tipoOficio, String idRequerimiento) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();	
		
		sql.append("SELECT ID_TIP_SOL, \n");
		sql.append("  DESC_TIP_SOL \n");
		sql.append("FROM CNBV_TIP_SOL \n");
		sql.append("WHERE TIPO_OFICIO = ? \n");
		sql.append("AND ID_TIP_REQ    = ?");
			
		try {
			return jdbcTemplateCorp.query(sql.toString(), new Object[]{tipoOficio, idRequerimiento}, new SolicitudMapper());
		} 
		catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
	}

	/**
	 * Obtiene consecutivo a usar en llave de respuesta de oficio
	 * No se usa secuencia para mantener números consecutivos para diferentes oficios
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */
	private int obtenerConsecutivo(String numOficio, String tipoOficio) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(1) + 1 \n");
		sql.append("FROM CNBV_RESP_OFICIO \n");
		sql.append("WHERE NUM_OFICIO = ? \n");
		sql.append("AND TIPO_OFICIO  = ?");
		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[]{numOficio, tipoOficio},Integer.class);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException("Error al obtener nuevo identificador para guardar respuesta:" + e.getMessage());
		}
	}

	/**
	 * Obtiene el item o registro de un requerimiento
	 * @param tipoOficio
	 * @param idRequerimiento
	 * @return
	 * @throws DAOException
	 */	
	@Override
	public ItemModel obtenerRequerimientoPorId(String tipoOficio, String idRequerimiento) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();		
		sql.append("SELECT ID_TIP_REQ, DESC_TIP_REQ FROM CNBV_TIP_REQ WHERE TIPO_OFICIO = ? AND ID_TIP_REQ = ?");		

		try {
			return jdbcTemplateCorp.queryForObject(sql.toString(), new Object[]{tipoOficio, idRequerimiento}, new RequerimientoMapper());
		} catch (DataAccessException e) {
			throw new DAOException(e);
		}
	}
	
	/**
	 * Obtiene las respuestas para el reporte de casos especiales
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 */
	@Override
	public List<RespuestaOficioModel> buscarRespuestasParaOficio(String numOficio, String tipoOficio){
		StringBuilder query = new StringBuilder();

		query.append("SELECT * FROM CNBV_RESP_OFICIO ");
		query.append("WHERE NUM_OFICIO = ? ");
		query.append("AND  TIPO_OFICIO = ? ");
		
		Map<String, Object> mapaParametros = new HashMap<String, Object>();
		mapaParametros.put("numOficio",  numOficio );
		mapaParametros.put("tipoOficio", tipoOficio );
		return jdbcTemplateCorp.query(query.toString(), new Object[]{numOficio, tipoOficio}, new RespuestasOficioMap());
		
	}
	
	
	
}
