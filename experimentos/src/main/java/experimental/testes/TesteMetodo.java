package experimental.testes;

import java.util.List;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.diversidade.impl.AmbiguidadeCalculoDiversidade;
import br.ufam.metodo.diversidade.util.Diversidades;
import br.ufam.metodo.util.calculo.DiversidadePrequencial;
import br.ufam.metodo.util.dados.Dados;
import br.ufam.metodo.util.drift.DetectorDrift;
import br.ufam.metodo.util.medidor.Indicadores;
import br.ufam.metodo.util.medidor.Resultado;
import br.ufam.metodo.util.model.IEnsembleSelection;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.Classificador;
import moa.classifiers.Classifier;
import moa.core.TimingUtils;

public class TesteMetodo {
	
	public static Resultado testPrequencial(Base base, List<BaseDrifts> listaBaseDrifts, Classificador classificador) {
        
		Resultado ResultadoTeste = new Resultado();
        
        Indicadores indicadores = new Indicadores();
        

        Dados dados = new Dados(base.getPathFile(), -1);
        dados.prepareForUse();

        Classifier classificadorEmTeste = classificador.reset(dados);
        
        
        DiversidadePrequencial diversidadePrequencial = null;
        if (classificadorEmTeste.getSubClassifiers() != null)
        	diversidadePrequencial = new DiversidadePrequencial(classificadorEmTeste.getSubClassifiers().length, null, new AmbiguidadeCalculoDiversidade());

        
        //PARA O CALCULO DO TEMPO DE EXECUCAO ------------------------------------
        long evaluateStartTime = TimingUtils.getNanoCPUTimeOfCurrentThread();
        long lastEvaluateStartTime = evaluateStartTime;
        double RAMHours = 0.0;
        // -----------------------------------------------------------------------        
        
        for (int iteracao = 1; dados.hasMoreInstances(); iteracao++) {
            
            if (base.getNumInstances() < iteracao) break;
            
            Instance instanciaAtual = dados.getProximaInstancia();
            
            

            boolean acertou;
            if (classificadorEmTeste.correctlyClassifies(instanciaAtual)) {
                acertou = true;
                indicadores.acertou();
            } else {

                acertou = false;
                indicadores.errou();
            }
            
            if (classificadorEmTeste.getSubClassifiers() != null)
            	diversidadePrequencial.calcula(classificadorEmTeste.getSubClassifiers(), instanciaAtual);
            
            Diversidades diversidades = new Diversidades();
            if (diversidadePrequencial != null)
            	diversidades.setAmbiguidade(diversidadePrequencial.getDiv());
            else
            	diversidades.setAmbiguidade(0);

            if (classificadorEmTeste instanceof IEnsembleSelection)
            {
            	 ResultadoTeste.registra(iteracao, "", diversidades, indicadores, acertou, ((IEnsembleSelection)classificadorEmTeste).getUltimoEnsembleSelecionadoLambda());
            }
            else
            {
            	 ResultadoTeste.registra(iteracao, "", diversidades, indicadores, acertou, null);
            }
            
           
            
            // ####  TREINA   ########################################
            classificadorEmTeste.trainOnInstance(instanciaAtual);

            
            if (classificadorEmTeste instanceof DetectorDrift)
            {
            	DetectorDrift detector = (DetectorDrift) classificadorEmTeste;
                if (detector.detectouDrift()) 
                {
                	ResultadoTeste.registraDrift(iteracao);
                	//System.out.print("" + iteracao + ", ");
                }
            }
            

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
        
        ResultadoTeste.registraTempo(time);
        ResultadoTeste.registraRamHours(RAMHours);
        
        return ResultadoTeste;
    }

}
