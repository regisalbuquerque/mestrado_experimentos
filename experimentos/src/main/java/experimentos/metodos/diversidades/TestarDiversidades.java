package experimentos.metodos.diversidades;

import java.util.ArrayList;
import java.util.List;

import experimental.analise.RelatResumo;
import experimental.analise.ResultadoClassificador;
import experimental.metodos.DDMConfig1;
import experimental.metodos.LeveragingBagSelectionV2Reacao;
import experimental.metodos.LeveragingBagVersaoOriginal;
import experimental.metodos.MetodoV12Reacao;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.MetodoFactory;
import experimental.testes.TestePrequential;

public class TestarDiversidades {
    
    private final String path;
    private final Base base;
    private final List<BaseDrifts> baseDrifts;
    

    public TestarDiversidades(String path, Base base, List<BaseDrifts> baseDrifts) {
        this.path = path;
        this.base = base;
        this.baseDrifts = baseDrifts;
    }
    

    public void executa()
    {
    	
    	int seed = 1;
    	
        List<ResultadoClassificador> listaResultados = new ArrayList<>();
        
        MetodoFactory[] classificadores = new MetodoFactory[6];
        
        
        //classificadores[0] = new LeveragingBagSelectionV2Reacao("SimpleReset", "Ambiguidade", seed).getMetodo();
        //classificadores[1] = new MetodoV12Reacao("SimpleReset","Ambiguidade", seed).getMetodo(); 
        classificadores[0] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Ambiguidade", seed).getMetodo(); 
        
        //classificadores[3] = new LeveragingBagSelectionV2Reacao("SimpleReset", "Margem", seed).getMetodo();
        //classificadores[4] = new MetodoV12Reacao("SimpleReset","Margem", seed).getMetodo(); 
        classificadores[1] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Margem", seed).getMetodo();
        
        //classificadores[6] = new LeveragingBagSelectionV2Reacao("SimpleReset", "Qstatistics", seed).getMetodo();
        //classificadores[7] = new MetodoV12Reacao("SimpleReset","Qstatistics", seed).getMetodo(); 
        classificadores[2] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Qstatistics", seed).getMetodo();
        
        //classificadores[9] = new LeveragingBagSelectionV2Reacao("SimpleReset", "Correlation", seed).getMetodo();
        //classificadores[10] = new MetodoV12Reacao("SimpleReset","Correlation", seed).getMetodo(); 
        classificadores[3] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Correlation", seed).getMetodo();
        
        //classificadores[12] = new LeveragingBagSelectionV2Reacao("SimpleReset", "Disagreement", seed).getMetodo();
        //classificadores[13] = new MetodoV12Reacao("SimpleReset","Disagreement", seed).getMetodo(); 
        classificadores[4] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","Disagreement", seed).getMetodo();

       
        //classificadores[15] = new LeveragingBagSelectionV2Reacao("SimpleReset", "DoubleFault", seed).getMetodo();
        //classificadores[16] = new MetodoV12Reacao("SimpleReset","DoubleFault", seed).getMetodo(); 
        classificadores[5] = new MetodoV12Reacao("RetreinaTodosComBufferWarning","DoubleFault", seed).getMetodo();
        
        
        for (int i = 0; i < classificadores.length; i++) {
        	System.out.println("************ TESTE DE REAÇÃO: " + (i+1) +"de" + classificadores.length);
            TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts, classificadores[i]);
            ResultadoClassificador resultado = testePrequential.test();
            
            resultado.setCodigo("test_" + classificadores[i].getCodigo());
            listaResultados.add(resultado);
        }
        
        RelatResumo.gravar(listaResultados, path, this.base.getNome(), false);
    }
    
}

