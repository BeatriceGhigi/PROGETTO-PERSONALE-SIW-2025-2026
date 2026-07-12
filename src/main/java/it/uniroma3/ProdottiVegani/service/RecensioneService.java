package it.uniroma3.ProdottiVegani.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Prodotto;
import it.uniroma3.ProdottiVegani.model.Recensione;
import it.uniroma3.ProdottiVegani.model.Utente;
import it.uniroma3.ProdottiVegani.repository.ProdottoRepository;
import it.uniroma3.ProdottiVegani.repository.RecensioneRepository;
import it.uniroma3.ProdottiVegani.repository.UtenteRepository;

@Service
public class RecensioneService {

	private RecensioneRepository recensioneRepository;
	private ProdottoRepository prodottoRepository;
	private UtenteRepository utenteRepository;

	public RecensioneService(RecensioneRepository recensioneRepository,
	                          ProdottoRepository prodottoRepository,
	                          UtenteRepository utenteRepository) {
		this.recensioneRepository = recensioneRepository;
		this.prodottoRepository = prodottoRepository;
		this.utenteRepository = utenteRepository;
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

	// Crea una nuova recensione, collegandola al prodotto e all'utente autenticato
	@Transactional
	public Recensione salva(Recensione recensione, Long prodottoId, String usernameAutore) {
		// Difensivo: questo metodo deve SEMPRE creare una nuova recensione (INSERT),
		// mai aggiornarne una esistente. Azzeriamo l'id nel caso in cui il form/client
		// abbia inviato un id residuo (es. resubmit di una pagina in cache), altrimenti
		// Spring Data interpreterebbe l'oggetto come "esistente" e tenterebbe un UPDATE,
		// causando un ObjectOptimisticLockingFailureException sulla recensione sbagliata.
		recensione.setId(null);

		Prodotto prodotto = this.prodottoRepository.findById(prodottoId)
			.orElseThrow(() -> new IllegalArgumentException("Prodotto non trovato con id " + prodottoId));

		Utente autore = this.utenteRepository.findByUsername(usernameAutore)
			.orElseThrow(() -> new IllegalArgumentException("Utente non trovato: " + usernameAutore));

		recensione.setProdotto(prodotto);
		recensione.setAutore(autore);
		recensione.setDataCreazione(LocalDateTime.now());
		recensione.setDataUltimaModifica(LocalDateTime.now());

		return this.recensioneRepository.save(recensione);
	}

	// Aggiorna una recensione esistente, SOLO se chi la modifica ne e' l'autore
	@Transactional
	public Recensione aggiorna(Long id, Recensione datiAggiornati, String usernameRichiedente) {
		Recensione recensione = this.recensioneRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Recensione non trovata con id " + id));

		if (!recensione.getAutore().getUsername().equals(usernameRichiedente)) {
			throw new AccessDeniedException("Non puoi modificare una recensione scritta da un altro utente.");
		}

		recensione.setVoto(datiAggiornati.getVoto());
		recensione.setCommento(datiAggiornati.getCommento());
		recensione.setDataUltimaModifica(LocalDateTime.now());

		return recensione;
	}

	// Elimina una recensione, SOLO se chi la elimina ne e' l'autore oppure e' un ADMIN.
	// Stesso principio di autorizzazione gia' applicato in aggiorna(...).
	@Transactional
	public void deleteById(Long id, String usernameRichiedente, boolean isAdmin) {
		Recensione recensione = this.recensioneRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("Recensione non trovata con id " + id));

		boolean isAutore = recensione.getAutore().getUsername().equals(usernameRichiedente);
		if (!isAutore && !isAdmin) {
			throw new AccessDeniedException("Non puoi eliminare una recensione scritta da un altro utente.");
		}

		this.recensioneRepository.delete(recensione);
	}
}