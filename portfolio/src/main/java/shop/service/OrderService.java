package shop.service;

import javax.sql.DataSource;

import shop.dao.OrderDTO;

public interface OrderService {
	public boolean inputOrderDTO(OrderDTO orderDTO);
	public boolean cancelOrder(String orderNum);
}
