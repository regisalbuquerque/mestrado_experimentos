package experimental.metodos;

import br.ufam.metodos.v13.MetodoClassificadorV13;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

public class MetodoV13Config1 implements MetodoTeste {
	
    private String reacao;
    private String medidaCalculo;
    private int randomSeed = 1;
    private String detector = "DDM";
    private int numBaseLeaners = 1;
    
    
    public MetodoV13Config1(String reacao, String medida, int randomSeed, String detectorOpt, int numBaseLeaners)
    {
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.detector = detectorOpt;
       this.numBaseLeaners = numBaseLeaners;
    }
    
    
    @Override
    public MetodoFactory getMetodo() {
        MetodoClassificadorV13 metodoClassificadorV13 = new MetodoClassificadorV13();
        
        metodoClassificadorV13.driftDetectionMethodOption.setValueViaCLIString(this.detector);
        metodoClassificadorV13.ensemblesNumberOption.setValue(11);
        metodoClassificadorV13.ensembleSizeOption.setValue(10);
        metodoClassificadorV13.lambdaMinOption.setValueViaCLIString("0.001");
        metodoClassificadorV13.lambdaMaxOption.setValueViaCLIString("100");
        //.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
        metodoClassificadorV13.selectionOptionMedidaCalculo.setChosenLabel(this.medidaCalculo);
        metodoClassificadorV13.medidaCalculoJanela.setValue(100);
        metodoClassificadorV13.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
        metodoClassificadorV13.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.reacao);
        metodoClassificadorV13.ensemblesNumRemoverRecuperacaoOption.setValue(-1);
        metodoClassificadorV13.numBaseLeanersOption.setValue(this.numBaseLeaners);
        metodoClassificadorV13.baseLearner1Option.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        metodoClassificadorV13.baseLearner2Option.setValueViaCLIString("lazy.kNN");
        metodoClassificadorV13.baseLearner3Option.setValueViaCLIString("trees.RandomHoeffdingTree");
        metodoClassificadorV13.baseLearner4Option.setValueViaCLIString("functions.Perceptron");
        metodoClassificadorV13.baseLearner5Option.setValueViaCLIString("bayes.NaiveBayes");
        metodoClassificadorV13.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(metodoClassificadorV13);
        metodo.setCodigo("V13_"+this.reacao+"_"+this.medidaCalculo+"_"+this.detector+"_basesLearners"+this.numBaseLeaners);
        return metodo;
    }
    

}
