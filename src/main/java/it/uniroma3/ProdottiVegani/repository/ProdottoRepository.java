package it.uniroma3.ProdottiVegani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import it.uniroma3.ProdottiVegani.model.Prodotto;

public interface ProdottoRepository extends JpaRepository<Prodotto, Long> {

	// Ricerca con filtri opzionali: se un parametro e' null, quel filtro viene ignorato
	@Query("SELECT p FROM Prodotto p WHERE "
		+ "(:categoriaId IS NULL OR p.categoria.id = :categoriaId) AND "
		+ "(:brandId IS NULL OR p.brand.id = :brandId) AND "
		+ "(:prezzoMin IS NULL OR p.prezzo >= :prezzoMin) AND "
		+ "(:prezzoMax IS NULL OR p.prezzo <= :prezzoMax)")
	List<Prodotto> cercaConFiltri(@Param("categoriaId") Long categoriaId,
	                               @Param("brandId") Long brandId,
	                               @Param("prezzoMin") Double prezzoMin,
	                               @Param("prezzoMax") Double prezzoMax);
}