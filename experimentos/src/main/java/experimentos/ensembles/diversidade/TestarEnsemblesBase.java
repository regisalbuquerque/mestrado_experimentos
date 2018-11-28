package experimentos.ensembles.diversidade;

import experimental.analise.RelatDiversidade;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.EnsembleFactory;
import java.util.List;

import br.ufam.metodo.util.medidor.Resultado;
import experimental.analise.RelatResumo;
import experimental.testes.TestePrequential;
import java.util.ArrayList;

public class TestarEnsemblesBase {
    
    private final String path;
    private final Base base;
    private final List<BaseDrifts> baseDrifts;
    

    public TestarEnsemblesBase(String path, Base base, List<BaseDrifts> baseDrifts) {
        this.path = path;
        this.base = base;
        this.baseDrifts = baseDrifts;
    }
    
    public void executa()
    {
        List<Resultado> listaResultados = new ArrayList<>();
        
        double[] ensemble_lambdas = new double[]{1};
        EnsembleFactory[] ensembleInfo = new EnsembleFactory[ensemble_lambdas.length];

        for (int i = 0; i < ensemble_lambdas.length; i++) {
            System.out.println("ENSEMBLE: " + ensemble_lambdas[i]);
            ensembleInfo[i] = new EnsembleFactory(ensemble_lambdas[i], 25, 5, true, 1, "ENSEMBLE_" + i);
            TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts, ensembleInfo[i]);
            Resultado resultado = testePrequential.test();
            resultado.setCodigo(String.valueOf(ensemble_lambdas[i]));
            listaResultados.add(resultado);
            
            RelatDiversidade.gravar(resultado, path, this.base.getNome() + ensemble_lambdas[i] + ".csv");
        }
        
        RelatResumo.gravar(listaResultados, path, this.base.getNome(), false);
    }

}
