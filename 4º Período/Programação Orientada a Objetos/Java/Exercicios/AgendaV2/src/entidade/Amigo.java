package entidade;

public class Amigo extends Contato implements IAmigo{
	private String nivel;

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public String getDados(){
		return "Nível: " + getNivel();
	}

}
