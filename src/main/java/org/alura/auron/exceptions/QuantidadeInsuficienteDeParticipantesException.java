package org.alura.auron.exceptions;

public class QuantidadeInsuficienteDeParticipantesException extends Exception {

	private static final long serialVersionUID = 1L;

	public QuantidadeInsuficienteDeParticipantesException() {
		super("Quantidade insuficiente de participantes para o sorteio!");
	}
}
