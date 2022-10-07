package adminpage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class admin_controller {
	
	@RequestMapping("admin")
	public String sdasdf() {
		
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
	public String s124f() {
		
		return "adminpages/admin_notice";
	}
	
	@RequestMapping("admin/product")
	public String sda25f() {
		
		return "adminpages/admin_product";
	}
	
	@RequestMapping("admin/shopping")
	public String sda11d5f() {
		
		return "adminpages/admin_shopping";
	}
}
