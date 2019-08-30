package br.com.caelum.ingresso.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.mail.Token;

@Repository
public class TokenDao {

	@PersistenceContext
	private EntityManager manager;

	public void save(Token token) {
		manager.persist(token);
	}
}
