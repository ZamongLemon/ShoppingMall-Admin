package shop.service;

import org.apache.commons.dbcp.BasicDataSource;

import shop.dao.SigninDTO;

public interface SigninService {
	
	public boolean insert(SigninDTO signinDTO,BasicDataSource dbsource); 
}
