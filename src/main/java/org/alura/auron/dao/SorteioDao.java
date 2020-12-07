package org.alura.auron.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.alura.auron.models.Par;
import org.alura.auron.models.Sorteio;

@Stateless
public class SorteioDao {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Sorteio sorteio) {
		em.persist(sorteio);
	}

	public List<Par> listarPares() {
		return em.createQuery("from Par", Par.class).getResultList();
	}
}
