package br.com.danilo.planet.service;

import org.springframework.stereotype.Component;

import br.com.danilo.planet.model.Planet;
import br.com.danilo.planet.model.PlanetApi;

@Component
public interface PlanetApiService {


	//public PlanetApi planetsApiQueryString(String chave, String page);

	public PlanetApi planetsApiAll();

	public Planet planetByName(String planetName);

	public PlanetApi planetsByPage(Integer pageNumber);

}
