/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental.metodos;

import br.ufam.metodos.v12.MetodoClassificadorV12;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class MetodoV12Reacao implements MetodoTeste{
    
    private String reacao;
    private String medidaCalculo;
    private int randomSeed = 1;
    private String detector = "DDM";
    private int numBaseLeaners = 1;
    
    public MetodoV12Reacao(String reacao)
    {
       this.reacao = reacao;
       this.medidaCalculo = "Ambiguidade";
    }

    public MetodoV12Reacao(String reacao, String medida, int randomSeed)
    {
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
    }
    
    public MetodoV12Reacao(String reacao, String medida, int randomSeed, int numBaseLeaners)
    {
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.numBaseLeaners = numBaseLeaners;
    }
    
    public MetodoV12Reacao(String reacao, String medida, int randomSeed, String detectorOpt)
    {
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
       this.detector = detectorOpt;
    }
    
    @Override
    public MetodoFactory getMetodo() {
        MetodoClassificadorV12 metodoClassificadorV12 = new MetodoClassificadorV12();
        
        metodoClassificadorV12.driftDetectionMethodOption.setValueViaCLIString(this.detector);
        metodoClassificadorV12.ensemblesNumberOption.setValue(11);
        metodoClassificadorV12.ensembleSizeOption.setValue(10);
        metodoClassificadorV12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
        metodoClassificadorV12.selectionOptionMedidaCalculo.setChosenLabel(this.medidaCalculo);
        metodoClassificadorV12.medidaCalculoJanela.setValue(100);
        metodoClassificadorV12.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
        metodoClassificadorV12.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.reacao);
        metodoClassificadorV12.ensemblesNumRemoverRecuperacaoOption.setValue(-1);
        metodoClassificadorV12.numBaseLeanersOption.setValue(this.numBaseLeaners);
        metodoClassificadorV12.baseLearner1Option.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        metodoClassificadorV12.baseLearner2Option.setValueViaCLIString("lazy.kNN");
        metodoClassificadorV12.baseLearner3Option.setValueViaCLIString("trees.RandomHoeffdingTree");
        metodoClassificadorV12.baseLearner4Option.setValueViaCLIString("functions.Perceptron");
        metodoClassificadorV12.baseLearner5Option.setValueViaCLIString("bayes.NaiveBayes");
        metodoClassificadorV12.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(metodoClassificadorV12);
        metodo.setCodigo("V12_"+this.reacao+"_"+this.medidaCalculo+"_"+this.detector);
        return metodo;
    }

}
