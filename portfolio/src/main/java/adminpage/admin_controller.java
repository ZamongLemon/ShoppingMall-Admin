package adminpage;

import java.io.File;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import adminpage.dao.admindao;
import adminpage.dao.coupon_dao;
import adminpage.dao.notice_board_dao;
import adminpage.dao.product_dao;
import adminpage.model.admin_couponinsder_model;
import adminpage.model.admin_login_model;
import adminpage.model.admin_notice_model;


@MultipartConfig(		
		fileSizeThreshold = 1024 * 1024 *1,
		maxFileSize= 1024*1024*2, 
		maxRequestSize = 1024*1024*4 
)
@Controller
public class admin_controller {

	@RequestMapping("admin/addmaster")
	public String add_master(){
		return"adminpages/add_master";
	}
	@RequestMapping("admin/")
	public String sdasdf() {

		return "adminpages/index";
	}
	@RequestMapping("admin/index.html")
	public String sdasd() {

		return "adminpages/index";
	}


	@RequestMapping("admin/sign")
	public String sdasdsf() {

		return "adminpages/add_master";
	}

	@RequestMapping("admin/search")
	public String sdasf() {

		return "adminpages/add_master_search";
	}

	@RequestMapping("admin/main")
	public String sdasdfsf() {

		return "adminpages/admin_main";
	}

	@RequestMapping("admin/config")
	public String s12f() {

		return "adminpages/admin_config";
	}

	@RequestMapping("admin/notice")
	public String s124f(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("utf-8");	
		int pn = (request.getParameter("p")==null || Integer.valueOf(request.getParameter("p"))<=0)? 1 : Integer.valueOf(request.getParameter("p"));  
		int pv = (request.getParameter("pv")==null)? 5 :  Integer.valueOf(request.getParameter("pv"));
		int view = pv;
		notice_board_dao d = new notice_board_dao();
		int maxsize = d.boardlength();
		int maxpage = (int)Math.ceil((double)maxsize/(double)view);
		if(maxpage <= pn) pn = maxpage;
		int page = (pn-1)*view;
		List<notice_board_dao> listA  = d.returnNotice();
		List<notice_board_dao> listB = d.returnNormal(page, view);
		request.setAttribute("blength", maxsize);
		request.setAttribute("listA", listA);
		request.setAttribute("listB", listB);
		request.setAttribute("pv", pv);
		request.setAttribute("pn", pn);
		return "adminpages/admin_notice";

	}
	@RequestMapping("admin/notice_write")
	public String s124df(HttpServletRequest request) throws Exception {

		return "adminpages/admin_notice_write";

	}

	@RequestMapping("admin/product")
	public String sda25f(HttpServletRequest request) throws Exception {

		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("p");
		if(page== null || Integer.valueOf(page)<=0) page = "1";
		String view = request.getParameter("pv");
		if(view== null || Integer.valueOf(view)<=0) view = "20";
		String type = request.getParameter("t");
		if(type== null || Integer.valueOf(type)<=0) type = "0";
		String word = request.getParameter("w");
		if(word==null) word = "";		
		int iview = Integer.valueOf(view);
		int ipage = Integer.valueOf(page);

		product_dao prd = new product_dao();
		int length =prd.retCount();
		double pd_maxPage=  Math.ceil((double)length/Double.valueOf(page));
		List<product_dao> pddl = prd.getProductList((ipage-1)*iview, iview, Integer.valueOf(type),word);
		request.setAttribute("productlist",pddl);
		request.setAttribute("c_maxpage", pd_maxPage);
		request.setAttribute("page", ipage);
		prd = null;
		return "adminpages/admin_product";
	}

