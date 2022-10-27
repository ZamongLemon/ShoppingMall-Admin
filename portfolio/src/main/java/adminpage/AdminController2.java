package adminpage;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import adminpage.dao.category_dao;
import adminpage.model.admin_notice_model;
import adminpage.model.cate_write_m;
import adminpage.model.fileupload;
import adminpage.model.findlargecodes;
import adminpage.model.product_insert_m;
import shop.dao.noticedao;


@MultipartConfig(
		
		fileSizeThreshold = 1024 * 1024 *1,
		maxFileSize= 1024*1024*4, 
		maxRequestSize = 1024*1024*8 
	)

@Controller
public class AdminController2 {

	@RequestMapping("admin/productwrite")
	public String productWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		findlargecodes fl = new findlargecodes();
		ArrayList<String> a = fl.large_codes();
		
		request.setAttribute("largecode", a);
		
		RequestDispatcher rd = request.getRequestDispatcher("./admin_product_write.jsp");
		return "adminpages/admin_product_write";
	}
	
	@RequestMapping("admin/productinsert")
	public void productInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String savepath =  request.getSession().getServletContext().getRealPath("product/");
		System.out.println(savepath);
		Part main = request.getPart("pdd_imgurl");
		Part sub1 = request.getPart("pdd_suburl");
		Part sub2 = request.getPart("pdd_suburl2");
		Enumeration<String> paramse = request.getParameterNames();
		ArrayList<String> values = new ArrayList<String>();
		ArrayList<String> params = new ArrayList<String>();
		while(paramse.hasMoreElements()) {
			String pas = paramse.nextElement();
			System.out.println(pas);
			params.add(pas);
			values.add(request.getParameter(pas));
		}
		fileupload fl = new fileupload();
		String pdd_imgurl,pdd_suburl,pdd_suburl2;

		try {
			pdd_imgurl = fl.upload(main, savepath,"0");
			pdd_suburl = fl.upload(sub1, savepath,"1");
			pdd_suburl2 = fl.upload(sub2, savepath,"2");
			
			
			params.add("pdd_imgurl");
			params.add("pdd_suburl");
			params.add("pdd_suburl2");
			values.add(pdd_imgurl);
			values.add(pdd_suburl);
			values.add(pdd_suburl2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response.getWriter().print("<script>alert('입력실패');history.back();</script>");
		}
		System.out.println(params);
		System.out.println(values);
		product_insert_m pim = new product_insert_m();
		if(pim.input(values)) {
			
			response.sendRedirect("./product?p=1");
		}
		
		else 
			response.getWriter().print("<script>alert('입력실패');history.back();</script>");
	
	}

	
	@RequestMapping("admin/writecategory")
	public String toWriteCategory() {
		
		return "adminpages/admin_category_write";
	}
	
	@RequestMapping("admin/categorylist")
	public String categoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("p");
		if(page== null || Integer.valueOf(page)<=0) page = "1";
		String view = request.getParameter("pv");
		if(view== null || Integer.valueOf(view)<=0) view = "5";
		
		int iview = Integer.valueOf(view);
		int ipage = Integer.valueOf(page);
		category_dao cgd = new category_dao();
		int length =cgd.retCount();
		double cate_maxPage=  Math.ceil((double)length/Double.valueOf(page));
		ArrayList<category_dao> cpdl = cgd.listnumb((ipage-1)*iview, iview);
		request.setAttribute("categorylist",cpdl);
		request.setAttribute("c_maxpage", cate_maxPage);
		
		return "adminpages/admin_category";
	}
	
	@RequestMapping("admin/catewrite")
	public void categoryWrite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 	request.setCharacterEncoding("utf-8");
	 	response.setContentType("text/html;charset=utf-8");
		String code =request.getParameter("code");
		cate_write_m cm = new cate_write_m();
		ArrayList<String> vals = new ArrayList<>();
		String lgc = request.getParameter("largecode");
		if(cm.coverlap(code)) {
			response.getWriter().print("<script>alert('중복된 코드입니다');history.back()</script>");
		}else {
			
			vals.add(code);
			vals.add(lgc);
			vals.add(request.getParameter("largename"));
			if(request.getParameter("clarge")!=null) {
				vals.add("n");
				vals.add("n");
				vals.set(0, vals.get(0)+"00");	
			}
			else {
				vals.add(request.getParameter("smallcode"));
				vals.add(request.getParameter("smallname"));
			}
			vals.add(request.getParameter("a"));
			String clarge = lgc+"00";
			if(!cm.coverlap(clarge)) {
				System.out.println("cpoint1");
				ArrayList<String> vals2 = new ArrayList<>();
				vals2.add(clarge);
				vals2.add(lgc);
				vals2.add(request.getParameter("largename"));
				vals2.add("n");
				vals2.add("n");
				vals2.add(request.getParameter("a"));
				cm.insertval(vals2);
			}

			cm.insertval(vals);
			response.sendRedirect("./categorylist?p=1");
		}
	}
	
	@RequestMapping("admin/writeproductcode")
	public String writeproductcode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		ArrayList<String> codes = new findlargecodes().large_codes();
		
		request.setAttribute("codes", codes);
		
		return "adminpages/admin_category_write";
	}
	
	@RequestMapping("admin/noticemodify")
	public String noticeModify(String idx,HttpServletResponse resp,HttpServletRequest req) throws Exception{
		
		if(idx==null) {
			resp.sendRedirect("notice_write");
		}
		admin_notice_model admNoticeModel = new admin_notice_model();
		noticedao noticedao =  admNoticeModel.getbyidx(idx);
		req.setAttribute("noticeinfo", noticedao);
		return "adminpages/admin_notice_modify";
		
	}
	@RequestMapping("admin/noticeupdate")
	public void noticeUpdate(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pwr = response.getWriter();
		String savepath =  request.getServletContext().getRealPath("") + "admin\\upload\\";	
		System.out.println("here?");
		Part filepart = request.getPart("bn_file");	
		String bn_file = null;
		String p = filepart.getSubmittedFileName().intern();
		String[] params = {"bn_title","bn_name","bn_txt","bn_ontop"};
		ArrayList<String> vals = new ArrayList<>(); 
		if(p!="") {			
		p = p.substring(p.lastIndexOf(".")+1);
		LocalDateTime ldt = LocalDateTime.now();
		ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));
		String dt = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
		p = dt+"upd."+p;
		File d = new File(savepath);
		if(!d.isDirectory()) {
			d.mkdir();
		}
		String url = savepath+p;

		filepart.write(url);
			bn_file= url.replace("/puhu17/tomcat/webapps/", "http://puhu17.cafe24.com/");
		}else {
			bn_file="default";
		}
		String ont = request.getParameter(params[3]);
		int j = params.length;
		for(int i = 0 ; i < j-1; i++) {
			vals.add(request.getParameter(params[i]));
		}
		if(ont==null) vals.add("0"); else vals.add(ont);
		vals.add(2,bn_file);
		vals.add(request.getParameter("bn_idx"));
		System.out.println(vals);
		admin_notice_model nm = new admin_notice_model();
		try {
			if(nm.modify_notice(vals)) {				
				response.sendRedirect("./notice");
			}else {response.getWriter().print("<script>alert('수정에 실패하였습니다.');history.back()</script>");}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}finally {
			response.getWriter().print("<script>alert('수정에 실패하였습니다.');history.back()</script>");
		}
		
	}
}
