package experimentos.ensembles.medias;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSine1;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseSine1 {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSine1();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
