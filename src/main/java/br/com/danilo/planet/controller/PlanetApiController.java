package br.com.danilo.planet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import br.com.danilo.planet.controller.dto.PlanetApiDto;
import br.com.danilo.planet.model.PlanetApi;
import br.com.danilo.planet.service.PlanetApiService;

@RestController
@RequestMapping("/planetsApi")
public class PlanetApiController {
	@Autowired
	private PlanetApiService planetApiService;

	@GetMapping
	public ResponseEntity<PlanetApiDto> list(
			@RequestParam(required = false, defaultValue = "1") Integer page) {
		try {
			PlanetApi planetsApi = planetApiService.planetsByPage(page);
			return ResponseEntity.ok(PlanetApiDto.convert(planetsApi));
		} catch (WebClientResponseException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
