package com.b2w.sgbd.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName = Planet.TABLE_NAME)
public class Planet {

	public static final String TABLE_NAME = "Planet";
	public static final String FIELDNAME_ID = "id";
	public static final String FIELDNAME_NAME = "name";
	public static final String FIELDNAME_WHEATER = "wheater";
	public static final String FIELDNAME_GROUND = "ground";
	
	private Long id;
	private String name;	
	private String ground;	
	private String wheater;
	
	@DynamoDBHashKey(attributeName = Planet.FIELDNAME_ID)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@DynamoDBAttribute(attributeName = Planet.FIELDNAME_NAME)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@DynamoDBAttribute(attributeName = Planet.FIELDNAME_WHEATER)
	public String getGround() {
		return ground;
	}

	public void setGround(String ground) {
		this.ground = ground;
	}

	@DynamoDBAttribute(attributeName = Planet.FIELDNAME_WHEATER)
	public String getWheater() {
		return wheater;
	}

	public void setWheater(String wheater) {
		this.wheater = wheater;
	}

}
