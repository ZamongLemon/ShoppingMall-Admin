package login;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dao.faqdao;
import dao.noticedao;
import model.faqmodel;
import model.noticemodel;

@Controller
public class main_controller {
		
	@Autowired
	BasicDataSource dbsource;
		@RequestMapping("")
		public String sfsfdf() {
			return "index";
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
		@RequestMapping("cs")
		public String ssdsa(HttpServletRequest req) {
			String c = req.getParameter("c");
			faqmodel f = new faqmodel(dbsource);
			List<faqdao> lists = new ArrayList<faqdao>();
			if(c!=null)
			lists = f.callbycategory(Integer.valueOf(c));
			else lists = f.callbycategory(6);
			req.setAttribute("faqlist", lists);
			return "cs";
		}
		
		@RequestMapping("notice")
		public String sdfseae(HttpServletRequest req) {
			String page = req.getParameter("p");
			noticemodel n = new noticemodel(dbsource);
			List<noticedao> lists = new ArrayList<>();
			if(page!=null && Integer.valueOf(page) > 0) {
				lists = n.returnnotices(5*(Integer.valueOf(page)-1),5);				
			}else {
				lists = n.returnnotices(0, 5);
			}
			req.setAttribute("noticelist", lists);
			return "notice";
		}
		
}
