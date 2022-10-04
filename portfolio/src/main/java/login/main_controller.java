package login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main_controller {
		
		@RequestMapping("test")
		public String sfsfd() {
			return "test2";
		}
		
		@RequestMapping("")
		public String sfsfdf() {
			return "index";
		}
		
		@RequestMapping("company")
		public String sdfs() {
			return "company";
		}

}
