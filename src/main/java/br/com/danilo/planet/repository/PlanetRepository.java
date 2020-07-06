package br.com.danilo.planet.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.danilo.planet.model.Planet;

public interface PlanetRepository extends MongoRepository<Planet, String>{
	
	public List<Planet> findByName(String name);

	public Page<Planet> findByName(String name, Pageable pageable);
}
