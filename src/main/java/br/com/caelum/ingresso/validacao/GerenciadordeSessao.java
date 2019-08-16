package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadordeSessao {

	public GerenciadordeSessao(List<Sessao> listaVazia) {

	}

	public boolean cabe(Sessao sessao) {
		return this.terminaHoje(sessao);

	}

	private boolean terminaHoje(Sessao sessao) {
		LocalDateTime terminoDaSessao = sessao.getHorario().atDate(LocalDate.now())
				.plus(sessao.getFilme().getDuracao());
		LocalDateTime ultimoSegundoDeHoje = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
		return terminoDaSessao.isBefore(ultimoSegundoDeHoje);
	}

}
