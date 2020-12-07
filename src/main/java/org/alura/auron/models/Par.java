package org.alura.auron.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "pares")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@ToString(of = { "id", "amigo", "amigoOculto" })
public class Par {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	private Participante amigo;

	@ManyToOne
	private Participante amigoOculto;

	@ManyToOne
	private Sorteio sorteio;

	public Par(Participante amigo, Participante amigoOculto, Sorteio sorteio) {
		this.amigo = amigo;
		this.amigoOculto = amigoOculto;
		this.sorteio = sorteio;
	}

}
