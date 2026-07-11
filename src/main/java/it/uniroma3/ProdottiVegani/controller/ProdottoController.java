package it.uniroma3.ProdottiVegani.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.service.BrandService;
import it.uniroma3.ProdottiVegani.service.CategoriaService;
import it.uniroma3.ProdottiVegani.service.IngredienteService;
import it.uniroma3.ProdottiVegani.service.ProdottoService;
import jakarta.validation.Valid;

@Controller
public class ProdottoController {

	private ProdottoService prodottoService;
	private BrandService brandService;
	private CategoriaService categoriaService;
	private IngredienteService ingredienteService;

	public ProdottoController(ProdottoService prodottoService,
	                           BrandService brandService,
	                           CategoriaService categoriaService,
	                           IngredienteService ingredienteService) {
		this.prodottoService = prodottoService;
		this.brandService = brandService;
		this.categoriaService = categoriaService;
		this.ingredienteService = ingredienteService;
	}

	// ===================== FUNZIONALITA' PUBBLICHE (Sezione 4.1) =====================

	@GetMapping("/prodotti")
	public String list(Model model) {
		List<Prodotto> prodotti = this.prodottoService.findAll();
		model.addAttribute("prodotti", prodotti);
		return "prodotti/list";
	}

	@GetMapping("/prodotti/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Prodotto> prodottoOptional = this.prodottoService.findById(id);
		if (prodottoOptional.isPresent()) {
			model.addAttribute("prodotto", prodottoOptional.get());
		}
		return "prodotti/show";
	}

	// ===================== FUNZIONALITA' ADMIN (Sezione 4.3) =====================

	@GetMapping("/admin/prodotti/nuovo")
	public String formNuovoProdotto(Model model) {
		model.addAttribute("prodotto", new Prodotto());
		aggiungiListeAlModel(model);
		return "admin/prodotti/form";
	}

	@PostMapping("/admin/prodotti")
	public String creaProdotto(@Valid @ModelAttribute("prodotto") Prodotto prodotto,
	                            BindingResult bindingResult,
	                            @RequestParam(value = "brandId", required = false) Long brandId,
	                            @RequestParam(value = "categoriaId", required = false) Long categoriaId,
	                            @RequestParam(value = "ingredientiIds", required = false) List<Long> ingredientiIds,
	                            Model model) {
		if (bindingResult.hasErrors()) {
			aggiungiListeAlModel(model);
			return "admin/prodotti/form";
		}

		Prodotto salvato = this.prodottoService.salva(prodotto, brandId, categoriaId, ingredientiIds);
		return "redirect:/prodotti/" + salvato.getId();
	}

	@GetMapping("/admin/prodotti/{id}/modifica")
	public String formModificaProdotto(@PathVariable("id") Long id, Model model) {
		Optional<Prodotto> prodottoOptional = this.prodottoService.findById(id);
		if (prodottoOptional.isEmpty()) {
			return "redirect:/prodotti";
		}
		model.addAttribute("prodotto", prodottoOptional.get());
		aggiungiListeAlModel(model);
		return "admin/prodotti/form";
	}

	@PostMapping("/admin/prodotti/{id}")
	public String aggiornaProdotto(@PathVariable("id") Long id,
	                                @Valid @ModelAttribute("prodotto") Prodotto prodottoForm,
	                                BindingResult bindingResult,
	                                @RequestParam(value = "brandId", required = false) Long brandId,
	                                @RequestParam(value = "categoriaId", required = false) Long categoriaId,
	                                @RequestParam(value = "ingredientiIds", required = false) List<Long> ingredientiIds,
	                                Model model) {
		if (bindingResult.hasErrors()) {
			prodottoForm.setId(id);
			aggiungiListeAlModel(model);
			return "admin/prodotti/form";
		}

		Prodotto aggiornato = this.prodottoService.aggiorna(id, prodottoForm, brandId, categoriaId, ingredientiIds);
		return "redirect:/prodotti/" + aggiornato.getId();
	}

	@PostMapping("/admin/prodotti/{id}/elimina")
	public String eliminaProdotto(@PathVariable("id") Long id) {
		this.prodottoService.elimina(id);
		return "redirect:/prodotti";
	}

	// Metodo di supporto: carica le liste necessarie per le select/checkbox del form
	private void aggiungiListeAlModel(Model model) {
		model.addAttribute("tuttiIBrand", this.brandService.findAll());
		model.addAttribute("tutteLeCategorie", this.categoriaService.findAll());
		model.addAttribute("tuttiGliIngredienti", this.ingredienteService.findAll());
	}
}