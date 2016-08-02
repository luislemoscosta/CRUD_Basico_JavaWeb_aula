package br.com.fabricadeprogramador;

import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;
import br.com.fabricadeprogramador.persistencia.jdbc.UsuarioDAO;

//no pacote de teste colocamos todas as classes que serviram de teste.
//gerado pelo MAVEN
//o maven,na hora de levar pra web,nao leva as classes de teste
public class TesteUsuarioDAO {

	public static void main(String[] args) {
		
		//testExcluir();
		//testBuscaporID();
        testBuscaTodos();
	}
	
	private static void testBuscaporID() {
		UsuarioDAO usuDao = new UsuarioDAO();
		Usuario usuRetorno = usuDao.buscaPorId(5);
		if(usuRetorno!=null) 
			System.out.println(usuRetorno); 
		
	}
	
	private static void testAutenticar() {
		UsuarioDAO usuDao = new UsuarioDAO();
		Usuario usu = new Usuario();
		usu.setLogin("jao"); usu.setSenha("123");
		Usuario usuRetorno = usuDao.autenticar(usu);
		if(usuRetorno!=null)
		System.out.println(usuRetorno);
		}
	
	private static void testBuscaTodos() {
		UsuarioDAO usuDao = new UsuarioDAO();
		List<Usuario> lista = usuDao.BuscaTodos(); 
		for (Usuario u : lista) {
		System.out.println(u);
		}
	}

	public static void testCadastrar() {
		//quero gravar no banco uma classe usuario.crio a classe usuario.
				//Intancia do DAO a ser testado
				
		        Usuario usuAlt = new Usuario();
				usuAlt.setNome("MARIA"); 
				usuAlt.setLogin("mar"); 
				usuAlt.setSenha("123"); 
				
				UsuarioDAO usuDao = new UsuarioDAO();
				usuDao.cadastrar(usuAlt);
				
				System.out.println("Cadastrado com sucesso");
	}
	
	public static void testAlterar() {
		//parte do ponto que o usuario ja existe,logo,precisamos localizar o ID
		Usuario usuAlt = new Usuario();
		usuAlt.setId(3);
		usuAlt.setNome("MARIA das couves"); 
		usuAlt.setLogin("marimar"); 
		usuAlt.setSenha("12356"); 
		
		UsuarioDAO usuDao = new UsuarioDAO();
		usuDao.alterar(usuAlt);
		
		System.out.println("Alterado com sucesso");
	}
    public static void testExcluir() {
    	Usuario usuAlt = new Usuario();
    	usuAlt.setId(2);
    	
    	UsuarioDAO usuDAO = new UsuarioDAO();
    	usuDAO.excluir(usuAlt);
    	
    	System.out.println("excluido com sucesso");
    }
    
    public static void testSalvar() {
    	UsuarioDAO usuDao = new UsuarioDAO();
    	Usuario usuSalvar = new Usuario();
    	usuSalvar.setId(3); 
    	usuSalvar.setNome("CARLOS"); 
    	usuSalvar.setLogin("CAR"); 
    	usuSalvar.setSenha("123"); 
    	usuDao.salvar(usuSalvar);
    	}
}
