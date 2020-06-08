/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental.metodos;

import br.ufam.metodos.originais.DriftDetectionMethodClassifier;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class DDMConfig1 implements MetodoTeste{
	
	private int randomSeed = 1;
	
	
	public DDMConfig1()
    {
       
    }
	
	public DDMConfig1(int randomSeed)
    {
       this.randomSeed = randomSeed;
    }

    @Override
    public MetodoFactory getMetodo() {
        DriftDetectionMethodClassifier DDM = new DriftDetectionMethodClassifier();
        DDM.baseLearnerOption.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        DDM.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(DDM);
        metodo.setCodigo("DDM_Original");
        return metodo;
    }
    
    
}
