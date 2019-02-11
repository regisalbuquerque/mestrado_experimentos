package experimental.analise;

import br.ufam.metodo.util.medidor.RegistroIteracao;
import br.ufam.metodo.util.medidor.Resultado;
import regisalbuquerque.utilslib.CSVUtil;

/**
 *
 * @author regis
 */
public class RelatResumoDrift {

    public static void gravar(Resultado resultadoClassificador, String path, String fileNameWithoutEXT) {

    	Resultado rt = resultadoClassificador;
        
    	CSVUtil csvITER = new CSVUtil(path, fileNameWithoutEXT + ".csv");
        csvITER.cabecalho("iteracao,taxa,acc,drift");
        
        for (RegistroIteracao registro : rt.getListaRegistrosIteracoes()) {

            	String drift = "0";
            	
            	if (rt.getLogDrifts().contains(registro.getIteracao()) )
            			drift = "1";
            	
            	csvITER.registro(registro.getIteracao() + ","
            			+ registro.getTaxaAcerto() + "," 
            			+ registro.getAcuraciaPrequencial() + ","
            			+ drift);
        }
        csvITER.fechar();
    }
}
