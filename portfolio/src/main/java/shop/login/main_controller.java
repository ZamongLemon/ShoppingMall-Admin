package shop.login;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.inicis.std.util.SignatureUtil;

import shop.dao.OrderDTO;
import shop.dao.ProductDTO;
import shop.dao.SessionDTO;
import shop.dao.SigninDTO;
import shop.dao.faqdao;
import shop.dao.noticedao;
import shop.model.faqmodel;
import shop.model.noticemodel;
import shop.service.OrderService;
import shop.service.OrderServiceImpl;
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

	@RequestMapping("carttest")
	public String vdsad() {
		return "carttest";
	}

	@RequestMapping({ "", "index" })
	public String sfsfdf(Model m, Integer code) {
		ProductService productService = new ProductServiceImpl(dbsource);
		if (code == null)
			code = 0;
		m.addAttribute("allObject", productService.getAll20Product());
		switch (code) {
		case 0:
			List<ProductDTO> list1 = productService.getAllProduct("0200");
			List<ProductDTO> list2 = productService.getAllProduct("0101");
			List<ProductDTO> list = new ArrayList<>();
			for (ProductDTO a : list1)
				list.add(a);
			for (ProductDTO b : list2)
				list.add(b);
			m.addAttribute("object", list);
			break;
		case 1:
			m.addAttribute("object", productService.getAllProduct("0322"));
			break;
		default:
			break;
		}
		m.addAttribute("code", code);
		productService = null;

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
	public String notice_view(Model m, String idx, HttpServletResponse resp) throws Exception {

		if (idx != null) {
			noticemodel ntcm = new noticemodel(dbsource);
			noticedao ntd = ntcm.getbyidx(idx);
			m.addAttribute("notice", ntd);
			ntcm = null;
		} else {
			return "redirect:notice";
		}
		return "noticeview";
	}

	@RequestMapping("cs")
	public String ssdsa(HttpServletRequest req) {
		String c = req.getParameter("c");
		faqmodel f = new faqmodel(dbsource);
		List<faqdao> lists = (c != null) ? f.callbycategory(Integer.valueOf(c)) : f.callbycategory(6);
		req.setAttribute("faqlist", lists);
		f = null;
		return "cs";
	}

	@RequestMapping("notice")
	public String sdfseae(HttpServletRequest req) {
		String page = req.getParameter("p");
		String type = req.getParameter("t");
		String word = req.getParameter("w");

		if (page == null || page == "")
			page = "1";
		noticemodel n = new noticemodel(dbsource);
		List<noticedao> lists = new ArrayList<>();
		if (type == null || type == "")
			type = "0";
		if (word == null)
			word = "";
		lists = n.returnnotices(Integer.valueOf(page), 5, Integer.valueOf(type), word);
		req.setAttribute("cnt", n.countbn(Integer.valueOf(page), 5, Integer.valueOf(type), word));
		req.setAttribute("type", type);
		req.setAttribute("page", Integer.valueOf(page));
		req.setAttribute("noticelist", lists);
		n = null;
		return "notice";
	}

	@RequestMapping("view")
	public String sdfsea23e(Model m, Integer no) {
		ProductService productService = new ProductServiceImpl(dbsource);
		if (no != null)
			m.addAttribute("productDTO", productService.getOne(no));
		m.addAttribute("view_delivery_cost", 3000);
		productService = null;
		return "item";
	}

	@RequestMapping("order")
	public String toOrderCart(HttpServletRequest req, @RequestParam Map<String, String> val) {
		
		String[] codecounts = val.get("codelists").split(",");
		String[] lists = new String[codecounts.length/2];
		String[] counts = new String[codecounts.length/2];
		
		for(int i = 0 ; i < lists.length; i++) {
			lists[i] = codecounts[i*2];
			counts[i] = codecounts[i*2+1];
		}

		req.setAttribute("cnts", counts);
		ProductService productService = new ProductServiceImpl(dbsource);
		List<ProductDTO> productLists = productService.getByCodes(lists);
		req.setAttribute("lists", productLists);

		req.setAttribute("codes", lists);
		productService = null;
		return "order";
	}
	
	@RequestMapping("orderSingle")
	public String orderSingle(HttpServletRequest req) {
		
		String[] lists = new String[1];
		String[] counts = new String[1];
		
		counts[0] = req.getParameter("count");
		lists[0] = req.getParameter("code");
		req.setAttribute("cnts", counts);
		req.setAttribute("codes", lists);
		
		ProductService productService = new ProductServiceImpl(dbsource);
		List<ProductDTO> productLists = productService.getByCodes(lists);
		req.setAttribute("lists", productLists);

		productService = null;
		return "order";
	}
	@RequestMapping("cancel")
	public String cancelOrder(String ord) {
		OrderService orderService = new OrderServiceImpl(dbsource);
		if(orderService.cancelOrder(ord)) {}
		
		orderService = null;
		
		return "redirect:index";
	}

	@RequestMapping(value = "complet", method = RequestMethod.POST)
	public String payment(Model m, @RequestParam Map<String, String> vals, HttpServletRequest req) throws Exception {
		/*
		 * ????????? ?????? ????????????, ???????????????('-'??????), ???????????????, ?????????, ????????????, ?????? ????????????
		 * 
		 * [????????????] ???????????? - CARD(????????????),Directbank(????????????) ????????????, ??????(?????????)- ?????????(?????????)
		 * 
		 */
		String buyerName = vals.get("buyerName");
		String buyerPhone = vals.get("buyerPhone0")+vals.get("buyerPhone1")+vals.get("buyerPhone2");
		String buyerEmail = vals.get("buyerEmail");
		String receiverName = vals.get("receiverName");
		String receiverPostcode = vals.get("receiverpostcode");
		String receiverStreetAddress = vals.get("receiverStreetAddress");
		String receiverAddressDetail = vals.get("receiverAddressDetail");
		String receiverPhone = vals.get("receiverPhone0")+vals.get("receiverPhone1")+vals.get("receiverPhone2");
		String receiverEmail = vals.get("receiverEmail");
		String codelist = vals.get("listCodes");
		String countlist = vals.get("counts");
		String payment = vals.get("payment");
		String memo = (vals.get("memo")==null)? "" : vals.get("memo");
		String depositor = (vals.get("depositor")==null)? "" : vals.get("depositor");
		String bank = (vals.get("bank")==null)? "" : vals.get("bank");
		String typeReceiptUse = (vals.get("typereceiptuse")==null)? "0" : vals.get("typereceiptuse");
		String orderNum = "";
		Integer sPrice = Integer.valueOf(vals.get("sPrice"));
		Integer nPrice = Integer.valueOf(vals.get("nPrice"));
		Random rand = new Random();
		for(int i = 0 ; i < 9 ; i++) {	
			orderNum+= rand.nextInt(10);}		
		LocalDateTime ldt = LocalDateTime.now() ;
		String date = ldt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		orderNum+=ldt.getSecond();
		orderNum = orderNum.substring(0,10);
		
		vals.put("orderNum", orderNum);
		vals.put("orderedDate", date);
		OrderDTO orderDTO = OrderDTO.builder().orderNum(orderNum).orderDate(date).buyerName(buyerName).buyerEmail(buyerEmail).buyerPhone(buyerPhone)
				.receiverName(receiverName).receiverPostcode(receiverPostcode).receiverStreetAddress(receiverStreetAddress).receiverAddressDetail(receiverAddressDetail).receiverPhone(receiverPhone).receiverEmail(receiverEmail).codeList(codelist).countList(countlist)
				.payment(payment).memo(memo).depositor(depositor).bank(bank).sPrice(sPrice).nPrice(nPrice).typeReceiptUse(typeReceiptUse).build();
		
		OrderService orderService = new OrderServiceImpl(dbsource);
		orderService.inputOrderDTO(orderDTO);
		orderService = null;
		String[] lists = vals.get("listCodes").substring(1, vals.get("listCodes").length() - 1).split(", ");
		String[] counts = vals.get("counts").substring(1, vals.get("counts").length() - 1).split(", ");
		req.setAttribute("cnts", counts);
		ProductService productService = new ProductServiceImpl(dbsource);
		List<ProductDTO> productLists = productService.getByCodes(lists);
		
		
		req.setAttribute("lists", productLists);
		req.setAttribute("orderDetail", orderDTO);
		String mid = "INIpayTest"; // ???????????????
		String signKey = "SU5JTElURV9UUklQTEVERVNfS0VZU1RS"; // ??? ?????? signkey

		String mKey = SignatureUtil.hash(signKey, "SHA-256");

		String timestamp = SignatureUtil.getTimestamp(); // util??? ????????? ????????????
		String orderNumber = mid + "_" + SignatureUtil.getTimestamp(); // ????????? ????????????(??????????????? ?????? ??????)
		String price = sPrice.toString(); // ????????????(???????????? ??????, ??????????????? ?????? ??????)

		Map<String, String> signParam = new HashMap<String, String>();
		Map<String,String> anotherValue = new HashMap<String, String>();
		anotherValue.put("mKey", mKey);
		anotherValue.put("signature", signKey);
		anotherValue.put("mid", mid);
		signParam.put("oid", orderNumber);
		signParam.put("price", price);
		signParam.put("timestamp", timestamp);
		req.setAttribute("vals", vals);
		String signature = SignatureUtil.makeSignature(signParam);
		String person_post = req.getParameter("person_post");
		req.setAttribute("signParam", signParam);
		req.setAttribute("anotherValue", anotherValue);
		req.setAttribute("signature", signature);
		return "complet";
	}

	@RequestMapping("trylgn")
	public String trylgn(String id, String password, HttpServletResponse resp, HttpServletRequest req)
			throws Exception {
		SessionService sessionService = new SessionServiceImpl();
		SessionDTO sessionDTO = sessionService.getSessionData(id, password, dbsource);

		if (sessionDTO != null) {
			HttpSession session = req.getSession();
			session.setAttribute("normalaccount", sessionDTO);
			session.setMaxInactiveInterval(3600);
			resp.sendRedirect("index");
		}
		sessionService = null;
		resp.getWriter().print("<script>alert('????????? ????????? ???????????????.');history.back();</script>");

		return null;
	}
	
}
