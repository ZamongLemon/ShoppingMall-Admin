<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./pagesource_admin/session.jsp"%>
<% ArrayList<String> lc = (ArrayList<String>)request.getAttribute("largecode"); %>
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
    <link rel="stylesheet" type="text/css" href="./css/product.css?v=5">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./jq/jquery.js"></script>
    <script src="./ckeditor/ckeditor.js"></script>
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
<form name="f" id="f">
<%@include file="./pagesource_admin/product_write.html"%>
</form>
</section>
</main>
<footer class="main_copyright">
<%@include file="./pagesource_admin/admin_footer.html"%>
</footer>
</body>
<script>
 var largecodes = document.getElementById("lg_menu");
 	<% for(int i =  0 ; i < lc.size();i++){%>
	var p = document.createElement("option");
	p.innerText='<%=lc.get(i)%>';
	p.value='<%=lc.get(i)%>';
	largecodes.append(p);
 	<%}%>
 	$(function(){
	 	var sm = $("#lg_menu");
 		$("#lg_menu").change(function(){aj();});
 		aj = function(){			
 			$.ajax({
 				type: "GET", 
 				url:"./smallajax.do",
 				data:{"key":sm.val()},
 				dataType:"text", 
 				success : function(result){
 					var str = result.slice(1,-1).split(", ");
 					var sm = $("#sm_menu");
 					sm.empty();
 					console.log(str);
 					if(str==""){
 						sm.append('<option value="00">00</option>');
 					}else{	
 					for(const a of str) sm.append('<option value="'+a+'">'+a+'</option>');
 					}
 					setpcode();
 					 					
 				},
 				error : function(a, b, c){
 					console.log(a + b + c);
 				}
 			});		
 		};
 		aj();
 		$("#sm_menu").change(function(){setpcode();});
 		function setpcode(){
 			var ctext = $("#category_code"); 			
 			ctext.val($("#lg_menu").val()+$("#sm_menu").val());
 			
 		}
 		
 		$("#code_button").click(function(){
 			var wholecode = $("#category_code").val()+$("#product_code").val();
 			$.ajax({
 				type: "GET", 
 				url:"./poverlapcheck.do",
 				data:{"code":wholecode},
 				dataType:"text", 
 				success : function(result){
 					console.log(result);
 					if(result==0){
 						$("#lg_menu").attr("disabled",true);
 						$("#sm_menu").attr("disabled",true);
 						$("#product_code").attr("readonly",true);
 						$("#code_button").attr("disabled",true);
 						alert("생성가능합니다.");
 					}else{alert("생성불가능합니다.");}
 				},
 				error : function(a, b, c){
 				}
 			});		
 			
 		});
 		
 		
 		$("#pdd_nprice").keyup(function(){
 			$("#pdd_sprice").val(Math.ceil($("#pdd_nprice").val()*(100-$("#pdd_saleper").val())/100)); 			
 		});
 		
 		$("#pdd_saleper").keyup(function(){
 			$("#pdd_sprice").val(Math.ceil($("#pdd_nprice").val()*(100-$("#pdd_saleper").val())/100));
 		});
  	});
 	
 	function submproduct(){
 		f.pdd_code.value = $("#category_code").val()+$("#product_code").val();
 		
 		f.enctype="multipart/form-data";
 		f.method="post";
 		f.action="./product_insert.do";
 		f.submit();
 	}
 	
 	
</script>
</html>