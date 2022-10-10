package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class admin_login_model {
	public config.lg tryadmlgn(String id,String pw) {
		config.lg suc = config.lg.fail;
		Connection con = null;
		try {
		config c = new config();
		con = c.dbc();
		String sql = "select * from admin_account where admin_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		if(rs.next()){
			if(rs.getString("admin_pw").intern()==pw.intern()){
			suc=(rs.getInt("admin_allowed")==1)? config.lg.success : config.lg.n_allowed;
			}
			else {suc = config.lg.fail;} 
			con.close();
		}		
		}catch(Exception e) {
			con.close();
		}finally{
			return suc;
		}		
	}
	public void loghis(String id) {
		Connection con = null;
		try {
			config c = new config();
			con = c.dbc();
			String sql = "insert into loghistory values('0',?,default)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			int n = ps.executeUpdate();
			con.close();
		}catch(Exception e) {
		}
	}
}
