package br.com.danilo.planet.controller;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import br.com.danilo.planet.model.Planet;
import br.com.danilo.planet.service.PlanetApiService;
import br.com.danilo.planet.service.PlanetService;
import io.restassured.http.ContentType;

@WebMvcTest
public class PlanetControllerTest {
	
	@Autowired
	private PlanetController planetController;
	
	@MockBean
	private PlanetService planetService;
	
	@MockBean
	private PlanetApiService planetApiService;
	
	@MockBean
	private WebClient webClient;

	@BeforeEach
	public void setup() {
		standaloneSetup(this.planetController);
	}
	
	@Test
	public void mustReturnSuccess_WhenGetPlanet( ) {
			
		when(this.planetService.planetById("1"))
			.thenReturn(new Planet("Terra", "hot", "Water"));
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/planets/{id}", "1")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void mustReturnNOTFOUND_WhenGetPlanet( ) {
			
		when(this.planetService.planetById("2"))
			.thenThrow(IllegalArgumentException.class);
		
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/planets/{id}", "2")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	/*TDD testing continues here*/
	
}
