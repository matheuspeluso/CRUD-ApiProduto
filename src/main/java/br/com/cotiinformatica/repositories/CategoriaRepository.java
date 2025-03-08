package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class CategoriaRepository {
	
	public List<Categoria> findAll() throws Exception {
		var connectionFactory = new ConnectionFactory();
		var connection = connectionFactory.getConnection();
		
		var query = """
				SELECT
					id, nome
				FROM
					categoria
				ORDER BY
					nome
				""";
		
		var statement = connection.prepareStatement(query);
		var result = statement.executeQuery();
		
		var lista = new ArrayList<Categoria>();
		
		while(result.next()) {
			var categoria = new Categoria();
			
			categoria.setId(UUID.fromString(result.getString("id")));
			categoria.setNome(result.getString("nome"));
			
			lista.add(categoria);	
		}
		
		connection.close();
		return lista;
	}
}
