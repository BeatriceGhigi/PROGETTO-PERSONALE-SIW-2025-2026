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

import it.uniroma3.ProdottiVegani.model.Brand;
import it.uniroma3.ProdottiVegani.service.BrandService;
import jakarta.validation.Valid;

@Controller
public class BrandController {

	private BrandService brandService;

	public BrandController(BrandService brandService) {
		this.brandService = brandService;
	}

	// ===================== FUNZIONALITA' PUBBLICHE (Sezione 4.1) =====================

	@GetMapping("/brand")
	public String list(Model model) {
		List<Brand> brands = this.brandService.findAll();
		model.addAttribute("brands", brands);
		return "brand/list";
	}

	@GetMapping("/brand/{id}")
	public String show(@PathVariable("id") Long id, Model model) {
		Optional<Brand> brandOptional = this.brandService.findById(id);
		if (brandOptional.isPresent()) {
			model.addAttribute("brand", brandOptional.get());
		}
		return "brand/show";
	}

	// ===================== FUNZIONALITA' ADMIN (Sezione 4.3) =====================

	@GetMapping("/admin/brand/nuovo")
	public String formNuovoBrand(Model model) {
		model.addAttribute("brand", new Brand());
		return "admin/brand/form";
	}

	@PostMapping("/admin/brand")
	public String creaBrand(@Valid @ModelAttribute("brand") Brand brand, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "admin/brand/form";
		}
		Brand salvato = this.brandService.salva(brand);
		return "redirect:/brand/" + salvato.getId();
	}

	@GetMapping("/admin/brand/{id}/modifica")
	public String formModificaBrand(@PathVariable("id") Long id, Model model) {
		Optional<Brand> brandOptional = this.brandService.findById(id);
		if (brandOptional.isEmpty()) {
			return "redirect:/brand";
		}
		model.addAttribute("brand", brandOptional.get());
		return "admin/brand/form";
	}

	@PostMapping("/admin/brand/{id}")
	public String aggiornaBrand(@PathVariable("id") Long id,
	                             @Valid @ModelAttribute("brand") Brand brandForm,
	                             BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			brandForm.setId(id);
			return "admin/brand/form";
		}
		Brand aggiornato = this.brandService.aggiorna(id, brandForm);
		return "redirect:/brand/" + aggiornato.getId();
	}

	@PostMapping("/admin/brand/{id}/elimina")
	public String eliminaBrand(@PathVariable("id") Long id) {
		this.brandService.elimina(id);
		return "redirect:/brand";
	}
}