<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./pagesource_admin/session.jsp" %>
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
    <script src="./jq/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="./jq/jquery-ui.css">
</head>
<body>
<header class="headercss">
<%@include file="./pagesource_admin/admin_header.html"%>
</header>
<nav class="navcss">
<%@include file="./pagesource_admin/admin_menu.html"%>
</nav>
<main class="maincss">
<section style="height: 85vh;">
<form name="f" id="f">
<%@include file="./pagesource_admin/coupon_insert.html" %> 
</form>
</section>
</main>
<footer class="main_copyright">
<%@include file="./pagesource_admin/admin_footer.html"%>
</footer>
</body>
<script>
	$(function(){
		$("#cp_start").datepicker({});
		$("#cp_end").datepicker({});
		
		$("#cp_type2").change(function(){
			var cp_val = $("#cp_type2").val();
			if(cp_val == 1){
				$("#cp_discount").val(1000);
			}else if(cp_val==2){
				$("#cp_discount").val(0);
			}			
		});
		$("#cp_discount").keypress(function(event){
			if(event.which>=48 && event.which<=57){
				var type2_val = $("#cp_type2").val();
				var discount_val = $("#cp_discount").val();
				if(type2_val== 2 && discount_val >100){
					$("#cp_discount").val(100);				
					event.preventDefault();
				}
			}
		});
	});
	function postCouponDetails(){
		f.enctype="multipart/form-data";
		f.method="post";
		f.action="./couponinsert";
		f.submit();
	}
	
	function couponlists(){
		location.href="./shopping";
	}
	
</script>
</html>