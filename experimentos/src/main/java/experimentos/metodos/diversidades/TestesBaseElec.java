package experimentos.metodos.diversidades;

import experimental.bases.BaseElec;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TestesBaseElec {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseElec();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
