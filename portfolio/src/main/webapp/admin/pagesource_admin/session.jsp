<%@page import="adminpage.dao.admindao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true"%>

<%	admindao acc = (admindao)session.getAttribute("account");
	if(acc==null) response.sendRedirect("./index.html");
	
%>

