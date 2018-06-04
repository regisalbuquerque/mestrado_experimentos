package experimentos.ensembles.pareto;

import experimental.bases.BaseFactory;
import experimental.bases.BaseKDDCup99;
import experimentos.config.Configuracoes;

public class TestesBaseKDD {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseKDDCup99();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
