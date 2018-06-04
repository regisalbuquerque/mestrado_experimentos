package testes;

import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.streams.generators.RandomRBFGenerator;

public class ManipularStream {

    public ManipularStream() {
    }
    
    public void run()
    {
        RandomRBFGenerator stream = new RandomRBFGenerator();
        stream.prepareForUse();
        
        Classifier classificador = new HoeffdingTree();
        classificador.setModelContext(stream.getHeader());
        classificador.prepareForUse();
        
        
        Instance instancia = stream.nextInstance().getData();
        
        //Teste antes do treino
        double [] votos = classificador.getVotesForInstance(instancia);
        boolean predicaoCerta = classificador.correctlyClassifies(instancia);
        
        //Treino
        classificador.trainOnInstance(instancia);
        
        //Teste após o treino
        double [] votos2 = classificador.getVotesForInstance(instancia);
        boolean predicaoCerta2 = classificador.correctlyClassifies(instancia);
        
        System.out.println("Teste terminado!");
        
    }
    
    public static void main(String[] args) {
        ManipularStream exp = new ManipularStream();
        exp.run();
    }
}
