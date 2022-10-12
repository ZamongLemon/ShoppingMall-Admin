<%@page import="adminpage.dao.category_dao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@include file="./pagesource_admin/session.jsp"%>
     <% ArrayList<category_dao> cpdl = (ArrayList<category_dao>)request.getAttribute("categorylist");
     	double maxpage =  (double)request.getAttribute("c_maxpage");%>
     	
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
    <link rel="stylesheet" type="text/css" href="./css/category.css?v=5">
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
<%@include file="./pagesource_admin/cate_list.html"%>
</section>
</main>
<footer class="main_copyright">
<%@include file="./pagesource_admin/admin_footer.html"%>
</footer>
</body>
<script>
	function mvcategorywrite(){
		location.href="./writeproductcode.do";
	}
	function mvproductlist(){
		location.href="./productlist.do?p=1";
	}
	function modifycate(idx,isuse){
		
		$.ajax({

			url: "./ntoy.do",
			dataType:"text",
			data: {"idx":idx,"isuse":isuse}, 
			method:"get",
			success: function(data) {
			if(data=="true"){
				location.reload();
			}else{
				alert("수정실패");
			}
			},
			error:function(){
				console.log("error");
			}
		});
	}
	$(function(){
		$("#checkall").change(function(){
			var check = document.getElementsByName("delcheck");
			if(this.checked){
				check.forEach(function(j){ j.checked=true;});
			}else{
				check.forEach(function(k){ k.checked=false;});
			}
			
		});
		
		$("#delcategory").click(function(){
			var check = document.getElementsByName("delcheck");
			var vals="";
			check.forEach(function (a){
				if(a.checked){vals += a.id+",";}					
			});
			
			console.log(vals);
				$.ajax({
				url: "./delcate.do",
				dataType:"text",
				data: {"ids":vals}, 
				method:"get",
				success: function(data) {
				if(data=="true"){
					location.reload();
				}else{
					alert("수정실패");
				}
				},
				error:function(){
					console.log("error");
				}
				});
		});
	});
</script>
</html>