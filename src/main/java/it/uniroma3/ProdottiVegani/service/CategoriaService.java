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

    public Categoria save(Categoria categoria) {
        return this.categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        this.categoriaRepository.deleteById(id);
    }
}
