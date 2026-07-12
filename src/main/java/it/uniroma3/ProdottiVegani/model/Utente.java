package it.uniroma3.ProdottiVegani.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Utente {

	public enum SkinType {
		SECCA,
		GRASSA,
		MISTA,
		SENSIBILE,
		NORMALE
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@Column(nullable = false)
	@NotBlank
	@Size(min = 6, message = "La password deve avere almeno 6 caratteri")
	private String password;

	@Column(nullable = false)
	private String ruolo; // Es. "USER", "ADMIN"

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private SkinType skinType;

	@OneToMany(mappedBy = "autore")
	private List<Recensione> reviews = new ArrayList<>();

	@OneToMany(mappedBy = "utente")
	private List<Routine> routines = new ArrayList<>();

	@OneToMany(mappedBy = "utente")
	private List<Wishlist> wishlist = new ArrayList<>();

	// COSTRUTTORE
	public Utente() {
	}

	// GET & SET
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public SkinType getSkinType() {
		return skinType;
	}

	public void setSkinType(SkinType skinType) {
		this.skinType = skinType;
	}

	public List<Recensione> getReviews() {
		return reviews;
	}

	public void setReviews(List<Recensione> reviews) {
		this.reviews = reviews;
	}

	public List<Routine> getRoutines() {
		return routines;
	}

	public void setRoutines(List<Routine> routines) {
		this.routines = routines;
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
		if (obj == null || getClass() != obj.getClass())
			return false;
		Utente other = (Utente) obj;
		return Objects.equals(id, other.id);
	}
}