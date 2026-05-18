package it.uniroma3.ProdottiVegani.model;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique=true)
    private String username;

    @Column(nullable = false)
    private String password;

    @jakarta.persistence.Enumerated(jakarta.persistence.EnumType.STRING)
    @Column(nullable = false)
    private SkinType skinType;

    @OneToMany(mappedBy = "utente")
    private List<Recensione> reviews;

    @OneToMany(mappedBy = "utente")
    private List<Routine> routines;

    // COSTRUTTORE
    public Utente() {
    	
    }

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
        Utente other = (Utente) obj;
        return Objects.equals(id, other.id);
    }
}
