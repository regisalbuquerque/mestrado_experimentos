package experimentos.ensembles.medias;

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
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
