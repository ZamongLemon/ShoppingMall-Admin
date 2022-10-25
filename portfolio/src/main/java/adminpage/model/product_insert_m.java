package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class product_insert_m {
	public boolean input(ArrayList<String> vals){
		boolean res = false;
		Connection con = null;		
		
		try {
			config c = new config();
			con= c.dbc();
			String sql = "insert into product_detail values('0',?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			int j = vals.size();
			for(int i = 0 ; i < j ; i++) {
				ps.setString(i+1, vals.get(i));
				System.out.println(vals.get(i));
			}
			int n = ps.executeUpdate();
			ps.close();
			res = (n>0)? true:false;
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				if(!con.isClosed()) con.close();
			}catch (Exception e) {
			}
			
		}
		return res;
		
	}
}
