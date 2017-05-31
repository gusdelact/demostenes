package com.gfi.bin.admctasweb.comun.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.gfi.bin.admctasweb.comun.dao.ComunDao;
import com.gfi.bin.admctasweb.comunes.exception.DAOException;
import com.gfi.bin.admctasweb.comunes.model.ItemModel;
/**
 * Dao que contiene las operaciones a las tablas comunes de la base de datos
 * @author <a href="mailto:mreyesc5@gmail.com">Manuel Reyes Castellanos</a>
 */
@Repository
public class ComunDaoImpl implements ComunDao{
	@Autowired
	private JdbcTemplate jdbcTemplateCorp;
	
	@Autowired
	private NamedParameterJdbcTemplate corpNamedTemplate;
	/**
	 * 
	 * @param idEmpresa
	 * @param cveDiaLiquida
	 * @return
	 */
	public Date getDateFechaEmpresa(Integer idEmpresa, String cveDiaLiquida){
		StringBuilder query = new StringBuilder();
		query.append(" SELECT F_REFERENCIA FROM FECHA_EMPRESA ");
		query.append(" WHERE ID_EMPRESA = " + idEmpresa); 
		query.append(" AND CVE_DIA_LIQUIDA = '"+cveDiaLiquida+"'"); 
	//	Object obj = jdbcTemplate.queryForObject("select sysdate from dual", java.util.Date.class);
		return (jdbcTemplateCorp.queryForObject(query.toString() , Date.class));
	}
	
	/**
	 * Obtiene la empresa asociada a un oficio en el objeto ItemModel que contiene la clave y descripción
	 * @param numOficio
	 * @param tipoOficio
	 * @return
	 * @throws DAOException
	 */	
	@Override
	public ItemModel obtenerEmpresaOficio(String numOficio, String tipoOficio) throws DAOException {
		// TODO Auto-generated method stub
		StringBuffer query = new StringBuffer();
		query.append("SELECT to_char(B.Id_Persona) Id_Persona, \n");
		query.append("  B.Nombre \n");
		query.append("FROM Cnbv_Oficio A, \n");
		query.append("  Persona B \n");
		query.append("WHERE A.Id_Empresa = B.Id_Persona \n");
		query.append("AND A.Num_Oficio   = ? \n");
		query.append("AND A.Tipo_Oficio  = ?");
		
		try {
			return jdbcTemplateCorp.queryForObject(query.toString() , new String[]{numOficio, tipoOficio}, new RowMapper<ItemModel>() {													
																												public ItemModel mapRow(ResultSet rs, int rowNum) throws SQLException {
																													ItemModel empresa = new ItemModel();
																													empresa.setClave(rs.getString("Id_Persona"));
																													empresa.setDescripcion(rs.getString("Nombre"));
																													return empresa;
																												}
																											});
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			throw new DAOException(e);
		}
		}

}
