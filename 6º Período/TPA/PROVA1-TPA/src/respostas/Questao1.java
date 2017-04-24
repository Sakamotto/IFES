package respostas;

import respostas.Utils.StreamLoader;
import respostas.hash.TADTabH;
import respostas.hash.TabHChain;

import java.io.BufferedReader;
import java.io.IOException;

public class Questao1 {

	private static TabHChain fromBaseDadosParaTabHash(String nomeArq, int tamArq){
		TabHChain fromFile = new TabHChain();
		StreamLoader streamLoader = new StreamLoader();
		BufferedReader br = streamLoader.readArq(nomeArq);
		String linha;

		try {
			while(br.ready()){
                linha = br.readLine();
                fromFile.insertItem(linha.split(";")[0], linha);
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
		streamLoader.closeArq(br);

		return fromFile;
	}

	/*
	 * CONSTRUA AQUI A RESPOSTA PARA A QUESTÃO 1.F
	 */
	public static void main(String[] args) {

		String path = "/home/cristian/Downloads/paraProva1TPA" + "/" + "Dominios_GovBR_basico.txt";

		TabHChain tadTabH = fromBaseDadosParaTabHash(path, 100);
		String elem = "eletrobras.gov.br";

		tadTabH.salvaColisoes("/home/cristian/Documentos/IFES/colisoes.txt");

		System.out.println(tadTabH.findElem(elem));

	}

}
