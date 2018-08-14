package com.b2w;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.b2w.swapi.client.view.SwapiPlanetSearchView;

public class TestWSapiClient {

	@Test
	public void searchPlanetByName() {
		String URL_SERVICE = "https://swapi.co/api/";
		String API_RESOURCE = "planets/";
		String API_QUERY_PARAM_NAME = "search";
		String API_QUERY_PARAM_NAME_VALUE = "Alderaan";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(URL_SERVICE).path(API_RESOURCE).queryParam(API_QUERY_PARAM_NAME, API_QUERY_PARAM_NAME_VALUE);
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON); 
		@SuppressWarnings("unused")
		SwapiPlanetSearchView output = invocationBuilder.get(SwapiPlanetSearchView.class);
		
		System.out.println("Filmes recuperados: "+output.getResults().get(0).getFilms().toString());
		Integer ok = 200;
		assertEquals(ok, Integer.valueOf(invocationBuilder.head().getStatus()));
	}
}
