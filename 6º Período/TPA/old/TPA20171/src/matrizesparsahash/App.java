package matrizesparsahash;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TadMatrixHash m1;
		TadMatrixHash m2;
		long inicio, fim;

		m1 = new TadMatrixHash();
		m2 = new TadMatrixHash();

		m1.carrega("bd-matrizes/mat-09-37x40.txt");
		m2.carrega("bd-matrizes/mat-10-40x43.txt");

		inicio = System.currentTimeMillis();
		m1.multiplica(m2);
		fim = System.currentTimeMillis();
		System.out.println("Tempo gasto: " + ((fim - inicio)/1000f) + " segundos");
		
	}

}
