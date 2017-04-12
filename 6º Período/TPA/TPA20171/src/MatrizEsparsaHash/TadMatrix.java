package MatrizEsparsaHash;
import tadchaininghash.TabHash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

class Cell {

	private int linha;
	private int coluna;
	private double valor;

	public Cell(int linha, int coluna, double valor) {
		this.linha = linha;
		this.coluna = coluna;
		this.valor = valor;
	}

	public int getLinha() {
		return linha;
	}

	public void setLinha(int linha) {
		this.linha = linha;
	}

	public int getColuna() {
		return coluna;
	}

	public void setColuna(int coluna) {
		this.coluna = coluna;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}

public class TadMatrix {

	private TabHash<String, Cell> hashMatrix;
	private int matLinhas;
	private int matColunas;

	public TadMatrix(int linhas, int colunas) {
		hashMatrix = new TabHash<>(100);
		setMatLinhas(linhas);
		setMatColunas(colunas);
	}

	public TadMatrix() {
		hashMatrix = new TabHash<>(100);
	}

	
	public int getMatLinhas() {
		return matLinhas;
	}

	public void setMatLinhas(int matLinhas) {
		this.matLinhas = matLinhas;
	}

	public int getMatColunas() {
		return matColunas;
	}

	public void setMatColunas(int matColunas) {
		this.matColunas = matColunas;
	}

	private String generateKey(int linha, int coluna){
		return linha + "-" + coluna;
	}
	

	private Cell createCell(int linha, int coluna, double valor) {
		return new Cell(linha, coluna, valor);
	}

	public double getValor(int linha, int coluna) {

		if ((linha >= 0 && linha < getMatLinhas()) && (coluna >= 0 && coluna < getMatColunas())) {
			hashMatrix.getElement(generateKey(linha, coluna));
		}
		return 0;
	}

	public void setValor(int linha, int coluna, double valor) {
		// IF que verifica se o valor procurado está dentro dos limites da
		// matriz ...
		if ((linha >= 0 && linha < getMatLinhas()) && (coluna >= 0 && coluna < getMatColunas())) {

			/**
			 * TODO terminar ...
			 */
			hashMatrix.add(generateKey(linha, coluna), createCell(linha, coluna, valor));

		}else{
			System.out.println("!!!");
			System.out.println(getMatLinhas() + " | " + getMatColunas() + " / "+ this.matLinhas + " | " + this.matColunas);
			System.out.println(linha + " | " + coluna);
		}
	}
	
	public void carrega(String nomeArq){	
		FileInputStream is;
		
		try{
			int qtdColunas = 0;
			int qtdLinhas = 0;
			
			is = new FileInputStream(new File("").getAbsolutePath()+ File.separator + nomeArq);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			String linha = br.readLine();
			String[] vetCol;
			
			vetCol = linha.trim().split(" +"); // dentro do split, o '+' é para remover todos os espaços que tiver ...
			
			qtdColunas = vetCol.length;
			qtdLinhas++;
			this.setMatColunas(qtdColunas);
			this.setMatLinhas(qtdLinhas);
			
			while(linha != null){
				for(int col = 0; col < vetCol.length; col++){
					this.setValor(getMatLinhas() - 1, col, Double.parseDouble(vetCol[col].replace(",", ".")));
				}				
				linha = br.readLine();
				
				if(linha != null){
					vetCol = linha.trim().split(" +");
					this.setMatLinhas(++qtdLinhas); // Atualizo a quantidade de linhas da matrix ...
				}
			}
			
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}		
	}
	
	public boolean ehIgual(TadMatrix mat){
		
		if(this.getMatColunas() == mat.getMatColunas() && this.getMatLinhas() == mat.getMatLinhas()){
			for(int i = 0; i < mat.getMatLinhas(); i++){
				for(int j = 0; j < mat.getMatColunas(); j++){
					if(this.getValor(i, j) != mat.getValor(i, j)){
						return false;						
					}
				}
			}
		}
		
		return true;
	}
	
	
	/*public void carregaMatrix(String nomeArq){
		FileInputStream in;
		TadMatrix m;
		int lin = 0, col = 0;
		try {
			in = new FileInputStream(new File("").getAbsolutePath()+ File.separator + nomeArq);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String[] parse = br.readLine().split(",");
			lin = Integer.parseInt(parse[0]);
			col = Integer.parseInt(parse[1]);
			
			m = new TadMatrix(lin, col);
			String linha = br.readLine();
			int i = 0;
			
			while(linha != null){
				String[] matrix_col = linha.split(" ");
				for(int j = 0; j < matrix_col.length; j++){
					matrix.add(createCell(i, j, Double.parseDouble(matrix_col[j])));					
				}
				
				i++;
				linha = br.readLine();
			}
			
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}*/
	
	public void salvar(String saida){
		
		try{
			FileOutputStream out = new FileOutputStream(new File("").getAbsolutePath() + File.separator + saida);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			
			bw.write(this.toString());			
			
			bw.close();			
		}catch(IOException e){
			e.printStackTrace();			
		}
		
	}
	
	
	public TadMatrix multiplica(TadMatrix mat2){
		TadMatrix result = new TadMatrix(this.getMatLinhas(), mat2.getMatColunas());
		double soma;
		
		if(this.getMatColunas() != mat2.getMatLinhas()){
			System.out.println("Matrizes incompatíveis!");
			return null;
		}
		
		for(int i = 0; i < this.getMatLinhas(); i++){
			for(int j = 0; j < mat2.getMatColunas(); j++){
				soma = 0;
				for(int k = 0; k < this.getMatColunas(); k++){
					soma += (this.getValor(i, k) * mat2.getValor(k, j));
					result.setValor(i, j, soma);
				}
			}			
		}
		
		return result;		
	}
	

	public String toString() {
		String result = "";
		for (int i = 0; i < matLinhas; i++) {
			for (int j = 0; j < matColunas; j++) {
				result += String.format("%9.1f", getValor(i, j));
			}
			result += "\n";
		}
		return result;
	}

}
