package experimentos.metodos.comparacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseGauss;
import experimentos.config.Configuracoes;

public class TesteBaseGauss {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseGauss();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
