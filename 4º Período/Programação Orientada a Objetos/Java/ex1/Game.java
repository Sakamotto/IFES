package ex1;
import java.util.Scanner;

public class Game {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pilha p = new Pilha();
		Scanner ler = new Scanner(System.in);
		int palpite, desempilhado;
		
		p.crie();
		
		for(int i = 1; i <= 10; i++){
			System.out.println(i + "º Valor: ");
			p.empilhe(ler.nextInt());
		}
		
		
		System.out.println("Digite o seu palpite: ");
		palpite = ler.nextInt();
		
		int i = 0;
		
		while(!p.vazia()){
			desempilhado = p.desempilhe();
			if(desempilhado == palpite){
				System.out.println("Pontos: " + (10 - i));
			}
			i++;
		}

	}

}
