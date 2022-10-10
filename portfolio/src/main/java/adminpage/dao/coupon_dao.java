package adminpage.dao;

	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.util.ArrayList;

import adminpage.model.config;
	public class coupon_dao {
		private String cp_idx;
		private String cp_name;
		private String cp_type1;
		private String cp_start;
		private String cp_end;
		private String cp_type2;
		private String cp_discount;
		private String cp_minimum;
		private String cp_imgurl;
		public String g_idx() {return this.cp_idx;}
		public String g_name() {return this.cp_name;}
		public String g_type1() {return this.cp_type1;}
		public String g_start() {return this.cp_start;}
		public String g_end() {return this.cp_end;}
		public String g_type2() {return this.cp_type2;}
		public String g_discount() {return this.cp_discount;}
		public String g_minimum() {return this.cp_minimum;}
		public String g_imgurl() {return this.cp_imgurl;}
		
		private void s_idx(String a) {this.cp_idx = a;}
		private void s_name(String a) {this.cp_name = a;}
		private void s_type1(String a) {this.cp_type1 = a;}
		private void s_start(String a) {this.cp_start = a;}
		private void s_end(String a) {this.cp_end = a;}
		private void s_type2(String a) {this.cp_type2 = a;}
		private void s_discount(String a) {this.cp_discount = a;}
		private void s_minimum(String a) {this.cp_minimum=a;}
		private void s_imgurl(String a) {this.cp_imgurl=a;}
		public ArrayList<coupon_dao> listnumb(int a, int b){
			ArrayList<coupon_dao> list = new ArrayList<>();
			try {
				
			config db = new config();
			Connection ct = db.dbc();
			String sql = "select * from coupon order by cp_idx desc limit ?,?";
			PreparedStatement ps = ct.prepareStatement(sql);
			ps.setInt(1,a);
			ps.setInt(2,b);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				coupon_dao t = new coupon_dao();
				t.s_idx(rs.getString("cp_idx"));
				t.s_name (rs.getString("cp_name"));
				t.s_type1(rs.getString("cp_type1"));
				t.s_start(rs.getString("cp_start"));
				t.s_end(rs.getString("cp_end"));
				t.s_type2(rs.getString("cp_type2"));
				t.s_discount(rs.getString("cp_discount"));
				t.s_minimum(rs.getString("cp_minimum"));
				t.s_imgurl(rs.getString("cp_imgurl"));
				list.add(t);
			}
			
			
			}catch(Exception e) {
			
			}
			return list;
		}
		
		public int retCount() {
			config db = new config();
			Connection ct = db.dbc();
			String sql = "select count(*) as c from coupon";
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


