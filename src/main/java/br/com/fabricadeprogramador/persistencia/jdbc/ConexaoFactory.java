package br.com.fabricadeprogramador.persistencia.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class ConexaoFactory {

	public static Connection getConnection() {
		try {
			// forçar o carregamento do driver
			//como esta na web,pode nao conseguir carregar manualmente
			Class.forName("org.postgresql.Driver");
			return  DriverManager.getConnection("jdbc:postgresql://localhost:5432/fabricaweb","postgres","admin");
		
		} catch (SQLException e) {
			//relanlando a excepiton pra deixar independende do DAO
			throw new RuntimeException(e);
		
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}
