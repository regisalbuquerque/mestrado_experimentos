package testes;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.metodo.util.calculo.Matematica;

import java.util.ArrayList;
import java.util.List;
import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.streams.generators.RandomRBFGenerator;

public class OnLineBaggingModificado {

    public static void main(String[] args) {

        System.out.println("OnLineBagging");

        double lambda = 1;
        RandomRBFGenerator stream = new RandomRBFGenerator();
        stream.prepareForUse();

        List<Classifier> listaClassifiers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Classifier classificador = new HoeffdingTree();
            classificador.setModelContext(stream.getHeader());
            classificador.prepareForUse();
            listaClassifiers.add(classificador);
        }

        for (int i = 0; i < 30; i++) {
            Instance instancia = stream.nextInstance().getData();
            System.out.println("--------------------------------------------------");
            System.out.println("INSTÂNCIA: " + i);
            System.out.println("CLASS VALUE: " + instancia.classValue());
            
            //Teste
            for (Classifier membroClassificador : listaClassifiers) {
                boolean correctly = membroClassificador.correctlyClassifies(instancia);
                double[] votos = membroClassificador.getVotesForInstance(instancia);
                
                System.out.println();
                for (double vote : votos) {
                    //System.out.printf(" ## %.8f", vote);
                    System.out.print(" ## " + vote);
                }
                
                System.out.print(" ## CORRETO? " + correctly);
            }
            System.out.println("");
            System.out.println("--------------------------------------------------");
            
            //Treino OnLineBagging
            for (Classifier membroClassificador : listaClassifiers) {
                //Para cada classificador executar:
                int distP = Matematica.getPoisson(lambda);
                while (distP > 0) {
                    membroClassificador.trainOnInstance(instancia);
                    distP = distP - 1;
                }
            }
            
            
        }
        
        
        
        

    }
}
