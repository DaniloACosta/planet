package br.com.danilo.planet.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.danilo.planet.controller.dto.PlanetDto;
import br.com.danilo.planet.controller.form.PlanetForm;
import br.com.danilo.planet.model.Planet;
import br.com.danilo.planet.service.PlanetService;

@RestController
@RequestMapping("/planets")
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@GetMapping
	public ResponseEntity<Page<PlanetDto>> list(@RequestParam(required = false) String namePlanet, 
			@PageableDefault(page = 0, size = 10) Pageable pageable) {
		
		Page<Planet> planets;
		if (namePlanet == null)
			planets = planetService.planetAll(pageable);
		else
			planets = planetService.planetByName(namePlanet, pageable);

		return ResponseEntity.ok(PlanetDto.convertList(planets));
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanetDto> detail(@PathVariable String id) {
		try {
			Planet planet = planetService.planetById(id);
			return ResponseEntity.ok(PlanetDto.convert(planet));
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping
	@Transactional
	public ResponseEntity<PlanetDto> create(@RequestBody @Valid PlanetForm form,
			UriComponentsBuilder uriComponentsBuilder) {
		Planet planet = form.convert();
		planetService.create(planet);

		URI location = uriComponentsBuilder.path("/planets/{id}").buildAndExpand(planet.getId()).toUri();
		return ResponseEntity.created(location).body(new PlanetDto(planet));
	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> delete(@PathVariable String id) {
		Planet planet;
		try {
			planet = planetService.planetById(id);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}

		planetService.delete(planet);
		return ResponseEntity.ok().build();
	}

}
