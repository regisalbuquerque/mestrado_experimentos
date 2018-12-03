package experimental.metodos;

import br.ufam.metodos.v12.MetodoClassificadorV12;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class MetodoV12Config1 implements MetodoTeste{

    private String reacao;
    private String medidaCalculo;
    private int randomSeed = 1;
    private String detector = "DDM";
    private int numBaseLeaners = 1;
    
    
    public MetodoV12Config1(String reacao, String medida, int randomSeed, String detectorOpt, int numBaseLeaners)
    {
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.detector = detectorOpt;
       this.numBaseLeaners = numBaseLeaners;
    }
	
    @Override
    public MetodoFactory getMetodo() {
        
        MetodoClassificadorV12 metodoClassificadorV12 = new MetodoClassificadorV12();
        
        metodoClassificadorV12.driftDetectionMethodOption.setValueViaCLIString(this.detector);
//        metodoClassificadorV12.ensemblesNumberOption.setValue(9);
//        metodoClassificadorV12.ensembleSizeOption.setValue(10);
//        metodoClassificadorV12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01");
        metodoClassificadorV12.ensemblesNumberOption.setValue(11);
        metodoClassificadorV12.ensembleSizeOption.setValue(10);
        metodoClassificadorV12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
        metodoClassificadorV12.selectionOptionMedidaCalculo.setChosenLabel(this.medidaCalculo);
        metodoClassificadorV12.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
        metodoClassificadorV12.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.reacao);
        metodoClassificadorV12.ensemblesNumRemoverRecuperacaoOption.setValue(-1);
        metodoClassificadorV12.numBaseLeanersOption.setValue(this.numBaseLeaners);
        metodoClassificadorV12.baseLearner1Option.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        metodoClassificadorV12.baseLearner2Option.setValueViaCLIString("lazy.kNN");
        metodoClassificadorV12.baseLearner3Option.setValueViaCLIString("trees.RandomHoeffdingTree");
        metodoClassificadorV12.baseLearner4Option.setValueViaCLIString("functions.Perceptron");
        metodoClassificadorV12.baseLearner5Option.setValueViaCLIString("bayes.NaiveBayes");
        
        MetodoFactory metodo = new MetodoFactory(metodoClassificadorV12);
        metodo.setCodigo("V12_Config1");
        return metodo;
    }
    
    
    
    
}
