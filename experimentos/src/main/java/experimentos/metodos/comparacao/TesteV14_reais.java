package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import experimental.bases.BaseCircle;
import experimental.bases.BaseElec;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseKDDCup99;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.bases.BaseSpam;
import experimental.metodos.MetodoV14Config1;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV14_reais {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "v14_hom_reais/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();
		List<MetodoFactory> classificadores = new ArrayList<>();

		//bases.add(new BaseElec());
		bases.add(new BaseSpam());
		//bases.add(new BaseKDDCup99());
		
		// Método v14
		//classificadores.add(new MetodoV14Config1("OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		classificadores.add(new MetodoV14Config1("LeverageBagging", "SimpleReset", "Ambiguidade", 1, "ADWINChangeDetector", 1).getMetodo());
		
		
		TesteExperimento teste = new TesteExperimento(PATH_EXPERIMENTO, 1, 5, bases, classificadores);
		teste.run();

	}
	
}



//bases.add(new BaseLine());
//bases.add(new BaseSine1());
//bases.add(new BaseGauss());
//bases.add(new BaseCircle());

//classificadores
//	.add(new LeveragingBagVersaoOriginal().getMetodo());

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