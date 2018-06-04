package experimentos.ensembles.pareto;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TestesBaseCircle {
    public static void main(String[] args) {
        BaseFactory baseTeste = new BaseCircle();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_PARETO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
