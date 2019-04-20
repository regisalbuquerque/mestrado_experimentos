package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.metodos.MetodoV14Config1;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV14_sinteticas {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "v14_hom/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();
		List<MetodoFactory> classificadores = new ArrayList<>();

		bases.add(new BaseLine());
		bases.add(new BaseSine1());
		bases.add(new BaseGauss());
		bases.add(new BaseCircle());
		
		// Método v14
		classificadores.add(new MetodoV14Config1("OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		classificadores.add(new MetodoV14Config1("LeverageBagging", "SimpleReset", "Ambiguidade", 1, "ADWINChangeDetector", 1).getMetodo());
		
		
		TesteExperimento teste = new TesteExperimento(PATH_EXPERIMENTO, 1, 30, bases, classificadores);
		teste.run();

	}
	
}

