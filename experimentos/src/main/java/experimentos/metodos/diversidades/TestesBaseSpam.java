package experimentos.metodos.diversidades;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSpam;
import experimentos.config.Configuracoes;

public class TestesBaseSpam {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSpam();
        
        TestarDiversidades testarBase = new TestarDiversidades(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
    }
}
