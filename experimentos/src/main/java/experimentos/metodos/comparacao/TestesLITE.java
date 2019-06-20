package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodos.v12.MetodoClassificadorV12;
import br.ufam.metodos.v12.MetodoClassificadorV12LITE;
import br.ufam.metodos.v14.MetodoClassificadorV14;
import br.ufam.metodos.v14.MetodoClassificadorV14LITE;
import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimental.metodos.DESDDConfig;
import experimental.metodos.DESDDLITEConfig;
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TestesLITE {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "testesLITE_comp/";
	
	public static void main(String[] args) {
		
		System.out.println("Testes LITE");
		
		List<BaseFactory> bases = new ArrayList<>();

		
		bases.add(new BaseLine());
		

		List<MetodoFactory> classificadores1 = new ArrayList<>();
		List<MetodoFactory> classificadores2 = new ArrayList<>();
		List<MetodoFactory> classificadores3 = new ArrayList<>();
		List<MetodoFactory> classificadores4 = new ArrayList<>();
		List<MetodoFactory> classificadores5 = new ArrayList<>();
		
		
		// Método v12
		MetodoClassificadorV12 v12 = new MetodoClassificadorV12();
		v12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
		classificadores1.add(new DESDDConfig(v12, "12", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v14
		MetodoClassificadorV14 v14 = new MetodoClassificadorV14();
		v14.lambdaMinOption.setValueViaCLIString("0.001");
		v14.lambdaMaxOption.setValueViaCLIString("100");
		classificadores2.add(new DESDDConfig(v14, "14", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v12
		MetodoClassificadorV12LITE v12lite = new MetodoClassificadorV12LITE();
		v12lite.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
		classificadores3.add(new DESDDLITEConfig(v12lite, "12", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v14
		MetodoClassificadorV14LITE v14lite = new MetodoClassificadorV14LITE();
		v14lite.lambdaMinOption.setValueViaCLIString("0.001");
		v14lite.lambdaMaxOption.setValueViaCLIString("100");
		classificadores4.add(new DESDDLITEConfig(v14lite, "14", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// LeveragingBag
		classificadores5.add(new LeveragingBagVersaoOriginal().getMetodo());

		
		TesteExperimentoLITE testev12 = new TesteExperimentoLITE(PATH_EXPERIMENTO, 1, 1, bases, classificadores1);
		TesteExperimentoLITE testev14 = new TesteExperimentoLITE(PATH_EXPERIMENTO, 1, 1, bases, classificadores2);
		TesteExperimentoLITE testev12lite = new TesteExperimentoLITE(PATH_EXPERIMENTO, 1, 1, bases, classificadores3);
		TesteExperimentoLITE testev14lite = new TesteExperimentoLITE(PATH_EXPERIMENTO, 1, 1, bases, classificadores4);
		TesteExperimentoLITE testeLB = new TesteExperimentoLITE(PATH_EXPERIMENTO, 1, 1, bases, classificadores5);
		
		testev12.run();
		testev14.run();
		testev12lite.run();
		testev14lite.run();
		testeLB.run();

	}
	
}

