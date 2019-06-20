
package experimental.analise;

import br.ufam.metodo.util.medidor.RegistroIteracao;
import br.ufam.metodo.util.medidor.Resultado;
import regisalbuquerque.utilslib.CSVUtil;

public class RelatResumoAcc {

	public static void gravar(Resultado resultado, int execucao, String pathFileName) {

		CSVUtil csv;

		if (execucao == 1)
		{
			csv = new CSVUtil(pathFileName);
			csv.cabecalho("cod,execucao,taxa_media,acc_media,tempo,RamHours");
		}
		else
		{
			csv = new CSVUtil(pathFileName, true); //APPEND
		}
			
		double soma_taxa = 0;
		double soma_acc = 0;

		int i = 0;
		for (RegistroIteracao registro : resultado.getListaRegistrosIteracoes()) 
		{
			i++;
			soma_taxa += registro.getTaxaAcerto();
			soma_acc += registro.getAcuraciaPrequencial();
		}
		double tam = i;
		double media_taxa = soma_taxa / tam;
		double media_acc = soma_acc / tam;

		csv.registro(resultado.getCodigo() + "," + execucao + "," + media_taxa + "," + media_acc + "," + resultado.getTempo() + ","
				+ resultado.getRamHours());

		csv.fechar();
	}
	
	public static void gravarPorResumo(Resultado resultado, int execucao, String pathFileName) {

		CSVUtil csv;

		if (execucao == 1)
		{
			csv = new CSVUtil(pathFileName);
			csv.cabecalho("cod,execucao,taxa_media,acc_media,tempo,RamHours");
		}
		else
		{
			csv = new CSVUtil(pathFileName, true); //APPEND
		}
			
		double media_taxa = resultado.getAcuraciaMedia();
		double media_acc = resultado.getAcuraciaPrequencialMedia();

		csv.registro(resultado.getCodigo() + "," + execucao + "," + media_taxa + "," + media_acc + "," + resultado.getTempo() + ","
				+ resultado.getRamHours());

		csv.fechar();
	}
	
}
