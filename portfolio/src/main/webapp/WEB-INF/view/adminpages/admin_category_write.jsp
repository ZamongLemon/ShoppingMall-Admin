<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="./pagesource_admin/session.jsp"%>
<!DOCTYPE html>

<%
	ArrayList<String> ops = (ArrayList<String>)request.getAttribute("codes");
 %>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>쇼핑몰 환경설정</title>
    <link rel="stylesheet" type="text/css" href="./css/basic.css">
    <link rel="stylesheet" type="text/css" href="./css/login.css?v=1">
    <link rel="stylesheet" type="text/css" href="./css/main.css">
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=2">
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
<form name="f" id="f" >
<%@include file="./pagesource_admin/cate_write.html"%>
</form>
</section>
</main>
<footer class="main_copyright">
<%@include file="./pagesource_admin/admin_footer.html"%>
</footer>
</body>
<script>
	function mvcategorylist(){
		location.href="./categorylist.do?p=1";
	}
	
	var k = document.getElementById("lg_menu");
	
	<%int j = ops.size();
	for(int i = 0 ; i < j ; i++){%>
	var p = document.createElement("option");
	p.innerText='<%=ops.get(i)%>';
	k.append(p);
	<%}%>
	function sum(){
		f.code.value= f.largecode.value + f.smallcode.value; 
		callsmall();
	}

	
	function catesubmit(){
		f.method="post";
		f.enctype="application/x-www-form-urlencoded";
		f.action="./catewrite.do";
		f.submit();
	}
</script>

</html>