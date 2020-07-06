package br.com.danilo.planet.model;

import lombok.Data;

@Data
public class PlanetApi {
	private int count;
	private String next;
	private String previous;
	private Planet[] results;
}
