package experimentos.metodos.reacao;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import experimental.analise.RelatResumo;
import experimental.metodos.DDMConfig1;
import experimental.metodos.LeveragingBagSelectionV2Reacao;
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.metodos.MetodoV12Reacao;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.MetodoFactory;
import experimental.testes.TestePrequential;

public class TestarReacao {
    
    private final String path;
    private final Base base;
    private final List<BaseDrifts> baseDrifts;
    

    public TestarReacao(String path, Base base, List<BaseDrifts> baseDrifts) {
        this.path = path;
        this.base = base;
        this.baseDrifts = baseDrifts;
    }
    

    public void executa()
    {
    	
    	int seed = 1;
    	
        List<Resultado> listaResultados = new ArrayList<>();
        
        MetodoFactory[] classificadores = new MetodoFactory[12];
        
        //DDM
        classificadores[0] = new DDMConfig1().getMetodo();
        
        // LeveragingBAG Original
        classificadores[1] = new LeveragingBagVersaoOriginal().getMetodo();
        
        classificadores[2] = new LeveragingBagSelectionV2Reacao("SimpleReset", "Ambiguidade", seed).getMetodo();
        classificadores[3] = new MetodoV12Reacao("SimpleReset","Ambiguidade", seed).getMetodo(); 
        
        classificadores[4] = new LeveragingBagSelectionV2Reacao("SimpleResetEnsemble", "Ambiguidade", seed).getMetodo();
        classificadores[5] = new MetodoV12Reacao("SimpleResetEnsemble","Ambiguidade", seed).getMetodo(); 
        
        classificadores[6] = new LeveragingBagSelectionV2Reacao("SimpleResetSystem", "Ambiguidade", seed).getMetodo();
        classificadores[7] = new MetodoV12Reacao("SimpleResetSystem","Ambiguidade", seed).getMetodo(); 
        
        
        //ADWIN com Reset System
        classificadores[8] = new MetodoV12Reacao("SimpleResetSystem1Detector","Ambiguidade", seed, "ADWINChangeDetector").getMetodo(); 
        
        //DDM com Reset System
        classificadores[9] = new MetodoV12Reacao("SimpleResetSystem1Detector","Ambiguidade", seed).getMetodo();
        
        //ADWIN com Retreina com Buffer
        classificadores[10] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Ambiguidade", seed, "ADWINChangeDetector").getMetodo();
        
        //DDM com Retreina com Buffer
        classificadores[11] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Ambiguidade", seed).getMetodo();
        
        
        
        for (int i = 0; i < classificadores.length; i++) {
        	System.out.println("************ TESTE DE REAÇÃO: " + (i+1) +"de" + classificadores.length);
            TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts, classificadores[i]);
            Resultado resultado = testePrequential.test();
            
            resultado.setCodigo(classificadores[i].getCodigo());
            listaResultados.add(resultado);
        }
        
        RelatResumo.gravar(listaResultados, path, this.base.getNome(), false);
    }
    
}

