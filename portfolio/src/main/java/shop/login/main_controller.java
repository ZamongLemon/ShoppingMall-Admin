package shop.login;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.inicis.std.util.SignatureUtil;

import shop.dao.SessionDTO;
import shop.dao.SigninDTO;
import shop.dao.faqdao;
import shop.dao.noticedao;
import shop.model.faqmodel;
import shop.model.noticemodel;
import shop.service.ProductService;
import shop.service.ProductServiceImpl;
import shop.service.SessionService;
import shop.service.SessionServiceImpl;
import shop.service.SigninService;
import shop.service.SigninServiceImpl;

@Controller
public class main_controller {

	@Autowired
	BasicDataSource dbsource;

	@RequestMapping({"","index"})
	public String sfsfdf(Model m) {
		ProductService productService = new ProductServiceImpl(dbsource);
		m.addAttribute("draw", productService.getAllProduct("0101"));
		m.addAttribute("sofa", productService.getAllProduct("0200"));
		m.addAttribute("bed", productService.getAllProduct("0322"));
		
		
		
		
		return "index";
	}

	@RequestMapping("member")
	public String member() {
		return "member";
	}
	@RequestMapping("member_login")
	public String member_login() {
		return "member_login";
	}
	@RequestMapping("ordercart")
	public String ordercart() {
		return "ordercart";
	}
	@RequestMapping("company")
	public String sdfs() {
		return "company";
	}

	@RequestMapping("agreement")
	public String qwefdx() {
		return "agreement";
	}

	@RequestMapping("guide")
	public String qwefsfdx() {
		return "guide";
	}

	@RequestMapping("privacy")
	public String qwefsdfdx() {
		return "privacy";
	}

	@RequestMapping("noticeview")
	public String notice_view(Model m, String idx, HttpServletResponse resp) throws Exception{
		
		if(idx!=null) {			
		noticemodel ntcm = new noticemodel(dbsource);
		noticedao ntd = ntcm.getbyidx(idx);
		m.addAttribute("notice",ntd);
		}else {
			return "redirect:notice";
		}
		
		return"noticeview";
	}
	@RequestMapping("cs")
	public String ssdsa(HttpServletRequest req) {
		String c = req.getParameter("c");
		faqmodel f = new faqmodel(dbsource);
		List<faqdao> lists = new ArrayList<faqdao>();
		if (c != null)
			lists = f.callbycategory(Integer.valueOf(c));
		else
			lists = f.callbycategory(6);
		req.setAttribute("faqlist", lists);
		return "cs";
	}

	@RequestMapping("notice")
	public String sdfseae(HttpServletRequest req) {
		String page = req.getParameter("p");
		String type = req.getParameter("t");
		String word = req.getParameter("w");

		if (page == null || page == "")	page = "1";
		noticemodel n = new noticemodel(dbsource);
		List<noticedao> lists = new ArrayList<>();
		if (type == null || type == "")	type = "0";
		if (word ==null) word = "";
		lists = n.returnnotices(Integer.valueOf(page), 5, Integer.valueOf(type), word);
		req.setAttribute("cnt", n.countbn(Integer.valueOf(page), 5, Integer.valueOf(type), word));
		req.setAttribute("type", type);
		req.setAttribute("page", Integer.valueOf(page));
		req.setAttribute("noticelist", lists);
		return "notice";
	}

	@RequestMapping("items")
	public String sdfsea23e(HttpServletRequest req) {
		return "item";
	}

	@RequestMapping("complet")
	public String toComplete(HttpServletRequest req) {
		return "complet";
	}

	@RequestMapping("order")
	public String toOrderCart(HttpServletRequest req) {
		return "order";
	}
	
		@RequestMapping(value="complet", method = RequestMethod.POST)
		public String payment(Model m , String cname,String chp,HttpServletRequest req) throws Exception{
			/*
			 * 결제자 정보
			 * 결제자명, 휴대폰번호('-'빼고), 이메일주소, 상품명, 주문번호, 최종 결제금액
			 * 
			 * [이니시스]
			 * 결제수단 - CARD(신용카드),Directbank(계좌이체)
			 * 상품갯수, 회원(아이디)- 비회원(휴대폰)
			 * 
			 */
		String mid					= "INIpayTest";		                    // 상점아이디					
		String signKey			    = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS";	// 웹 결제 signkey
		
		String mKey = SignatureUtil.hash(signKey, "SHA-256");

		String timestamp			= SignatureUtil.getTimestamp();			// util에 의해서 자동생성
		String orderNumber			= mid+"_"+SignatureUtil.getTimestamp();	// 가맹점 주문번호(가맹점에서 직접 설정)
		String price				= "1000";								// 상품가격(특수기호 제외, 가맹점에서 직접 설정)


		Map<String, String> signParam = new HashMap<String, String>();

		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);

		String signature = SignatureUtil.makeSignature(signParam);
		String person_post = req.getParameter("person_post");
		
			return "complet";
		}
		
		
		@RequestMapping("trylgn")
		public String trylgn(String id, String password,HttpServletResponse resp, HttpServletRequest req) throws Exception{
			SessionService sessionService = new SessionServiceImpl();
			SessionDTO sessionDTO = sessionService.getSessionData(id,password,dbsource);

			if(sessionDTO !=null) {
				HttpSession session = req.getSession();
				session.setAttribute("normalaccount",sessionDTO);
				session.setMaxInactiveInterval(3600);
				resp.sendRedirect("index");
			}
			resp.getWriter().print("<script>alert('로그인 정보를 확인하세요.');history.back();</script>");
			return null;
		}
}

		

