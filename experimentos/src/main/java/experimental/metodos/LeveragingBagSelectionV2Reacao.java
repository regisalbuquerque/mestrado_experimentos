/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental.metodos;

import br.ufam.metodos.LB.LeveragingBagSelection;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class LeveragingBagSelectionV2Reacao implements MetodoTeste{
    
    private String reacao;
    private String medidaCalculo;
    private int randomSeed = 1;
    
    public LeveragingBagSelectionV2Reacao(String reacao)
    {
       this.reacao = reacao;
       this.medidaCalculo = "Ambiguidade";
       
    }
    
    public LeveragingBagSelectionV2Reacao(String reacao, String medida, int randomSeed)
    {
       this.reacao = reacao;
       this.medidaCalculo = medida;
       this.randomSeed = randomSeed;
    }

    @Override
    public MetodoFactory getMetodo() {
    	
    	LeveragingBagSelection leveragingSelection = new LeveragingBagSelection();
        leveragingSelection.ensemblesNumberOption.setValue(11);
        leveragingSelection.weightShrinkOptionList.setValueViaCLIString("100,50,10,5,1,0.5,0.1,0.05,0.01,0.005,0.001");
        //leveragingSelection.outputCodesOption.setValue(true);
        leveragingSelection.selectionOptionMedidaCalculo.setChosenLabel(this.medidaCalculo);
        leveragingSelection.medidaCalculoJanela.setValue(-1);
        leveragingSelection.selectionOptionEstrategiaSelecao.setChosenLabel("Pareto");
        leveragingSelection.selectionOptionEstrategiaRecuperacao.setChosenLabel(this.reacao);
        leveragingSelection.baseLearnerOption.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        leveragingSelection.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(leveragingSelection);
        metodo.setCodigo("LB_SelectionV2" + this.reacao+"_"+this.medidaCalculo);
    	
        return metodo;
    }

}
