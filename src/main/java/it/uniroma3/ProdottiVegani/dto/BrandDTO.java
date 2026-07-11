package it.uniroma3.ProdottiVegani.dto;

import it.uniroma3.ProdottiVegani.model.Brand;

public class BrandDTO {

	private Long id;
	private String nome;

	public BrandDTO(Long id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public static BrandDTO from(Brand brand) {
		return new BrandDTO(brand.getId(), brand.getNome());
	}

	public Long getId() { return id; }
	public String getNome() { return nome; }
}