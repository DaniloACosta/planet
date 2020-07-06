package br.com.danilo.planet.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.com.danilo.planet.model.Planet;

@Component
public interface PlanetService {
	
	public List<Planet> planetAll();
	public Planet planetById(String id);
	public Page<Planet> planetByName(String name, Pageable pageable);
	public Planet create(Planet planet);
	public void delete(Planet planet);
	public Page<Planet> planetAll(Pageable pageable);
}
