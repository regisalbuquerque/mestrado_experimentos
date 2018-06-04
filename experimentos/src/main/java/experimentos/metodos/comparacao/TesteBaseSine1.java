package experimentos.metodos.comparacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSine1;
import experimentos.config.Configuracoes;

public class TesteBaseSine1 {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseSine1();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
