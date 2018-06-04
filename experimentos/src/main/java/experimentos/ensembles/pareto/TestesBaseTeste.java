package experimentos.ensembles.pareto;

import experimental.bases.BaseFactory;
import experimental.bases.BaseTeste;
import experimentos.config.Configuracoes;

public class TestesBaseTeste {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseTeste();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
