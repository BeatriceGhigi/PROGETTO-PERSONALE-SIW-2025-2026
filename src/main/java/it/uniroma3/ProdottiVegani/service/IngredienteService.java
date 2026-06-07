package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Ingrediente;
import it.uniroma3.ProdottiVegani.repository.IngredienteRepository;

@Service
public class IngredienteService {
	private IngredienteRepository ingredienteRepository;

    public IngredienteService(IngredienteRepository ingredienteRepository) {
        this.ingredienteRepository = ingredienteRepository;
    }

    @Transactional(readOnly = true)
    public List<Ingrediente> findAll() {
        List<Ingrediente> ingredienti = new ArrayList<>();
        this.ingredienteRepository.findAll().forEach(ingredienti::add);
        return ingredienti;
    }

    @Transactional(readOnly = true)
    public Optional<Ingrediente> findById(Long id) {
        return this.ingredienteRepository.findById(id);
    }

    public Ingrediente save(Ingrediente ingrediente) {
        return this.ingredienteRepository.save(ingrediente);
    }

    public void deleteById(Long id) {
        this.ingredienteRepository.deleteById(id);
    }
}
