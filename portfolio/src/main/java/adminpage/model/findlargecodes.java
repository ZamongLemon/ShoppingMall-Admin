package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class findlargecodes {
	ArrayList<String> codes = null;
	Connection con = null;
	public ArrayList<String> large_codes() {
		codes = new ArrayList<String>();
		config d = new config();
		this.con = d.dbc();
		String sql = "select pdc_largecode as p from product_category where pdc_smallcode is null order by pdc_largecode";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				String p = rs.getString("p");
				codes.add(p);
			}

			
		}catch(Exception e) {System.out.println(e.getMessage());}
		
		
		return codes;
	}

}
