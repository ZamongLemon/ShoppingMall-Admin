package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class admin_couponinsder_model {
		Connection con = null;
		PreparedStatement ps = null;
		public boolean insert_datas(ArrayList<String> vals) {
			boolean res = false;
			try {config c = new config();
				con = c.dbc();
				String sql = "insert into coupon values('0',?,?,?,?,?,?,?,?)";
				ps = con.prepareStatement(sql);
				int j = vals.size();
				for(int i = 0 ; i < j ; i++) ps.setString(i+1, vals.get(i));
				
				int k = ps.executeUpdate();
				res = (k > 0)? true : false;
			}catch(Exception e) {}
			try {
				if(con!=null) con.close();
				if(ps!=null) ps.close();
			}catch(Exception d) {}
			return res;
		}
	}


