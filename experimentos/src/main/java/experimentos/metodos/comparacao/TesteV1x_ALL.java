package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodos.DESDD.MetodoClassificadorV12;
import br.ufam.metodos.DESDD.MetodoClassificadorV14;
import experimental.bases.BaseAgrawalAbrupt;
import experimental.bases.BaseAgrawalAbruptNoise;
import experimental.bases.BaseAgrawalGradual;
import experimental.bases.BaseAgrawalGradualNoise;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseForestCovertype;
import experimental.bases.BaseGauss;
import experimental.bases.BaseKDDCup99;
import experimental.bases.BasePokerHand;
import experimental.bases.BaseSEAAbrupt;
import experimental.bases.BaseSEAAbruptNoise;
import experimental.bases.BaseSEAGradual;
import experimental.bases.BaseSEAGradualNoise;
import experimental.bases.BaseSine1;
import experimental.bases.BaseSpam;
import experimental.metodos.DDDVersaoOriginal;
import experimental.metodos.DESDDConfig;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV1x_ALL {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "comp_HDDM_DDD_allbases/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();

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
		
		bases.add(new BasePokerHand());
		bases.add(new BaseForestCovertype());
		bases.add(new BaseSpam());
		bases.add(new BaseKDDCup99());
		
		//bases.add(new BaseLine());
		

		List<MetodoFactory> classificadores1 = new ArrayList<>();
		
		// LeveragingBag
		//classificadores1.add(new LeveragingBagVersaoOriginal().getMetodo());
		
		// DDM
		//classificadores1.add(new DDMConfig1().getMetodo());
		
		// DDD
		classificadores1.add(new DDDVersaoOriginal().getMetodo());
		
		// Método v12
		MetodoClassificadorV12 v12 = new MetodoClassificadorV12();
		v12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
		classificadores1.add(new DESDDConfig(v12, "12", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "HDDM_A_Test", 1).getMetodo());
		
		// Método v14
		MetodoClassificadorV14 v14 = new MetodoClassificadorV14();
		v14.lambdaMinOption.setValueViaCLIString("0.001");
		v14.lambdaMaxOption.setValueViaCLIString("100");
		//classificadores1.add(new DESDDConfig(v14, "14", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "HDDM_A_Test", 1).getMetodo());
		

		TesteExperimento teste = new TesteExperimento(PATH_EXPERIMENTO, 1, 1, bases, classificadores1);
		teste.run();


	}
	
}


