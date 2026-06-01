package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.repository.ProdottoRepository;


@Service
public class ProdottoService {
	private ProdottoRepository prodottoRepository;
	
	public ProdottoService(ProdottoRepository prodottoRepository) {
		this.prodottoRepository=prodottoRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Prodotto> findAll() {
        List<Prodotto> prodotti = new ArrayList<>();
        this.prodottoRepository.findAll().forEach(prodotti::add);
        return prodotti;
    }

    @Transactional(readOnly = true)
    public Optional<Prodotto> findById(Long id) {
        return this.prodottoRepository.findById(id);
    }
}
