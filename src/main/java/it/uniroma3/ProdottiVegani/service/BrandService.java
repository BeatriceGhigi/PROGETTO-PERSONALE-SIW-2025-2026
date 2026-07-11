package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Brand;
import it.uniroma3.ProdottiVegani.repository.BrandRepository;

@Service
public class BrandService {

	private BrandRepository brandRepository;

	public BrandService(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	@Transactional(readOnly = true)
	public List<Brand> findAll() {
		List<Brand> brands = new ArrayList<>();
		this.brandRepository.findAll().forEach(brands::add);
		return brands;
	}

	@Transactional(readOnly = true)
	public Optional<Brand> findById(Long id) {
		return this.brandRepository.findById(id);
	}

	@Transactional
	public Brand salva(Brand brand) {
		return this.brandRepository.save(brand);
	}

	@Transactional
	public Brand aggiorna(Long id, Brand datiAggiornati) {
		Brand brand = this.brandRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Brand non trovato con id " + id));

		brand.setNome(datiAggiornati.getNome());
		brand.setDescrizione(datiAggiornati.getDescrizione());
		brand.setPaese(datiAggiornati.getPaese());
		brand.setSitoWeb(datiAggiornati.getSitoWeb());

		return brand;
	}

	@Transactional
	public void elimina(Long id) {
		Brand brand = this.brandRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Brand non trovato con id " + id));

		if (!brand.getProdotti().isEmpty()) {
			throw new IllegalStateException(
				"Impossibile eliminare il brand '" + brand.getNome()
				+ "': ci sono ancora prodotti associati.");
		}

		this.brandRepository.deleteById(id);
	}
}