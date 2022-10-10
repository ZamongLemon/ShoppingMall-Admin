package adminpage.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class admin_callset_model {

private String[] params = {"hpsign_title", "hpsign_email", "hpsign_usepoint", "hpsign_basicpoint", "hpsign_firstlevel", "hpset_companyname",
		"hpset_companyreginum","hpset_agentname", "hpset_agenttel", "hpset_mailsalesnum", "hpset_subsalesnum", "hpset_buispostcode",
		"hpset_buisaddress", "hpset_securitymanagername", "hpset_securitymanageremail", "payset_bankname", "payset_accnum",
		"payset_creditcard", "payset_phone", "payset_bookgiftcard", "payset_payminimumpoint", "payset_paymaximumpoint",
		"payset_cashreceipt", "payset_postcompanyname", "payset_postfee", "payset_posteddayselect"};
private String[] values = new String[params.length];

	public String return_values(int i) {
		return this.values[i];
	}
	public String[] returnAll() {
		return this.values;
	}
	public admin_callset_model setValues() {
		
		config db = new config();
		Connection ct = db.dbc();
		String sql = "select * from admin_config order by setTime desc limit 1 ";
		try {
			
		PreparedStatement ps = ct.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			int p = 0;
			for(String k : params) {
				values[p]=(rs.getString(k));
				p++;
			}
		}else {
			return null;
		}
		
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
		return this;
	}

}


