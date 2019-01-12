package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimental.metodos.MetodoV14Config1;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV14_DDM_HOM_sinteticas {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "v14_ddm_hom_online/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();
		List<MetodoFactory> classificadores = new ArrayList<>();

		bases.add(new BaseLine());
		
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