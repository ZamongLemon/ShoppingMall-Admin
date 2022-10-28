package shop.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import shop.dao.ProductDTO;


public class ProductServiceImpl implements ProductService {
	JdbcTemplate jdbct = null;
	public ProductServiceImpl(BasicDataSource dbsource) {
		this.jdbct = new JdbcTemplate(dbsource);
	}
	@Override
	public List<ProductDTO> getAllProduct(String code) {
		List<ProductDTO> lists = new ArrayList<>();
		String sql = "select * from product_detail";
		lists = jdbct.query(sql, new RowMapper<ProductDTO>() {
			@Override
			public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductDTO f = ProductDTO.builder().
						pdd_idx(rs.getString(1)).pdd_code(rs.getString(2)).pdd_name(rs.getString(3))
						.pdd_explain(rs.getString(4)).pdd_nprice(rs.getInt(5)).pdd_saleper(rs.getInt(6)).pdd_sprice(rs.getInt(7))
						.pdd_ea(rs.getInt(8)).pdd_issale(rs.getInt(9)).pdd_fastsoldout(rs.getInt(10)).
						pdd_detail(rs.getString(11)).pdd_imgurl(rs.getString(12)).pdd_suburl(rs.getString(13)).pdd_suburl2(rs.getString(14)).build();
				
			return f;
			}});
		
		return filterByLargeCode(lists,code);		
	}
	
	public List<ProductDTO> filterByLargeCode(List<ProductDTO> list, String code){
		List<ProductDTO> list2 = new ArrayList<>();
		for(int i = 0 ; i < list.size() ;i++) {
			if(list.get(i).getPdd_code().substring(0,4).intern()==code) {
				list2.add(list.get(i));
			}
		}
		
		return list2;
		
	}
	@Override
	public ProductDTO getOne(Integer no) {
		List<ProductDTO> lists = new ArrayList<>();
		String sql = "select * from product_detail where pdd_idx= ?";
		lists = jdbct.query(sql, new RowMapper<ProductDTO>() {
			@Override
			public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductDTO f = ProductDTO.builder().
						pdd_idx(rs.getString(1)).pdd_code(rs.getString(2)).pdd_name(rs.getString(3))
						.pdd_explain(rs.getString(4)).pdd_nprice(rs.getInt(5)).pdd_saleper(rs.getInt(6)).pdd_sprice(rs.getInt(7))
						.pdd_ea(rs.getInt(8)).pdd_issale(rs.getInt(9)).pdd_fastsoldout(rs.getInt(10)).
						pdd_detail(rs.getString(11)).pdd_imgurl(rs.getString(12)).pdd_suburl(rs.getString(13)).pdd_suburl2(rs.getString(14)).build();
				
			return f;
			}},no);
		
		return lists.get(0);
	}
	@Override
	public List<ProductDTO> getAll20Product() {
		List<ProductDTO> lists = new ArrayList<>();
		String sql = "select * from product_detail order by pdd_idx desc limit 20";
		lists = jdbct.query(sql, new RowMapper<ProductDTO>() {
			@Override
			public ProductDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductDTO f = ProductDTO.builder().
						pdd_idx(rs.getString(1)).pdd_code(rs.getString(2)).pdd_name(rs.getString(3))
						.pdd_explain(rs.getString(4)).pdd_nprice(rs.getInt(5)).pdd_saleper(rs.getInt(6)).pdd_sprice(rs.getInt(7))
						.pdd_ea(rs.getInt(8)).pdd_issale(rs.getInt(9)).pdd_fastsoldout(rs.getInt(10)).
						pdd_detail(rs.getString(11)).pdd_imgurl(rs.getString(12)).pdd_suburl(rs.getString(13)).pdd_suburl2(rs.getString(14)).build();
				
			return f;
			}});
		return lists;
	}
}
