package experimentos.metodos.reacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSpam;
import experimentos.config.Configuracoes;

public class TestesBaseSpam {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSpam();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
