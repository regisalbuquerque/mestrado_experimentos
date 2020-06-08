package experimental.metodos;

import br.ufam.metodos.DESDD.MetodoClassificadorV14;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class MetodoV14Config1 implements MetodoTeste {
	
	private String geracao;
    private String reacao;
    private String medidaCalculo;
    private int randomSeed = 1;
    private String detector = "DDM";
    private int numBaseLeaners = 1;
    
    
    public MetodoV14Config1(String geracao, String reacao, String medida, int randomSeed, String detectorOpt, int numBaseLeaners)
    {
       this.geracao = geracao;
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.detector = detectorOpt;
       this.numBaseLeaners = numBaseLeaners;
    }
    
    
    @Override
    public MetodoFactory getMetodo() {
        MetodoClassificadorV14 metodoClassificadorV14 = new MetodoClassificadorV14();
        
        metodoClassificadorV14.selectionOptionEstrategiaGeracao.setValueViaCLIString(this.geracao);
        metodoClassificadorV14.driftDetectionMethodOption.setValueViaCLIString(this.detector);
        metodoClassificadorV14.ensemblesNumberOption.setValue(11);
        metodoClassificadorV14.ensembleSizeOption.setValue(10);
        metodoClassificadorV14.lambdaMinOption.setValueViaCLIString("0.001");
        metodoClassificadorV14.lambdaMaxOption.setValueViaCLIString("100");
        //.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
        metodoClassificadorV14.selectionOptionMedidaCalculo.setChosenLabel(this.medidaCalculo);
        metodoClassificadorV14.medidaCalculoJanela.setValue(-1);
        metodoClassificadorV14.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
        metodoClassificadorV14.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.reacao);
        metodoClassificadorV14.ensemblesNumRemoverRecuperacaoOption.setValue(-1);
        metodoClassificadorV14.numBaseLeanersOption.setValue(this.numBaseLeaners);
        metodoClassificadorV14.baseLearner1Option.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        metodoClassificadorV14.baseLearner2Option.setValueViaCLIString("lazy.kNN");
        metodoClassificadorV14.baseLearner3Option.setValueViaCLIString("trees.RandomHoeffdingTree");
        metodoClassificadorV14.baseLearner4Option.setValueViaCLIString("functions.Perceptron");
        metodoClassificadorV14.baseLearner5Option.setValueViaCLIString("bayes.NaiveBayes");
      
        metodoClassificadorV14.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(metodoClassificadorV14);
        
        String codigo = "V14"; 
        
        if (this.numBaseLeaners > 1)
        	codigo = codigo + "_HET";
        else
        	codigo = codigo + "_HOM";
        
        codigo = codigo + "_" + this.geracao + "_" + this.detector;
        
        metodo.setCodigo(codigo);
        return metodo;
    }
    

}
