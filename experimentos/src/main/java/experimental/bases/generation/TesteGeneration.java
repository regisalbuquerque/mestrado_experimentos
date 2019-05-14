package experimental.bases.generation;

import moa.streams.ConceptDriftStream;
import moa.streams.generators.SEAGenerator;
import moa.tasks.WriteStreamToARFFFile;

public class TesteGeneration {

	public static void main(String[] args) {
		
		SEAGenerator seaGenerator3 = new SEAGenerator();
		seaGenerator3.functionOption.setValue(3);
		
		SEAGenerator seaGenerator2 = new SEAGenerator();
		seaGenerator2.functionOption.setValue(2);
		
		ConceptDriftStream conceptDriftStream = new ConceptDriftStream();
		conceptDriftStream.streamOption.setCurrentObject(seaGenerator3);
		conceptDriftStream.driftstreamOption.setCurrentObject(seaGenerator2);
		conceptDriftStream.positionOption.setValue(50000);
		conceptDriftStream.widthOption.setValue(20000);
		
		
		WriteStreamToARFFFile writeStreamToARFFFile = new WriteStreamToARFFFile();
		writeStreamToARFFFile.streamOption.setCurrentObject(conceptDriftStream);
		writeStreamToARFFFile.arffFileOption.setValue("example1.arff");
		writeStreamToARFFFile.maxInstancesOption.setValue(100000);
		writeStreamToARFFFile.prepareForUse();
		writeStreamToARFFFile.doTask();

	}

}
