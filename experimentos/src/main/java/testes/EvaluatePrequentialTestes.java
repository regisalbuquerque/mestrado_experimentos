package testes;

import moa.evaluation.LearningCurve;
import moa.streams.ArffFileStream;
import moa.tasks.EvaluatePrequential;
import moa.tasks.StandardTaskMonitor;
import moa.tasks.TaskMonitor;

public class EvaluatePrequentialTestes {
    public static void main(String[] args) {
        
        System.out.println("Executing ");
        TaskMonitor monitor = new StandardTaskMonitor();
        
        ArffFileStream trainStream = new ArffFileStream();
			trainStream.arffFileOption.setValue("/home/regis/Documents/git/regis/mestrado/implementacoes/experimentos/src/main/resources/dados/gauss.arff");
			trainStream.prepareForUse();
        
        
        EvaluatePrequential evaluatePrequential = new EvaluatePrequential();
        
        evaluatePrequential.streamOption.setCurrentObject(trainStream);
        
        evaluatePrequential.prepareForUse();
        
        LearningCurve learningCurve = (LearningCurve) evaluatePrequential.doTask(monitor, null);
        
        for (int i = 0; i < 10; i++) {
            System.out.println(learningCurve.getMeasurementName(i) + " ==> " + learningCurve.getMeasurement(0, i));
        }
        
    }
}
