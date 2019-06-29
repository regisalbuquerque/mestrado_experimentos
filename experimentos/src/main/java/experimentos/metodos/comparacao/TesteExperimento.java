package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import br.ufam.metodo.util.model.IEnsemblesResultados;
import experimental.analise.AnaliseCompleta;
import experimental.analise.RelatDiversidade;
import experimental.analise.RelatResumoAcc;
import experimental.analise.RelatResumoDrift;
import experimental.bases.BaseFactory;
import experimental.model.MetodoFactory;
import experimental.testes.TestePrequential;

public class TesteExperimento {

	String PATH_CSV = "";
	int numExecInicio = 1;
	int numExecTermino = 1;
	List<BaseFactory> bases = new ArrayList<>();
	List<MetodoFactory> classificadores = new ArrayList<>();
	
	public TesteExperimento(String pathCSV, int numExecInicio, int numExecTermino, List<BaseFactory> bases, 
			List<MetodoFactory> classificadores) {
		this.PATH_CSV = pathCSV;
		this.numExecInicio = numExecInicio;
		this.numExecTermino = numExecTermino;
		this.bases = bases;
		this.classificadores = classificadores;
	}

	public void run() {

		for (int i = numExecInicio; i <= numExecTermino; i++) 
		{
			for (int c = 0; c < classificadores.size(); c++) 
			{
				for (int b = 0; b < bases.size(); b++) 
				{
					System.out.println("EXEC: "+i + " CLASS: " +c +" BASE: " + b);
					TestePrequential testePrequential = new TestePrequential(bases.get(b).getBase(), bases.get(b).getBaseDrifts(), classificadores.get(c));
					Resultado resultadoClassificador = testePrequential.test();
					resultadoClassificador.setCodigo(classificadores.get(c).getCodigo());
					
					String pathFileName = PATH_CSV + "RESULT_" + classificadores.get(c).getCodigo() + "_" + bases.get(b).getBase().getNome();
					
					//FAZER APPEND NO ARQUIVO
					// GRAVAR: Classificador + Base + Execução + Acc + Tempo
					RelatResumoAcc.gravar(resultadoClassificador, i, pathFileName);
					
					String PATH_CLASS = classificadores.get(c).getCodigo() + "/"+ bases.get(b).getBase().getNome() + "/" + "pareto__exec_" + i + "/";
					String FILENAME = classificadores.get(c).getCodigo() + "_" + bases.get(b).getBase().getNome() + "_pareto__exec_" + i;
					
					//Gravar a diversidade do Método
//					RelatDiversidade.gravar(resultadoClassificador, PATH_CSV, FILENAME + "_div");
					
					//Gravar os drifts do Método
					RelatResumoDrift.gravar(resultadoClassificador, PATH_CSV, FILENAME + "_drift");
					
//					if (classificadores.get(c).getClassificador() instanceof IEnsemblesResultados) {
//						IEnsemblesResultados classificadorEnsembler = (IEnsemblesResultados) classificadores.get(c).getClassificador();
//						// Análise de Pareto - Dos Ensembles
//						AnaliseCompleta analiseCompleta = new AnaliseCompleta(
//								classificadorEnsembler.getEnsemblesResultados(),
//								PATH_CSV + "/pareto/" + PATH_CLASS,
//								FILENAME);
//						analiseCompleta.analisa(false); // False para minimizar
//					}
					
					testePrequential = null;

				}
			}
		}
	}	
}
