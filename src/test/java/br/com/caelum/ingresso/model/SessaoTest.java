package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SessaoTest {

	private Filme filme;
	private Sala sala;
	private Sessao sessao;

	@Before
	public void setup() {
		sala = new Sala("Eldorado  -  IMax", new BigDecimal("22.5"));
		filme = new Filme("Rogue  One", Duration.ofMinutes(120), "SCI-FI", new BigDecimal("12.0"));
		sessao = new Sessao(LocalTime.parse("10:00:00"), filme, sala);
	}

	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());

		Assert.assertEquals(somaDosPrecosDaSalaEFilme, sessao.getPreco());
	}

	@Test
	public void garanteQueOLugarA1EstaOcupadoEOsLugaresA2eA3Disponiveis() {
		Lugar a1 = new Lugar("A", 1);
		Lugar a2 = new Lugar("A", 2);
		Lugar a3 = new Lugar("A", 3);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());

		sessao.setIngressos(ingressos);

		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));
	}

}
