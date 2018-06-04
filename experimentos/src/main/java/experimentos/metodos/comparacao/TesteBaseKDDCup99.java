package experimentos.metodos.comparacao;

import experimental.bases.BaseFactory;
import experimental.bases.BaseKDDCup99;
import experimentos.config.Configuracoes;

public class TesteBaseKDDCup99 {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseKDDCup99();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
