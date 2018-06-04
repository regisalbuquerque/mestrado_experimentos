package experimental.analise;

import experimental.util.CSVUtil;
import java.util.List;

/**
 *
 * @author regis
 */
public class RelatDiversidade {

    public static void gravar(ResultadoClassificador resultadoClassificador, String path, String fileName) {
        //Gravar o CSV
        CSVUtil csv = new CSVUtil(path, fileName);

        csv.cabecalho("iteracao,ambiguidade,margem,qstatisics,correlation,disagreement,doublefault,acertou,acc");

        List<RegistroIteracao> lista = resultadoClassificador.getListaRegistrosIteracoes();
        
        for (int i = 0; i < lista.size(); i++) {
            RegistroIteracao registro = lista.get(i);

            csv.registro(registro.getIteracao() + ","
                + registro.getDiversidades().getAmbiguidade() + ","
                + registro.getDiversidades().getMargem() + ","
                + registro.getDiversidades().getQstatistics() + ","
                + registro.getDiversidades().getCorrelation() + ","
                + registro.getDiversidades().getDisagreement() + ","
                + registro.getDiversidades().getDoubleFault() + ","
                + registro.isAcertou() + ","
                + registro.getAcuraciaPrequencial());
            
        }

        csv.fechar();
    }
}
