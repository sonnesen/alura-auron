package org.alura.auron.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.alura.auron.models.Participante;

@Stateless
public class ParticipanteDao {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Participante participante) {
		em.persist(participante);
	}

	public List<Participante> getParticipantes() {
		return em.createQuery("from Participante", Participante.class).getResultList();
	}

	public Participante getParticipante(String username, String password) {
		TypedQuery<Participante> query = em.createQuery(
				"from Participante p "
				+ "where p.email = :username "
				+ "and p.senha = :password", Participante.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		return query.getSingleResult();
	}

}
