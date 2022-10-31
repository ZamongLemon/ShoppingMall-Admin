package shop.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import shop.dao.OrderDTO;

@Service
public class OrderServiceImpl implements OrderService {
	JdbcTemplate jdbct = null;
	public OrderServiceImpl(BasicDataSource dbsource) {
		this.jdbct = new JdbcTemplate(dbsource);
	}
	@Override
	public boolean inputOrderDTO(OrderDTO orderDTO) {
		int[] k = jdbct.batchUpdate("insert into orderdetail values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, orderDTO.getOrderNum());
                ps.setString(2, orderDTO.getOrderDate());
                ps.setString(3, orderDTO.getBuyerName());
                ps.setString(4, orderDTO.getBuyerPhone());
                ps.setString(5, orderDTO.getBuyerEmail());
                ps.setString(6, orderDTO.getReceiverName());
                ps.setString(7, orderDTO.getReceiverPhone());
                ps.setString(8, orderDTO.getReceiverEmail());
                ps.setString(9, orderDTO.getReceiverPostcode());
                ps.setString(10, orderDTO.getReceiverStreetAddress());
                ps.setString(11, orderDTO.getReceiverAddressDetail());
                ps.setString(12, orderDTO.getCodeList());
                ps.setString(13, orderDTO.getCountList());
                ps.setString(14, orderDTO.getMemo());
                ps.setString(15, orderDTO.getPayment());
                ps.setString(16, orderDTO.getDepositor());
                ps.setString(17, orderDTO.getBank());
                ps.setString(18, orderDTO.getTypeReceiptUse());
                ps.setInt(19, orderDTO.getNPrice());
                ps.setInt(20, orderDTO.getSPrice());
            }

			@Override
			public int getBatchSize() {
				return 1;
			}
		});
		
		System.out.println(k[0]);
		if(k[0] >0 )return true;
		else return false;

	}
	@Override
	public boolean cancelOrder(String orderNum) {
		int k = jdbct.update("delete from orderdetail where ordernum = ?",orderNum);
		
		if(k >0 )return true;
		else return false;
	}

}
