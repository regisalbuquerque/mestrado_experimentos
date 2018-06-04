package experimentos.ensembles.pareto;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSpam;
import experimentos.config.Configuracoes;

public class TestesBaseSpam {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSpam();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
