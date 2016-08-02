<%@page
	import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
 h1{
  text-align:center;
  font-family:Rockwell;
    left: 46%; 
    margin: -130px 0 0 -210px; 
    padding:10px;
    position: absolute; 
    top: 50%;
 }
</style>
</head>
<body>
    
	<%@include file="menu.jsp"%>
	
	<h1>Seja Bem vindo! <br> Cadastre ou altere seus dados!</h1>
	<%
		Usuario usuario = (Usuario)session.getAttribute("usuAutenticado");
	%>
	
</body>
</html>