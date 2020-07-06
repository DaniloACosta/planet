package br.com.danilo.planet.controller.dto;

import java.util.HashMap;
import java.util.Map;

import br.com.danilo.planet.model.PlanetApi;
import lombok.Getter;

@Getter
public class PlanetApiDto {

	private PlanetDto[] content;
	private String nextPage;

	public PlanetApiDto(PlanetApi planetsApi) {
		this.nextPage = getNumberNextPage(planetsApi.getNext(), "page");
		this.content = PlanetDto.convert(planetsApi.getResults());
	}

	private String getNumberNextPage(String urlNextPage, String queryParameter) {
		
		if (urlNextPage == null) {
			return null;
		}
		
		Map<String, String> parameterURL = getParameterURL(urlNextPage);
		
		return parameterURL.get(queryParameter);
	}

	public static Map<String, String> getParameterURL(String query) {
		query = query.substring(query.indexOf('?')).replace("?", "");
		String[] params = query.split("/");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String[] p = param.split("=");
			String name = p[0];
			if (p.length > 1) {
				String value = p[1];
				map.put(name, value);
			}
		}
		return map;
	}

	public static PlanetApiDto convert(PlanetApi planetsApi) {
		return new PlanetApiDto(planetsApi);
	}
}