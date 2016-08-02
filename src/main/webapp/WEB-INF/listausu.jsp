
<%@page
	import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<style>
   #tbl{
      margin: 70px 0 0 530px;
      
   }
   
   #btn {
      margin: 10px 0 0 631px;
      
   }
   
   a {
     color: #000;
      font-family:Rockwell;
   }
</style>

<!-- script pra confirmar exclusao -->

<script type="text/javascript">
	function confirmaExclusao(id) {
		if (window.confirm("Tem certeza que deseja excluir?")) {
			location.href = "usucontroller.do?acao=excluir&id=" + id;
		}
	}
	
    function novo() {
		location.href = 'usucontroller.do?acao=cadastrar'
	}

</script>
</head>
<body>
     <%@include file="menu.jsp"%>
	<!-- < % = atalho:substitui a açao java anterior -->
	<%
		List<Usuario> lista = (List<Usuario>) request.getAttribute("lista");
	%>
	<table border="1px" cellpadding = "6px" cellspacing="0" id="tbl">
		<tr>
			<th>ID</th>
			<th>Nome</th>
			<th>Ação</th>
		</tr>
		<%
			for (Usuario u : lista) {
		%>
		<tr>
			<td>
				<%
					out.print(u.getId());
				%>
			</td>
			<td><%=u.getNome()%></td>
			<td><a href="javascript:confirmaExclusao(<%=u.getId()%>)">
					excluir </a> | <a href="usucontroller.do?acao=alterar&id=<%=u.getId()%>">alterar
			</a></td>
		</tr>

		<%
			}
		%>
	</table>
	<input type="button" value="Novo" id="btn" onclick="javascript:novo()">
</body>
</html>
