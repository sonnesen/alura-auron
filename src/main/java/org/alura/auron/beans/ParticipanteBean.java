package org.alura.auron.beans;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.alura.auron.dao.ParticipanteDao;
import org.alura.auron.models.Participante;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

@Named
@RequestScoped
public class ParticipanteBean {

	@Inject
	private ParticipanteDao participanteDao;

	@Inject
	private Subject user;

	@Inject
	private FacesContext ctx;

	private List<Participante> participantes;

	private Participante participante = new Participante();

	public void cadastrar() {
		participanteDao.salvar(participante);
	}

	public Participante getParticipante() {
		return participante;
	}

	public List<Participante> getParticipantes() {
		if (participantes == null)
			return participanteDao.getParticipantes();
		return participantes;
	}

	public String login() {
		try {
			UsernamePasswordToken token = new UsernamePasswordToken(participante.getEmail(), participante.getSenha());
			user.login(token);

			return "sorteio?faces-redirect=true";
		}
		catch (AuthenticationException e) {
			ctx.addMessage(null, new FacesMessage("E-mail ou senha inválidos!"));
		}
		return null;
	}
}
