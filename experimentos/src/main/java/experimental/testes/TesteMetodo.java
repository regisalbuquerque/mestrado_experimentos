package experimental.testes;

import java.util.List;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.metodo.util.dados.Dados;
import br.ufam.metodo.util.drift.DetectorDrift;
import experimental.analise.ResultadoClassificador;
import experimental.model.Base;
import experimental.model.BaseDrifts;
import experimental.model.Classificador;
import moa.classifiers.Classifier;
import moa.core.TimingUtils;

public class TesteMetodo {
	
	public static ResultadoClassificador testPrequencial(Base base, List<BaseDrifts> listaBaseDrifts, Classificador classificador) {
        
		ResultadoClassificador ResultadoTeste = new ResultadoClassificador();
        
        Indicadores indicadores = new Indicadores();

        Dados dados = new Dados(base.getPathFile(), -1);
        dados.prepareForUse();

        Classifier classificadorEmTeste = classificador.reset(dados);
        
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

            ResultadoTeste.registra(iteracao, null, indicadores, acertou);
            
            // ####  TREINA   ########################################
            classificadorEmTeste.trainOnInstance(instanciaAtual);

            
            if (classificadorEmTeste instanceof DetectorDrift)
            {
            	DetectorDrift detector = (DetectorDrift) classificadorEmTeste;
                if (detector.detectouDrift()) 
                {
                	ResultadoTeste.registraDrift(iteracao);
                	System.out.print("" + iteracao + ", ");
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
