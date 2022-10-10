<%@page import="adminpage.dao.coupon_dao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./pagesource_admin/session.jsp"%>
<% ArrayList<coupon_dao> cpd = (ArrayList<coupon_dao>)request.getAttribute("couponlist");%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 환경설정</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/shipping.css?v=1">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./jq/jquery.js"></script>
</head>
<body>
<header class="headercss">
<%@include file="./pagesource_admin/admin_header.html"%>
</header>
<nav class="navcss">
<%@include file="./pagesource_admin/admin_menu.html"%>
</nav>
<main class="maincss">
<section style="height: auto;">
 <%@include file="./pagesource_admin/shopping_list.html"%>   
</section>
</main>

<footer class="main_copyright">
<%@include file="./pagesource_admin/admin_footer.html"%>
</footer>
</body>
<script>
	function makeCoupon(){
		location.href="./admin_coupon_config.jsp";
	}
	
	function makeCouponLists(){
		
		var head = $("#cp_listspan");
		console.log(head);
		<% int j = cpd.size();
		for(int i = 0 ; i < j ; i++ ){%>
			var ol = document.createElement("ol");
			ol.className="coupon_lists";
			var li = [];
			for(var k = 0 ; k < 8; k++){
				li[k] = document.createElement("li");
				ol.append(li[k]);
			}
			li[0].innerText="<%=cpd.get(i).g_idx()%>";
			li[1].innerText="<%=cpd.get(i).g_name()%>";
			li[2].innerText=<%if(cpd.get(i).g_type1()=="1"){%>
				"상품할인";
			<%}else {
			%> "배송할인";
			<%}%>
			li[3].innerText="<%=cpd.get(i).g_start().substring(0,10)%>";
			li[4].innerText="<%=cpd.get(i).g_end().substring(0,10)%>";
			li[5].innerText=<%if(cpd.get(i).g_type2().intern()=="1"){%>	"정액할인";
			<%}else if(cpd.get(i).g_type2().intern()=="2") {%> "정률할인";<%}%>
			li[6].innerText=<%if(cpd.get(i).g_type2().intern()=="1"){%>	"<%=cpd.get(i).g_discount()%>";
			<%}else if(cpd.get(i).g_type2().intern()=="2") {%>"<%=cpd.get(i).g_discount()%>%";
			<%}%>
			li[7].innerText=<%=cpd.get(i).g_minimum()%>;
			head.append(ol);
		<%}%>
	}
	<%if(cpd.size()!=0){%>
	 makeCouponLists();
	<%}%>
</script>
</html>