package it.uniroma3.ProdottiVegani.model;

import java.util.List;
import java.util.Objects;
<<<<<<< Updated upstream
=======
<<<<<<< HEAD


=======
>>>>>>> 66407a5ae55a7f0ca02e1009ad46c441f505cdcf
>>>>>>> Stashed changes
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
<<<<<<< Updated upstream
=======
<<<<<<< HEAD

=======
>>>>>>> 66407a5ae55a7f0ca02e1009ad46c441f505cdcf
>>>>>>> Stashed changes
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Prodotto {
	
	public enum SkinType {
        SECCA,
        GRASSA,
        MISTA,
        SENSIBILE,
        NORMALE
    }
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(length = 2000)
    private String descrizione;

    @Column(nullable = false)
    private Double prezzo;

    @Column(nullable = false)
    private String formato;   // es. "50ml", "100ml"

    @Column(nullable = false)
    private boolean vegan;

    @Column(nullable = false)
    private boolean crueltyFree;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SkinType skinType;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Categoria categoria;

   @ManyToMany
   private List<Ingrediente> ingredienti;
   

    @OneToMany(mappedBy = "prodotto")
    private List<Recensione> recensioni;

    @OneToMany(mappedBy = "prodotto")
    private List<Routine> routineSteps;
    
    @OneToMany(mappedBy = "prodotto")
    private List<Wishlist> wishlist;
    

    // COSTRUTTORE
    public Prodotto() {
        super();
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

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }

    public boolean isCrueltyFree() {
        return crueltyFree;
    }

    public void setCrueltyFree(boolean crueltyFree) {
        this.crueltyFree = crueltyFree;
    }

    public SkinType getSkinType() {
        return skinType;
    }

    public void setSkinType(SkinType skinType) {
        this.skinType = skinType;
    }
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Categoria getCategory() {
        return categoria;
    }

    public void setCategory(Categoria category) {
        this.categoria = category;
    }
      
    public List<Ingrediente> getIngredienti() {
        return ingredienti;
    }

    public void setIngredienti(List<Ingrediente> ingredienti) {
        this.ingredienti = ingredienti;
    }

    public List<Recensione> getReviews() {
        return recensioni;
    }

    public void setReviews(List<Recensione> reviews) {
        this.recensioni = reviews;
    }

    public List<Routine> getRoutineSteps() {
        return routineSteps;
    }

    public void setRoutineSteps(List<Routine> routineSteps) {
        this.routineSteps = routineSteps;
    }
    

    public List<Wishlist> getWishlist() {
		return wishlist;
	}

	public void setWishlist(List<Wishlist> wishlist) {
		this.wishlist = wishlist;
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
        Prodotto other = (Prodotto) obj;
        return Objects.equals(id, other.id);
    }


  

	}
