package experimentos.metodos.comparacao;

import experimental.analise.RelatResumo;
import experimental.analise.ResultadoClassificador;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.metodos.MetodoV13Config1;
import experimental.metodos.MetodoV14Config1;
import experimental.model.MetodoFactory;

public class TesteV13_V14_30x_Sinteticas {

	public static void main(String[] args) {

		int NUM_EXECUCOES = 30;
		int seed = 1;
		int NUM_CLASSIFICADORES = 4;
		int NUM_BASES = 4;
		
		double[][][] resultado = new double[NUM_EXECUCOES][NUM_CLASSIFICADORES][NUM_BASES];
		double[][] medias = new double[NUM_CLASSIFICADORES][NUM_BASES];
		double[][] desvios = new double[NUM_CLASSIFICADORES][NUM_BASES];
		
		
		

		for (int i = 0; i < NUM_EXECUCOES; i++) {
			System.out.println(" EXECUÇÃO " + (i + 1) + " de " + NUM_EXECUCOES);
			
			
			BaseFactory[] bases = new BaseFactory[NUM_BASES];

			bases[0] = new BaseLine();
			bases[1] = new BaseSine1();
			bases[2] = new BaseGauss();
			bases[3] = new BaseCircle();

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

		

//        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
//        testarBase.executa();
//        
//
//        
//        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
//        testarBase.executa();
//        
//
//        
//        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
//        testarBase.executa();
//        
//
//        
//        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
//        testarBase.executa();

//		// Método v12
//		classificadores[0] = new MetodoV12Reacao("RetreinaTodosComBufferWarning", "Ambiguidade", seed).getMetodo();
//
//		// DDM
//		classificadores[1] = new DDMConfig1(seed).getMetodo();
//
//		// LeveragingBAG Original
//		classificadores[2] = new LeveragingBagVersaoOriginal(seed).getMetodo();

//		RelatResumo.gravar(listaResultados, path, this.base.getNome() + "_comp_" + seed + "__", true);
//		
//		RelatResumo30.gravar(listaResultados, path, this.base.getNome() + "_comp30_" + seed + "__");

	}

}
