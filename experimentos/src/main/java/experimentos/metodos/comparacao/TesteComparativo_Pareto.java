package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import br.ufam.metodo.util.model.IEnsemblesResultados;
import br.ufam.util.CSVUtil;
import br.ufam.util.Registro;
import experimental.analise.AnaliseCompleta;
import experimental.analise.RelatDiversidade;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteComparativo_Pareto {

	public static void main(String[] args) {

		int NUM_EXECUCOES = 1;
		int seed = 1;

		List<Resultado> listaResultados = new ArrayList<>();

		List<BaseFactory> bases = new ArrayList<>();
		List<MetodoFactory> classificadores = new ArrayList<>();

		bases.add(new BaseLine());
		bases.add(new BaseSine1());
		bases.add(new BaseGauss());
		bases.add(new BaseCircle());

		classificadores
			.add(new LeveragingBagVersaoOriginal().getMetodo());
		
		/*
		// Método v12
		classificadores
				.add(new MetodoV12Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 1).getMetodo());
		classificadores
				.add(new MetodoV12Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 5).getMetodo());
		// Método v14
		classificadores
				.add(new MetodoV14Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 1).getMetodo());
		classificadores
				.add(new MetodoV14Config1("RetreinaTodosComBufferWarning", "Ambiguidade", seed, "DDM", 5).getMetodo());
				
				*/

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
					
					String PATH = Configuracoes.PATH_PARETO_METODO + "/" + classificadores.get(c).getCodigo() + "/"
							+ bases.get(b).getBase().getNome() + "/";
					
					String FILENAME = bases.get(b).getBase().getNome() + "_pareto__exec_" + i;
					
					RelatDiversidade.gravar(resultadoClassificador, PATH, FILENAME);

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

		if (NUM_EXECUCOES > 1) {

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
			CSVUtil csv = new CSVUtil(Configuracoes.PATH_COMPARACAO, "TesteComparativo_Sinteticas.csv");

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
			CSVUtil csvDados = new CSVUtil(Configuracoes.PATH_COMPARACAO, "TesteComparativo_Dados_Sinteticas.csv");

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
}
