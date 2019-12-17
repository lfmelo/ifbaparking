package model.dao;

import javax.persistence.EntityManager;

import connection.ConnectionFactory;

public abstract class DAO {

	EntityManager em;
	
	public DAO() {
		em = new ConnectionFactory().getConnection();
	}
	
	public void iniciarTransacao() {
		if (em.getTransaction().isActive() == false) {
			em.getTransaction().begin();
		}
	}
	
	public void rollbackTransacao() {
		em.getTransaction().rollback();
	}

	public void commitTransacao() {
		em.getTransaction().commit();
	}
	
	public void closeTransacao() {
		em.close();
	}
	
}
