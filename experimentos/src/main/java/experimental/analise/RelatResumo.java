package experimental.analise;

import experimental.util.CSVUtil;
import java.util.List;

/**
 *
 * @author regis
 */
public class RelatResumo {

    public static void gravar(List<ResultadoClassificador> listaResultados, String path, String fileNameWithoutEXT, boolean gravaIteracao) {
        //Gravar o CSV
        CSVUtil csv = new CSVUtil(path, fileNameWithoutEXT + "_resumo.csv");

        csv.cabecalho("cod,taxa_media,acc_media,tempo,RamHours");

        CSVUtil csvITER = null;
        
        for (ResultadoClassificador rt : listaResultados) //Para cada TESTE
        {
            double soma_ambiguidade = 0;
            double soma_margem = 0;
            double soma_qstatistics = 0;
            double soma_correlaion = 0;
            double soma_disagreement = 0;
            double soma_doublefault = 0;
            double soma_taxa = 0;
            double soma_acc = 0;
            
            if(gravaIteracao)
            {
            	csvITER = new CSVUtil(path, fileNameWithoutEXT + rt.getCodigo() + "_iteracao.csv");

            	csvITER.cabecalho("iteracao,taxa,acc,drift");
            }

            int i = 0;
            for (RegistroIteracao registro : rt.getListaRegistrosIteracoes()) {
                i++;
                soma_ambiguidade += (registro.getDiversidades() != null) ? registro.getDiversidades().getAmbiguidade(): 0;
                soma_margem += (registro.getDiversidades() != null) ? registro.getDiversidades().getMargem(): 0;
                soma_qstatistics += (registro.getDiversidades() != null) ? registro.getDiversidades().getQstatistics(): 0;
                soma_correlaion += (registro.getDiversidades() != null) ? registro.getDiversidades().getCorrelation(): 0;
                soma_disagreement += (registro.getDiversidades() != null) ? registro.getDiversidades().getDisagreement(): 0;
                soma_doublefault +=  (registro.getDiversidades() != null) ? registro.getDiversidades().getDoubleFault(): 0;
                soma_taxa += registro.getTaxaAcerto();
                soma_acc += registro.getAcuraciaPrequencial();
                
                if(gravaIteracao)
                {
                	String drift = "";
                	
                	if (rt.getLogDrifts().contains(registro.getIteracao()) )
                			drift = "1";
                	
                	csvITER.registro(registro.getIteracao() + ","
                			+ registro.getTaxaAcerto() + "," 
                			+ registro.getAcuraciaPrequencial() + ","
                			+ drift);
                }
            }
            
            if(gravaIteracao)
            {
            	csvITER.fechar();
            }

            double tam = i;
            
            double media_ambiguidade = soma_ambiguidade / tam;
            double media_margem = soma_margem / tam;
            double media_qstatistics = soma_qstatistics / tam;
            double media_correlation = soma_correlaion / tam;
            double media_disagreement = soma_disagreement / tam;
            double media_doublefault = soma_doublefault / tam;
            double media_taxa = soma_taxa / tam;
            double media_acc = soma_acc / tam;

            csv.registro(rt.getCodigo() + ","
                    + media_taxa + ","
                    + media_acc + ","
                    + rt.getTempo() + ","
                    + rt.getRamHours());
        }
        csv.fechar();
    }
}
