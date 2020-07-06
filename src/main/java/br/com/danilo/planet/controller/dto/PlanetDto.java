package br.com.danilo.planet.controller.dto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.danilo.planet.model.Planet;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PlanetDto {

	public PlanetDto(Planet planet) {
		this.id = planet.getId();
		this.name = planet.getName();
		this.climate = planet.getClimate();
		this.terrain = planet.getTerrain();
		this.NumberFilmsAppeared = planet.getNumberFilmsAppeared();
	}

	private String id;
	private String name;
	private String climate;
	private String terrain;
	private int NumberFilmsAppeared;

	public static Page<PlanetDto> convertList(Page<Planet> planets) {
		return planets.map(PlanetDto::new);
	}

	public static PlanetDto convert(Planet planet) {
		return new PlanetDto(planet);
	}
	
	public static PlanetDto[] convert(Planet[] planets) {
		List<Planet> planetsList = Arrays.asList(planets);
		List<PlanetDto> planetsDtoList = new ArrayList<PlanetDto>();
		
		planetsList.forEach(p -> {
			planetsDtoList.add(PlanetDto.convert(p));			
		});
		
		return planetsDtoList.toArray(new PlanetDto[planetsDtoList.size()]);
	}
}
