package com.b2w.sw.server.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.b2w.sgbd.DynamoDBClientSW;
import com.b2w.sgbd.model.Planet;


@Path("planets")
public class PlanetResource {
		
	private DynamoDBClientSW entityDB;
	
	public PlanetResource() {
		entityDB = new DynamoDBClientSW();
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Planet> getAll() {
    	
    	return entityDB.getAll();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Planet getByName(@PathParam("name") String name) {
    	return entityDB.getByName(name);
    }
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Planet planet) {
    	if (planet.getName() == null || planet.getName().trim().equals("")) {
    		throw new WebApplicationException(Response
    					.status(Response.Status.BAD_REQUEST)
    					.entity("O nome do contato é obrigatório").build());
    	}
    	
//    	if(createAndLoadData().contains(planet))
//    		throw new WebApplicationException(Response
//    				.status(Response.Status.CONFLICT)
//    				.entity("Planeta já existe no cadastro").build());
    		
    	//Pegar nome completo do recurso
    	//Remover o código do ID que esta fixo
    	String uriRecurso = String.format("/starwarsapi/planets/%d", 100);
    	//TODO avaliar o retorno do ID do objeto inserido para usar no cabeçalho de retorno
    	entityDB.add(planet);
    	
    	return Response.created(URI.create(uriRecurso)).build();
    }
    
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Planet planet) {
    	System.out.printf("objeto recebido com sucesso | %s", planet.getName());
    }
    
    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("name") String name) {
    	Planet planet = entityDB.getByName(name);
    	entityDB.delete(planet);
    }
}
