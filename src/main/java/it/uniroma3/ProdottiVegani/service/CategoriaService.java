package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Categoria;
import it.uniroma3.ProdottiVegani.repository.CategoriaRepository;

@Service
public class CategoriaService {

	private CategoriaRepository categoriaRepository;

	public CategoriaService(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Transactional(readOnly = true)
	public List<Categoria> findAll() {
		List<Categoria> categorie = new ArrayList<>();
		this.categoriaRepository.findAll().forEach(categorie::add);
		return categorie;
	}

	@Transactional(readOnly = true)
	public Optional<Categoria> findById(Long id) {
		return this.categoriaRepository.findById(id);
	}

	@Transactional
	public Categoria salva(Categoria categoria) {
		return this.categoriaRepository.save(categoria);
	}

	@Transactional
	public Categoria aggiorna(Long id, Categoria datiAggiornati) {
		Categoria categoria = this.categoriaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con id " + id));

		categoria.setNome(datiAggiornati.getNome());
		categoria.setDescrizione(datiAggiornati.getDescrizione());

		return categoria;
	}

	@Transactional
	public void elimina(Long id) {
		Categoria categoria = this.categoriaRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Categoria non trovata con id " + id));

		if (!categoria.getProdotti().isEmpty()) {
			throw new IllegalStateException(
				"Impossibile eliminare la categoria '" + categoria.getNome()
				+ "': ci sono ancora prodotti associati.");
		}

		this.categoriaRepository.deleteById(id);
	}
}