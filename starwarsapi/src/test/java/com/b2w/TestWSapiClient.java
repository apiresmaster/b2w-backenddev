package com.b2w;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.junit.Test;

import com.b2w.service.client.model.PlanetCli;

public class TestWSapiClient {

	@Test
	public void getPlanetById() {
		String URL_SERVICE = "https://swapi.co/api/planets/3/?format=json";
		Client client = ClientBuilder.newClient();
		WebTarget webTarget = client.target(URL_SERVICE);//.path("planets").path(String.valueOf(3));
		 
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
 
		@SuppressWarnings("unused")
		PlanetCli output = invocationBuilder.get(PlanetCli.class);
		
		System.out.println(output.getFilms().toString());
		Integer ok = 200;
		assertEquals(ok, Integer.valueOf(invocationBuilder.head().getStatus()));
	}
}
