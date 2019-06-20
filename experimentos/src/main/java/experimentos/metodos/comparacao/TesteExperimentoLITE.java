package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import experimental.analise.RelatResumoAcc;
import experimental.bases.BaseFactory;
import experimental.model.MetodoFactory;
import experimental.testes.TestePrequentialLITE;

public class TesteExperimentoLITE {

	String PATH_CSV = "";
	int numExecInicio = 1;
	int numExecTermino = 1;
	List<BaseFactory> bases = new ArrayList<>();
	List<MetodoFactory> classificadores = new ArrayList<>();
	
	public TesteExperimentoLITE(String pathCSV, int numExecInicio, int numExecTermino, List<BaseFactory> bases, 
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
					TestePrequentialLITE testePrequential = new TestePrequentialLITE(bases.get(b).getBase(), bases.get(b).getBaseDrifts(), classificadores.get(c));
					Resultado resultadoClassificador = testePrequential.test();
					resultadoClassificador.setCodigo(classificadores.get(c).getCodigo());
					
					String pathFileName = PATH_CSV + "RESULT_" + classificadores.get(c).getCodigo() + "_" + bases.get(b).getBase().getNome();
					
					//FAZER APPEND NO ARQUIVO
					// GRAVAR: Classificador + Base + Execução + Acc + Tempo
					RelatResumoAcc.gravarPorResumo(resultadoClassificador, i, pathFileName);
					
					String PATH_CLASS = classificadores.get(c).getCodigo() + "/"+ bases.get(b).getBase().getNome() + "/" + "pareto__exec_" + i + "/";
					String FILENAME = classificadores.get(c).getCodigo() + "_" + bases.get(b).getBase().getNome() + "_pareto__exec_" + i;
					
					testePrequential = null;

				}
			}
		}
	}	
}
