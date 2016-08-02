package br.com.fabricadeprogramador.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

@WebServlet("/autenticador.do")
public class AutenticadorServlet extends HttpServlet {

	// encerra sessão

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// entro na sessão
		// esse false significa que
		// caso não exista uma
		// sessão,não sera
		// criada outra
		HttpSession session = req.getSession(false); 
        
		if (session != null) {
			//se existe sessao,invalida
			session.invalidate();
		}
		
		resp.sendRedirect("login.html");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);

		UsuarioDAO uDao = new UsuarioDAO();
		Usuario usuAutenticado = uDao.autenticar(usuario);

		// criando a sessão
		if (usuAutenticado != null) {
			// o getSession ou recupera uma sessão criada ou cria uma nova
			HttpSession session = req.getSession();
			// posso pendurar varias coisas na section
			session.setAttribute("usu", usuAutenticado);
			// duraçao da sessão.tempo em segundos
			session.setMaxInactiveInterval(1000);

			req.getRequestDispatcher("WEB-INF/index.jsp").forward(req, resp);
		} else {
			resp.getWriter()
					.print("<script> alert('Usuário não encontrado!'); " + "location.href='login.html' </script>");
		}
	}

}
