package br.com.danilo.planet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootApplication
@EnableCaching
@EnableSwagger2
public class PlanetApplication {

	@Bean
	public WebClient webClient(WebClient.Builder builder) {
		return builder.baseUrl("https://swapi.dev/api/")
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_STREAM_JSON_VALUE).build();
	}

	public static void main(String[] args) {
		SpringApplication.run(PlanetApplication.class, args);
	}

}