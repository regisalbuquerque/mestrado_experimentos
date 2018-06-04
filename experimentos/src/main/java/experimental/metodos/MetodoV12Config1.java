package experimental.metodos;

import br.ufam.metodos.v12.MetodoClassificadorV12;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class MetodoV12Config1 implements MetodoTeste{

    @Override
    public MetodoFactory getMetodo() {
        
        MetodoClassificadorV12 metodoClassificadorV12 = new MetodoClassificadorV12();
        metodoClassificadorV12.ensemblesNumberOption.setValue(9);
        metodoClassificadorV12.ensembleSizeOption.setValue(10);
        metodoClassificadorV12.lambdasOption.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01");
        metodoClassificadorV12.selectionOptionMedidaCalculo.setChosenLabel("Ambiguidade");
        metodoClassificadorV12.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
        metodoClassificadorV12.selectionOptionEstrategiaRecuperacao.setChosenLabel("RetreinaTodosComBufferWarning");
        metodoClassificadorV12.ensemblesNumRemoverRecuperacaoOption.setValue(-1);
        metodoClassificadorV12.numBaseLeanersOption.setValue(1);
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
