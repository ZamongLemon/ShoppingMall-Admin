package shop.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SigninDTO {
	public String idnum;
	public String id;
	public String password;
	public String name;
	public String email;
	public String phone;
	public String address;
}
