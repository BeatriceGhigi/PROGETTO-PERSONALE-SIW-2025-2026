package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Recensione;
import it.uniroma3.ProdottiVegani.repository.RecensioneRepository;


@Service
public class RecensioneService {
	private RecensioneRepository recensioneRepository;
	
	public RecensioneService(RecensioneRepository recensioneRepository) {
		this.recensioneRepository=recensioneRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Recensione> findAll() {
        List<Recensione> recensioni = new ArrayList<>();
        this.recensioneRepository.findAll().forEach(recensioni::add);
        return recensioni;
    }

    @Transactional(readOnly = true)
    public Optional<Recensione> findById(Long id) {
        return this.recensioneRepository.findById(id);
    }
    
    //prende l'oggetto che contiene i dati scritti dall'utente
    //e dice al database di salvarlo
    @Transactional
    public Recensione save(Recensione recensione) {
        return this.recensioneRepository.save(recensione);
    }

    //prende l'id di una recensione e dice al database di eliminarla
    @Transactional
    public void deleteById(Long id) {
        this.recensioneRepository.deleteById(id);
    }
}
