package shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dao.shoplistdao;

public class shopmodel {
	JdbcTemplate jdbct = null;
	public shopmodel(DataSource dbsource) {
		this.jdbct = new JdbcTemplate(dbsource);
	}
	
	public List<shoplistdao> calltable(String tablename,int count){
		List<shoplistdao> lists = new ArrayList<>();
		String sql= "select * from "+tablename+" order by tp_idx desc limit "+count;
		lists = jdbct.query(sql, new RowMapper<shoplistdao>() {
			@Override
			public shoplistdao mapRow(ResultSet rs, int rowNum) throws SQLException {
				shoplistdao f = new shoplistdao();
			f.setTp_idx(rs.getInt(1));
			f.setTp_storename(rs.getString(2));
			f.setTp_pdname(rs.getString(3));
			f.setTp_saleper(rs.getInt(4));
			f.setTp_saleprice(rs.getInt(5));
			f.setTp_originprice(rs.getInt(6));
			f.setTp_imgurl(rs.getString(7));
			return f;
			}});
		return lists;
		
	}
}
