package adminpage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import adminpage.dao.notice_board_dao;
import adminpage.model.admin_callset_model;
import adminpage.model.admin_siteset_model;
import adminpage.model.adminapprv;
import adminpage.model.asignmodule;
import adminpage.model.config;

@RestController
public class admin_ajaxcontroller {
	@PostMapping("admin/admin_overlap_check")
	public void adminOverlap(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("utf-8");
		String p = request.getParameter("a_id");
		asignmodule am = new asignmodule();
		PrintWriter pwr = response.getWriter();
		try {
			
		if(am.overlap_check(p)) {
			pwr.print("no");
		}else {
			pwr.print("ok");
		}
		
		}catch(Exception e) {
			
		}finally {
			pwr.close();
		}
	}
	
	@PostMapping("admin/adminapproval")
	public void asdfasd(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String ap = request.getParameter("approve");
		String index = request.getParameter("idx");
		adminapprv adp = new adminapprv();
		try {
			
		if(adp.approval(ap, index)) {
			response.getWriter().print("<script>alert('성공');</script>");
		}else {
			response.getWriter().print("<script>alert('실패');</script>");
		}
		}catch(Exception e) {
			response.getWriter().print("<script>alert('실패');</script>");
		}
	}
	
	@PostMapping("admin/adminsiteset")
	public void sdfasdf(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		Enumeration<String> names = request.getParameterNames();
		ArrayList<String> params = new ArrayList<>();
		ArrayList<String> datas = new ArrayList<>();
		int i = 0;
		while(names.hasMoreElements()) {
			params.add(names.nextElement());
			datas.add(request.getParameter(params.get(i)));
			i++;
		}		
		admin_siteset_model asm = new admin_siteset_model();
		try {			
		if(asm.hpregister(datas)) {
			response.sendRedirect("./config");
		}else {
		}
		}catch(Exception e) {}
	}
	
	
	@GetMapping("admin/callhpset")
	public void sdfsd(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		if(request.getParameter("key").intern()=="values") {
		admin_callset_model h = new admin_callset_model();
		h = h.setValues();		
		response.getWriter().print(Arrays.toString(h.returnAll()));
	}
	}
	
	@PostMapping("admin/del_notice")
	public void sdfsdf(HttpServletRequest request, HttpServletResponse response) throws Exception  {
		request.setCharacterEncoding("utf-8");
		
		String p = request.getParameter("key");
		String[] v = p.split(",");
		Set<Integer> a = new HashSet<Integer>();
		for(int i = 0 ; i < v.length;i++) {
			a.add(Integer.valueOf(v[i]));
		}
		notice_board_dao nd = new notice_board_dao();
		if(nd.delnotice(a.toArray()))
		response.getWriter().print("suc");
		else {
			response.getWriter().print("fail");
		}
	}

	@PostMapping("admin/logout")
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
	
	@GetMapping("admin/smallcode")
	public void getsmallcode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String small = request.getParameter("key");
		Connection con = new config().dbc();
		String sql = "select pdc_smallcode from product_category where pdc_largecode = ? and pdc_smallcode is not null order by pdc_smallcode asc";
		try {
			
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, small);
		ResultSet rs = ps.executeQuery();
		ArrayList<String> smlist = new ArrayList<String>();
		while(rs.next()) {
			smlist.add(rs.getString("pdc_smallcode"));
		}
		con.close();
		if(smlist.size()==0)
			response.getWriter().print("0");
		else response.getWriter().print(smlist); 
		}catch(Exception e){
			
		}
	}
	
	@GetMapping("admin/poverlapcheck")
	public void overlapcheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		String k = request.getParameter("code");
		Connection con = new config().dbc();
		String sql = "select count(*) as c from product_detail where pdd_code = ?";
		PreparedStatement ps = null;
		
		int j = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, k);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				j =rs.getInt("c");			}
			rs.close();
		}catch(Exception e) {
			
		}finally {
			try {
				if(con!=null)con.close();
				if(ps!=null)con.close();
			}catch (Exception e) {
			}
		}

		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(j);
	
	}
}




