package br.com.caelum.ingresso.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
@Entity
public class Permissao implements GrantedAuthority {

	@Id
	private String nome;

	@Override
	public String getAuthority() {
		return this.nome;
	}

	/**
	 * @deprecated Hibernate only
	 */
	public Permissao() {
	}

	public Permissao(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
