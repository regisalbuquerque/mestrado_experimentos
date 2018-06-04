package experimentos.ensembles.medias;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

/**
 *
 * @author regis
 */
public class TestesBaseCircle {
    public static void main(String[] args) {
        
        BaseFactory baseTeste = new BaseCircle();
        
        TestarEnsemblesBase testarBase = new TestarEnsemblesBase(Configuracoes.PATH_MEDIAS, baseTeste.getBase(), baseTeste.getBaseDrifts());
        testarBase.executa();
        
        
    }
}
