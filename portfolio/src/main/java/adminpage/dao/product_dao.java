package adminpage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import adminpage.model.config;

public class product_dao {

	private String pdd_idx;
	private String pdd_code;
	private String pdd_name;
	private String pdd_explain;
	private String pdd_nprice;
	private String pdd_saleper;
	private String pdd_sprice;
	private String pdd_ea;
	private String pdd_issale;
	private String pdd_fastsoldout;
	private String pdd_detail;
	private String pdd_imgurl;
	private String pdd_suburl;
	private String pdd_suburl2;

	public String g_idx() {
		return this.pdd_idx;
	}

	public String g_code() {
		return this.pdd_code;
	}

	public String g_name() {
		return this.pdd_name;
	}

	public String g_explain() {
		return this.pdd_explain;
	}

	public String g_nprice() {
		return this.pdd_nprice;
	}

	public String g_saleper() {
		return this.pdd_saleper;
	}

	public String g_sprice() {
		return this.pdd_sprice;
	}

	public String g_ea() {
		return this.pdd_ea;
	}

	public String g_issale() {
		return this.pdd_issale;
	}

	public String g_fastsoldout() {
		return this.pdd_fastsoldout;
	}

	public String g_detail() {
		return this.pdd_detail;
	}

	public String g_imgurl() {
		return this.pdd_imgurl;
	}

	public String g_suburl() {
		return this.pdd_suburl;
	}

	public String g_suburl2() {
		return this.pdd_suburl2;
	}

	private void s_idx(String a) {
		this.pdd_idx = a;
	}

	private void s_code(String a) {
		this.pdd_code = a;
	}

	private void s_name(String a) {
		this.pdd_name = a;
	}

	private void s_explain(String a) {
		this.pdd_explain = a;
	}

	private void s_nprice(String a) {
		this.pdd_nprice = a;
	}

	private void s_saleper(String a) {
		this.pdd_saleper = a;
	}

	private void s_sprice(String a) {
		this.pdd_sprice = a;
	}

	private void s_ea(String a) {
		this.pdd_ea = a;
	}

	private void s_issale(String a) {
		this.pdd_issale = a;
	}

	private void s_fastsoldout(String a) {
		this.pdd_fastsoldout = a;
	}

	private void s_detail(String a) {
		this.pdd_detail = a;
	}

	private void s_imgurl(String a) {
		this.pdd_imgurl = a;
	}

	private void s_suburl(String a) {
		this.pdd_suburl = a;
	}

	private void s_suburl2(String a) {
		this.pdd_suburl2 = a;
	}

	public ArrayList<product_dao> listnumb(int a, int b) {
		ArrayList<product_dao> list = new ArrayList<>();
		try {

			config db = new config();
			Connection ct = db.dbc();
			String sql = "select * from product_detail order by pdd_idx desc limit ?,?";
			PreparedStatement ps = ct.prepareStatement(sql);
			ps.setInt(1, a);
			ps.setInt(2, b);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				product_dao t = new product_dao();
				t.s_idx(rs.getString("pdd_idx"));
				t.s_code(rs.getString("pdd_code"));
				t.s_name(rs.getString("pdd_name"));
				t.s_explain(rs.getString("pdd_explain"));
				t.s_nprice(rs.getString("pdd_nprice"));
				t.s_saleper(rs.getString("pdd_saleper"));
				t.s_sprice(rs.getString("pdd_sprice"));
				t.s_ea(rs.getString("pdd_ea"));
				t.s_issale(rs.getString("pdd_issale"));
				t.s_fastsoldout(rs.getString("pdd_fastsoldout"));
				t.s_detail(rs.getString("pdd_detail"));
				t.s_imgurl(rs.getString("pdd_imgurl"));
				t.s_suburl(rs.getString("pdd_suburl"));
				t.s_suburl2(rs.getString("pdd_suburl2"));
				list.add(t);
			}

		} catch (Exception e) {

		}
		return list;
	}

	public int retCount() {
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select count(*) as c from product_detail";
		int c = 0;
		try {

			PreparedStatement ps = ct.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				c = rs.getInt("c");
			}
			ct.close();
			rs.close();
		} catch (Exception e) {

		}
		return c;
	}

	public product_dao getbyname(String nm, ArrayList<product_dao> list) {
		product_dao a = null;
		for (product_dao ad : list) {
			if (ad.g_name().intern() == nm.intern()) {
				a = ad;
			}
		}
		return a;
	}
}
