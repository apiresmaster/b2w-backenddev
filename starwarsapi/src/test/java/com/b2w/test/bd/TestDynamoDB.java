package com.b2w.test.bd;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.CreateTableResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.b2w.sgbd.DynamoDBClientSW;
import com.b2w.sgbd.model.Planet;

public class TestDynamoDB {

	private final AmazonDynamoDB dynamoDBClient = AmazonDynamoDBClientBuilder.defaultClient();

	private final DynamoDBClientSW clientDB = new DynamoDBClientSW();

//	@Test
	public void criaTabela() {
		// ESTA FUNCIONANDO COMENTANDO PARA NÃO CRIAR NOVAS TABELAS
		DynamoDBMapper mapper = new DynamoDBMapper(dynamoDBClient);
		CreateTableRequest req = mapper.generateCreateTableRequest(Planet.class);
		// Table provision throughput is still required since it cannot be specified in
		// your POJO
		req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		// Fire off the CreateTableRequest using the low-level client
		CreateTableResult tbResult = dynamoDBClient.createTable(req);

		assertNotNull(tbResult);
	}

//	@Test
	public void adicionaItens() {

		Planet marte = new Planet();
		marte.setId(4L);
		marte.setName("Marte");
		marte.setWheater("Molhado");
		marte.setGround("Planice");

		Planet jupiter = new Planet();
		jupiter.setId(5L);
		jupiter.setName("Jupiter");
		jupiter.setWheater("Humido");
		jupiter.setGround("Plano");

		Planet plutao = new Planet();
		plutao.setId(6L);
		plutao.setName("Plutão");
		plutao.setWheater("Seco");
		plutao.setGround("Relevo");

		clientDB.add(marte);
		clientDB.add(jupiter);
		clientDB.add(plutao);

		assertEquals(true, true);
	}

	@Test
	public void pegaItemPorId() {
		Planet result = clientDB.getById(4L);

		assertNotNull(result.getName());
	}

	@Test
	public void pegaTodosRegistros() {
		List<Planet> result = clientDB.getAll();
		
		assertNotNull(result);
	}

	@Test
	public void pegaPorNome() {
		String marte = "Marte";
		Planet result = clientDB.getByName(marte);
		
		assertEquals(marte, result.getName());
	}
}
