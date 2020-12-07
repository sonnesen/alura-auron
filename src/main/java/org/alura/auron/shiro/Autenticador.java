package org.alura.auron.shiro;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.alura.auron.dao.ParticipanteDao;
import org.alura.auron.models.Participante;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class Autenticador implements Realm {

	private ParticipanteDao participanteDao;

	private ParticipanteDao getParticipanteDao() {
		Properties properties = new Properties();
		properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

		try {
			InitialContext ctx = new InitialContext(properties);
			ParticipanteDao participanteDao = (ParticipanteDao) ctx.lookup("java:module/ParticipanteDao");
			return participanteDao;
		}
		catch (NamingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String getName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return true;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;

		String username = usernamePasswordToken.getUsername();
		String password = new String(usernamePasswordToken.getPassword());

		this.participanteDao = getParticipanteDao();

		Participante participante = this.participanteDao.getParticipante(username, password);

		if (participante == null) {
			throw new AuthenticationException();
		}

		return new SimpleAuthenticationInfo(username, password, getName());
	}

}
