package adminpage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import adminpage.model.config;

public class category_dao {
	private String pdc_idx;
	private String pdc_code;
	private String pdc_largecode;
	private String pdc_largename;
	private String pdc_smallcode;
	private String pdc_smallname;
	private String pdc_isuse;
	public String g_idx() {return this.pdc_idx;}
	public String g_code() {return this.pdc_code;}
	public String g_largecode() {return this.pdc_largecode;}
	public String g_largename() {return this.pdc_largename;}
	public String g_smallcode() {return this.pdc_smallcode;}
	public String g_smallname() {return this.pdc_smallname;}
	public String g_isuse() {return this.pdc_isuse;}
	
	private void s_idx(String a) {this.pdc_idx = a;}
	private void s_code(String a) {this.pdc_code = a;}
	private void s_largecode(String a) {this.pdc_largecode = a;}
	private void s_largename(String a) {this.pdc_largename = a;}
	private void s_smallcode(String a) {this.pdc_smallcode = a;}
	private void s_smallname(String a) {this.pdc_smallname = a;}
	private void s_isuse(String a) {this.pdc_isuse = a;}
	public ArrayList<category_dao> listnumb(int a, int b){
		ArrayList<category_dao> list = new ArrayList<>();
		try {
			
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select * from product_category order by pdc_idx desc limit ?,?";
		PreparedStatement ps = ct.prepareStatement(sql);
		ps.setInt(1,a);
		ps.setInt(2,b);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			category_dao t = new category_dao();
			t.s_idx(rs.getString("pdc_idx"));
			t.s_code (rs.getString("pdc_code"));
			t.s_largecode(rs.getString("pdc_largecode"));
			t.s_largename(rs.getString("pdc_largename"));
			t.s_smallcode(rs.getString("pdc_smallcode"));
			t.s_smallname(rs.getString("pdc_smallname"));
			t.s_isuse(rs.getString("pdc_isuse"));
			list.add(t);
		}
		
		
		}catch(Exception e) {
		
		}
		return list;
	}
	
	public int retCount() {
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select count(*) as c from product_category";
		int c = 0;
		try {
			
		PreparedStatement ps = ct.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()) {
			c = rs.getInt("c");
		}
		ct.close();
		rs.close();
		}catch(Exception e) {
			
		}		
		return c;
	}
	public coupon_dao getbyname(String nm , ArrayList<coupon_dao> list) {
		coupon_dao a = null;
		for(coupon_dao ad : list) {
			if(ad.g_name().intern()==nm.intern()) {
				a = ad;
			}
		}
		return a;
	}	
}
