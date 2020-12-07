package org.alura.auron.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.alura.auron.dao.ParticipanteDao;
import org.alura.auron.dao.SorteioDao;
import org.alura.auron.models.Par;
import org.alura.auron.models.Participante;
import org.alura.auron.models.Sorteador;
import org.alura.auron.models.Sorteio;

import lombok.Getter;

@Named
@RequestScoped
public class SorteioBean {

	@Inject
	private ParticipanteDao participanteDao;

	@Inject
	private SorteioDao sorteioDao;

	@Getter
	private Sorteio sorteio = new Sorteio();

	public void sortear() {
		try {
			List<Participante> participantes = participanteDao.getParticipantes();
			Sorteador sorteador = new Sorteador(sorteio, participantes);
			sorteador.sortear();
			sorteioDao.salvar(sorteio);
		}
		catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public List<Par> getPares() {
		return sorteioDao.listarPares();
	}
}
