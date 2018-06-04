package experimental.metodos;

import br.ufam.metodos.leveraging.v1.LeveragingBagOriginal;
import experimental.model.MetodoFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class LeveragingBagVersaoOriginal implements MetodoTeste{
	
	
	private int randomSeed = 1;
	
	
	public LeveragingBagVersaoOriginal()
    {
       
    }
	
	public LeveragingBagVersaoOriginal(int randomSeed)
    {
       this.randomSeed = randomSeed;
    }
	
    public MetodoFactory getMetodo()
    {
        LeveragingBagOriginal leveraging = new LeveragingBagOriginal();
        leveraging.baseLearnerOption.setValueViaCLIString(Configuracoes.BASE_CLASSIFIER);
        leveraging.setRandomSeed(this.randomSeed);
        //leveraging.outputCodesOption.setValue(true);

        MetodoFactory metodo = new MetodoFactory(leveraging);
        metodo.setCodigo("LB_Original");
        
        return metodo;
    }
}
