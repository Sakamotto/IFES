package matrizesparsa;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TadMatrix m1;
		TadMatrix m2;
		long inicio, fim;

		m1 = new TadMatrix();
		m2 = new TadMatrix();

		m1.carrega("bd-matrizes/mat-09-37x40.txt");
		m2.carrega("bd-matrizes/mat-10-40x43.txt");

		System.out.println("Calculando ...");
		inicio = System.currentTimeMillis();
		m1.multiplica(m2);
		fim = System.currentTimeMillis();
		System.out.println("Tempo gasto: " + ((fim - inicio)/1000f) + " segundos");
		
	}

}
