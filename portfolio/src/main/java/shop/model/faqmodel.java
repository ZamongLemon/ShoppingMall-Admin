package shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dao.faqdao;


public class faqmodel {
	JdbcTemplate jdbct = null;
	public faqmodel(DataSource dbsource) {
		this.jdbct = new JdbcTemplate(dbsource);
	}
	
	public List<faqdao> callbycategory(int k){
		List<faqdao> lists = new ArrayList<>();
		String sql= "select * from faqtable where faq_type ='"+(k-1)+"'";
		lists = jdbct.query(sql, new RowMapper<faqdao>() {
			@Override
			public faqdao mapRow(ResultSet rs, int rowNum) throws SQLException {
			faqdao f = new faqdao();
			f.setType(rs.getInt("faq_type"));
			f.setTitle(rs.getString("faq_title"));
			f.setText(rs.getString("faq_text"));
			return f;
			}});
		return lists;
		
	}
}
