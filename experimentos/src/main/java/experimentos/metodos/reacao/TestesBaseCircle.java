package experimentos.metodos.reacao;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TestesBaseCircle {
    
    public static void main(String[] args) {

        BaseFactory baseTeste = new BaseCircle();
        
        TestarReacao testarBase = new TestarReacao(Configuracoes.PATH_REACAO, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
