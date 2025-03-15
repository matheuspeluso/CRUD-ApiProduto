package br.com.cotiinformatica.dtos;

import java.util.UUID;

import lombok.Data;

@Data
public class ProdutoRequestDto {
	
	private String nome;
	private Double preco;
	private Integer quantidade;
	private UUID CategoriaId;

}
