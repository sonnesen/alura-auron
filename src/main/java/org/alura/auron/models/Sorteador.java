package org.alura.auron.models;

import java.util.Collections;
import java.util.List;

import org.alura.auron.exceptions.ListaDeParticipantesNulaException;
import org.alura.auron.exceptions.QuantidadeInsuficienteDeParticipantesException;

public class Sorteador {

	private static final int QUANTIDADE_MINIMA_DE_PARTICIPANTES = 3;

	private Sorteio sorteio;

	private List<Participante> participantes;

	private int totalDeParticipantes;

	public Sorteador(Sorteio sorteio, final List<Participante> participantes) throws ListaDeParticipantesNulaException {
		if (participantes == null) {
			throw new ListaDeParticipantesNulaException();
		}

		this.sorteio = sorteio;
		this.participantes = participantes;
		this.totalDeParticipantes = participantes.size();
	}

	public void sortear() throws QuantidadeInsuficienteDeParticipantesException, ListaDeParticipantesNulaException {
		totalDeParticipantes = participantes.size();

		verificaQuantidadeMinimaDeParticipantes();
		embaralharListaDeParticipantes();

		for (int indiceAtual = 0; indiceAtual < totalDeParticipantes; indiceAtual++) {

			if (participanteAtualEOUltimo(indiceAtual)) {
				criaEAdicionaParAoSorteio(sorteio, indiceAtual, 0);
				break;
			}

			criaEAdicionaParAoSorteio(sorteio, indiceAtual, indiceAtual + 1);
		}
	}

	private void embaralharListaDeParticipantes() {
		Collections.shuffle(participantes);
	}

	private void verificaQuantidadeMinimaDeParticipantes() throws QuantidadeInsuficienteDeParticipantesException {
		if (totalDeParticipantes < QUANTIDADE_MINIMA_DE_PARTICIPANTES) {
			throw new QuantidadeInsuficienteDeParticipantesException();
		}
	}

	private boolean participanteAtualEOUltimo(int indiceAtual) {
		return indiceAtual == totalDeParticipantes - 1;
	}

	private void criaEAdicionaParAoSorteio(Sorteio sorteio, int indiceAtual, int indiceFinal) {
		Par par = Par.builder()
				.amigo(participantes.get(indiceAtual))
				.amigoOculto(participantes.get(indiceFinal))
				.sorteio(sorteio)
				.build();
		sorteio.adicionarPar(par);
	}

}
