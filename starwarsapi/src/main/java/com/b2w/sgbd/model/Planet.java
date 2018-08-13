package com.b2w.sgbd.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = Planet.TABLE_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
public class Planet {

	public static final String TABLE_NAME = "Planet";
//	public static final String FIELDNAME_ID = "id";
	public static final String FIELDNAME_NAME = "name";
	public static final String FIELDNAME_WEATHER = "weather";
	public static final String FIELDNAME_GROUND = "ground";
	
//	@XmlTransient()
//	@JsonbTransient
//	private Long id;
	private String name;	
	private String ground;	
	private String weather;
	
//	@DynamoDBHashKey(attributeName = Planet.FIELDNAME_ID)
//	@XmlTransient
//	@JsonbTransient
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	@DynamoDBHashKey(attributeName = Planet.FIELDNAME_NAME)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName = Planet.FIELDNAME_GROUND)
	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	@DynamoDBAttribute(attributeName = Planet.FIELDNAME_WEATHER)
	public String getWeather() {
		return weather;
	}

	public void setWeather(String wheater) {
		this.weather = wheater;
	}

}
