package it.uniroma3.ProdottiVegani.dto;

import it.uniroma3.ProdottiVegani.model.Categoria;

public class CategoriaDTO {

	private Long id;
	private String nome;

	public CategoriaDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static CategoriaDTO from(Categoria categoria) {
		return new CategoriaDTO(categoria.getId(), categoria.getNome());
	}

	public Long getId() { return id; }
	public String getNome() { return nome; }
}