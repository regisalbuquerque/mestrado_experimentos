package experimentos.metodos.diversidades;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TestesBaseCircle {
    
    public static void main(String[] args) {

        BaseFactory baseTeste = new BaseCircle();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
