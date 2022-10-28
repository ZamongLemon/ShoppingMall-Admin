
package shop.service;

import java.util.List;

import shop.dao.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getAllProduct(String code);
	public List<ProductDTO> getAll20Product();
	public ProductDTO getOne(Integer no);
}
