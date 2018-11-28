package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import br.ufam.metodo.util.model.IEnsemblesResultados;
import br.ufam.metodos.v13.MetodoClassificadorV13;
import br.ufam.util.CSVUtil;
import br.ufam.util.Registro;
import experimental.analise.AnaliseCompleta;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.metodos.MetodoV13Config1;
import experimental.metodos.MetodoV14Config1;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV13_V14_30x_Sinteticas {

	public static void main(String[] args) {

		int NUM_EXECUCOES = 3;
		int seed = 1;
		int NUM_CLASSIFICADORES = 1;
		int NUM_BASES = 1;
		
		List<Resultado> listaResultados = new ArrayList<>();
		
		BaseFactory[] bases = new BaseFactory[NUM_BASES];
		MetodoFactory[] classificadores = new MetodoFactory[NUM_CLASSIFICADORES];

		double[][][] resultado = new double[NUM_EXECUCOES][NUM_CLASSIFICADORES][NUM_BASES];
		double[][] medias = new double[NUM_CLASSIFICADORES][NUM_BASES];
		double[][] desvios = new double[NUM_CLASSIFICADORES][NUM_BASES];
		
		String[] classificadores_nome = new String[NUM_CLASSIFICADORES];
//		classificadores_nome[0] = "V13_HOM";
		classificadores_nome[0] = "V13_HET";
//		classificadores_nome[2] = "V14_HOM";
//		classificadores_nome[3] = "V14_HET";
		
		String[] bases_nome = new String[NUM_BASES];
		bases_nome[0] = "Line";
//		bases_nome[1] = "Sine1";
//		bases_nome[2] = "Gauss";
//		bases_nome[3] = "Circle";
		
		for (int i = 0; i < NUM_EXECUCOES; i++) {
			System.out.println(" EXECUÇÃO " + (i + 1) + " de " + NUM_EXECUCOES);
			
			bases[0] = new BaseLine();
//			bases[1] = new BaseSine1();
//			bases[2] = new BaseGauss();
//			bases[3] = new BaseCircle();
			

			// Método v13
//			classificadores[0] = new MetodoV13Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 1)
//					.getMetodo();

			// Método v13
			classificadores[0] = new MetodoV13Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 5)
					.getMetodo();
			
			MetodoClassificadorV13.gerarLambdas(); //Para o V13 os lambdas são gerados para cada execução

//			// Método v14
//			classificadores[2] = new MetodoV14Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 1)
//					.getMetodo();
//
//			// Método v14
//			classificadores[3] = new MetodoV14Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 5)
//					.getMetodo();
			
			

			for (int c = 0; c < NUM_CLASSIFICADORES; c++) {
				System.out.println(" >>> CLASSIFICADOR " + (c + 1) + " de " + classificadores.length);
				for (int b = 0; b < NUM_BASES; b++) 
				{
					System.out.println(" >>> ooo BASE " + (b + 1) + " de " + bases.length);
					TestarClassificadorBase testarBase = new TestarClassificadorBase(bases[b].getBase(), bases[b].getBaseDrifts());
					Resultado resultadoClassificador = testarBase.executa(classificadores[c]);
					resultado[i][c][b] = resultadoClassificador.getAcuraciaMedia();
					listaResultados.add(resultadoClassificador);
					
					if (classificadores[c].getClassificador() instanceof IEnsemblesResultados)
					{
						IEnsemblesResultados classificadorEnsembler = (IEnsemblesResultados) classificadores[c].getClassificador();
						
						//Análise de Pareto - Dos Ensembles
						AnaliseCompleta analiseCompleta = new AnaliseCompleta(classificadorEnsembler.getEnsemblesResultados(), 
								Configuracoes.PATH_PARETO_METODO, 
								bases_nome[b], 
								bases_nome[b] + "_pareto__exec_" + i);
						analiseCompleta.analisa(false); //False para minimizar
					}
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

        Registro registroCab1 = new Registro();
        registroCab1.adiciona("classificador");
        for (int i = 0; i < NUM_BASES; i++) {
        	registroCab1.adiciona("m"+i);
        	registroCab1.adiciona("d"+i);
		}
        csv.registro(registroCab1.toString());
        
        
        for (int c = 0; c < NUM_CLASSIFICADORES; c++) 
        {
        	Registro registro = new Registro();
        	registro.adiciona(classificadores_nome[c]);
        	for (int b = 0; b < NUM_BASES; b++) {
        		registro.adiciona(medias[c][b]);
            	registro.adiciona(desvios[c][b]);
			}
        	csv.registro(registro.toString());
		}
        csv.fechar();
        
        
        //Gravar o CSV
        CSVUtil csvDados = new CSVUtil(Configuracoes.PATH_COMPARACAO, "Testev13_v14_30x_Dados_Sinteticas.csv");

        Registro registroCab2 = new Registro();
        registroCab2.adiciona("classificador");
        for (int i = 0; i < NUM_EXECUCOES; i++) {
        	registroCab2.adiciona("i"+i);
		}
        csvDados.registro(registroCab2.toString());
        
        for (int c = 0; c < NUM_CLASSIFICADORES; c++) 
        {
        	Registro registro = new Registro();
        	registro.adiciona(classificadores_nome[c]);
        	for (int b = 0; b < NUM_BASES; b++) {
        		for (int i = 0; i < NUM_EXECUCOES; i++) {
        			registro.adiciona(resultado[i][c][b]);
        		}
			}
        	csvDados.registro(registro.toString());
		}
        csvDados.fechar();
	}
}
