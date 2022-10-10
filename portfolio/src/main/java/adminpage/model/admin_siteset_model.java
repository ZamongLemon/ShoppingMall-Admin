package adminpage.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class admin_siteset_model {
	public boolean hpregister(ArrayList<String> vals) throws Exception{
		boolean res = false;
		Connection con = null;		
		
		try {
			config c = new config();
			con= c.dbc();
			String sql = "insert into admin_config values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,default)";
			PreparedStatement ps = con.prepareStatement(sql);
			boolean[] str = {true,true,false,false,false,true,true,true,true,true,true,true,true,true,true,true,true,false,false,false,false,false,false,true,false,false}; 
			int j = 1,k=vals.size();
			for(int i = 1 ; i <= k;i++) {
				if(str[i-1]) {
					System.out.println(vals.get(i-1));
					ps.setString(i, vals.get(i-1));					
				}else {
					ps.setInt(i, Integer.valueOf(vals.get(i-1)));			
				}
			}
			int n = ps.executeUpdate();
			
			res = (n>0)? true:false;
			
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			con.close();
		}
		
		return res;
	}
}