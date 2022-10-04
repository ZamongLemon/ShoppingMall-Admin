package login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class main_controller {
		
		@RequestMapping("")
		public String sfsfdf() {
			return "index";
		}
		
		@RequestMapping("company")
		public String sdfs() {
			return "company";
		}

}