	@RequestMapping("admin/shopping")
	public String sda11d5f(HttpServletRequest request) throws Exception {
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("p");
		if(page== null || Integer.valueOf(page)<=0) page = "1";
		String view = request.getParameter("pv");
		if(view== null || Integer.valueOf(view)<=0) view = "5";
		
		int iview = Integer.valueOf(view);
		int ipage = Integer.valueOf(page);
		coupon_dao cpd = new coupon_dao();
		int length =cpd.retCount();
		double coupon_maxPage=  Math.ceil((double)length/Double.valueOf(page));
		ArrayList<coupon_dao> cpdl = cpd.listnumb((ipage-1)*iview, iview);
		request.setAttribute("couponlist",cpdl);
		request.setAttribute("c_maxpage", coupon_maxPage);

		return "adminpages/admin_shopping";
	}

	@RequestMapping("admin/login")
	public void sdfaews(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pwr = response.getWriter();
		String id = request.getParameter("a_id");
		String pw = request.getParameter("a_pw");
		admin_login_model alm = new admin_login_model();
		String msg = null;
		switch (alm.tryadmlgn(id, pw)) {
		case success:
			HttpSession session = request.getSession();
			admindao a = new admindao();
			alm.loghis(id);

			a = a.getbyid(id, a.all_list());
			session.setAttribute("account", a);
			session.setMaxInactiveInterval(3600);
			response.sendRedirect("main");
			break;
		case fail:
			msg = "<script>alert('계정정보를 확인해주세요.');history.back();</script>";
			break;
		case n_allowed:
			msg = "<script>alert('관리자 허가가 필요합니다.');history.back();</script>";
			break;
		}
		pwr.print(msg);

	}
	
	@RequestMapping("admin/writenotice")
	public void sadfaews(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");	
		response.setContentType("text/html; charset=utf-8");
		PrintWriter pwr = response.getWriter();
		String savepath =  request.getServletContext().getRealPath("") + "admin\\upload\\";	
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
			bn_file= url.replace("/puhu17/tomcat/webapps/", "http://puhu17.cafe24.com/");		}else {
			bn_file="default";
		}
		String ont = request.getParameter(params[3]);
		int j = params.length;
		for(int i = 0 ; i < j-1; i++) {
			vals.add(request.getParameter(params[i]));
		}
		if(ont==null) vals.add("0"); else vals.add(ont);
		vals.add(2,bn_file);
		admin_notice_model nm = new admin_notice_model();
		try {
			if(nm.write_notice(vals)) {				
				response.sendRedirect("./notice");
			}else {System.out.println("fail");}
		}catch(Exception e) {
		}finally {
		}

	}
	
	@RequestMapping("admin/coupon_config")
	public String s342124df(HttpServletRequest request) throws Exception {

		return "adminpages/admin_coupon_config";

	}
	
	@RequestMapping("admin/couponinsert")
	public void aer2312(HttpServletRequest request,HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ArrayList<String> vals = new ArrayList<>();
		Part pt = request.getPart("cp_imgurl");
		String name = pt.getSubmittedFileName().intern();
		String url =  request.getServletContext().getRealPath("")+"admin\\coupon_img";
		String cp_imgurl=null;
		File f = new File(url);
		if(!f.isDirectory()) f.mkdir();
		if(name!="") {
			url = (url+"\\"+name);
			pt.write(url);
			cp_imgurl= url.replace("/puhu17/tomcat/webapps/", "http://puhu17.cafe24.com/");
			}else {
			cp_imgurl="default";
		}
		String[] params = {"cp_name","cp_type1","cp_start","cp_end","cp_type2","cp_discount","cp_minimum"};
		int j = params.length;
		for(int i = 0 ; i < j ; i++) {
			vals.add(request.getParameter(params[i]));
		}
		vals.set(2,vals.get(2) +" 00:00:00");
		vals.set(3,vals.get(3) +" 23:59:59");
				
		vals.add(cp_imgurl);
		
		admin_couponinsder_model c = new admin_couponinsder_model();
		if(c.insert_datas(vals)) 		
		response.sendRedirect("./shopping");

	}

}
