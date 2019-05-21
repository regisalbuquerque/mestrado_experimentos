package experimental.bases.generation;

import moa.streams.ConceptDriftStream;
import moa.streams.ExampleStream;
import moa.streams.InstanceStream;
import moa.streams.generators.SEAGenerator;
import moa.tasks.WriteStreamToARFFFile;

public class TesteGeneration {

	public static void main(String[] args) {
		
		/*SEAGenerator seaGenerator3 = new SEAGenerator();
		seaGenerator3.functionOption.setValue(3);
		seaGenerator3.noisePercentageOption.setValue(10);
		
		SEAGenerator seaGenerator2 = new SEAGenerator();
		seaGenerator2.functionOption.setValue(2);
		seaGenerator2.noisePercentageOption.setValue(10);
		
		ConceptDriftStream conceptDriftStream = new ConceptDriftStream();
		conceptDriftStream.streamOption.setCurrentObject(seaGenerator3);
		conceptDriftStream.driftstreamOption.setCurrentObject(seaGenerator2);
		conceptDriftStream.positionOption.setValue(50000);
		conceptDriftStream.widthOption.setValue(20000);*/
		
		
		WriteStreamToARFFFile writeStreamToARFFFile = new WriteStreamToARFFFile();
		writeStreamToARFFFile.streamOption.setCurrentObject(concept_drift(10));
		writeStreamToARFFFile.arffFileOption.setValue("/Users/regisalbuquerque/Desktop/mixed_drifts_noise.arff");
		writeStreamToARFFFile.maxInstancesOption.setValue(100000);
		writeStreamToARFFFile.prepareForUse();
		writeStreamToARFFFile.doTask();

	}
	
	static InstanceStream concept_drift(int num)
	{
		System.out.println("NUM = " + num + " MOD = " + (num%4 + 1));
		
		
		if (num == 1)
		{
			SEAGenerator seaGenerator_aux = new SEAGenerator();
			seaGenerator_aux.functionOption.setValue(num % 4 + 1);
			seaGenerator_aux.noisePercentageOption.setValue(10);
			
			return seaGenerator_aux;
		}
		else
		{
			SEAGenerator seaGenerator_aux = new SEAGenerator();
			seaGenerator_aux.functionOption.setValue(num % 4 + 1);
			seaGenerator_aux.noisePercentageOption.setValue(10);
			
			ConceptDriftStream conceptDriftStream = new ConceptDriftStream();
			conceptDriftStream.streamOption.setCurrentObject(seaGenerator_aux);
			conceptDriftStream.driftstreamOption.setCurrentObject(concept_drift(--num));
			conceptDriftStream.positionOption.setValue(50000);
			conceptDriftStream.widthOption.setValue(20000);
			
			return conceptDriftStream;
		}
	}

}
