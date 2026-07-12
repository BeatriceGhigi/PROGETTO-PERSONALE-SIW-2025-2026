package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.exception.UsernameGiaRegistratoException;
import it.uniroma3.ProdottiVegani.model.Utente;
import it.uniroma3.ProdottiVegani.repository.UtenteRepository;

@Service
public class UtenteService {

	private UtenteRepository utenteRepository;
	private PasswordEncoder passwordEncoder;

	public UtenteService(UtenteRepository utenteRepository, PasswordEncoder passwordEncoder) {
		this.utenteRepository = utenteRepository;
		this.passwordEncoder = passwordEncoder;
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

	@Transactional(readOnly = true)
	public Optional<Utente> findByUsername(String username) {
		return this.utenteRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public boolean existsByUsername(String username) {
		return this.utenteRepository.existsByUsername(username);
	}

	// Registrazione di un nuovo utente: verifica che lo username sia libero,
	// codifica la password e forza il ruolo USER
	@Transactional
	public Utente registraNuovoUtente(Utente utente) {
		if (this.existsByUsername(utente.getUsername())) {
			throw new UsernameGiaRegistratoException(utente.getUsername());
		}
		utente.setPassword(this.passwordEncoder.encode(utente.getPassword()));
		utente.setRuolo("USER");
		return this.utenteRepository.save(utente);
	}
}