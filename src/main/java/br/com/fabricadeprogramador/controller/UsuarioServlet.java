package br.com.fabricadeprogramador.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

//.do é uma convençao,como se fosse um arquivo.se eu digitar o endereço abaixo,o mapeamento ira localizar essa classe
//http://localhost:8080/fabricaweb/usucontroller.do

@WebServlet("/usucontroller.do")
public class UsuarioServlet extends HttpServlet {

	public UsuarioServlet() {
		// o servlet é instanciado apenas uma vez.cada nova requisiçao usara
		// esse servlet
		System.out.println("Construtor");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("Init...");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		/*
		 * //passo os parametros que quero alterar no banco na url da requisiçao
		 * //os parametros começam depois de "?" e são separados pelo caractere
		 * "&".é chamado query string //são no formato chave=valor // ex:
		 * http://localhost:8080/fabricaweb/usucontroller.do?nome=jao&login=jao&
		 * senha=123 //normalmente esse formato e usado pra fazer consulta,mas
		 * no exemplo esta sendo usado pra alterar o banco String nome =
		 * req.getParameter("n"); //aqui pego do link do browser String login =
		 * req.getParameter("login"); String senha = req.getParameter("senha");
		 * 
		 * //com as informaçoes,seto no novo usuario Usuario usu = new
		 * Usuario(); usu.setNome(nome); usu.setLogin(login);
		 * usu.setSenha(senha);
		 * 
		 * //insiro esse usuario no banco de dados UsuarioDAO usuDao = new
		 * UsuarioDAO(); usuDao.salvar(usu);
		 * 
		 * 
		 */
		resp.setContentType("text/html"); // o response se converte em html
		String acao = req.getParameter("acao");
		UsuarioDAO usuDao = new UsuarioDAO();
		if (acao.equals("excluir")) {
			String id = req.getParameter("id");
			Usuario usu = new Usuario();
			if (id != null)
				usu.setId(Integer.parseInt(id));

			usuDao.excluir(usu);
			resp.getWriter().println("Excluido com sucesso!");

			// reenvia pra outro caminho.acontece no brownser
			resp.sendRedirect("usucontroller.do?acao=listar");

		} else if (acao.equals("listar")) {
			List<Usuario> lista = usuDao.BuscaTodos();
			// resp.getWriter().print(lista);
			// mapeio a lista:associo o objeto a uma chave,que sera colocada no
			// response
			req.setAttribute("lista", lista);
			// objeto pra encaminhar
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/listausu.jsp");
			// encaminha os mesmos que vieram no metodo pro jsp,porem o req vai
			// com uma carga
			// acontece no servidor
			dispatcher.forward(req, resp);

		} else if (acao.equals("alterar")) {
			String id = req.getParameter("id");
			Usuario usuario = usuDao.buscaPorId(Integer.parseInt(id));
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/FormPOST.jsp");
			dispatcher.forward(req, resp);
		} else if (acao.equals("cadastrar")) {
			Usuario usuario = new Usuario();
			usuario.setId(0);
			usuario.setLogin("");
			usuario.setNome("");
			usuario.setSenha("");
			req.setAttribute("usu", usuario);
			RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/FormPOST.jsp");
			dispatcher.forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// enviando os dados no Post

		String id = req.getParameter("id");
		String nome = req.getParameter("n");
		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usu = new Usuario();
		if (id != null)
			usu.setId(Integer.parseInt(id));

		usu.setNome(nome);
		usu.setLogin(login);
		usu.setSenha(senha);

		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.salvar(usu);

		resp.sendRedirect("usucontroller.do?acao=listar");
		System.out.println("Sucesso no POST!");

	}

	@Override
	public void destroy() {
		System.out.println("Destroi...");
		super.destroy();
	}
}
