package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fabricadeprogramador.persistencia.entidade.Usuario;

public class UsuarioDAO {
	private Connection con = ConexaoFactory.getConnection();

	public void cadastrar(Usuario usuAlt) {
		//o banco tem varios algoritmos de criptografia nativos
	    String sql = "insert into usuario (nome,login,senha) values (?,?,md5(?))";
		
		try (PreparedStatement preparator = con.prepareStatement(sql)){
			
			//Substituindo os parametros do SQL pelos valores do objeto
			//substitui o '?'
                        preparator.setString(1, usuAlt.getNome()); 
			preparator.setString(2, usuAlt.getLogin()); 
			preparator.setString(3, usuAlt.getSenha());
			//Executa SQL preparador.execute();
			preparator.execute();
			//como extende da interface AutoCloseable,o objeto deve ser encerrado depois.
			//pode substituir o close colocando o 'try entre ()'
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void alterar(Usuario usuAlt) {
        
		String sql = "update usuario set nome = ?,login= ?,senha= md5(?) where id = ?";
		
		try (PreparedStatement preparator = con.prepareStatement(sql)){
			preparator.setString(1, usuAlt.getNome()); 
			preparator.setString(2, usuAlt.getLogin()); 
			preparator.setString(3, usuAlt.getSenha());
			preparator.setInt(4, usuAlt.getId());
			preparator.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
   }
	
   public void excluir(Usuario usuAlt) {
	   String sql = "delete from usuario where id = ?";
		
		try (PreparedStatement preparator = con.prepareStatement(sql)){
			preparator.setInt(1, usuAlt.getId());
			preparator.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
   }
   //criando o metodo salvar.metodo que encapsula os metodos cadastrar e alterar.o criterio de decisão é o id do usuario
   //se for null ou maior que zero,o usuario ja existe e então é alterar(),caso contrario,cadastrar()
   
   public void salvar(Usuario usu){ 
	  if(usu.getId()!=null && usu.getId()>0){ 
		  alterar(usu);
      }else{ 
    	  cadastrar(usu);
      }
   }
   
   public Usuario buscaPorId(Integer id) {
	
	String sql = "select * from usuario where id=?";
	try ( PreparedStatement preparador = con.prepareStatement(sql)){ 
		//seta o id no primeiro ponto de interrogaçao
		preparador.setInt(1, id);
      
		//pega os resultados da query e coloca num Resultset
	    ResultSet resultado = preparador.executeQuery();
	  
	    //resultado.next posiciona o ponteiro no primeiro registro.se tivessem 2,estaria no segundo registro, e etc
	   //se existe registro,então converte pra uma classe
	       if (resultado.next()) {
	       Usuario usuRetorno1 = new Usuario();
	       usuRetorno1.setId(resultado.getInt("id"));
	       usuRetorno1.setNome(resultado.getString("nome")); 
	       usuRetorno1.setLogin(resultado.getString("login")); 
	       usuRetorno1.setSenha(resultado.getString("senha"));
	       return usuRetorno1;
	 }
	
	  System.out.println("Não encontrado!");
	  
	} catch (SQLException e) { 
		e.printStackTrace();
	}
	return null;
  }
   
   public List<Usuario> BuscaTodos() {
	   //Objeto de retorno do método
	   List<Usuario> listaRetorno = new ArrayList<Usuario>(); 
	   String sql = "select * from usuario order by id";
	   
	   try (PreparedStatement preparador = con.prepareStatement(sql)){
	        //Retorno da consulta em Resultset
	       ResultSet resultado = preparador.executeQuery(); // como preciso de um retorno do banco,não posso simplesmente usar execute().
	       //Navegada nos registros 
	       
	       while(resultado.next()){
	       //instancia o objeto Usuario
	       
	       Usuario usu = new Usuario();
	       //Carga de dados no usuário 
	       usu.setId(resultado.getInt("id")); 
	       usu.setNome(resultado.getString("nome")); 
	       usu.setLogin(resultado.getString("login")); 
	       usu.setSenha(resultado.getString("senha"));
	      //adiciona na lista 
	       listaRetorno.add(usu);
	   }
	   
	   } catch (SQLException e) { 
		   e.printStackTrace();
	   }
	   
	   return listaRetorno;
   }
   
   public Usuario autenticar(Usuario usuConsulta){
	 //Objeto de retorno do método
	
	 String sql = "select * from usuario where login=? and senha=md5(?)"; 
	 try (PreparedStatement preparador = con.prepareStatement(sql)){
	      preparador.setString(1, usuConsulta.getLogin()); 
	      preparador.setString(2, usuConsulta.getSenha());
	      //Retorno da consulta em Resultset
	      ResultSet resultado = preparador.executeQuery();
	      //Se tem registro 
	      if(resultado.next()){
	      //instancia o objeto Usuario 
	    	 Usuario usuRetorno = new Usuario();
	         usuRetorno.setId(resultado.getInt("id")); 
	         usuRetorno.setNome(resultado.getString("nome")); 
	         usuRetorno.setLogin(resultado.getString("login")); 
	         usuRetorno.setSenha(resultado.getString("senha"));
	        
	         return usuRetorno;
	        
	      }
	 } catch (SQLException e) {
	   e.printStackTrace();
	 }
	 return null;
	 }

}
