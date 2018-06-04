package experimentos.metodos.reacao;

import experimental.bases.BaseElec;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TestesBaseElec {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseElec();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
