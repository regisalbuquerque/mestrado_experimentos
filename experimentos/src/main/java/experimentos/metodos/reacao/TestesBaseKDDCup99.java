package experimentos.metodos.reacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseKDDCup99;
import experimentos.config.Configuracoes;

public class TestesBaseKDDCup99 {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseKDDCup99();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
