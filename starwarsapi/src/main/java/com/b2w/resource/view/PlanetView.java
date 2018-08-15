package com.b2w.resource.view;

import javax.json.bind.annotation.JsonbPropertyOrder;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.config.PropertyOrderStrategy;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Render view of Planet
 * @author apiresmaster
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonbPropertyOrder(PropertyOrderStrategy.LEXICOGRAPHICAL)
public class PlanetView {

	@XmlTransient()
	@JsonbTransient
	private Long id;

	private String name;

	private String ground;

	private String weather;
	
	private Integer countFilms;
	
	private String URI;
	
	public PlanetView() {
		this.name = new String();
	}
	
	public PlanetView(Long id, String name, String ground, String weather, Integer countFilms, String URI) {
		this.id = id;
		this.name = name;
		this.ground = ground;
		this.weather = weather;
		this.countFilms = countFilms;
		this.URI = URI;
	}

	@XmlTransient
	@JsonbTransient
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String wheater) {
		this.weather = wheater;
	}

	public Integer getQuantidadeFilmes() {
		return countFilms;
	}

	public void setQuantidadeFilmes(Integer quantidadeFilmes) {
		this.countFilms = quantidadeFilmes;
	}	

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
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
		PlanetView other = (PlanetView) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
}
