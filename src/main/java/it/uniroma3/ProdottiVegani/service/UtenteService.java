package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Utente;
import it.uniroma3.ProdottiVegani.repository.UtenteRepository;

@Service
public class UtenteService {
	private UtenteRepository utenteRepository;
	
	public UtenteService(UtenteRepository utenteRepository) {
		this.utenteRepository=utenteRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Utente> findAll() {
        List<Utente> utenti = new ArrayList<>();
        this.utenteRepository.findAll().forEach(utenti::add);
        return utenti;
    }

    @Transactional(readOnly = true)
    public Optional<Utente> findById(Long id) {
        return this.utenteRepository.findById(id);
    }
}
