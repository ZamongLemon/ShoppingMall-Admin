package adminpage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import adminpage.model.config;

public class notice_board_dao {

	private String[] params = { "bn_idx", "bn_title", "bn_name", "bn_file", "bn_txt", "bn_writed", "bn_ontop",
			"bn_access" };
	private String[] datas = new String[params.length];

	public String return_values(int i) {
		return this.datas[i];
	}

	public String[] returnAll() {
		return this.datas;
	}

	public List<notice_board_dao> returnNormal(int page, int view) {

		List<notice_board_dao> boardlist = new ArrayList<>();
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select * from board_notice order by bn_writed desc limit ?,?";
		try {
			PreparedStatement ps = ct.prepareStatement(sql);
			ps.setInt(1, page);
			ps.setInt(2, view);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int p = 0;
				notice_board_dao temp = new notice_board_dao();
				for (String k : params) {
					temp.datas[p] = (rs.getString(k));
					p++;
				}
				boardlist.add(temp);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return boardlist;
	}

	public List<notice_board_dao> returnNotice() {
		List<notice_board_dao> boardlist = new ArrayList<>();
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select * from board_notice where bn_ontop = 1 order by bn_writed ";

		try {
			PreparedStatement ps = ct.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int p = 0;
				notice_board_dao temp = new notice_board_dao();
				for (String k : params) {
					temp.datas[p] = (rs.getString(k));
					p++;
				}
				boardlist.add(temp);
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return boardlist;
	}

	public int boardlength() {
		int k = 0;
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select count(*) as c from board_notice";

		try {
			PreparedStatement ps = ct.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				k = Integer.valueOf(rs.getString("c"));
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return 0;
		}
		return k;
	}

	public boolean delnotice(Object[] vals) {
			boolean res = false;
			config db = new config();
			Connection ct = db.dbc();
			PreparedStatement ps = null;
			int p = 0;
			try {
				for(int i = 0 ; i < vals.length;i++) {
				String sql = "delete from board_notice where bn_idx = ?";

				ps = ct.prepareStatement(sql);						
				ps.setObject(1, vals[i]);;
				p  += ps.executeUpdate();					
				}
				res=(p>0)? true: false;
					
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}

	return res;

}}
