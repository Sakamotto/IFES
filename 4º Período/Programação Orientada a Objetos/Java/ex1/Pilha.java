package ex1;

public class Pilha {
	private static final int Max = 100;
	private int topo;
	private int[] vet;
	
	public void crie(){
		topo = -1;
		vet = new int[Max];
	}
	
	public boolean empilhe(int elem){
		if (topo >= Max - 1) return false;
		else{
			++topo;
			vet[topo] = elem;
			return true;
		}
	}
	
	public int desempilhe(){
		int elem;
		if (topo < 0) return -1;
		else {
			elem = vet[topo];
			topo = topo - 1;
			return elem;
		}
	}
	
	public boolean vazia() {
		return topo < 0;
	}
}
