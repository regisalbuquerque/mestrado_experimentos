package experimental.testes;

import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.Classificador;

public class TestePrequential {

    private final Base base;
    private final List<BaseDrifts> listaBaseDrifts;
    private final Classificador classificador;
    

    public TestePrequential(Base base, List<BaseDrifts> listaDrifts, Classificador classificador) {
        this.base = base;
        this.classificador = classificador;
        this.listaBaseDrifts = listaDrifts;
    }

    public Resultado test() {
            
    	if (classificador.isMetodo())
    		return TesteMetodo.testPrequencial(base, listaBaseDrifts, classificador);
    	else if(classificador.isEnsemble())
    		return TesteEnsemble.testPrequencial(base, listaBaseDrifts, classificador);
    	
    	return null;

    }
    

    

}
