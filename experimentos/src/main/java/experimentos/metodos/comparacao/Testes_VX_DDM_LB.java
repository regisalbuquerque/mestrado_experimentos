package experimentos.metodos.comparacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodos.leveraging.v1.LeveragingBagOriginal;
import br.ufam.metodos.v12.MetodoClassificadorV12;
import br.ufam.metodos.v14.MetodoClassificadorV14;
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
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class Testes_VX_DDM_LB {
	
	static String PATH_EXPERIMENTO = Configuracoes.PATH_BASE + "VX_DDM_LB_LambdasMaiores/";
	
	public static void main(String[] args) {
		
		System.out.println("Teste");
		
		List<BaseFactory> bases = new ArrayList<>();

//		bases.add(new BaseSine1());
//		bases.add(new BaseGauss());
//		bases.add(new BaseCircle());
//		
//		bases.add(new BaseAgrawalAbrupt());
//		bases.add(new BaseAgrawalAbruptNoise());
//		bases.add(new BaseAgrawalGradual());
//		bases.add(new BaseAgrawalGradualNoise());
//		
//		bases.add(new BaseSEAAbrupt());
//		bases.add(new BaseSEAAbruptNoise());
		bases.add(new BaseSEAGradual());
//		bases.add(new BaseSEAGradualNoise());
		
//		bases.add(new BasePokerHand());
//		bases.add(new BaseForestCovertype());
//		bases.add(new BaseSpam());
//		bases.add(new BaseKDDCup99());
		

		List<MetodoFactory> classificadores1 = new ArrayList<>();
		List<MetodoFactory> classificadores2 = new ArrayList<>();
		List<MetodoFactory> classificadores3 = new ArrayList<>();
		List<MetodoFactory> classificadores4 = new ArrayList<>();
		
		// LeveragingBag
		classificadores1.add(new LeveragingBagVersaoOriginal().getMetodo());
		
		// DDM
		classificadores2.add(new DDMConfig1().getMetodo());
		
		// Método v12
		MetodoClassificadorV12 v12 = new MetodoClassificadorV12();
		v12.lambdasOption.setValueViaCLIString("1000,500,100,50,30,20,10,5,1,0.5,0.1");
		classificadores3.add(new DESDDConfig(v12, "12", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		
		// Método v14
		MetodoClassificadorV14 v14 = new MetodoClassificadorV14();
		v14.lambdaMinOption.setValueViaCLIString("0.1");
		v14.lambdaMaxOption.setValueViaCLIString("1000");
		classificadores4.add(new DESDDConfig(v14, "14", "OnlineBagging", "RetreinaTodosComBufferWarning", "Ambiguidade", 1, "DDM", 1).getMetodo());
		

		
		TesteExperimento teste1 = new TesteExperimento(PATH_EXPERIMENTO, 1, 1, bases, classificadores1);
		//teste1.run();
		
		TesteExperimento teste2 = new TesteExperimento(PATH_EXPERIMENTO, 1, 1, bases, classificadores2);
		//teste2.run();
		
		TesteExperimento teste3 = new TesteExperimento(PATH_EXPERIMENTO, 1, 1, bases, classificadores3);
		teste3.run();
		
		TesteExperimento teste4 = new TesteExperimento(PATH_EXPERIMENTO, 1, 30, bases, classificadores4);
		//teste4.run();

	}
	
}

