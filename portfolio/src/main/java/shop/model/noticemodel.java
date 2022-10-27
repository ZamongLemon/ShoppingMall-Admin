package shop.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dao.SessionDTO;
import shop.dao.noticedao;

public class noticemodel {
	JdbcTemplate jdbct = null;
	public noticemodel(DataSource dbsource) {
		this.jdbct = new JdbcTemplate(dbsource);
	}
	
	public List<noticedao> returnnotices(int page,int objectcnt, int type, String word ){
		List<noticedao> lists = new ArrayList<>();
		String sql = null;
		sql= "select * from board_notice";		
		sql+= stringmaker(page, objectcnt, type, word,false);
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
	public int countbn(int page,int objectcnt, int type, String word ) {
		String sql = "select count(*) from board_notice";
		sql += stringmaker(page, objectcnt, type, word,true);
		List<Integer> cnt = jdbct.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {				
			return rs.getInt("count(*)");
			}});

		
		return cnt.get(0);
	}
	
	public String stringmaker(int page,int objectcnt, int type, String word, boolean cnt ) {
		if(page<=0) page = 1;
		int start = (page-1)*objectcnt;
		String sql = "";
		switch(type) {
		case 1:
			sql+= " where bn_ontop = 0"; break;
		case 2:
			sql+= " where bn_ontop = 1"; break;
		default:
			sql+= " where bn_ontop is not null";
			break;		
		}		
		if(word!= null || word !="") {
			sql+=" and (bn_title like '%"+word+"%' or bn_txt like '%"+word+"%' ) ";
		}
		sql+=(!cnt)?
		" order by bn_idx desc limit "+start+","+objectcnt		
		: "order by bn_idx desc ";
		return sql;
	}
	public noticedao getbyidx(String idx) {

		List<noticedao> results = this.jdbct.query(
				"select * from board_notice where bn_idx= ?",
				new RowMapper<noticedao>() {
					@Override
					public noticedao mapRow(ResultSet rs, int rowNum) throws SQLException {
						noticedao noticeDAO
						= noticedao.builder().bn_title(rs.getString("bn_title")).bn_name(rs.getString("bn_name")).
						bn_txt(rs.getString("bn_txt")).bn_file(rs.getString("bn_file")).bn_writed(rs.getString("bn_writed"))
						.build();
						
						return noticeDAO;
					}
				}, idx);
			return results.isEmpty() ? null : results.get(0);
	}
	
}
