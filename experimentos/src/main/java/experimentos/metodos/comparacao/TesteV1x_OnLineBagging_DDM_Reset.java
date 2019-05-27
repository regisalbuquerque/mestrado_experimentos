package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodos.leveraging.v1.LeveragingBagOriginal;
import experimental.bases.BaseAgrawalAbrupt;
import experimental.bases.BaseAgrawalAbruptNoise;
import experimental.bases.BaseAgrawalGradual;
import experimental.bases.BaseAgrawalGradualNoise;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimental.bases.BaseLine;
import experimental.bases.BaseSEAAbrupt;
import experimental.bases.BaseSEAAbruptNoise;
import experimental.bases.BaseSEAGradual;
import experimental.bases.BaseSEAGradualNoise;
import experimental.bases.BaseSine1;
import experimental.metodos.DDMConfig1;
import experimental.metodos.DESDDConfig;
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV1x_OnLineBagging_DDM_Reset {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "v1x_Online_DDM_ResetAll_newbases/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();
		List<MetodoFactory> classificadores = new ArrayList<>();

		bases.add(new BaseLine());
		bases.add(new BaseSine1());
		bases.add(new BaseGauss());
		bases.add(new BaseCircle());
		
		bases.add(new BaseAgrawalAbrupt());
		bases.add(new BaseAgrawalAbruptNoise());
		bases.add(new BaseAgrawalGradual());
		bases.add(new BaseAgrawalGradualNoise());
		
		bases.add(new BaseSEAAbrupt());
		bases.add(new BaseSEAAbruptNoise());
		bases.add(new BaseSEAGradual());
		bases.add(new BaseSEAGradualNoise());
		

		// Método v12
		//classificadores.add(new DESDDConfig("12", "OnlineBagging", "SimpleResetSystem1Detector", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v14
		//classificadores.add(new DESDDConfig("14", "OnlineBagging", "SimpleResetSystem1Detector", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// LeveragingBag
		//classificadores.add(new LeveragingBagVersaoOriginal().getMetodo());
		
		// DDM
		//classificadores.add(new DDMConfig1().getMetodo());
		
		TesteExperimento teste = new TesteExperimento(PATH_EXPERIMENTO, 1, 1, bases, classificadores);
		teste.run();

	}
	
}

