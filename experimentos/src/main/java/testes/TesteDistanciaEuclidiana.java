package testes;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.metodo.util.calculo.Matematica;
import br.ufam.metodo.util.dados.Dados;

/**
 *
 * @author regis
 */
public class TesteDistanciaEuclidiana {
    public static void main(String[] args) {
        System.out.println("Teste");
        
        Dados dados = new Dados("src/main/resources/dados/teste.arff", -1); //new Dados(new SEAGenerator()); //new Dados("src/main/resources/dados/gauss.arff", 3);
        dados.prepareForUse();
        
        
        if (dados.hasMoreInstances()) 
        {
            Instance instanciaAtual = dados.getProximaInstancia();
            
            System.out.println("Teste:" + instanciaAtual.numInputAttributes());
            System.out.println("Teste:" + instanciaAtual.numOutputAttributes());
            
            
            System.out.println("Teste" + instanciaAtual.value(0));
            System.out.println("Teste" + instanciaAtual.value(1));
            System.out.println("Teste" + instanciaAtual.value(2));
        }
        
        Instance instancia1 = dados.getProximaInstancia();
        
        while (dados.hasMoreInstances())
        {
            Instance instancia2 = dados.getProximaInstancia();

            double distancia = Matematica.euclidean(instancia1, instancia2);

            System.out.println("Distancia: " + distancia);
        }

    }
}
