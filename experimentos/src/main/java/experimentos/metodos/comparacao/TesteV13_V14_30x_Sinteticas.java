package experimentos.metodos.comparacao;

import experimental.analise.ResultadoClassificador;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.metodos.MetodoV13Config1;
import experimental.metodos.MetodoV14Config1;
import experimental.model.MetodoFactory;
import experimental.util.CSVUtil;
import experimentos.config.Configuracoes;

public class TesteV13_V14_30x_Sinteticas {

	public static void main(String[] args) {

		int NUM_EXECUCOES = 2;
		int seed = 1;
		int NUM_CLASSIFICADORES = 4;
		int NUM_BASES = 1;
		
		BaseFactory[] bases = new BaseFactory[NUM_BASES];

		bases[0] = new BaseLine();
		//bases[1] = new BaseSine1();
		//bases[2] = new BaseGauss();
		//bases[3] = new BaseCircle();
		
		double[][][] resultado = new double[NUM_EXECUCOES][NUM_CLASSIFICADORES][NUM_BASES];
		double[][] medias = new double[NUM_CLASSIFICADORES][NUM_BASES];
		double[][] desvios = new double[NUM_CLASSIFICADORES][NUM_BASES];
		
		String[] classificadores_nome = new String[NUM_CLASSIFICADORES];
		classificadores_nome[0] = "V13_HOM";
		classificadores_nome[1] = "V13_HET";
		classificadores_nome[2] = "V14_HOM";
		classificadores_nome[3] = "V14_HET";
		
		for (int i = 0; i < NUM_EXECUCOES; i++) {
			System.out.println(" EXECUÇÃO " + (i + 1) + " de " + NUM_EXECUCOES);
			
			


			MetodoFactory[] classificadores = new MetodoFactory[NUM_CLASSIFICADORES];

			// Método v13
			classificadores[0] = new MetodoV13Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 1)
					.getMetodo();

			// Método v13
			classificadores[1] = new MetodoV13Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 5)
					.getMetodo();

			// Método v14
			classificadores[2] = new MetodoV14Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 1)
					.getMetodo();

			// Método v14
			classificadores[3] = new MetodoV14Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 5)
					.getMetodo();
			
			

			for (int c = 0; c < NUM_CLASSIFICADORES; c++) {
				System.out.println(" >>> CLASSIFICADOR " + (c + 1) + " de " + classificadores.length);
				for (int b = 0; b < NUM_BASES; b++) {
					System.out.println(" >>> ooo BASE " + (b + 1) + " de " + bases.length);
					TestarClassificadorBase testarBase = new TestarClassificadorBase(bases[b].getBase(),
							bases[b].getBaseDrifts());
					ResultadoClassificador resultadoClassificador = testarBase.executa(classificadores[c]);
					resultado[i][c][b] = resultadoClassificador.getAcuraciaMedia();
				}
			}
		}
		
		//Calcular Média e Desvio PADRÃO
		for (int c = 0; c < NUM_CLASSIFICADORES; c++) {
			for (int b = 0; b < NUM_BASES; b++) {
				
				//Calcular Média
				double soma = 0;
				for (int i = 0; i < NUM_EXECUCOES; i++) {
					soma += resultado[i][c][b];
				}
				double media = soma / (double) NUM_EXECUCOES;
				
				//Calcula Desvio PADRÃO
				double soma_quad = 0;
				for (int i = 0; i < NUM_EXECUCOES; i++) {
					soma_quad += Math.pow((resultado[i][c][b] - media), 2);
				}
				double desvio = Math.sqrt((soma_quad/(double) NUM_EXECUCOES));

				medias[c][b] = media;
				desvios[c][b] = desvio;
				
			}
		}
		
		//Gravar o CSV
        CSVUtil csv = new CSVUtil(Configuracoes.PATH_COMPARACAO, "Testev13_v14_30x_Sinteticas.csv");

       // csv.cabecalho("classificador,line_acc,line_des,sine1_acc,sine1_dess,gauss_acc,gauss_dess,circle_acc,circle_dess");
        csv.cabecalho("classificador,line_acc,line_des");
        
        for (int c = 0; c < NUM_CLASSIFICADORES; c++) {
        	
//        	csv.registro(
//        			classificadores_nome[c] + ","
//                    + medias[c][0] + ","
//                    + desvios[c][0] + ","
//                    		+ medias[c][1] + ","
//                            + desvios[c][1] + ","
//                            		+ medias[c][2] + ","
//                                    + desvios[c][2] + ","
//                                    		+ medias[c][3] + ","
//                                            + desvios[c][3]);
        	
        	csv.registro(
        			classificadores_nome[c] + ","
                    + medias[c][0] + ","
                    + desvios[c][0] );
        	
		}
        csv.fechar();


	}

}
