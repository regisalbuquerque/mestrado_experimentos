package experimentos.metodos.reacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimentos.config.Configuracoes;

public class TestesBaseGauss {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseGauss();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
