package shop.login;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shop.dao.SigninDTO;
import shop.dao.shoplistdao;
import shop.model.shopmodel;
import shop.service.SigninService;



@RestController
public class ajax_controller {

	@Autowired
	BasicDataSource dbsource;
	@RequestMapping("bestproduct")
	public String deded(HttpServletRequest req,String tablename) {
		List<shoplistdao> lis= new ArrayList<shoplistdao>();
		shopmodel sm = new shopmodel(dbsource);
		lis = sm.calltable(tablename, 5);
		JSONArray j = new JSONArray();
		for(shoplistdao k : lis) {
			JSONObject jo = new JSONObject();
			jo.put("tp_idx", k.getTp_idx());
			jo.put("tp_storename", k.getTp_storename());
			jo.put("tp_pdname", k.getTp_pdname());
			jo.put("tp_saleper", k.getTp_saleper());
			jo.put("tp_saleprice", k.getTp_saleprice());
			jo.put("tp_originprice", k.getTp_originprice());
			jo.put("tp_imgurl", k.getTp_imgurl());
			j.add(jo);
		}
		return	j.toString();
	}

	@Autowired
	SigninService signinService;
	@PostMapping("signin")
	public SigninDTO signin(@RequestBody SigninDTO signinDTO) {
		System.out.println(signinDTO.getEmail());
		
		
		if(signinService.insert(signinDTO)) return signinDTO;
		else return null;
		
		
	}
}

