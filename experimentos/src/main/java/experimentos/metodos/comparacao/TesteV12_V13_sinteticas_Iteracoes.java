package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimental.bases.BaseSine1;
import experimental.metodos.DESDDConfig;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV12_V13_sinteticas_Iteracoes {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "Teste_v12_v13/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();
		List<MetodoFactory> classificadores = new ArrayList<>();

		bases.add(new BaseLine());
		bases.add(new BaseSine1());
		//bases.add(new BaseGauss());
		//bases.add(new BaseCircle());
		
		// Método v12
		classificadores.add(new DESDDConfig("12", "LeverageBagging", "SimpleReset", "Ambiguidade", 1, "ADWINChangeDetector", 1).getMetodo());
		
		//Método v13 
		classificadores.add(new DESDDConfig("13", "LeverageBagging", "SimpleReset", "Ambiguidade", 1, "ADWINChangeDetector", 1).getMetodo());
		
		TesteExperimento teste = new TesteExperimento(PATH_EXPERIMENTO, 1, 1, bases, classificadores);
		teste.run();

	}
	
}

