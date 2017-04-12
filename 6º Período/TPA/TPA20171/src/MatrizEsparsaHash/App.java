package MatrizEsparsaHash;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		TadMatrix m1;
		TadMatrix m2;
		long inicio, fim;
		
		int i = 1;
		while(i < 50){
			m1 = new TadMatrix();
			m2 = new TadMatrix();
			
			m1.carrega("bd-matrizes/mat" + String.format("%03d", i) + ".txt");
			m2.carrega("bd-matrizes/mat" + String.format("%03d", i + 1) + ".txt");

			inicio = System.currentTimeMillis();
			m1.multiplica(m2);
            fim = System.currentTimeMillis();
            System.out.println("Tempo gasto: " + (fim - inicio));
            //m1.multiplica(m2).salvar("resultados/" + "mat" + i + "*mat" + (i + 1) + ".txt");
			i += 2;
		}
		
		
	}

}
