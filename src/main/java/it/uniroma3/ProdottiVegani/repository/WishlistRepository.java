package it.uniroma3.ProdottiVegani.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.ProdottiVegani.model.Wishlist;


public interface WishlistRepository extends CrudRepository<Wishlist, Long>{
	List<Wishlist> findByUtenteId(Long utenteId);
}
