package it.uniroma3.ProdottiVegani.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.ProdottiVegani.model.Wishlist;


public interface WishlistRepository extends JpaRepository<Wishlist, Long>{
	List<Wishlist> findByUtenteId(Long utenteId);
}