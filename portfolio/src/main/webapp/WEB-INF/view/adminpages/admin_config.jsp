<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="./pagesource_admin/session.jsp"%>
 
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
    <link rel="stylesheet" type="text/css" href="./css/subpage.css?v=3">
    <link rel="icon" href="./img/logo.png" sizes="128x128">
    <link rel="icon" href="./img/logo.png" sizes="64x64">
    <link rel="icon" href="./img/logo.png" sizes="32x32">
    <link rel="icon" href="./img/logo.png" sizes="16x16">
    <script src="./jq/jquery.js"></script>
    <script src="./fns/configfs.js"></script>
</head>
<body>
<header class="headercss">
<%@include file="./pagesource_admin/admin_header.html"%>
</header>
<nav class="navcss">
<%@include file="./pagesource_admin/admin_menu.html"%>

</nav>

	
<%if(acc!=null){%>
<form id="frm" name="frm">
<main class="maincss">
<section style="height: auto;">
<%@include file="./pagesource_admin/admin_siteinfo.html"%>
</section>
</main>
</form>
<%}%>
<footer class="main_copyright">
<%@include file="./pagesource_admin/admin_footer.html"%>
</footer>
</body>
</html>