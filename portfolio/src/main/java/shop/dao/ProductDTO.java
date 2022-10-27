package shop.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {
	private String pdd_idx;
	private String pdd_code;
	private String pdd_name;
	private String pdd_explain;
	private int pdd_nprice;
	private int pdd_saleper;
	private int pdd_sprice;
	private int pdd_ea;
	private int pdd_issale;
	private int pdd_fastsoldout;
	private String pdd_detail;
	private String pdd_imgurl;
	private String pdd_suburl;
	private String pdd_suburl2;
	
	
	
}
