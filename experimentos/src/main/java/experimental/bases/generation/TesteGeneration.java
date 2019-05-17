package experimental.bases.generation;

import moa.streams.ConceptDriftStream;
import moa.streams.generators.SEAGenerator;
import moa.tasks.WriteStreamToARFFFile;

public class TesteGeneration {

	public static void main(String[] args) {
		
		SEAGenerator seaGenerator3 = new SEAGenerator();
		seaGenerator3.functionOption.setValue(3);
		seaGenerator3.noisePercentageOption.setValue(10);
		
		SEAGenerator seaGenerator2 = new SEAGenerator();
		seaGenerator2.functionOption.setValue(2);
		seaGenerator2.noisePercentageOption.setValue(10);
		
		ConceptDriftStream conceptDriftStream = new ConceptDriftStream();
		conceptDriftStream.streamOption.setCurrentObject(seaGenerator3);
		conceptDriftStream.driftstreamOption.setCurrentObject(seaGenerator2);
		conceptDriftStream.positionOption.setValue(50000);
		conceptDriftStream.widthOption.setValue(20000);
		
		
		WriteStreamToARFFFile writeStreamToARFFFile = new WriteStreamToARFFFile();
		writeStreamToARFFFile.streamOption.setCurrentObject(conceptDriftStream);
		writeStreamToARFFFile.arffFileOption.setValue("/Users/regisalbuquerque/Desktop/example1_noise.arff");
		writeStreamToARFFFile.maxInstancesOption.setValue(100000);
		writeStreamToARFFFile.prepareForUse();
		writeStreamToARFFFile.doTask();

	}

}
