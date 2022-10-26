package shop.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

import javax.activation.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import shop.dao.SigninDTO;

public class SigninServiceImpl implements SigninService{

	
	@Override
	public boolean insert(SigninDTO signinDTO,BasicDataSource dbsource ) {
		UUID uuid = UUID.randomUUID();
		signinDTO.setIdnum(uuid.toString());
		JdbcTemplate jdbc = new JdbcTemplate(dbsource);
		int[] k = jdbc.batchUpdate("insert into shopuser values(?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, signinDTO.getIdnum());
                ps.setString(2, signinDTO.getId());
                ps.setString(3, signinDTO.getPassword());
                ps.setString(4, signinDTO.getName());
                ps.setString(5, signinDTO.getEmail());
                ps.setString(6, signinDTO.getPhone());
                ps.setString(7, signinDTO.getAddress());
            }

			@Override
			public int getBatchSize() {
				return 1;
			}
		});
		
		System.out.println(k[0]);
		if(k[0] >0 )return true;
		else return false;
	}
	
}
