package experimental.testes;

import java.util.List;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.metodo.diversidade.util.DiversidadeUtil;
import br.ufam.metodo.diversidade.util.Diversidades;
import br.ufam.metodo.util.calculo.Predicoes;
import br.ufam.metodo.util.dados.Dados;
import br.ufam.metodo.util.medidor.Indicadores;
import br.ufam.metodo.util.medidor.Resultado;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.Classificador;
import moa.classifiers.Classifier;
import moa.core.TimingUtils;

public class TesteEnsemble {

	public static Resultado testPrequencial(Base base, List<BaseDrifts> listaBaseDrifts, Classificador classificador) {
        Resultado teste = new Resultado();
        
        
        
        Indicadores indicadores = new Indicadores();

        Dados dados = new Dados(base.getPathFile(), -1);
        dados.prepareForUse();

        DriftDetector driftDetector = new DriftDetector(listaBaseDrifts);

        Classifier classificadorEmTeste = classificador.reset(dados);
        
        Predicoes predicoes = new Predicoes(classificadorEmTeste.getSubClassifiers().length, null);
        
        //PARA O CALCULO DO TEMPO DE EXECUCAO ------------------------------------
        long evaluateStartTime = TimingUtils.getNanoCPUTimeOfCurrentThread();
        long lastEvaluateStartTime = evaluateStartTime;
        double RAMHours = 0.0;
        // -----------------------------------------------------------------------  
        for (int iteracao = 1; dados.hasMoreInstances(); iteracao++) {
            
            if (base.getNumInstances() < iteracao) break;
            
            Instance instanciaAtual = dados.getProximaInstancia();
            predicoes.incluiInstanciaOnline(classificadorEmTeste.getSubClassifiers(), instanciaAtual); 
            //System.out.println(" -------- ITERACAO: " + iteracao + " ----------------------------------------------------------");


            if (driftDetector.isDriftOcorreu(iteracao)) {
                teste.registraDrift(iteracao); //Logando o drift - para relatorios de bases Reais
                
                if (classificador.resetWhenDrift()) {
                    classificadorEmTeste = classificador.reset(dados);
                    
                    predicoes = new Predicoes(classificadorEmTeste.getSubClassifiers().length, null);
                    predicoes.incluiInstanciaOnline(classificadorEmTeste.getSubClassifiers(), instanciaAtual); 
                    
                    //Reset Indicadores
                    indicadores.resetPrequencial();

                }
            }

            // ####  TESTA   ########################################
            //int trueClass = (int) instanciaAtual.classValue();

            boolean acertou;
            if (classificadorEmTeste.correctlyClassifies(instanciaAtual)) {
                acertou = true;
                indicadores.acertou();
            } else {

                acertou = false;
                indicadores.errou();
            }

            driftDetector.input(acertou ? 0.0 : 1.0);
            
            //OTIMIZADO PARA CALCULO DO MÉTODO
            //Calcular a diversidade usando os subclassificadores
            Diversidades diversidades = DiversidadeUtil.calculaDiversidades(predicoes);
            
            teste.registra(iteracao, "", diversidades, indicadores, acertou, null);
            
            // ####  TREINA   ########################################
            classificadorEmTeste.trainOnInstance(instanciaAtual);


        }
        //CALCULO DO TEMPO E DA QUANTIDADE DE RAM_Hours ---------------------------------------------
        long evaluateTime = TimingUtils.getNanoCPUTimeOfCurrentThread();
        double time = TimingUtils.nanoTimeToSeconds(evaluateTime - evaluateStartTime);
        double timeIncrement = TimingUtils.nanoTimeToSeconds(evaluateTime - lastEvaluateStartTime);
        double RAMHoursIncrement = 0;//classificadorEmTeste.measureByteSize() / (1024.0 * 1024.0 ); //MBs
        RAMHoursIncrement *= (timeIncrement / 3600.0); //Hours
        RAMHours += RAMHoursIncrement;
        lastEvaluateStartTime = evaluateTime;
        //-------------------------------------------------------------------------------------------
        
        teste.registraTempo(time);
        teste.registraRamHours(RAMHours);
        
        return teste;
    }
	
}
