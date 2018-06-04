package testes;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.metodo.util.dados.Dados;
import br.ufam.metodos.leveraging.v1.LeveragingBagOriginal;
import br.ufam.metodos.v12.MetodoClassificadorV12;
import moa.classifiers.Classifier;

public class TesteMetodo {
    
    public static void main(String[] args) {
        
        int acertos1 = 0, acertos2 = 0;
        int erros1 = 0, erros2 = 0;
        
        Dados dados = new Dados("src/main/resources/dados/gauss.arff", -1); //new Dados(new SEAGenerator()); //new Dados("src/main/resources/dados/gauss.arff", 3);
        dados.prepareForUse();
        
        
        MetodoClassificadorV12 metodoClassificadorV12 = new MetodoClassificadorV12();
        metodoClassificadorV12.selectionOptionEstrategiaSelecao.setValueViaCLIString("ParetoMaiorAcc");
        
        LeveragingBagOriginal leveragingOriginal = new LeveragingBagOriginal();
        
        Classifier metodoClassificador1 = leveragingOriginal;
        Classifier metodoClassificador2 = metodoClassificadorV12;
        
        metodoClassificador1.setModelContext(dados.getDataHeader());
        metodoClassificador1.prepareForUse();
        
        metodoClassificador2.setModelContext(dados.getDataHeader());
        metodoClassificador2.prepareForUse();
        
        for (int i = 0; dados.hasMoreInstances(); i++)
        {
            Instance instanciaAtual = dados.getProximaInstancia();
            
  

            if (metodoClassificador1.correctlyClassifies(instanciaAtual))
                acertos1++;
            else
                erros1++;
            
            if (metodoClassificador2.correctlyClassifies(instanciaAtual))
                acertos2++;
            else
                erros2++;
            
            

            metodoClassificador1.trainOnInstance(instanciaAtual);
            metodoClassificador2.trainOnInstance(instanciaAtual);
                
        }
        
        System.out.println("INDICADORES 1: " + acertos1 + " acertos " + erros1 + " erros." );
        System.out.println("INDICADORES 2: " + acertos2 + " acertos " + erros2 + " erros." );
 
    }
    
}