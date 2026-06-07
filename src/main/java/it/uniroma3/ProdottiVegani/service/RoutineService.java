package it.uniroma3.ProdottiVegani.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.uniroma3.ProdottiVegani.model.Routine;
import it.uniroma3.ProdottiVegani.repository.RoutineRepository;

@Service
public class RoutineService {
	private RoutineRepository routineRepository;
	
	public RoutineService(RoutineRepository routineRepository) {
		this.routineRepository=routineRepository;
	}
	
	@Transactional(readOnly = true)
    public List<Routine> findAll() {
        List<Routine> routine = new ArrayList<>();
        this.routineRepository.findAll().forEach(routine::add);
        return routine;
    }

    @Transactional(readOnly = true)
    public Optional<Routine> findById(Long id) {
        return this.routineRepository.findById(id);
    }
}
