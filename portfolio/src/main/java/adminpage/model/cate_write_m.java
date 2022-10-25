package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class cate_write_m {
	public boolean coverlap(String code) {
		boolean res = false;
		Connection con = null;		
		PreparedStatement ps = null;
		
		try {
			config c = new config();
			con= c.dbc();
			String sql = "select count(*) as c from product_category where pdc_code = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, code);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				res= (Integer.parseInt(rs.getString("c")) > 0)?  true : false;
			}
			

		}catch(Exception e) {System.out.println(e.getMessage());}
		finally {
			try {
				if(!ps.isClosed())ps.close();
				if(!con.isClosed())con.close();
			}catch(Exception f) {}
		}
		return res;
	}
	public boolean insertval(ArrayList<String> vals) {
		boolean res = false;
		Connection con = null;		
		config c = new config();
		con= c.dbc();
		PreparedStatement ps = null;						
		
		try {
			String sql = "insert into product_category values('0',?,?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			int a = 1 ; 
			for(String k : vals) {
				if(k.intern()=="n") {
					k = null;
				}
				ps.setString(a++, k);
			}
			int b = ps.executeUpdate();
			
			res= (b>0)? true : false;
			
		}catch(Exception e) {System.out.println(e.getMessage());}
		finally {
			try {
				if(!ps.isClosed())ps.close();
				if(!con.isClosed())con.close();
			}catch(Exception f) {}
		}
		return res;
	}
	
		
	
}
