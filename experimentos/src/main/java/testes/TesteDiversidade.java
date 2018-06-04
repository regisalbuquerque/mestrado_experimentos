package testes;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.diversidade.MedidaCalculo;
import br.ufam.diversidade.impl.AmbiguidadeCalculoDiversidade;
import br.ufam.diversidade.impl.CorrelationCalculoDiversidade;
import br.ufam.diversidade.impl.DisagreementCalculoDiversidade;
import br.ufam.diversidade.impl.DoubleFaultCalculoDiversidade;
import br.ufam.diversidade.impl.MargemCalculoDiversidade;
import br.ufam.diversidade.impl.QstatisticsCalculoDiversidade;
import br.ufam.metodo.util.calculo.Predicoes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import moa.classifiers.Classifier;
import moa.classifiers.trees.HoeffdingTree;
import moa.core.MiscUtils;
import moa.core.Utils;
import moa.streams.generators.RandomRBFGenerator;

public class TesteDiversidade {

    public static void main(String[] args) {
    	
    	MedidaCalculo ambiguidade = new AmbiguidadeCalculoDiversidade();
    	MedidaCalculo margem = new MargemCalculoDiversidade();
    	
    	MedidaCalculo qstatistics = new QstatisticsCalculoDiversidade();
    	MedidaCalculo correlation = new CorrelationCalculoDiversidade();
    	MedidaCalculo disagreement = new DisagreementCalculoDiversidade();
    	MedidaCalculo doublefault = new DoubleFaultCalculoDiversidade();

        int N_CLASSIFICADORES = 10;
        int N_INSTANCIAS = 10;
        
        Predicoes predicoes = new Predicoes(N_CLASSIFICADORES, null);
        
        double LAMBDA = 1;
        
        Random classifierRandom = new Random(1);
        
        RandomRBFGenerator stream = new RandomRBFGenerator();
        stream.prepareForUse();

        List<Classifier> ENSEMBLE = new ArrayList<>();
        for (int i = 0; i < N_CLASSIFICADORES; i++) {
            Classifier classificador = new HoeffdingTree();
            classificador.setModelContext(stream.getHeader());
            classificador.prepareForUse();
            ENSEMBLE.add(classificador);
            
        }

        // i => Instância
        for (int i = 0; i < N_INSTANCIAS; i++) {
            Instance instancia = stream.nextInstance().getData();
            int trueClass = (int) instancia.classValue();
            
            System.out.println("--------------------------------------------------");
            System.out.println("INSTÂNCIA: " + i);
            System.out.println("CLASS VALUE: " + trueClass);
            
            
            List<Integer> listaVotos = new ArrayList<>();
            
            //Teste
            // c => classificador
            for (int c = 0; c < ENSEMBLE.size(); c++) 
            {
                Classifier membroClassificador = ENSEMBLE.get(c);
                
                boolean correctly = membroClassificador.correctlyClassifies(instancia);
                double[] votos = membroClassificador.getVotesForInstance(instancia);
                
                int prediction_classificador = Utils.maxIndex(votos);
                
                listaVotos.add(prediction_classificador);
                
                System.out.println("");
                System.out.print("  MEMBRO VOTO: " + prediction_classificador);
                System.out.print(" ## CORRETO? " + correctly);
                
                //Calcula a diversidade passando as predições atuais
                
                
            }
            
            predicoes.addVotos(listaVotos, trueClass);
            
            double div_ambiguidade = ambiguidade.calcula(predicoes);
            double div_margem = margem.calcula(predicoes);
            
            double div_qstatistics = qstatistics.calcula(predicoes);
            double div_correlation = correlation.calcula(predicoes);
            double div_disagreement = disagreement.calcula(predicoes);
            double div_doublefault = doublefault.calcula(predicoes);
            
            System.out.println("");
            System.out.println(" Ambiguidade: " + div_ambiguidade);
            System.out.println(" Margem: " + div_margem);
            System.out.println(" Qstatistics: " + div_qstatistics);
            System.out.println(" Correlation: " + div_correlation);
            System.out.println(" Disagreement: " + div_disagreement);
            System.out.println(" DoubleFault: " + div_doublefault);
            System.out.println("--------------------------------------------------");
            
            //Treino OnLineBagging - ENSEMBLE
            for (Classifier membroClassificador : ENSEMBLE) {
                //Para cada classificador executar:
                int distP = MiscUtils.poisson(LAMBDA, classifierRandom);
                while (distP > 0) {
                    membroClassificador.trainOnInstance(instancia);
                    distP = distP - 1;
                }
            }
        }
        

    }
}
