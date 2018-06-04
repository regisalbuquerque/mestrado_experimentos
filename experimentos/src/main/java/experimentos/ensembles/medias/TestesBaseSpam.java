package experimentos.ensembles.medias;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSpam;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseSpam {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseSpam();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
