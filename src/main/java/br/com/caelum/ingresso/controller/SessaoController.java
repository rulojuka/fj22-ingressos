package br.com.caelum.ingresso.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.form.SessaoForm;
import br.com.caelum.ingresso.validacao.GerenciadorDeSessao;

@Controller
public class SessaoController {

	@Autowired
	private SalaDao salaDao;
	@Autowired
	private FilmeDao filmeDao;
	@Autowired
	private SessaoDao sessaoDao;

	@GetMapping("/admin/sessao")
	public ModelAndView form(@RequestParam("salaId") Integer salaId, SessaoForm form) {
		ModelAndView modelAndView = new ModelAndView("sessao/sessao");
		modelAndView.addObject("sala", salaDao.findOne(salaId));
		modelAndView.addObject("filmes", filmeDao.findAll());
		modelAndView.addObject("form", form);
		return modelAndView;
	}

	@PostMapping("/admin/sessao")
	@Transactional
  public ModelAndView salva( @Valid SessaoForm sessaoForm, BindingResult result){
	
    if(result.hasErrors()){ // Voltar para a tela do form *com dados*
      return this.form(sessaoForm.getSalaId(), sessaoForm); 
	} else{ // Redirecionar para a tela 
		Sessao sessaoNova = sessaoForm.toSessao(filmeDao, salaDao);
		Sala sala = salaDao.findOne(sessaoForm.getSalaId());
		List<Sessao> sessoesExistentes = sessaoDao.sessoesDaSala(sala);




		GerenciadorDeSessao gerenciadorDeSessao = new GerenciadorDeSessao(sessoesExistentes);
		if( gerenciadorDeSessao.cabe(sessaoNova)==true ){
			sessaoDao.save(sessaoNova);
		}else{
			// não salva
		}





		
		return new ModelAndView("redirect:/admin/sala/" + sessaoForm.getSalaId() + "/sessoes/");
	}
  }
}
