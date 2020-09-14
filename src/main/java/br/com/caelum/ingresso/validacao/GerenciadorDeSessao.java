package br.com.caelum.ingresso.validacao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorDeSessao {

	// Construtor que recebe várias sessões existentes (Lista)
    List<Sessao> sessoesExistentes;

	public GerenciadorDeSessao(List<Sessao> sessoesExistentes) {
		this.sessoesExistentes = sessoesExistentes;
	}

    // Método que, dado uma sessaoNova, diz se ela "cabe" nas sessões existentes (boolean)
    public boolean cabe(Sessao sessaoNova){
        // for (Sessao sessaoAtual : sessoesExistentes) {
        //     if( this.conflitaHorario(sessaoNova, sessaoAtual) ){
        //         return false;
        //     }
        // }
        // return true;
        return sessoesExistentes
                .stream()
                .noneMatch(sessaoExistente -> this.conflitaHorario(sessaoExistente, sessaoNova));

        
    }

    private boolean conflitaHorario(Sessao sessaoA, Sessao sessaoB) {
        LocalDate hoje = LocalDate.now();
        LocalDateTime inicioDaSessaoA = sessaoA.getHorario().atDate(hoje);
        LocalDateTime terminoDaSessaoA = inicioDaSessaoA.plus(sessaoA.getFilme().getDuracao());
        LocalDateTime inicioDaSessaoB = sessaoB.getHorario().atDate(hoje);
        LocalDateTime terminoDaSessaoB = inicioDaSessaoB.plus(sessaoB.getFilme().getDuracao());

        if( this.terminaNoOutroDia(inicioDaSessaoA,terminoDaSessaoA) || this.terminaNoOutroDia(inicioDaSessaoB,terminoDaSessaoB)){
            return true;
        }

        if(inicioDaSessaoB.isAfter(inicioDaSessaoA) && inicioDaSessaoB.isBefore(terminoDaSessaoA)){
            return true;
        }

        if(terminoDaSessaoB.isAfter(inicioDaSessaoA) && terminoDaSessaoB.isBefore(terminoDaSessaoA)){
            return true;
        }

        return false;
    }

    private boolean terminaNoOutroDia(LocalDateTime inicioDaSessaoA, LocalDateTime terminoDaSessaoA) {
        return inicioDaSessaoA.isAfter(terminoDaSessaoA);
    }
}
