package experimentos.metodos.diversidades;

import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimentos.config.Configuracoes;

public class TestesBaseGauss {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseGauss();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
