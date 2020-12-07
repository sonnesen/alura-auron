package org.alura.auron.models;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sorteios")
@Data
@EqualsAndHashCode(of = {"id", "nome"})
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sorteio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String nome;

	@OneToMany(mappedBy = "sorteio", cascade = CascadeType.PERSIST)
	@Builder.Default
	private Set<Par> pares = new LinkedHashSet<Par>();
	
	public Set<Par> getPares() {
		return Collections.unmodifiableSet(this.pares);
	}

	public void adicionarPar(Par par) {
		this.pares.add(par);
	}

	public int getQuantidadeDePares() {
		return this.pares.size();
	}
}
