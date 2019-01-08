package experimental.analise;

import java.util.List;

import br.ufam.metodo.util.medidor.RegistroIteracao;
import br.ufam.metodo.util.medidor.Resultado;
import regisalbuquerque.utilslib.CSVUtil;

/**
 *
 * @author regis
 */
public class RelatResumo30 {

    public static void gravar(List<Resultado> listaResultados, String path, String fileNameWithoutEXT) {
        //Gravar o CSV
        CSVUtil csv = new CSVUtil(path, fileNameWithoutEXT + "_resumo.csv");

        csv.cabecalho("cod, taxa30_media, acc30_media");

        for (Resultado rt : listaResultados) //Para cada MÉTODO
        {
            
            double tam = 30;
            Double particao = rt.getListaRegistrosIteracoes().size() /(double)tam;
            int particao_int = particao.intValue();
        	
            double soma_taxa = 0;
            double soma_acc = 0;
            
            for (int i = 1; i<=tam; i++) {
            	int index = particao_int*i;
            	
            	RegistroIteracao registro = rt.getListaRegistrosIteracoes().get(index);

                soma_taxa += registro.getTaxaAcerto();
                soma_acc += registro.getAcuraciaPrequencial();
 
            }
            
            double media_taxa = soma_taxa / tam;
            double media_acc = soma_acc / tam;

            csv.registro(rt.getCodigo() + ","
                    + media_taxa + ","
                    + media_acc + ",");
        }
        csv.fechar();
    }
}
