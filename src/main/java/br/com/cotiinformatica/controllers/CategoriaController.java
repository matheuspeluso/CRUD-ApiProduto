package br.com.cotiinformatica.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cotiinformatica.entities.Categoria;
import br.com.cotiinformatica.repositories.CategoriaRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/categorias")
@Tag(name = "Categorias", description = "API para gerenciamento de categorias de produtos")
public class CategoriaController {

	@Operation(summary = "Listar todas as categorias", description = "Serviço para consultar todas as categorias de produtos do sistema.")
	@GetMapping
	public List<Categoria> getAll() throws Exception {

		var categoriaRepository = new CategoriaRepository();
		return categoriaRepository.findAll();
	}
}
