package br.com.danilo.planet.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
 
@Data 
@Document
public class Planet {
	@Id 
	private String id;
	private String name;
	private String orbital_period;
	private String rotation_period;
	private String gravity;
	private String population;
	private String climate;
	private String terrain;
	private String surface_water;
	private String[] residents;
	private String[] films;
	private String url;
	private Date created;
	private Date edited;
	private int numberFilmsAppeared;
	
	public Planet(String name, String climate, String terrain) {
		this.name = name;
		this.climate = climate;
		this.terrain = terrain;
	}
}
