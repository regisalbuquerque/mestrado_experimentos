package experimentos.ensembles.medias;

import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseGauss {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseGauss();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
