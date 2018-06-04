package experimentos.ensembles.medias;

import experimental.bases.BaseFactory;
import experimental.bases.BaseKDDCup99;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseKDDCup99 {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseKDDCup99();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
