package com.b2w.resource;

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

import com.b2w.entity.model.Planet;
import com.b2w.resource.view.PlanetView;

/**
 * Resource to request information about Planet
 * @author apiresmaster
 *
 */
@Path(PlanetResource.PATH_PLANETS)
@Produces(MediaType.APPLICATION_JSON)
public class PlanetResource {
	
	public static final String PATH_PLANETS = "planets";
		
	private PlanetService planetService;
	
	public PlanetResource() {
		planetService = new PlanetService();
	}
	
    @GET
    public List<PlanetView> getAll() {
    	
    	return planetService.getAll();    	
    }

    @GET
    @Path("/{name}")
    public PlanetView getByName(@PathParam("name") String name) {
    	
    	return planetService.getByName(name);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(PlanetView planetView) {
    	
    	if (planetView.getName() == null || planetView.getName().trim().equals("")) {
    		throw new WebApplicationException(Response
    					.status(Response.Status.BAD_REQUEST)
    					.entity("O nome do contato é obrigatório").build());
    	}
    	
    	PlanetView exist = planetService.getByName(planetView.getName());
    	
    	if(!exist.getName().isEmpty())
    		return Response.ok("/starwarsapi/planets/"+exist.getId()).build();
    	    	
    	planetService.add(planetView);
    	String uriRecurso = String.format("/starwarsapi/planets/%d", planetView.getId());
    	
    	return Response.created(URI.create(uriRecurso)).build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void update(Planet planet) {
    	System.out.printf("objeto recebido com sucesso | %s", planet.getName());
    }
    
    @DELETE
    @Path("/{name}")
    public Response delete(@PathParam("name") String name) {
    	
    	planetService.delete(name);
    	    	
    	return Response.noContent().build();
    }
}
