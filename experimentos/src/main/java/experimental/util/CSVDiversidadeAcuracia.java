package experimental.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author regis
 */
public class CSVDiversidadeAcuracia {
    
    private final String pathFileName;
    private BufferedWriter buffWriter;

    public CSVDiversidadeAcuracia(String pathFileName) {
        this.pathFileName = pathFileName;
    }

    public void init() throws IOException
    {
        buffWriter = new BufferedWriter(new FileWriter(this.pathFileName));
        buffWriter.append("iteracao,diversidade,acuracia\n");
    }
    
    public void registra(int iteracao, double diversidade, double acuracia) throws IOException
    {
        buffWriter.append(iteracao + "," + diversidade + "," + acuracia+"\n");
    }
    
    
    public void destroy() throws IOException
    {
        buffWriter.close();
    }
    
    
//    
//        public static void criarCVS(EntityManager em, String path, Execucao execucao, Base base) throws IOException
//    {
//        List<Resumo> lista = Resumo.listaPorExecucao(em, execucao, base);
//        
//        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + base.getNome() + ".csv"));
//        buffWrite.append("classe,lambda,acuracia_media,diversidade_media\n");
//        
//        for (Resumo rr : lista) {
//            buffWrite.append(rr.getEnsembleId().getClasse() + "," + rr.getEnsembleId().getLambda() + "," + rr.getTaxaAcertosMedia() + "," +  rr.getDiversidadeMedia() + "\n");
//        }
//        
//        buffWrite.close();
//    }
//    
//    public static void criarCVS2(EntityManager em, String path, Execucao execucao, Base base) throws IOException
//    {
//        List<Resumo> lista = Resumo.listaPorExecucao(em, execucao, base);
//        
//        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path + base.getNome() + ".csv"));
//        buffWrite.append("Metodo,acuracia_media,diversidade_media\n");
//        
//        for (Resumo rr : lista) {
//            buffWrite.append(rr.getMetodoId().getAlgoritmo().name() + "," + rr.getTaxaAcertosMedia() + "," +  rr.getDiversidadeMedia() + "\n");
//        }
//        
//        buffWrite.close();
//    }
//    
//    
//    
//    
//    
//    
//    
//    
    
    
}
