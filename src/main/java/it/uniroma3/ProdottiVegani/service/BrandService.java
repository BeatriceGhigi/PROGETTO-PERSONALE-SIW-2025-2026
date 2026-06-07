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
    

    public Brand save(Brand brand) {
        return this.brandRepository.save(brand);
    }

    public void deleteById(Long id) {
        this.brandRepository.deleteById(id);
    }
}
