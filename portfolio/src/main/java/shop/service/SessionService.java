package shop.service;

import org.apache.commons.dbcp.BasicDataSource;

import shop.dao.SessionDTO;

public interface SessionService {
	public SessionDTO getSessionData(String id,String password,BasicDataSource dbsource);
}
