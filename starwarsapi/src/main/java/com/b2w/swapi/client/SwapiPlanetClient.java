package com.b2w.swapi.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.b2w.swapi.client.view.SwapiPlanetSearchView;

/**
 * Resource client of Swapi client to request methods 
 * @author apiresmaster
 *
 */
public class SwapiPlanetClient {
	
	private Client client; 
	private WebTarget webTarget;
	private MediaType mediaType;
 
	/**URL DO SERVIÇO REST QUE VAMOS ACESSAR */
	private final String URL_SERVICE = "https://swapi.co/api/";
	private final String PLANET_RESOURCE = "planets/";
	private final String API_QUERY_PARAM_SEARCH = "search";
 
	/**CONSTRUTOR DA NOSSA CLASSE*/	
	public SwapiPlanetClient(MediaType mediaType) {
		
		this.client = ClientBuilder.newClient();
		this.mediaType = mediaType;
	}
	
	/**
	 * Get the result search by name of Planet
	 * @param name The Planet name
	 * @return View Swapi content the result of search 
	 */
	public SwapiPlanetSearchView getPlanetByName(String name) {
	
		this.webTarget = this.client.target(URL_SERVICE)
									.path(PLANET_RESOURCE)
									.queryParam(API_QUERY_PARAM_SEARCH, name);
		
		Invocation.Builder invocationBuilder =  this.webTarget.request(this.mediaType);
		
		return invocationBuilder.get(SwapiPlanetSearchView.class);
				 	
	}
}
