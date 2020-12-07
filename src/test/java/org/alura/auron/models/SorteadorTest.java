package org.alura.auron.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.alura.auron.exceptions.ListaDeParticipantesNulaException;
import org.alura.auron.exceptions.QuantidadeInsuficienteDeParticipantesException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SorteadorTest {

	private static final int _TOTAL_DE_PARTICIPANTES = 10;

	private List<Participante> participantes;

	private Sorteio sorteio;

	@BeforeEach
	void setUp() throws Exception {
		participantes = new ArrayList<>();

		for (int i = 1; i <= _TOTAL_DE_PARTICIPANTES; i++) {
			Participante participante = Participante.builder().nome("Amigo " + i).build();
			participantes.add(participante);
		}

		sorteio = new Sorteio();
	}

	@Test
	void quantidadeDeParesEParticipantesDevemSerIguais()
			throws QuantidadeInsuficienteDeParticipantesException, ListaDeParticipantesNulaException {
		int quantidadeDeParticipantes = participantes.size();

		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();

		int quantidadeDePares = sorteio.getQuantidadeDePares();

		assertEquals(quantidadeDePares, quantidadeDeParticipantes);
	}

	@Test
	void naoDeveRepetirUmAmigoOculto()
			throws QuantidadeInsuficienteDeParticipantesException, ListaDeParticipantesNulaException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();

		List<Par> pares = new ArrayList<>(sorteio.getPares());

		for (int i = 0; i < pares.size(); i++) {
			for (int j = i + 1; j < pares.size(); j++) {
				Par par1 = pares.get(i);
				Par par2 = pares.get(j);
				Participante amigoOculto1 = par1.getAmigoOculto();
				Participante amigoOculto2 = par2.getAmigoOculto();
				assertFalse(amigoOculto1.equals(amigoOculto2));
			}
		}

	}

	@Test
	void naoDeveRepetirUmAmigo()
			throws QuantidadeInsuficienteDeParticipantesException, ListaDeParticipantesNulaException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();

		List<Par> pares = new ArrayList<>(sorteio.getPares());

		for (int i = 0; i < pares.size(); i++) {
			for (int j = i + 1; j < pares.size(); j++) {
				Par par1 = pares.get(i);
				Par par2 = pares.get(j);
				Participante amigo1 = par1.getAmigo();
				Participante amigo2 = par2.getAmigo();
				assertFalse(amigo1.equals(amigo2));
			}
		}

	}

	@Test
	void naoDevePermitirQueOAmigoSejaIgualAoAmigoOculto()
			throws QuantidadeInsuficienteDeParticipantesException, ListaDeParticipantesNulaException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();

		List<Par> pares = new ArrayList<>(sorteio.getPares());

		for (int i = 0; i < pares.size(); i++) {
			for (int j = i + 1; j < pares.size(); j++) {
				Par par = pares.get(i);
				Participante amigo = par.getAmigo();
				Participante amigoOculto = par.getAmigoOculto();
				assertFalse(amigo.equals(amigoOculto));
			}
		}

	}
	
	@Test
	public void deveVerificarSeAmigoOcultoDoUltimoEOAmigoDoPrimeiroPar() throws QuantidadeInsuficienteDeParticipantesException, ListaDeParticipantesNulaException {
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		sorteador.sortear();

		List<Par> pares = new ArrayList<>(sorteio.getPares());
		
		Par primeiroPar = pares.get(0);
		Par ultimoPar = pares.get(pares.size() - 1);
		
		Participante primeiro = primeiroPar.getAmigo();
		Participante ultimo = ultimoPar.getAmigoOculto();
		
		assertEquals(primeiro, ultimo);
	}

	@Test
	public void naoDeveAceitarUmaListaComMenosDeDoisParticipantes() throws ListaDeParticipantesNulaException, QuantidadeInsuficienteDeParticipantesException {
		List<Participante> participantes = Arrays.asList(Participante.builder().nome("Amigo 1").build());
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		assertThrows(QuantidadeInsuficienteDeParticipantesException.class, () -> sorteador.sortear());
	}
	
	@Test
	public void naoDeveAceitarUmaListaDeParticipantesVazia() throws ListaDeParticipantesNulaException, QuantidadeInsuficienteDeParticipantesException {
		List<Participante> participantes = Collections.emptyList();
		Sorteador sorteador = new Sorteador(sorteio, participantes);
		assertThrows(QuantidadeInsuficienteDeParticipantesException.class, () -> sorteador.sortear());
	}

	@Test
	public void naoDeveAceritarUmaListaDeParticipantesNula() throws ListaDeParticipantesNulaException, QuantidadeInsuficienteDeParticipantesException {
		assertThrows(ListaDeParticipantesNulaException.class, () -> new Sorteador(sorteio, null));
	}

}
