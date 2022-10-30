<%@page import="adminpage.dao.notice_board_dao"%>

<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
 List<notice_board_dao> listnotice = (List<notice_board_dao>)request.getAttribute("listA");
 List<notice_board_dao> listnormal = (List<notice_board_dao>)request.getAttribute("listB");
 int whole = (int)request.getAttribute("blength");
 int pn = (int)request.getAttribute("pn");
 int pv = (int)request.getAttribute("pv");
%>
<script>
 
<% int j = listnotice.size(); 
 for(int p = 0 ; p < j ; p++){%>

	var d = document.getElementById("notice_table");
	var o = document.createElement("ol");
	var l = [];
	for(var i = 0 ; i < 6 ; i ++){
		l[i] = document.createElement("li");
		o.append(l[i]);
	}

	l[0].innerHTML="<input type='checkbox' name='cbox' id='<%=listnotice.get(p).return_values(0)%>' >";
	l[1].innerText="<%=p+1%>";
	l[2].innerHTML="<a href='javascript:noticemodify(<%=listnotice.get(p).return_values(0)%>)'><div><%=listnotice.get(p).return_values(1)%></div></a>";
	l[3].innerText="<%=listnotice.get(p).return_values(2)%>";
	l[4].innerText="<%=listnotice.get(p).return_values(5).substring(0,19)%>";
	l[5].innerText="<%=listnotice.get(p).return_values(7)%>";
	
	d.append(o);
	
<%}
int n = listnormal.size();
for(int m = 0 ; m < n ; m++){%>
	
	var d = document.getElementById("notice_table");
	var o = document.createElement("ol");
	var l = [];
	for(var i = 0 ; i < 6 ; i ++){
		l[i] = document.createElement("li");
		o.append(l[i]);
	}
	
	l[0].innerHTML="<input type='checkbox' name='cbox' id='<%=listnormal.get(m).return_values(0)%>'>";
	l[1].innerText="<%=whole - m - (pn - 1) * pv%>";
	l[2].innerHTML="<a href='javascript:noticemodify(<%=listnormal.get(m).return_values(0)%>)'><div><%=listnormal.get(m).return_values(1)%></div></a>";
	l[3].innerText="<%=listnormal.get(m).return_values(2)%>";
	l[4].innerText="<%=listnormal.get(m).return_values(5).substring(0, 19)%>";
	l[5].innerText="<%=listnormal.get(m).return_values(7)%>";
	
	d.append(o);
<%}%>



var currentpage = <%=request.getAttribute("pn")%>
var endp = Math.ceil(<%=(double) whole%>/<%=(double) pv%>);
var loop = 0;
	if( Math.floor(endp/10) <= Math.floor(currentpage/10)){
		loop = Math.floor((endp/10 - Math.floor(endp/10))*10);
	}else{
		loop = 10;
	}
	var fl ;
	
	if(Math.ceil(currentpage/10)==Math.ceil(endp/10)) fl = Math.floor(endp%10);
	else fl = 10;
	
	const a = document.getElementById("pagemove");
	const np = document.getElementById("nxpage");
	for(var i = 0 ; i < fl ; i++){
		var b = document.createElement("li");
		var pn = Math.floor(currentpage/10)*10+(i+1);
		b.innerText=pn;
		b.className = "movepage";
		a.insertBefore(b,np);
	}
	 $(".movepage").click(function(){
		 location.href="./notice?p="+this.innerText;		 
	 })
	
	 $("#tofirst").click(function(){
		 
		 	if(window.location.search!=""){		 		
		 	var empty = get_page(window.location.href);
			var a = empty.split("p=");
			var newstr = a[0]+"p=1"+a[1];
			location.href=newstr;
		 	}else{
		 	location.href=window.location.href+"?p=1";	
		 	}
	 });
	 $("#toend").click(function(){
		 
		 if(window.location.search!=""){	
		 var empty = get_page(window.location.href);
		 var a = empty.split("p=");
		 var newstr = a[0]+"p="+endp+a[1];
		 location.href=newstr;
		 }else{
			 location.href=window.location.href+"?p="+endp;
		 }
	 });
	$("#prpage").click(function(){
		var newpage = Math.floor((currentpage-10)/10) * 10+1;
		if(newpage <=0) newpage = 1;
		else if(newpage >= endp) newpage = Math.floor((endp-10)/10)*10+1;
		var empty = get_page(window.location.href);
		var a = empty.split("p=");
		var newstr = a[0]+"p="+newpage+a[1];
		location.href= newstr;
	});
	$("#nxpage").click(function(){
		var newpage = (Math.floor((currentpage+10)/10))*10+1;
		if (newpage >= endp) newpage = (Math.floor(endp/10))*10+1;		
		var empty = get_page(window.location.href);
		var a = empty.split("p=");
		var newstr = a[0]+"p="+newpage+a[1];
 		location.href= newstr;
	});
	
	noticemodify = (idx) =>{
		location.href="noticemodify?idx="+idx;
	}
	 function get_page(loc){
		 var h,p,ps;
		 if(loc.indexOf("?")>0){			 
		  h = loc.split("?")[0];
		  p = loc.split("?")[1];
		  ps = p.split(/=|&/);
		 }
		 
 		 for(var pf = 0 ; pf < ps.length; pf++){
 			 if(ps[pf] == "p"){
 				 ps[pf+1]=""; break;
 			 }
 		 }
 		 var newps="?";
 		 for(var psf = 0 ; psf < ps.length; psf++){
  			newps+= (psf%2==0)? (ps[psf]+"=") : (ps[psf]+"&");
 		 }
 		 return (h+newps.substring(0,newps.length-1));
	 }
	 
	 function del_notice(){
		var k = document.getElementsByName("cbox");
		var n = [];		
		var cnt = 0;
		for(var i = 0 ; i < k.length; i++){
			if(k[i].checked){				
			n[cnt] = k[i].id;			
			cnt++;
			}
		}
		if(n.length!=0 && confirm("물품을 삭제하시겠습니까? (데이터는 복구 불가능합니다.)")){
			$.ajax({
			    url: "del_notice", 
			    data: { "key": n.toString()},                        
			    method: "post",                      
			    dataType: "text",
			    success: function(data){
					location.reload();
				},error: console.log("error")
				});
		}
	}


</script>
