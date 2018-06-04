package experimentos.metodos.comparacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseSpam;
import experimentos.config.Configuracoes;

public class TesteBaseSpam {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseSpam();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
