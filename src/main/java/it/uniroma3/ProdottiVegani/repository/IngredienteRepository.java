package it.uniroma3.ProdottiVegani.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.uniroma3.ProdottiVegani.model.Ingrediente;

public interface IngredienteRepository extends JpaRepository<Ingrediente, Long>{

}