package com.b2w.service.client.service;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.b2w.service.client.model.PlanetCli;

public class PlanetCliService {
	
	/**GERENCIA A INFRAESTRUTURA DE COMUNIÇÃO DO LADO 
	 * CLIENTE PARA EXECUTAR AS SOLICITAÇÕES REALIZADAS*/
	private Client client;
 
	/**ACESSA UM RECURSO IDENTIFICADO PELO URI(Uniform Resource Identifier/Identificador Uniforme de Recursos)*/
	private WebTarget webTarget;
 
	/**URL DO SERVIÇO REST QUE VAMOS ACESSAR */
	private final String URL_SERVICE = "http://swapi.co/api/";
 
	/**CONSTRUTOR DA NOSSA CLASSE*/
	public PlanetCliService(){ 
		this.client = ClientBuilder.newClient();  
	}	
	
	public PlanetCli consulta(String nome) {
				
		//try {
	
			this.webTarget = this.client.target(URL_SERVICE).path("planets").path(String.valueOf(3));
			 
			Invocation.Builder invocationBuilder =  this.webTarget.request(MediaType.APPLICATION_JSON);
	 
			PlanetCli output = invocationBuilder.get(PlanetCli.class);
	 
			return output;	
	}
}
