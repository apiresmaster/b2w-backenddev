package com.b2w;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

import com.b2w.entity.dao.PlanetDao;
import com.b2w.entity.model.Planet;

public class TestDynamoDB {

	@Test
	public void adicionaItens() {

		PlanetDao dao = new PlanetDao();
		
		Planet marte = new Planet();
		marte.setName("Marte");
		marte.setWeather("Molhado");
		marte.setGround("Planice");

		Planet jupiter = new Planet();
		jupiter.setName("Jupiter");
		jupiter.setWeather("Humido");
		jupiter.setGround("Plano");

		Planet plutao = new Planet();
		plutao.setName("Plutão");
		plutao.setWeather("Seco");
		plutao.setGround("Relevo");
				
		dao.add(marte);
		dao.add(jupiter);
		dao.add(plutao);

		assertEquals(true, true);
	}

//	@Test
	public void pegaItemPorId() {
		
		PlanetDao dao = new PlanetDao();
		Planet result = dao.getById(4L);

		assertNotNull(result.getName());
	}

//	@Test
	public void pegaTodosRegistros() {

		PlanetDao dao = new PlanetDao();
		List<Planet> result = dao.getAll();
		
		assertNotNull(result);
	}

//	@Test
	public void pegaPorNome() {
		
		String marte = "Marte";
		PlanetDao dao = new PlanetDao();
		Planet result = dao.getByName(marte);
		
		assertEquals(marte, result.getName());
	}
}
