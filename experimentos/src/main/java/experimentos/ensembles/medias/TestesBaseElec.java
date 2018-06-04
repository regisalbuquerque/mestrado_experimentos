package experimentos.ensembles.medias;

import experimental.bases.BaseElec;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseElec {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseElec();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
