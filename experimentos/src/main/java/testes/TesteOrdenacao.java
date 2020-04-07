package testes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufam.metodo.util.model.EnsembleOnLineBagging;
import br.ufam.metodo.util.model.EnsembleDiversidadeAcuraciaVoto;

public class TesteOrdenacao {
    public static void main(String[] args) {
        EnsembleOnLineBagging e1 = new EnsembleOnLineBagging();
        e1.lambda = 1;
        
        EnsembleOnLineBagging e2 = new EnsembleOnLineBagging();
        e2.lambda = 2;
        
        EnsembleOnLineBagging e3 = new EnsembleOnLineBagging();
        e3.lambda = 3;
        
        EnsembleOnLineBagging e4 = new EnsembleOnLineBagging();
        e4.lambda = 4;
        
        EnsembleOnLineBagging e5 = new EnsembleOnLineBagging();
        e5.lambda = 5;
        
        EnsembleOnLineBagging e6 = new EnsembleOnLineBagging();
        e6.lambda = 6;
        
        
        
        List<EnsembleDiversidadeAcuraciaVoto> lista = new ArrayList<>();
        lista.add(new EnsembleDiversidadeAcuraciaVoto(e1, 1.0, 1.0, 1));
        lista.add(new EnsembleDiversidadeAcuraciaVoto(e2, 2.0, 4.0, 2));
        lista.add(new EnsembleDiversidadeAcuraciaVoto(e3, 3.0, 3.0, 3));
        lista.add(new EnsembleDiversidadeAcuraciaVoto(e4, 4.0, 4.0, 4));
        lista.add(new EnsembleDiversidadeAcuraciaVoto(e5, 1.0, 4.0, 5));
        lista.add(new EnsembleDiversidadeAcuraciaVoto(e6, 6.0, 6.0, 6));
        
        Collections.sort(lista, EnsembleDiversidadeAcuraciaVoto.comparatorAcuraciaDiversidade());
        //Collections.sort(lista, EnsembleDiversidadeAcuraciaVoto.comparatorDiversidade());
        
        System.out.println("Ensemble(E) - Acurácia(A) - Diversidade(D) - Voto(V)");
        for (EnsembleDiversidadeAcuraciaVoto aux : lista) {
            System.out.print(aux.ensemble.lambda + "(E) - ");
            System.out.print(aux.acuracia + "(A) - ");
            System.out.print(aux.diversidade + "(D) - ");
            System.out.print(aux.voto + "(V)");
            System.out.println("");
        }
    }
}
