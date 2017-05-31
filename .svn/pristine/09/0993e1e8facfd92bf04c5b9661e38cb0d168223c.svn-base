package com.gfi.bin.admctasweb.catalogos.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.catalogos.dao.EstatusSeguimientoDAO;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;

@Repository
public class EstatusSeguimientoDAOImpl implements EstatusSeguimientoDAO {

	@Autowired
	private JdbcTemplate jdbcTemplateCorp;

	/**
	 * Obtiene un item del catálogo de seguimiento por clave
	 * @param clave
	 * @return
	 * @throws DAOException
	 */	
	@Override
	public ItemModel obtenerEstatusPorClave(final String clave) throws DAOException {

		StringBuffer query = new StringBuffer();
		query.append("SELECT Cve_Estatus as clave, \n");
		query.append("  Descripcion as descripcion \n");
		query.append("FROM Cnbv_Estatus_Seg \n");
		query.append("WHERE Cve_Estatus = ?");
		
		try {
			return jdbcTemplateCorp.queryForObject(query.toString(),new String[] { clave }, new RowMapper<ItemModel>() {													
																							public ItemModel mapRow(ResultSet rs, int rowNum) throws SQLException {
																								ItemModel estatus = new ItemModel();
																								estatus.setClave(clave);
																								estatus.setDescripcion(rs.getString("descripcion"));
																								return estatus;
																							}
																						});
		} 
		catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
				
	}

}
