package testes;

import com.yahoo.labs.samoa.instances.Instance;
import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.streams.ArffFileStream;

public class ManipularArff {

    public ManipularArff() {
    }
    
    public void run()
    {
        ArffFileStream stream = new ArffFileStream("src/main/resources/dados/gauss.arff", 3);
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
        ManipularArff exp = new ManipularArff();
        exp.run();
    }
}
