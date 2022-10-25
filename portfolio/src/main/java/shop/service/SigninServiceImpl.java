package shop.service;

import java.util.UUID;

import javax.activation.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import shop.dao.SigninDTO;

@Service
public class SigninServiceImpl implements SigninService{
	@Autowired
	private BasicDataSource dbsource;
	
	@Override
	public boolean insert(SigninDTO signinDTO) {
		UUID uuid = UUID.randomUUID();
		signinDTO.setIdnum(uuid.toString());
		JdbcTemplate jdbc = new JdbcTemplate(dbsource);
		jdbc.batchUpdate("insert into shopuser ");
		return false;
	}
	
}
