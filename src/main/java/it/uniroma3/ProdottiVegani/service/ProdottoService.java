package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Brand;
import it.uniroma3.ProdottiVegani.model.Categoria;
import it.uniroma3.ProdottiVegani.model.Ingrediente;
import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.repository.BrandRepository;
import it.uniroma3.ProdottiVegani.repository.CategoriaRepository;
import it.uniroma3.ProdottiVegani.repository.IngredienteRepository;
import it.uniroma3.ProdottiVegani.repository.ProdottoRepository;

@Service
public class ProdottoService {

	private ProdottoRepository prodottoRepository;
	private BrandRepository brandRepository;
	private CategoriaRepository categoriaRepository;
	private IngredienteRepository ingredienteRepository;

	public ProdottoService(ProdottoRepository prodottoRepository,
	                        BrandRepository brandRepository,
	                        CategoriaRepository categoriaRepository,
	                        IngredienteRepository ingredienteRepository) {
		this.prodottoRepository = prodottoRepository;
		this.brandRepository = brandRepository;
		this.categoriaRepository = categoriaRepository;
		this.ingredienteRepository = ingredienteRepository;
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

	// Usato dall'endpoint REST /api/prodotti per la ricerca a filtri (React)
	@Transactional(readOnly = true)
	public List<Prodotto> cercaConFiltri(Long categoriaId, Long brandId, Double prezzoMin, Double prezzoMax) {
		return this.prodottoRepository.cercaConFiltri(categoriaId, brandId, prezzoMin, prezzoMax);
	}

	@Transactional
	public Prodotto salva(Prodotto prodotto, Long brandId, Long categoriaId, List<Long> ingredientiIds) {
		collegaRelazioni(prodotto, brandId, categoriaId, ingredientiIds);
		return this.prodottoRepository.save(prodotto);
	}

	@Transactional
	public Prodotto aggiorna(Long id, Prodotto datiAggiornati, Long brandId, Long categoriaId, List<Long> ingredientiIds) {
		Prodotto prodotto = this.prodottoRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con id " + id));

		prodotto.setNome(datiAggiornati.getNome());
		prodotto.setDescrizione(datiAggiornati.getDescrizione());
		prodotto.setPrezzo(datiAggiornati.getPrezzo());
		prodotto.setFormato(datiAggiornati.getFormato());
		prodotto.setVegan(datiAggiornati.isVegan());
		prodotto.setCrueltyFree(datiAggiornati.isCrueltyFree());
		prodotto.setSkinType(datiAggiornati.getSkinType());

		collegaRelazioni(prodotto, brandId, categoriaId, ingredientiIds);

		return prodotto;
	}

	@Transactional
	public void elimina(Long id) {
		Prodotto prodotto = this.prodottoRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con id " + id));

		this.prodottoRepository.delete(prodotto);
	}

	private void collegaRelazioni(Prodotto prodotto, Long brandId, Long categoriaId, List<Long> ingredientiIds) {
		if (brandId != null) {
			Brand brand = this.brandRepository.findById(brandId)
				.orElseThrow(() -> new IllegalArgumentException("Brand non trovato con id " + brandId));
			prodotto.setBrand(brand);
		}

		if (categoriaId != null) {
			Categoria categoria = this.categoriaRepository.findById(categoriaId)
				.orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con id " + categoriaId));
			prodotto.setCategoria(categoria);
		}

		List<Ingrediente> ingredienti = new ArrayList<>();
		if (ingredientiIds != null) {
			for (Long ingredienteId : ingredientiIds) {
				Ingrediente ingrediente = this.ingredienteRepository.findById(ingredienteId)
					.orElseThrow(() -> new IllegalArgumentException("Ingrediente non trovato con id " + ingredienteId));
				ingredienti.add(ingrediente);
			}
		}
		prodotto.setIngredienti(ingredienti);
	}
}