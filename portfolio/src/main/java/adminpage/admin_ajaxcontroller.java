package adminpage;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

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

@RestController
public class admin_ajaxcontroller {
		
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
}




