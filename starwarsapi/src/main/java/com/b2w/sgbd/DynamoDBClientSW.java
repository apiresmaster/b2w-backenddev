package com.b2w.sgbd;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.services.dynamodbv2.model.ScanResult;
import com.b2w.sgbd.model.Planet;

public class DynamoDBClientSW implements Operacoes{

	private final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();

	@Override
	public Planet getById(Long id) {
		
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		
		return mapper.load(Planet.class, id);
	}

	@Override
	public Planet getByName(String name) {
		
		Condition scanFilter = new Condition()
				.withComparisonOperator(ComparisonOperator.CONTAINS)
				.withAttributeValueList(new AttributeValue().withS(name));
		
		Map<String, Condition> conditions = new HashMap<String, Condition>();
		conditions.put("name", scanFilter);
		
		//TODO igual ao metodo do getAll. Rever
		ScanRequest scanRequest = new ScanRequest().withTableName(Planet.TABLE_NAME)
													.withScanFilter(conditions);
		
		ScanResult result = dynamoDBClient.scan(scanRequest);
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
	
		List<Planet> retorno = mapper.marshallIntoObjects(Planet.class, result.getItems());
		
		return retorno.get(0);
	}

	@Override
	public List<Planet> getAll() {
		
		ScanRequest scanRequest = new ScanRequest().withTableName(Planet.TABLE_NAME);
		ScanResult result = dynamoDBClient.scan(scanRequest);
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		
		List<Planet> itens = mapper.marshallIntoObjects(Planet.class, result.getItems());
		return itens;
	}

	@Override
	public void add(Planet planet) {
		
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		mapper.save(planet);
	}

	@Override
	public Planet update(Planet planet) {

		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		mapper.save(planet);
	        
        return mapper.load(Planet.class, planet.getId(), DynamoDBMapperConfig.ConsistentReads.CONSISTENT);
	}

	@Override
	public Boolean delete(Planet planet) {
		
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		mapper.delete(planet);
		
		Planet isDeleted = mapper.load(Planet.class, planet.getId());
		
		return (isDeleted == null) ? true : false;
	}
}
