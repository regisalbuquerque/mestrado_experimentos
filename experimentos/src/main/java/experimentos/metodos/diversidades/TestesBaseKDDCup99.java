package experimentos.metodos.diversidades;

import experimental.bases.BaseFactory;
import experimental.bases.BaseKDDCup99;
import experimentos.config.Configuracoes;

public class TestesBaseKDDCup99 {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseKDDCup99();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
