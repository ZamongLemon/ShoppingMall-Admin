package adminpage.dao;
import java.sql.*;
import java.util.ArrayList;

import adminpage.model.config;
public class admindao {
		private String a_idx;
		private String a_id;
		private String a_nm;
		private String a_mail;
		private String a_tel;
		private String a_part;
		private String a_grade;
		private String a_allowed;
		private String a_signed;
		public String g_idx() {return this.a_idx;}
		public String g_id() {return this.a_id;}
		public String g_name() {return this.a_nm;}
		public String g_mail() {return this.a_mail;}
		public String g_tel() {return this.a_tel;}
		public String g_part() {return this.a_part;}
		public String g_grade() {return this.a_grade;}
		public String g_allowed() {return this.a_allowed;}
		public String g_signed() {return this.a_signed;}
		
		private void s_idx(String a) {this.a_idx = a;}
		private void s_id(String a) {this.a_id = a;}
		private void s_name(String a) {this.a_nm = a;}
		private void s_mail(String a) {this.a_mail = a;}
		private void s_tel(String a) {this.a_tel = a;}
		private void s_part(String a) {this.a_part = a;}
		private void s_grade(String a) {this.a_grade = a;}
		private void s_allowed(String a) {this.a_allowed=a;}
		private void s_signed(String a) {this.a_signed=a;}
		public ArrayList<admindao> all_list(){
			ArrayList<admindao> list = new ArrayList<>();
			
			try {
				
			config db = new config();
			Connection ct = db.dbc();
			String sql = "select * from admin_account";
			PreparedStatement ps = ct.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				admindao t = new admindao();
				t.s_idx(rs.getString("admin_idx"));
				t.s_id (rs.getString("admin_id"));
				t.s_name(rs.getString("admin_nm"));
				t.s_mail(rs.getString("admin_email"));
				t.s_tel(rs.getString("admin_tel"));
				t.s_part(rs.getString("admin_part"));
				t.s_grade(rs.getString("admin_grade"));
				t.s_allowed(rs.getString("admin_allowed"));
				t.s_signed(rs.getString("admin_signed"));
				list.add(t);
			}
			
			
			}catch(Exception e) {
			
			}
			return list;
		}
		
		public admindao getbyid(String id , ArrayList<admindao> list) {
			admindao a = null;
			for(admindao ad : list) {
				if(ad.g_id().intern()==id.intern()) {
					a = ad;
				}
			}
			return a;
		}	
	}
