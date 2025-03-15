package br.com.cotiinformatica.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.cotiinformatica.entities.Produto;
import br.com.cotiinformatica.factories.ConnectionFactory;

public class ProdutoRepository {
	
	private ConnectionFactory connectionFactory = new ConnectionFactory();
	
	public void create(Produto produto) throws Exception {
		
		var connection = connectionFactory.getConnection();
		var query = """
					INSERT INTO produto(id, nome, preco, quantidade, categoria_id) 
					VALUES(?,?,?,?,?)
				""";
		
		var statement = connection.prepareStatement(query);
		statement.setObject(1, produto.getId());
		statement.setString(2, produto.getNome());
		statement.setDouble(3, produto.getPreco());
		statement.setInt(4, produto.getQuantidade());
		statement.setObject(5, produto.getCategoria().getId());
		statement.execute();
		
		connection.close();
	}
	
	public void update(Produto produto) throws Exception{
		
		var connection = connectionFactory.getConnection();
		var query = """
					UPDATE produto
					SET nome = ?,
					preco = ?,
					quantidade = ?,
					categoria_id = ?
					
					WHERE
						id = ?
				""";
		
		var statement = connection.prepareStatement(query);
		statement.setString(1, produto.getNome());
		statement.setDouble(2, produto.getPreco());
		statement.setInt(3, produto.getQuantidade());
		statement.setObject(4, produto.getCategoria().getId());
		statement.setObject(5, produto.getId());
		
		connection.close();
	}
	
	public void delete(UUID id) throws Exception{
		
		var connection = connectionFactory.getConnection();
		var query = """
					DELETE FROM produto
					WHERE id = ?
				
				""";
		
		var statement = connection.prepareStatement(query);
		statement.setObject(1, id);
		statement.execute();
		
		connection.close();
	}
	
	public List<Produto> findByNome(String nome) throws Exception{
		
		var connection = connectionFactory.getConnection();
		var query = """
					SELECT
						p.id,
						p.nome,
						p.preco,
						p.quantidade
					FROM
						produto p
					WHERE
						p.nome LIKE ?
					ORDER BY
						p.nome
				""";
		var statement = connection.prepareStatement(query);
		statement.setString(1, "%"+nome+"%");
		var result = statement.executeQuery();
		
		var lista = new ArrayList<Produto>();
		
		while(result.next()) {
			var produto = new Produto();
			produto.setId(UUID.fromString(result.getString("id")));
			produto.setNome(result.getString("nome"));
			produto.setPreco(result.getDouble("quantidade"));
			
			lista.add(produto);
		}
		
		connection.close();
		return lista;
	}
	
public Produto findById(UUID id) throws Exception{
		
		var connection = connectionFactory.getConnection();
		var query = """
					SELECT
						p.id,
						p.nome,
						p.preco,
						p.quantidade
					FROM
						produto p
					WHERE
						p.id = ?
				""";
		var statement = connection.prepareStatement(query);
		statement.setObject(1, id);
		var result = statement.executeQuery();
		
		Produto produto = null;
		
		if(result.next()) {
			produto = new Produto();
			produto.setId(UUID.fromString(result.getString("id")));
			produto.setNome(result.getString("nome"));
			produto.setPreco(result.getDouble("quantidade"));
		}
		
		connection.close();
		return produto;
	}
}
