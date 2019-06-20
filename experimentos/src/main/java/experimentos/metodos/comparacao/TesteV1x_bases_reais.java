package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodos.leveraging.v1.LeveragingBagOriginal;
import br.ufam.metodos.v12.MetodoClassificadorV12;
import br.ufam.metodos.v14.MetodoClassificadorV14;
import br.ufam.metodos.v14.MetodoClassificadorV14LITE;
import experimental.bases.BaseAgrawalAbrupt;
import experimental.bases.BaseAgrawalAbruptNoise;
import experimental.bases.BaseAgrawalGradual;
import experimental.bases.BaseAgrawalGradualNoise;
import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimental.bases.BaseForestCovertype;
import experimental.bases.BaseGauss;
import experimental.bases.BaseKDDCup99;
import experimental.bases.BaseLine;
import experimental.bases.BasePokerHand;
import experimental.bases.BaseSEAAbrupt;
import experimental.bases.BaseSEAAbruptNoise;
import experimental.bases.BaseSEAGradual;
import experimental.bases.BaseSEAGradualNoise;
import experimental.bases.BaseSine1;
import experimental.bases.BaseSpam;
import experimental.metodos.DDMConfig1;
import experimental.metodos.DESDDConfig;
import experimental.metodos.DESDDLITEConfig;
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class TesteV1x_bases_reais {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "comp_v12_v14_LB_DDM__reais/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();

		
		bases.add(new BasePokerHand());
		//bases.add(new BaseForestCovertype());
		//bases.add(new BaseSpam());
		//bases.add(new BaseKDDCup99());
		

		List<MetodoFactory> classificadores1 = new ArrayList<>();
		List<MetodoFactory> classificadores2 = new ArrayList<>();
		List<MetodoFactory> classificadores3 = new ArrayList<>();
		List<MetodoFactory> classificadores4 = new ArrayList<>();
		List<MetodoFactory> classificadores5 = new ArrayList<>();
		
		// LeveragingBag
		classificadores1.add(new LeveragingBagVersaoOriginal().getMetodo());
		
		// DDM
		classificadores2.add(new DDMConfig1().getMetodo());
		
		// Método v12
		MetodoClassificadorV12 v12 = new MetodoClassificadorV12();
		v12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
		classificadores3.add(new DESDDConfig(v12, "12", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v14
		MetodoClassificadorV14LITE v14lite = new MetodoClassificadorV14LITE();
		v14lite.lambdaMinOption.setValueViaCLIString("0.001");
		v14lite.lambdaMaxOption.setValueViaCLIString("100");
		classificadores4.add(new DESDDLITEConfig(v14lite, "14", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v14
		MetodoClassificadorV14LITE v14lite_adwin = new MetodoClassificadorV14LITE();
		v14lite_adwin.lambdaMinOption.setValueViaCLIString("0.001");
		v14lite_adwin.lambdaMaxOption.setValueViaCLIString("100");
		classificadores5.add(new DESDDLITEConfig(v14lite_adwin, "14", "OnlineBagging", "SimpleResetSystem", "Ambiguidade", 1, "ADWINChangeDetector", 1).getMetodo());
		

		TesteExperimentoLITE testev14lite = new TesteExperimentoLITE(PATH_EXPERIMENTO, 12, 30, bases, classificadores4);
		TesteExperimentoLITE testev14lite_adwin = new TesteExperimentoLITE(PATH_EXPERIMENTO, 1, 30, bases, classificadores5);
		
		
		testev14lite_adwin.run();
		testev14lite.run();
		//testeDDM.run();
		//testeLB.run();

	}
	
}

