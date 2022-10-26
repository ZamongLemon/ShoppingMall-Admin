package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import shop.dao.noticedao;

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
		
		public noticedao getbyidx(String idx) throws Exception {
			Connection con = new config().dbc();
			String sql = "select * from board_notice where bn_idx = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, idx);
			ResultSet rs = ps.executeQuery();
			
			noticedao ntc = null;
			if(rs.next()) {
				ntc = noticedao.builder().bn_idx(rs.getInt(1)).bn_title(rs.getString(2))
						.bn_name(rs.getString(3)).bn_file(rs.getString(4)).
						bn_txt(rs.getString(5)).bn_writed(rs.getString(6)).
						bn_ontop(rs.getString(7)).bn_access(rs.getString(8)).build();						
			}
			
			return ntc;
		}
	}
	
