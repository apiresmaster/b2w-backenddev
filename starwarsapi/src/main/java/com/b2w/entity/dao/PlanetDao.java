package com.b2w.entity.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.QueryException;

import com.b2w.entity.Operacoes;
import com.b2w.entity.PersistenceUtil;
import com.b2w.entity.model.Planet;

public class PlanetDao implements Operacoes {

	@Override
	public Planet getById(Long id) {
		
		return PersistenceUtil.getEntityManager().find(Planet.class, id);
	}

	@Override
	public Planet getByName(String name) {
		try {
		
		return PersistenceUtil.getEntityManager()
					.createQuery("select p from Planet p where p.name = :name", Planet.class)
					.setParameter("name", name)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
	}

	@Override
	public List<Planet> getAll() {
		
		try {
			
		return PersistenceUtil.getEntityManager()
					.createQuery("select p from Planet p", Planet.class)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			throw new QueryException(e);
		}
	}

	@Override
	public Long add(Planet planet) {
		
		EntityManager em = PersistenceUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.persist(planet);
			em.getTransaction().commit();
		
		}catch (Exception e) {
			e.getStackTrace();
			em.getTransaction().rollback();
		
		}finally {
			em.close();
		}

		return planet.getId();
	}

	@Override
	public Planet update(Planet planet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Planet planet) {
		EntityManager em = PersistenceUtil.getEntityManager();
		
		try {
			em.getTransaction().begin();
			em.remove(planet);
			em.getTransaction().commit();
		
		}catch (Exception e) {
			e.getStackTrace();
			em.getTransaction().rollback();
		
		}finally {
			em.close();
		}
	}

}
