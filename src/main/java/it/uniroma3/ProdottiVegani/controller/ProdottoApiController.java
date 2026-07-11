package it.uniroma3.ProdottiVegani.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.uniroma3.ProdottiVegani.dto.BrandDTO;
import it.uniroma3.ProdottiVegani.dto.CategoriaDTO;
import it.uniroma3.ProdottiVegani.dto.ProdottoDTO;
import it.uniroma3.ProdottiVegani.service.BrandService;
import it.uniroma3.ProdottiVegani.service.CategoriaService;
import it.uniroma3.ProdottiVegani.service.ProdottoService;

// Espone dati in JSON per il frontend React standalone (vedi CorsConfig per l'origine consentita)
@RestController
@RequestMapping("/api")
public class ProdottoApiController {

	private ProdottoService prodottoService;
	private BrandService brandService;
	private CategoriaService categoriaService;

	public ProdottoApiController(ProdottoService prodottoService,
	                              BrandService brandService,
	                              CategoriaService categoriaService) {
		this.prodottoService = prodottoService;
		this.brandService = brandService;
		this.categoriaService = categoriaService;
	}

	// GET /api/prodotti?categoriaId=1&brandId=2&prezzoMin=5&prezzoMax=20
	// Tutti i parametri sono opzionali: quelli omessi non filtrano.
	@GetMapping("/prodotti")
	public List<ProdottoDTO> cercaProdotti(@RequestParam(required = false) Long categoriaId,
	                                        @RequestParam(required = false) Long brandId,
	                                        @RequestParam(required = false) Double prezzoMin,
	                                        @RequestParam(required = false) Double prezzoMax) {
		return this.prodottoService.cercaConFiltri(categoriaId, brandId, prezzoMin, prezzoMax)
			.stream()
			.map(ProdottoDTO::from)
			.toList();
	}

	@GetMapping("/categorie")
	public List<CategoriaDTO> tutteLeCategorie() {
		return this.categoriaService.findAll()
			.stream()
			.map(CategoriaDTO::from)
			.toList();
	}

	@GetMapping("/brand")
	public List<BrandDTO> tuttiIBrand() {
		return this.brandService.findAll()
			.stream()
			.map(BrandDTO::from)
			.toList();
	}
}