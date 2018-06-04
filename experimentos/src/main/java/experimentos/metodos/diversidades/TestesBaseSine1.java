package experimentos.metodos.diversidades;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSine1;
import experimentos.config.Configuracoes;

public class TestesBaseSine1 {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSine1();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
