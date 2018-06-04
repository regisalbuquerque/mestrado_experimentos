package experimentos.metodos.comparacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseLine;
import experimentos.config.Configuracoes;

public class TesteBaseLine {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseLine();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
