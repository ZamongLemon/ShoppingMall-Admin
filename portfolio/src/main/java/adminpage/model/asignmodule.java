package adminpage.model;

import java.sql.*;
import java.util.ArrayList;

public class asignmodule {
	
	public boolean overlap_check(String id) throws Exception{
		boolean res = false;
		Connection con = null;
		try {
			
		config c = new config();
		con = c.dbc();
		String sql = "select admin_id from admin_account where admin_id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, id);
		ResultSet rs = ps.executeQuery();
		res =(rs.next())? true :false;
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			con.close();			
		}
		return res;
	}
	public boolean register(ArrayList<String> strs) throws Exception{
		boolean res = false;
		Connection con = null;		
		
		try {
			config c = new config();
			con= c.dbc();
			if(!overlap_check(strs.get(0))) {
			
				strs.set(1, strs.get(1).replace(" ", ""));
			String sql = "insert into admin_account values('0',?,?,?,?,?,?,?,0,default)";
			PreparedStatement ps = con.prepareStatement(sql);
			int j = 1;
			for(int i = 1 ; i <= strs.size();i++) {
				if(i==7 || i==6) {
					ps.setInt(i, Integer.valueOf(strs.get(i-1)));
				}else {
					ps.setString(i, strs.get(i-1));
				}
			}
			int n = ps.executeUpdate();
			
			res = (n>0)? true:false;
			
			}
		}catch(Exception e) {
		}finally {
			con.close();
		}
		
		return res;
	}
}
