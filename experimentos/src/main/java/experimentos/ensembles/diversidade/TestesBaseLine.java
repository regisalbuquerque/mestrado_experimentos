package experimentos.ensembles.diversidade;

import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseLine {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseLine();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_DIVERSIDADES, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
