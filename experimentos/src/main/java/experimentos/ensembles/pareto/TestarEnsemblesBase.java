package experimentos.ensembles.pareto;

import experimental.analise.AnaliseCompleta;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.EnsembleFactory;
import java.util.List;
import experimental.analise.ResultadoClassificador;
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
        List<ResultadoClassificador> listaResultados = new ArrayList<>();
        
        double[] ensemble_lambdas = new double[]{100, 50, 10, 5, 1, 0.5, 0.1, 0.05, 0.01, 0.005, 0.001};
        //double[] ensemble_lambdas = new double[]{100,50,10,5,1,0.5,0.1,0.05,0.01};
        EnsembleFactory[] ensembleInfo = new EnsembleFactory[ensemble_lambdas.length];

        for (int i = 0; i < ensemble_lambdas.length; i++) {
            System.out.println("########################## #############    --    ENSEMBLE " + ensemble_lambdas[i]);
            ensembleInfo[i] = new EnsembleFactory(ensemble_lambdas[i], 25, 1, true, 1, "ENSEMBLE_" + i);
            TestePrequential testePrequential = new TestePrequential(this.base, this.baseDrifts, ensembleInfo[i]);
            ResultadoClassificador resultado = testePrequential.test();
            resultado.setCodigo(String.valueOf(ensemble_lambdas[i]));
            listaResultados.add(resultado);
        }
        
        AnaliseCompleta analiseCompleta = new AnaliseCompleta(listaResultados, path, base.getNome(), base.getNome() + "_pareto");
        analiseCompleta.analisa(false); //False para minimizar
    }
    
    
    
}
