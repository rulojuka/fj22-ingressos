package br.com.caelum.ingresso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {

	@GetMapping("/usuario/request")
	public ModelAndView formSolicitacaoDeAcesso() {
		return new ModelAndView("usuario/form-email");
	}

	@PostMapping("/usuario/request")
	public ModelAndView solicitacaoDeAcesso(String email) {
		return new ModelAndView("usuario/adicionado");
	}
}
