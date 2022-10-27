package adminpage.model;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;



@MultipartConfig(
		
		fileSizeThreshold = 1024 * 1024 *1,
		maxFileSize= 1024*1024*2, 
		maxRequestSize = 1024*1024*4 
	)
public class fileupload{
	public String upload(Part part,String path,String index) throws Exception {
		String p = part.getSubmittedFileName().intern();
		if(p=="") return "null";
		else {
			p = p.substring(p.lastIndexOf(".")+1);
			LocalDateTime ldt = LocalDateTime.now();
			ldt.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSSS"));
			String dt = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date());
			p = dt+"upd"+index+"."+p;
			File d = new File(path);
			if(!d.isDirectory()) d.mkdir();
			String url = (path+p);
			part.write(url);
			
			return url.replace("/puhu17/tomcat/webapps/", "http://puhu17.cafe24.com/");
		}
		
	}
}
