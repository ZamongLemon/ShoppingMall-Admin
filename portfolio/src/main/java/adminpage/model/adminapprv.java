package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class adminapprv {
	public boolean approval(String a, String b) throws Exception{
		boolean res = false;
		Connection con = null;
		try {
			
		config c = new config();
		con = c.dbc();
		String sql = "update admin_account set admin_allowed=? where admin_idx=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, Integer.parseInt(a));
		ps.setString(2, b);
		int p = ps.executeUpdate();
		res =(p>0)? true :false;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			con.close();			
		}
		return res;
	}

}
