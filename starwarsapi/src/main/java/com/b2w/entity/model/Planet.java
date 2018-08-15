package com.b2w.entity.model;

import java.io.Serializable;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * Planet model to persistence information.
 * @author apiresmaster
 *
 */
@Entity
@Table(uniqueConstraints = 
@UniqueConstraint(columnNames = "name", name = "name_uk"))
public class Planet implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String ground;

	@Column(nullable = false)
	private String weather;

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

}
