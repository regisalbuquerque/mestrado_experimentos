package experimental.metodos;

import br.ufam.metodo.util.model.DESDDClassifier;
import br.ufam.metodos.DESDD.MetodoClassificadorV12;
import br.ufam.metodos.DESDD.MetodoClassificadorV13;
import br.ufam.metodos.DESDD.MetodoClassificadorV14;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class DESDDConfig implements MetodoTeste{

	private DESDDClassifier desddClassifier;
	private String versao;
	private String geracao;
    private String reacao;
    private String medidaCalculo;
    private int randomSeed = 1;
    private String detector = "DDM";
    private int numBaseLeaners = 1;
    
    
    public DESDDConfig(String versao, String geracao, String reacao, String medida, int randomSeed, String detectorOpt, int numBaseLeaners)
    {
       this.desddClassifier = null;
       this.versao = versao;
       this.geracao = geracao;
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.detector = detectorOpt;
       this.numBaseLeaners = numBaseLeaners;
    }
    
    public DESDDConfig(DESDDClassifier desddClassifier, String versao, String geracao, String reacao, String medida, int randomSeed, String detectorOpt, int numBaseLeaners)
    {
       this.desddClassifier = desddClassifier;
       this.versao = versao;
       this.geracao = geracao;
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.detector = detectorOpt;
       this.numBaseLeaners = numBaseLeaners;
    }
    
    @Override
    public MetodoFactory getMetodo() {
    	
    	DESDDClassifier metodoClassificadorDESDD = null;
    	String codigo = "V" + this.versao;
    	
    	if (this.desddClassifier != null)
    	{
    		//Foi passado como argumento
    		metodoClassificadorDESDD = this.desddClassifier;
    	}
    	else
    	{
	    	switch (versao) {
			case "12":
				metodoClassificadorDESDD = new MetodoClassificadorV12();
				MetodoClassificadorV12 v12 = (MetodoClassificadorV12) metodoClassificadorDESDD;
				v12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
				break;
			case "13":
				metodoClassificadorDESDD = new MetodoClassificadorV13();
				MetodoClassificadorV13 v13 = (MetodoClassificadorV13) metodoClassificadorDESDD;
				
				v13.lambdasOption.setValueViaCLIString("99.61200861412439,36.55414207024308,67.92271054320723,41.63812120946448,7.970389050693205,66.54491782828323,79.85414980992073,21.76491697893891,27.061342359340998,61.288316583438174,22.526020005390873");
				v13.lambdaMinOption.setValueViaCLIString("0.001");
				v13.lambdaMaxOption.setValueViaCLIString("100");
				break;
			case "14":
				metodoClassificadorDESDD = new MetodoClassificadorV14();
				MetodoClassificadorV14 v14 = (MetodoClassificadorV14) metodoClassificadorDESDD;
				v14.lambdaMinOption.setValueViaCLIString("0.001");
				v14.lambdaMaxOption.setValueViaCLIString("100");
				break;
			default:
				break;
			}
    	}

    	metodoClassificadorDESDD.selectionOptionEstrategiaGeracao.setValueViaCLIString(this.geracao);
    	metodoClassificadorDESDD.driftDetectionMethodOption.setValueViaCLIString(this.detector);
    	metodoClassificadorDESDD.ensemblesNumberOption.setValue(11);
    	metodoClassificadorDESDD.ensembleSizeOption.setValue(10);
    	metodoClassificadorDESDD.selectionOptionMedidaCalculo.setChosenLabel(this.medidaCalculo);
    	metodoClassificadorDESDD.medidaCalculoJanela.setValue(-1);
    	metodoClassificadorDESDD.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
    	metodoClassificadorDESDD.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.reacao);
    	metodoClassificadorDESDD.ensemblesNumRemoverRecuperacaoOption.setValue(-1);
    	metodoClassificadorDESDD.numBaseLeanersOption.setValue(this.numBaseLeaners);
    	metodoClassificadorDESDD.baseLearner1Option.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
    	metodoClassificadorDESDD.baseLearner2Option.setValueViaCLIString("lazy.kNN");
    	metodoClassificadorDESDD.baseLearner3Option.setValueViaCLIString("trees.RandomHoeffdingTree");
    	metodoClassificadorDESDD.baseLearner4Option.setValueViaCLIString("functions.Perceptron");
    	metodoClassificadorDESDD.baseLearner5Option.setValueViaCLIString("bayes.NaiveBayes");
    	metodoClassificadorDESDD.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(metodoClassificadorDESDD);
  
        if (this.numBaseLeaners > 1)
        	codigo = codigo + "_HET";
        else
        	codigo = codigo + "_HOM";
        
        codigo = codigo + "_" + this.geracao + "_" + this.detector + "_" + this.reacao;
        
        metodo.setCodigo(codigo);
        return metodo;
    }
	
}
