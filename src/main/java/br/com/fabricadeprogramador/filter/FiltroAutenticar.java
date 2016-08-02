package br.com.fabricadeprogramador.filter;

import java.io.IOException;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//url patters= qualquer requisi�ao passa pelo filtro (/*)
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = "/*")
public class FiltroAutenticar implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Passou pelo filtro");
		
		//Casting do HttpServelt Request
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		
		//Capturando Sessao("/fabricaweb/usuconcontroller")
		String uri = httpServletRequest.getRequestURI();
		
		//Est� logado ou tentando acessar uma recurso que � publico permito sem estar logado?
		HttpSession sessao = httpServletRequest.getSession(false);
		
		        //s� permtite a passagens desses
		       //lastindexof = retorna a posi��o da letra ou -1 se n�o encontrar
				if (sessao != null || uri.lastIndexOf("login.html")!= -1 || uri.lastIndexOf("autenticador.do")!= -1 )
				{   
					//Permite o fluxo de requisicao
					chain.doFilter(request, response); 
				
		        }else{
		           //redireciona para login
		           ((HttpServletResponse) response).sendRedirect("login.html");
		        }
	}
	
	@Override
	public void destroy() {
		

	}

}
