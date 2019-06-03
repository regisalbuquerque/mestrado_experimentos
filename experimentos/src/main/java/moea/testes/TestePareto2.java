package moea.testes;

import java.util.List;

import br.ufam.metodo.util.pareto.FronteiraDePareto;
import br.ufam.metodo.util.pareto.ParetoFront;
import br.ufam.metodo.util.pareto.Solucao;


/**
 *
 * @author regis
 */
public class TestePareto2 {
    public static void main(String[] args) {
        
        System.out.println("Teste");
        
        Solucao[] solucoes = new Solucao[5];
        solucoes[0] = new Solucao(1, 3.0, 1.0);
        solucoes[1] = new Solucao(2, 0.0, 3.0);
        solucoes[2] = new Solucao(3, 2.0, 2.0);
        solucoes[3] = new Solucao(4, 1.0, 2.0);
        solucoes[4] = new Solucao(5, 1.0, 3.0);
        
        ParetoFront pareto = new ParetoFront(solucoes, true, false);
        
        List<Solucao> solucoesPareto = pareto.getParetoSoluctions();
        
        for (Solucao valorValor : solucoesPareto) {
            System.out.println(valorValor.getIndex() + " # " + valorValor.getValor1() + " -  " + valorValor.getValor2());
        }
        
        System.out.println();
        
        FronteiraDePareto pareto2 = new FronteiraDePareto(solucoes, true, false);
        
        List<Solucao> solucoesPareto2 = pareto2.getParetoSoluctions();
        
        for (Solucao valorValor : solucoesPareto2) {
            System.out.println(valorValor.getIndex() + " # " + valorValor.getValor1() + " -  " + valorValor.getValor2());
        }
        
        
    }
}
