package adminpage.model;

import java.sql.*;

public class config {
	public enum lg {success,fail,n_allowed};
	
	Connection c = null;

	public Connection dbc() {
	
		try {		
			String dbdrive = "com.mysql.jdbc.Driver";
			String dburl = "jdbc:mysql://localhost:3306/puhu17";
			String dbuser = "";
			String dbpass = "";
			Class.forName(dbdrive);//JDBC 클래스 로드명 
			c = DriverManager.getConnection(dburl,dbuser,dbpass);	
		}catch(Exception e) {
			e.getMessage();
		}
		return c;
	}

}
