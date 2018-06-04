package experimentos.ensembles.pareto;

import experimental.bases.BaseElec;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TestesBaseElec {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseElec();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
