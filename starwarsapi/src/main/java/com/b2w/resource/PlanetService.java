package com.b2w.resource;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.QueryException;

import com.b2w.entity.dao.PlanetDao;
import com.b2w.entity.model.Planet;
import com.b2w.resource.view.PlanetView;
import com.b2w.swapi.client.SwapiPlanetClient;
import com.b2w.swapi.client.view.SwapiPlanetSearchView;

public class PlanetService  implements Serializable {

	private PlanetDao dao;
	public PlanetService() {
		dao = new PlanetDao();
	}
	public List<PlanetView> getAll() {
		
    	List<PlanetView> viewResults = new ArrayList<PlanetView>();
    	
    	try {
    		for (Planet source : dao.getAll()) {
    			PlanetView target = new PlanetView(); 
    			BeanUtils.copyProperties(target, source);
    			
    			SwapiPlanetClient swapiPlanetClient = new SwapiPlanetClient(MediaType.APPLICATION_JSON_TYPE);
    			SwapiPlanetSearchView swapiSearchResult = swapiPlanetClient.getPlanetByName(target.getName());
    			
    			target.setQuantidadeFilmes(swapiSearchResult.getResults().get(0).getFilms().size());
    			viewResults.add(target);
    		}
		    	
		} catch (IllegalAccessException | InvocationTargetException | QueryException e) {
			
			e.printStackTrace();
		}
    	
    	return viewResults;
	}
	
	public PlanetView getByName(String name) {
		
		PlanetView view = new PlanetView();
    	Planet origem;

    	try {
    		origem = dao.getByName(name);
    		
			BeanUtils.copyProperties(view, origem);
			
		} catch (IllegalAccessException | InvocationTargetException | QueryException e) {
			
			e.printStackTrace();
			 view = new PlanetView();
		}
    	
    	return view;
	}
	public void delete(String name) {
		
    	Planet p = dao.getByName(name);
    	dao.delete(p);    	
	}
	public void add(PlanetView planetView) {
		
		try {
			Planet planet = null;
			BeanUtils.copyProperties(planet, planetView);		
			dao.add(planet);
			
		} catch (IllegalAccessException | InvocationTargetException | QueryException e) {
			
			e.printStackTrace();
		}
				
	}

	
}
