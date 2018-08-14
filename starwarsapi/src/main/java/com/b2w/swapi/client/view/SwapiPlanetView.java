package com.b2w.swapi.client.view;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class SwapiPlanetView {

	private String name;
	private List<String> films;
	
	public SwapiPlanetView() {
		
	}
	
	@JsonbProperty
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@JsonbProperty
	public List<String> getFilms() {
		return films;
	}
	
	public void setFilms(List<String> films) {
		this.films = films;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SwapiPlanetView other = (SwapiPlanetView) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SwapiPlanetView [name=" + name + ", films=" + films + "]";
	}	
	
	
}
