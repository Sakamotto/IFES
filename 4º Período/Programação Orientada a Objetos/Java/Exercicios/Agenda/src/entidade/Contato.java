package entidade;

public class Contato {
	private Contato segundaClasse;
	/*
	 * segundaClasse serve para adicionar uma outra categoria de contato para um
	 * contato. Caso um contato seja um colega e um amigo ele deve ser
	 * adicionado como uma dessas categorias mas deve tb ter a variável
	 * segundaClasse apontando para o outro objeto. A entrada de dados tem de
	 * levar esta questão em consideração, inclusive na definicao de convidados
	 * do evento.
	 */

	private String nome;

	public Contato() {
		setSegundaClasse(null);
	}

	public Contato(Contato c) {
		/* define o segundo nível de relacionamento para o contato */
		this.setSegundaClasse(c);

	}

	public String getNome() {
		return nome;
	}

	public String getDados(){
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Contato getSegundaClasse() {
		return segundaClasse;
	}

	public void setSegundaClasse(Contato segundaClasse) {
		this.segundaClasse = segundaClasse;
	}

}
