package shop.login;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import shop.service.SigninServiceImpl;



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
		sm=null;
		return	j.toString();
	}
	
	@PostMapping("signin")
	public void signin(@RequestBody Map<String,String> signinMAP,HttpServletResponse resp) throws Exception {
		String msg = null;
		SigninDTO signinDTO = SigninDTO.builder().id(signinMAP.get("id")).password(signinMAP.get("password")).
				name(signinMAP.get("name")).email(signinMAP.get("email")).phone(signinMAP.get("phone")).address(signinMAP.get("address")).build();
		SigninService sign = new SigninServiceImpl();
		if(sign.insert(signinDTO,dbsource)) {
			msg="true";
		}else {
			msg = "<script>alert('가입에 실패했습니다.');history.back();</script>";
		}
		sign=null;
		resp.getWriter().print(msg);		
	}
	
	@PostMapping("logout")
	public void aseq2ve(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pwr = response.getWriter();
		String id = request.getParameter("lgout").intern();
		
		if(id=="true") {
			HttpSession session = request.getSession();
			session.invalidate();
		}
		pwr.print(id);
	
	}

}

