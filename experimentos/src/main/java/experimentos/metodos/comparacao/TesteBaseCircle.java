package experimentos.metodos.comparacao;

import experimental.bases.BaseCircle;
import experimental.bases.BaseFactory;
import experimentos.config.Configuracoes;

public class TesteBaseCircle {
    
    public static void main(String[] args) {
        
        BaseFactory base = new BaseCircle();
        
        TestarClassificadorBase testarBase = new TestarClassificadorBase(Configuracoes.PATH_COMPARACAO, base.getBase(), base.getBaseDrifts());
        testarBase.executa();
        
        
    }
    
}
