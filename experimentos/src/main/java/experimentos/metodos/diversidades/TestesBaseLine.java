package experimentos.metodos.diversidades;

import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimentos.config.Configuracoes;

public class TestesBaseLine {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseLine();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
