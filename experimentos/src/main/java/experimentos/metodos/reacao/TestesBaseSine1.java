package experimentos.metodos.reacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSine1;
import experimentos.config.Configuracoes;

public class TestesBaseSine1 {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSine1();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
