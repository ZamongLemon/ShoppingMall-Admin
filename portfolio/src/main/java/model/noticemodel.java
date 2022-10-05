package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import dao.noticedao;

public class noticemodel {
	JdbcTemplate jdbct = null;
	public noticemodel(DataSource dbsource) {
		this.jdbct = new JdbcTemplate(dbsource);
	}
	
	public List<noticedao> returnnotices(int j,int k){
		List<noticedao> lists = new ArrayList<>();
		String sql= "select * from board_notice order by bn_idx desc limit "+j+","+k;
		lists = jdbct.query(sql, new RowMapper<noticedao>() {
			@Override
			public noticedao mapRow(ResultSet rs, int rowNum) throws SQLException {
				noticedao f = new noticedao();
				f.setBn_idx(rs.getInt(1));
				f.setBn_title(rs.getString(2));
				f.setBn_name(rs.getString(3));
				f.setBn_file(rs.getString(4));
				f.setBn_txt(rs.getString(5));
				f.setBn_writed(rs.getString(6));
				f.setBn_ontop(rs.getString(7));
				f.setBn_access(rs.getString(8));	
			return f;
			}});
		return lists;
		
	}
}
