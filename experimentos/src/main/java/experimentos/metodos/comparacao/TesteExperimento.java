package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import br.ufam.metodo.util.model.IEnsemblesResultados;
import experimental.analise.AnaliseCompleta;
import experimental.analise.RelatDiversidade;
import experimental.bases.BaseFactory;
import experimental.model.MetodoFactory;
import regisalbuquerque.utilslib.CSVUtil;
import regisalbuquerque.utilslib.DiretorioUtil;
import regisalbuquerque.utilslib.Registro;

public class TesteExperimento {
	
	String PATH_CSV = "";
	int NUM_EXECUCOES = 1;
	int seed = 1;
	List<Resultado> listaResultados = new ArrayList<>();
	List<BaseFactory> bases = new ArrayList<>();
	List<MetodoFactory> classificadores = new ArrayList<>();
		
		
	public TesteExperimento(String pathCSV, int numExecucoes, List<BaseFactory> bases, List<MetodoFactory> classificadores)
	{
		this.PATH_CSV = pathCSV;
		this.NUM_EXECUCOES = numExecucoes;
		this.bases = bases;
		this.classificadores = classificadores;
	}

	public void run() {

		double[][][] resultado = new double[NUM_EXECUCOES][classificadores.size()][bases.size()];
		double[][] medias = new double[classificadores.size()][bases.size()];
		double[][] desvios = new double[classificadores.size()][bases.size()];

		for (int i = 0; i < NUM_EXECUCOES; i++) {
			System.out.println(" EXECUÇÃO " + (i + 1) + " de " + NUM_EXECUCOES);

			for (int c = 0; c < classificadores.size(); c++) {
				System.out.println(" >>> CLASSIFICADOR " + (c + 1) + " de " + classificadores.size());
				for (int b = 0; b < bases.size(); b++) {
					System.out.println(" >>> ooo BASE " + (b + 1) + " de " + bases.size());
					TestarClassificadorBase testarBase = new TestarClassificadorBase(bases.get(b).getBase(),
							bases.get(b).getBaseDrifts());
					Resultado resultadoClassificador = testarBase.executa(classificadores.get(c));
					listaResultados.add(resultadoClassificador);
					resultado[i][c][b] = resultadoClassificador.getAcuraciaMedia();
					
					String PATH = PATH_CSV + classificadores.get(c).getCodigo() + "/"
							+ bases.get(b).getBase().getNome() + "/";
					DiretorioUtil.createPath(PATH);
					
					String FILENAME = bases.get(b).getBase().getNome() + "_pareto__exec_" + i;
					
					RelatDiversidade.gravar(resultadoClassificador, PATH, FILENAME + "_div");

					if (classificadores.get(c).getClassificador() instanceof IEnsemblesResultados) {
						IEnsemblesResultados classificadorEnsembler = (IEnsemblesResultados) classificadores.get(c)
								.getClassificador();

						// Análise de Pareto - Dos Ensembles
						AnaliseCompleta analiseCompleta = new AnaliseCompleta(
								classificadorEnsembler.getEnsemblesResultados(),
								PATH,
								FILENAME);
						analiseCompleta.analisa(false); // False para minimizar
					}
				}
			}
		}

			// Calcular Média e Desvio PADRÃO
			for (int c = 0; c < classificadores.size(); c++) {
				for (int b = 0; b < bases.size(); b++) {

					// Calcular Média
					double soma = 0;
					for (int i = 0; i < NUM_EXECUCOES; i++) {
						soma += resultado[i][c][b];
					}
					double media = soma / (double) NUM_EXECUCOES;

					// Calcula Desvio PADRÃO
					double soma_quad = 0;
					for (int i = 0; i < NUM_EXECUCOES; i++) {
						soma_quad += Math.pow((resultado[i][c][b] - media), 2);
					}
					double desvio = Math.sqrt((soma_quad / (double) NUM_EXECUCOES));

					medias[c][b] = media;
					desvios[c][b] = desvio;
				}
			}

			// Gravar o CSV
			CSVUtil csv = new CSVUtil(PATH_CSV, "TesteComparativo_Sinteticas.csv");

			Registro registroCab1 = new Registro();
			registroCab1.adiciona("classificador");
			for (int i = 0; i < bases.size(); i++) {
				registroCab1.adiciona("m" + i);
				registroCab1.adiciona("d" + i);
			}
			csv.registro(registroCab1.toString());

			for (int c = 0; c < classificadores.size(); c++) {
				Registro registro = new Registro();
				registro.adiciona(classificadores.get(c).getCodigo());
				for (int b = 0; b < bases.size(); b++) {
					registro.adiciona(medias[c][b]);
					registro.adiciona(desvios[c][b]);
				}
				csv.registro(registro.toString());
			}
			csv.fechar();

			// Gravar o CSV
			CSVUtil csvDados = new CSVUtil(PATH_CSV, "TesteComparativo_Dados_Sinteticas.csv");

			Registro registroCab2 = new Registro();
			registroCab2.adiciona("classificador");
			for (int i = 0; i < NUM_EXECUCOES; i++) {
				registroCab2.adiciona("i" + i);
			}
			csvDados.registro(registroCab2.toString());

			for (int c = 0; c < classificadores.size(); c++) {
				Registro registro = new Registro();
				registro.adiciona(classificadores.get(c).getCodigo());
				for (int b = 0; b < bases.size(); b++) {
					for (int i = 0; i < NUM_EXECUCOES; i++) {
						registro.adiciona(resultado[i][c][b]);
					}
				}
				csvDados.registro(registro.toString());
			}
			csvDados.fechar();

		}
}
