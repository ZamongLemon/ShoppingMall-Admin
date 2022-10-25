package shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import shop.dao.SigninDTO;

@Repository
public interface SigninMapper {
		@Select("select * from shopuser")
		public List<SigninDTO> getAll();
}
