package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Wishlist;
import it.uniroma3.ProdottiVegani.repository.WishlistRepository;

@Service
public class WishlistService {
private WishlistRepository wishlistRepository;
	
	public WishlistService(WishlistRepository wishlistRepository) {
		this.wishlistRepository=wishlistRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Wishlist> findAll() {
        List<Wishlist> wishlist = new ArrayList<>();
        this.wishlistRepository.findAll().forEach(wishlist::add);
        return wishlist;
    }

    @Transactional(readOnly = true)
    public Optional<Wishlist> findById(Long id) {
        return this.wishlistRepository.findById(id);
    }
    
    //Recupera tutti gli elementi della wishlist di un determinato utente
    @Transactional(readOnly = true)
    public List<Wishlist> findByUtenteId(Long utenteId) {
        return this.wishlistRepository.findByUtenteId(utenteId);
    }

    //Cancella un elemento dalla wishlist tramite il suo ID
    @Transactional
    public void deleteById(Long id) {
        this.wishlistRepository.deleteById(id);
    }

    /*
    //serve quando l'utente cliccherà su "Aggiungi alla Wishlist"
    @Transactional
    public Wishlist save(Wishlist wishlist) {
        return this.wishlistRepository.save(wishlist);
    }
    */
}
