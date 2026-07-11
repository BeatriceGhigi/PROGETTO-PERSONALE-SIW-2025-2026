package it.uniroma3.ProdottiVegani.model;

import java.util.List;
import java.util.Objects;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Ingrediente {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 2000)
    private String descrizione;
    
    public enum FunzioneIngrediente {
        IDRATANTE,
        LENITIVO,
        ESFOLIANTE,
        ANTIOSSIDANTE,
        EMOLLIENTE,
        DETERGENTE,
        CONSERVANTE,
        PROFUMANTE
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FunzioneIngrediente funzione;

    private boolean naturale;

    @ManyToMany(mappedBy = "ingredienti")
    private List<Prodotto> prodotti;

    // COSTRUTTORE
    public Ingrediente() {
 
    }

    
    // GET & SET
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public FunzioneIngrediente getFunzione() {
	    return funzione;
	}

	public void setFunzione(FunzioneIngrediente funzione) {
	    this.funzione = funzione;
	}

	public boolean isNaturale() {
		return naturale;
	}

	public void setNaturale(boolean naturale) {
		this.naturale = naturale;
	}
 public List<Prodotto> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<Prodotto> prodotti) {
		this.prodotti = prodotti;
	}

// EQUALS & HASHCODE
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ingrediente other = (Ingrediente) obj;
		return Objects.equals(id, other.id);
	}
    
	
   
}
