package br.com.cotiinformatica.factories;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	public Connection getConnection()throws Exception{
		var host = "jdbc:postgresql://localhost:5435/projetoApiProdutos";
		var user = "admin";
		var pass = "coti";
		
		return DriverManager.getConnection(host,user,pass);
	}
}