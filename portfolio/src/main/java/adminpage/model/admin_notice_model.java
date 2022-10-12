package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class admin_notice_model {
		public boolean write_notice (ArrayList<String> vals) {
			boolean res = false;
			Connection con = null;		
			
			try {
				config c = new config();
				con= c.dbc();
				String sql = "insert into board_notice values('0',?,?,?,?,default,?,default)";
				PreparedStatement ps = con.prepareStatement(sql);
				int j = vals.size();
				for(int i = 0 ; i < j ; i++) {
					ps.setString(i+1, vals.get(i));
				}
				int n = ps.executeUpdate();
				
				res = (n>0)? true:false;
				
				con.close();
				
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}finally {
			}
			
			return res;
		}
	}
	
