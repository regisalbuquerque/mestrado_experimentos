package experimentos.metodos.comparacao;

import experimental.bases.BaseElec;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TesteBaseElec {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseElec();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
