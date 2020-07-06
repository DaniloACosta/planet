package br.com.danilo.planet.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.danilo.planet.model.Planet;

public class PlanetForm {

	@NotNull
	@NotEmpty
	@Size(min = 2)
	private String name;
	@NotNull
	@NotEmpty
	@Size(min = 2)
	private String climate;
	@NotNull
	@NotEmpty
	@Size(min = 2)
	private String terrain;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClimate() {
		return climate;
	}

	public void setClimate(String climate) {
		this.climate = climate;
	}

	public String getTerrain() {
		return terrain;
	}

	public void setTerrain(String terrain) {
		this.terrain = terrain;
	}

	public Planet convert() {
		return new Planet(name, climate, terrain);
	}
}
