package br.com.caelum.ingresso.validacao;

import static org.junit.Assert.assertFalse;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadordeSessaoTest {

	private Filme filme;
	private Sala sala;
	private LocalTime horario;
	private Sessao sessao;

	@Before
	public void setup() {
		filme = new Filme("Taxi3", Duration.ofMinutes(45), "Ação");
		sala = new Sala("Sala 01");
		horario = LocalTime.parse("23:30:00");
		sessao = new Sessao(horario, sala, filme);
	}
	
	@Test
	public void naoDeveCaberQuandoASessaoComecaEmUmDiaETerminaNoOutro() {
		GerenciadorDeSessao gerenciadordeSessao = new GerenciadorDeSessao(Arrays.asList(sessao));
		
		boolean cabe = gerenciadordeSessao.cabe(sessao);

		assertFalse(cabe);
	}

	@Test
	public void deveCaberQuandoAListaEstiverVazia() {
		List<Sessao> listaVazia = new ArrayList<Sessao>();
		new GerenciadorDeSessao(listaVazia);
	}
}
