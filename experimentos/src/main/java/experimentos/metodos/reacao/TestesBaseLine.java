package experimentos.metodos.reacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimentos.config.Configuracoes;

public class TestesBaseLine {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseLine();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
