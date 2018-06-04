package experimentos.ensembles.pareto;

import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimentos.config.Configuracoes;

public class TestesBaseGauss {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseGauss();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
