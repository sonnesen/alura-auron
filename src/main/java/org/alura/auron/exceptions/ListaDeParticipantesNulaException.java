package org.alura.auron.exceptions;

public class ListaDeParticipantesNulaException extends Exception {

	private static final long serialVersionUID = 1L;

	public ListaDeParticipantesNulaException() {
		super("Lista de participantes para o sorteio não pode ser nula!");
	}
}
