/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package experimental.metodos;

import br.ufam.metodos.originais.DiversityForDealingWithDrifts;
import experimental.model.MetodoFactory;

/**
 *
 * @author regis
 */
public class DDDVersaoOriginal implements MetodoTeste{
	
	private int randomSeed = 1;
	
	
	public DDDVersaoOriginal()
    {
       
    }
	
	public DDDVersaoOriginal(int randomSeed)
    {
       this.randomSeed = randomSeed;
    }

    @Override
    public MetodoFactory getMetodo() {
        DiversityForDealingWithDrifts DDD = new DiversityForDealingWithDrifts();
        DDD.setRandomSeed(this.randomSeed);
        
        MetodoFactory metodo = new MetodoFactory(DDD);
        metodo.setCodigo("DDD_Original");
        return metodo;
    }
    
    
}
