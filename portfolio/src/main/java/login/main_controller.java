package login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
			JdbcTemplate jdbct = new JdbcTemplate(dbsource);
			String sql = "select count(*) as c from faqtable where faq_type='"+(Integer.valueOf(c)-1)+"'";
			List<Integer> res = jdbct.query(sql, new RowMapper<Integer>() {
				@Override
				public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getInt(1);
			}
			});
			System.out.println(res);
			return "cs";
		}
		
}
