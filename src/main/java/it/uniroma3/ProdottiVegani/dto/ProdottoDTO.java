package it.uniroma3.ProdottiVegani.dto;

import it.uniroma3.ProdottiVegani.model.Prodotto;

public class ProdottoDTO {

	private Long id;
	private String nome;
	private String descrizione;
	private Double prezzo;
	private String formato;
	private boolean vegan;
	private boolean crueltyFree;
	private String skinType;
	private String brandNome;
	private String categoriaNome;

	public ProdottoDTO(Long id, String nome, String descrizione, Double prezzo, String formato,
	                    boolean vegan, boolean crueltyFree, String skinType,
	                    String brandNome, String categoriaNome) {
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.formato = formato;
		this.vegan = vegan;
		this.crueltyFree = crueltyFree;
		this.skinType = skinType;
		this.brandNome = brandNome;
		this.categoriaNome = categoriaNome;
	}

	// Factory: costruisce il DTO a partire dall'entita' Prodotto
	public static ProdottoDTO from(Prodotto prodotto) {
		return new ProdottoDTO(
			prodotto.getId(),
			prodotto.getNome(),
			prodotto.getDescrizione(),
			prodotto.getPrezzo(),
			prodotto.getFormato(),
			prodotto.isVegan(),
			prodotto.isCrueltyFree(),
			prodotto.getSkinType() != null ? prodotto.getSkinType().name() : null,
			prodotto.getBrand() != null ? prodotto.getBrand().getNome() : null,
			prodotto.getCategoria() != null ? prodotto.getCategoria().getNome() : null
		);
	}

	// GET (nessun set: e' un DTO di sola lettura verso il frontend)
	public Long getId() { return id; }
	public String getNome() { return nome; }
	public String getDescrizione() { return descrizione; }
	public Double getPrezzo() { return prezzo; }
	public String getFormato() { return formato; }
	public boolean isVegan() { return vegan; }
	public boolean isCrueltyFree() { return crueltyFree; }
	public String getSkinType() { return skinType; }
	public String getBrandNome() { return brandNome; }
	public String getCategoriaNome() { return categoriaNome; }
}