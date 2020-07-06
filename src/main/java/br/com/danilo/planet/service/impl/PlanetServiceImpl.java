package br.com.danilo.planet.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.danilo.planet.model.Planet;
import br.com.danilo.planet.repository.PlanetRepository;
import br.com.danilo.planet.service.PlanetApiService;
import br.com.danilo.planet.service.PlanetService;

@Service
public class PlanetServiceImpl implements PlanetService {

	@Autowired
	private PlanetRepository planetRepository;

	@Autowired
	private PlanetApiService planetApiService;

	@Override
	public List<Planet> planetAll() {
		return this.planetRepository.findAll();
	}

	@Override
	public Planet planetById(String codigo) {
		return this.planetRepository.findById(codigo)
				.orElseThrow(() -> new IllegalArgumentException("Planet not found"));
	}

	@Override
	public Page<Planet> planetByName(String name, Pageable pageable) {
		return this.planetRepository.findByName(name, pageable);
	}

	@Override
	public Planet create(Planet planet) {
		Planet planetByName = planetApiService.planetByName(planet.getName());

		if (planetByName != null) {
			List<String> planetFilmsUrls = Arrays.asList(planetByName.getFilms());
			planet.setNumberFilmsAppeared(planetFilmsUrls.size());
		}

		planet = this.planetRepository.save(planet);
		return planet;
	}

	@Override
	public void delete(Planet planet) {
		this.planetRepository.delete(planet);
	}

	@Override
	public Page<Planet> planetAll(Pageable pageable) {
		return this.planetRepository.findAll(pageable);
	}

}
