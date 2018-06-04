package experimentos.ensembles.pareto;

import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimentos.config.Configuracoes;

public class TestesBaseLine {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseLine();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
