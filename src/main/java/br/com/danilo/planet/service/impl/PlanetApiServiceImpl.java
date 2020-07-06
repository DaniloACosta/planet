package br.com.danilo.planet.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.danilo.planet.model.Planet;
import br.com.danilo.planet.model.PlanetApi;
import br.com.danilo.planet.service.PlanetApiService;
import reactor.core.publisher.Mono;

@Service
public class PlanetApiServiceImpl implements PlanetApiService {

	@Autowired
	private WebClient webClient;

	@Cacheable(value = "planetsApi")
	private PlanetApi planetsApiQueryString(String key, String value) {
		
		Mono<PlanetApi> monoPlanetApi = this.webClient.method(HttpMethod.GET)
				.uri(uriBuilder -> uriBuilder
						.path("/planets/")
						.queryParam(key, value)
						.build())
				.retrieve().bodyToMono(PlanetApi.class);

		PlanetApi resultplanetsApi = monoPlanetApi.block();
		
		//List<Planet> planets = new ArrayList<Planet>(Arrays.asList(resultplanetsApi.getResults()));

		return resultplanetsApi;
	}

	@Override
	public PlanetApi planetsApiAll() {
		return this.planetsApiQueryString("", "");
	}

	@Override
	public Planet planetByName(String planetName) {
		List<Planet> planets = new ArrayList<Planet>(Arrays.asList(planetsApiQueryString("search", planetName).getResults()));
		return planets.isEmpty() ? null : planets.get(0);
	}

	@Override
	public PlanetApi planetsByPage(Integer pageNumber) {
		return planetsApiQueryString("page", Integer.toString(pageNumber));
	}
}
