package com.b2w.sgbd;

import java.util.List;

import com.b2w.sgbd.model.Planet;

/**
 * Realiza operações básicas em um model
 * @author apiresmaster
 *
 */
public interface Operacoes {

	/**
	 * Return the entity by ID
	 * @param id Identify of entity
	 * @return Entity
	 */
	public Planet getById(Long id);
	
	public Planet getByName(String name);
	
	public List<Planet> getAll();
	
	public void add(Planet planet);
	
	public Planet update(Planet planet);
	
	public Boolean delete(Planet planet);
}
