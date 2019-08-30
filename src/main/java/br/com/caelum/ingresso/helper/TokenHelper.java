package br.com.caelum.ingresso.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.caelum.ingresso.dao.TokenDao;
import br.com.caelum.ingresso.mail.Token;

@Component
public class TokenHelper {

	@Autowired
	private TokenDao dao;

	public Token generateFrom(String email) {
		Token token = new Token(email);
		dao.save(token);
		return token;
	}
}