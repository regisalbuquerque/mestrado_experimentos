package experimental.analise;

import com.yahoo.labs.samoa.instances.Instance;

import br.ufam.metodo.util.model.IEnsembleSelection;
import moa.classifiers.Classifier;
import moa.core.Utils;
import regisalbuquerque.utilslib.CSVUtil;

public class RelatIteracao {

	
	String path;
	CSVUtil[] CSVUtil;
	CSVUtil CSVEscolhas;
	String nomeMetodo;
	
	
	public RelatIteracao(String path, String nomeMetodo)
	{
		this.path = path;
		CSVUtil = null;
		CSVEscolhas = null;
		this.nomeMetodo = nomeMetodo;
	}
	
	public void registraIteracao(Classifier classificadorEmTeste, Instance instance)
	{
		if (!(classificadorEmTeste instanceof IEnsembleSelection))
			return;
		
		IEnsembleSelection metodo =  (IEnsembleSelection)classificadorEmTeste;
		
		if (CSVUtil == null)
		{
			CSVUtil = new CSVUtil[metodo.getClassifiers().length];
			
			for (int i = 0; i < CSVUtil.length; i++) {
				CSVUtil[i] = new CSVUtil(path, this.nomeMetodo + "_" + String.valueOf(i) + ".csv");
			}
			
			CSVEscolhas = new CSVUtil(path, this.nomeMetodo + "_escolhas.csv");
			
		}
		
		//Para cada ensemble - Gravar VOTACOES
		Classifier[][] ensembles = metodo.getClassifiers();
		for (int i = 0; i < ensembles.length; i++ )
		{
			String linha = "";
			for (int j = 0; j < ensembles[i].length; j++) {
				
				Classifier classificadorMembro = ensembles[i][j];
				
				int votoClassificador = Utils.maxIndex(classificadorMembro.getVotesForInstance(instance));
				linha +=String.valueOf(votoClassificador);
				
				if (j != (ensembles[i].length - 1))
					linha += ",";
				
			}
			CSVUtil[i].registro(linha);
		}
		
		CSVEscolhas.registro(String.valueOf(metodo.getUltimoEnsembleSelecionadoIndex()));
		
	}
	
	public void close()
	{
		if (CSVUtil != null)
		{
			for (int i = 0; i < CSVUtil.length; i++) {
				CSVUtil[i].fechar();
			}
			
			CSVEscolhas.fechar();
		}
	}
	
}
