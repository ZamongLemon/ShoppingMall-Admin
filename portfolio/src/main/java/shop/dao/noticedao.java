package shop.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class noticedao {
	int bn_idx;
	String bn_title;
	String bn_name;
	String bn_file;
	String bn_txt;
	String bn_writed;
	String bn_ontop;
	String bn_access;
}
