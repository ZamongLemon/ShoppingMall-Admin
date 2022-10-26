package shop.service;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dao.SessionDTO;
import shop.dao.SigninDTO;

public class SessionServiceImpl implements SessionService{
	@Override
	public SessionDTO getSessionData(String id,String password,BasicDataSource dbsource ) {
		
			JdbcTemplate jdbc = new JdbcTemplate(dbsource);
	
			List<SessionDTO> results = jdbc.query(
					"select * from shopuser where id = ? and password = ?",
					new RowMapper<SessionDTO>() {
						@Override
						public SessionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
							SessionDTO sessionDTO
							= SessionDTO.builder().idnum(rs.getString("idnum")).id(rs.getString("id")).
							name(rs.getString("name")).email(rs.getString("email")).phone(rs.getString("phone")).
							address(rs.getString("address")).build();
							
							return sessionDTO;
						}
					}, id,password);
				return results.isEmpty() ? null : results.get(0);

		}

}
