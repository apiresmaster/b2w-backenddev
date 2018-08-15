package com.b2w.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.b2w.resource.view.PlanetView;

/**
 * Resource to request information about Planet
 * @author apiresmaster
 *
 */
@Path(PlanetResource.PATH_PLANETS)
@Produces(MediaType.APPLICATION_JSON)
public class PlanetResource {
	
    @Context
    private UriInfo info;
    
	public static final String PATH_PLANETS = "planets";
		
	private PlanetService planetService;
	
	public PlanetResource() {
		planetService = new PlanetService();
	}
	
    @GET
    public List<PlanetView> getAll() {
    	
    	List<PlanetView> results = planetService.getAll();
    	for (PlanetView planetView : results) {
    		planetView.setURI(createPlanetViewURI(planetView.getId()));
		}
    	
    	return results;
    }

    @GET
    @Path("/{id}")
    public PlanetView getById(@PathParam("id") Long id) {
    	
    	PlanetView result = planetService.getById(id);
    	result.setURI(createPlanetViewURI(result.getId()));
    	
    	return result;
    }

    @GET
    @Path("/query")
    public PlanetView getByName(@QueryParam("name") String name) {
    	
    	PlanetView result = planetService.getByName(name);
    	result.setURI(createPlanetViewURI(result.getId()));
    	
    	return result;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(PlanetView planetView) {
    	
    	if (planetView.getName() == null || planetView.getName().trim().equals("")) {
    		throw new WebApplicationException(Response
    					.status(Response.Status.BAD_REQUEST)
    					.entity("The name is required").build());
    	}
    	
    	PlanetView exist = planetService.getByName(planetView.getName());
    	
    	if(!exist.getName().isEmpty()) {
    		String uri = createPlanetViewURI(exist.getId());
    		return Response.created(URI.create(uri)).build();
    	}
    	
    	Response.created(URI.create(exist.getURI()));
    	    	
    	Long id = planetService.add(planetView);
    	
    	return Response.created(URI.create(createPlanetViewURI(id))).build();
    }
    
    @DELETE
    @Path("/{name}")
    public Response delete(@PathParam("name") String name) {
    	
    	planetService.delete(name);
    	    	
    	return Response.noContent().build();
    }
    
	private String createPlanetViewURI(Long id) {
		URI result = info.getBaseUriBuilder().path(PlanetResource.class).build();
		
		return String.format("%s/%d", result.toASCIIString(), id);
	}
}
