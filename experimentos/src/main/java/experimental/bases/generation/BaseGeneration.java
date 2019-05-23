package experimental.bases.generation;

import moa.streams.ConceptDriftStream;
import moa.streams.InstanceStream;
import moa.streams.generators.AgrawalGenerator;
import moa.streams.generators.SEAGenerator;
import moa.tasks.WriteStreamToARFFFile;

public class BaseGeneration {

	public static void main(String[] args) {
		
		int concepts = 10;
		int maxinstances = 10000;
		int noisepercentage_10 = 10;
		int without_noise = 0;
		int driftdistance_ABRUPTO = 1;
		int driftdistance_GRADUAL = 400;

		String path = "/Users/regisalbuquerque/Documents/git/regis/mestrado/mestrado_experimentos/experimentos/src/main/resources/dados/";
		
		generate_base(path, "agrawal_abrupt.arff", "AgrawalGenerator", concepts, maxinstances, driftdistance_ABRUPTO, without_noise);
		generate_base(path, "agrawal_abrupt_noise.arff", "AgrawalGenerator", concepts, maxinstances, driftdistance_ABRUPTO, noisepercentage_10);
		generate_base(path, "agrawal_gradual.arff", "AgrawalGenerator", concepts, maxinstances, driftdistance_GRADUAL, without_noise);
		generate_base(path, "agrawal_gradual_noise.arff", "AgrawalGenerator", concepts, maxinstances, driftdistance_GRADUAL, noisepercentage_10);
		
		generate_base(path, "sea_abrupt.arff", "SEAGenerator", concepts, maxinstances, driftdistance_ABRUPTO, without_noise);
		generate_base(path, "sea_abrupt_noise.arff", "SEAGenerator", concepts, maxinstances, driftdistance_ABRUPTO, noisepercentage_10);
		generate_base(path, "sea_gradual.arff", "SEAGenerator", concepts, maxinstances, driftdistance_GRADUAL, without_noise);
		generate_base(path, "sea_gradual_noise.arff", "SEAGenerator", concepts, maxinstances, driftdistance_GRADUAL, noisepercentage_10);
		

	}
	static void generate_base(String path, String arffile, String gerador, int concepts, int maxinstances, int driftdistance, int noisepercentage)
	{
		int position =  maxinstances / concepts;
		
		
		WriteStreamToARFFFile writeStreamToARFFFile = new WriteStreamToARFFFile();
		writeStreamToARFFFile.streamOption.setCurrentObject(concept_drift(gerador, concepts, position, driftdistance, noisepercentage));
		writeStreamToARFFFile.arffFileOption.setValue(path + arffile);
		writeStreamToARFFFile.maxInstancesOption.setValue(maxinstances);
		writeStreamToARFFFile.prepareForUse();
		writeStreamToARFFFile.doTask();
		
	}
	
	static InstanceStream concept_drift(String gerador, int num, int position, int driftdistance, int noisepercentage)
	{
		if (num == 1)
		{
			InstanceStream stream = fabricaBaseGenerator(gerador, num, noisepercentage);
			
			return stream;
		}
		else
		{
			InstanceStream stream_aux = fabricaBaseGenerator(gerador, num, noisepercentage);
			
			ConceptDriftStream conceptDriftStream = new ConceptDriftStream();
			conceptDriftStream.streamOption.setCurrentObject(stream_aux);
			conceptDriftStream.driftstreamOption.setCurrentObject(concept_drift(gerador, --num, position, driftdistance, noisepercentage));
			conceptDriftStream.positionOption.setValue(position);
			conceptDriftStream.widthOption.setValue(driftdistance);
			
			return conceptDriftStream;
		}
	}
	
	static InstanceStream fabricaBaseGenerator(String gerador, int num, int noisepercentage)
	{
		switch (gerador) {
		case "SEAGenerator":
			SEAGenerator seaGenerator_aux = new SEAGenerator();
			seaGenerator_aux.functionOption.setValue(num % 4 + 1);
			System.out.println("NUM = " + num + " MOD = " + (num%4 + 1));
			seaGenerator_aux.noisePercentageOption.setValue(noisepercentage);
			return seaGenerator_aux;
			
		case "AgrawalGenerator":
			AgrawalGenerator agravAgrawalGenerator = new AgrawalGenerator();
			agravAgrawalGenerator.functionOption.setValue(num % 10 + 1);
			System.out.println("NUM = " + num + " MOD = " + (num%10 + 1));
			double fracao = (double)noisepercentage / 100.00;
			agravAgrawalGenerator.peturbFractionOption.setValue(fracao);
			return agravAgrawalGenerator;
		default:
			return null;
		}
	}

}
