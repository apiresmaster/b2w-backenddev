package com.b2w.resource;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.util.ArrayList;
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

import org.apache.commons.beanutils.BeanUtils;

import com.b2w.entity.dao.PlanetDao;
import com.b2w.entity.model.Planet;
import com.b2w.service.client.service.PlanetCliService;
import com.b2w.service.server.SwapiPlanetView;


@Path(PlanetResource.PATH_PLANETS)
public class PlanetResource {
	
	public static final String PATH_PLANETS = "planets";
		
	private PlanetDao planetService;
	
	public PlanetResource() {
		planetService = new PlanetDao();
	}
	
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<SwapiPlanetView> getAll() {
    	
    	List<SwapiPlanetView> views = new ArrayList<SwapiPlanetView>();
    	List<Planet> planets = planetService.getAll();
    	try {
    		for (Planet source : planets) {
    			SwapiPlanetView target = new SwapiPlanetView(); 
    			BeanUtils.copyProperties(source, target);
    			PlanetCliService planetClient = new PlanetCliService();
    			planetClient.consulta(target.getName());
    			
    			views.add(target);
    		}
			
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return views;
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public SwapiPlanetView getByName(@PathParam("name") String name) {
    	
    	SwapiPlanetView view = new SwapiPlanetView();
    	Planet origem = planetService.getByName(name);
    	try {
			BeanUtils.copyProperties(origem, view);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	return view;
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
    	    	
    	planetService.add(planet);
    	//Pegar nome completo do recurso
    	//Remover o código do ID que esta fixo
    	String uriRecurso = String.format("/starwarsapi/planets/%d", planet.getId());
    	
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
    	
    	Planet p = planetService.getByName(name);
    	planetService.delete(p);
    }
}
