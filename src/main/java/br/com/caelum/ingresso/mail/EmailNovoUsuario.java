package br.com.caelum.ingresso.mail;

public class EmailNovoUsuario implements Email{
	
	private final Token token;
	
	public EmailNovoUsuario(Token token) {
		this.token = token;
	}

	@Override
	public String getTo() {
		return token.getEmail();
	}

	@Override
	public String getBody() {
		StringBuilder body = new StringBuilder("<html>");
		body.append("<body>");
		body.append("<h2> Bem Vindo </h2>");
		body.append(String.format("Acesso o <a href=%s>link para criar seu login no sistema de ingressos.", makeURL()));
		body.append("</body>");
		body.append("</html>");
		return body.toString();
	}

	private String makeURL() {
		return String.format("http://localhost:8080/usuario/validate?uuid=%s", token.getUuid());
	}

	@Override
	public String getSubject() {
		return "Cadastro sistema de ingressos";
	}

}
