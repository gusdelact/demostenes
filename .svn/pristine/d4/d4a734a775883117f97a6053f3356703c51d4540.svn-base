/**
 * 
 */
package com.gfi.bin.admctasweb.reportes.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.impl.BitacoraDocumentoDAOImpl;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.reportes.dao.BitacoraExcepcionDAO;
import com.gfi.bin.admctasweb.reportes.mapper.BitacoraExcepcionMapper;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel;
import com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionModel;
import com.gfi.bin.admctasweb.util.Constantes;
import com.gfi.bin.admctasweb.util.Util;

/**
 * @author MUDF4038 - Fernando Munive Dorantes
 *
 */
@Repository
public class BitacoraExcepcionDAOImpl implements BitacoraExcepcionDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BitacoraDocumentoDAOImpl.class);

	/* (non-Javadoc)
	 * @see com.gfi.bin.admctasweb.reportes.dao.BitacoraExcepcionDAO#consultarBitacoraExcepcion(com.gfi.bin.admctasweb.reportes.model.BitacoraExcepcionListModel)
	 */
	@Override
	public List<BitacoraExcepcionModel> consultarBitacoraExcepcion(BitacoraExcepcionListModel parametros) throws DAOException {
		
		List<BitacoraExcepcionModel> bitExcepcionList = null;

		StringBuilder query = new StringBuilder();
		query.append("SELECT BEC.ID_CONTRATO, BEC.F_EXCEPCION, BEC.H_EXCEPCION, BEC.CVE_TIP_EXCEP, EXP.DESC_EXCEPCION,");
		query.append(" BEC.CVE_USUARIO, BEC.TX_MOTIVO, BEC.SIT_EXCEPCION,");
		query.append(" PER.NOMBRE TITULAR,");
		query.append(" PER2.NOMBRE USUARIO,");
		query.append(" DECODE(BEC.SIT_EXCEPCION, 'AC', 'Inactivo', 'CA', 'Activo', BEC.SIT_EXCEPCION) AS SITUACION");
		query.append(" FROM BIT_EXCEP_CTE BEC, EXCEPCION EXP, CONTRATO CTO, PERSONA PER, USUARIO USR, PERSONA PER2");
		query.append(" WHERE BEC.CVE_TIP_EXCEP = EXP.CVE_TIP_EXCEP");
		query.append(" AND CTO.ID_CONTRATO = BEC.ID_CONTRATO");

		if (parametros != null) {
			if (parametros.getIdEmpresa() != null) {
				query.append(" AND CTO.ID_EMPRESA = " + parametros.getIdEmpresa());
			}		
			if (parametros.getIdContrato() != null) {			
				query.append(" AND CTO.ID_CONTRATO = " + parametros.getIdContrato());
			}
			if (parametros.getfInicio() != null && parametros.getfFin() != null) {
				query.append(" AND BEC.F_EXCEPCION BETWEEN TO_DATE('" + Util.dateToString(parametros.getfInicio(), Constantes.FORMATO_YYYYMMDD) + "','" + Constantes.FORMATO_YYYYMMDD + "')" + 
	                                                 " AND TO_DATE('" + Util.dateToString(parametros.getfFin(), Constantes.FORMATO_YYYYMMDD) +  "','" + Constantes.FORMATO_YYYYMMDD + "')");
				
			}
			if (parametros.getCveTipoExcepcion() != null && StringUtils.isNotBlank(parametros.getCveTipoExcepcion())) {
				query.append(" AND BEC.CVE_TIP_EXCEP= '" + parametros.getCveTipoExcepcion() + "'");
			}
		}
		query.append(" AND PER.ID_PERSONA = CTO.ID_TITULAR");
		query.append(" AND USR.CVE_USUARIO = BEC.CVE_USUARIO");
		query.append(" AND USR.ID_PERSONA = PER2.ID_PERSONA");
		query.append(" ORDER BY CTO.ID_CONTRATO, BEC.F_EXCEPCION, BEC.H_EXCEPCION");
		
		LOGGER.info(query.toString());

		try {				
			bitExcepcionList = (List<BitacoraExcepcionModel>)
					this.jdbcTemplateCorp.query(query.toString(), new BitacoraExcepcionMapper());
		} catch (DataAccessException e) {
			String cadenaError = "No existen datos en la tabla de Bit\u00e1cora de Excepciones";
			LOGGER.error(cadenaError);
			throw new DAOException(cadenaError, e);
		}
		return bitExcepcionList;
	}

}
