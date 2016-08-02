<%@page
	import="br.com.fabricadeprogramador.persistencia.entidade.Usuario"%>
<!DOCTYPE html>
<!-- criando um formulario pro POST -->
<html>
<head>
<meta charset="ISO-8859-1">
<style type="text/css">
#fm {
	left: 50%;
	margin: -130px 0 0 -210px;
	padding: 10px;
	position: absolute;
	top: 50%;
}

fieldset {
	width: 300px;
	height: 200px;
	background-color: #FAFAD2
}

legend {
	text-align: center;
	color: #FF4500;
	font-size: 20px;
	font-family: Rockwell;
}
</style>
</head>
<body>
	<%
		Usuario u = (Usuario) request.getAttribute("usu");
	%>
	<form method="post" action="usucontroller.do" id="fm">
		<!-- aqui no form é onde vamos processar a requisiçao.action é quem vai receber a requisiçao -->
		<fieldset>
			<legend>Cadastre-se</legend>
			ID: &nbsp &nbsp &nbsp <input type="number" name="id"
				value="<%=u.getId()%>"> <br>
			<br> Nome: <input type="text" name="n" value="<%=u.getNome()%>">
			<br>
			<br> Login: <input type="text" name="login"
				value="<%=u.getLogin()%>"> <br>
			<br> Senha: <input type="password" name="senha"
				value="<%=u.getSenha()%>"> <br>
			<br> <input type="submit" value="SALVAR">
			<a href="usucontroller.do?acao=listar"><input type="button" value="VOLTAR"> </a>
		</fieldset>
		<br>
		<br>


	</form>

</body>
</html>