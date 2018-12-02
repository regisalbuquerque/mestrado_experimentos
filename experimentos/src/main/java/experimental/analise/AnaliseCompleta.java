package experimental.analise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import br.ufam.metodo.util.medidor.RegistroIteracao;
import br.ufam.metodo.util.medidor.Resultado;
import br.ufam.metodo.util.pareto.ParetoFront;
import br.ufam.metodo.util.pareto.Solucao;
import br.ufam.util.CSVUtil;

/**
 *
 * @author regis
 */
public class AnaliseCompleta {
    List<Resultado> listaResultadosClassificador = new ArrayList<>();
    
    int num_iteracoes;
    String path;
    String base;
    String fileName_without_ext;
    
    public AnaliseCompleta(List<Resultado> listaResultados, String path, String base, String fileName_without_ext){
        this.listaResultadosClassificador = listaResultados;
        this.num_iteracoes = listaResultados.get(0).getListaRegistrosIteracoes().size();
        this.path = path;
        this.base = base;
        this.fileName_without_ext = fileName_without_ext;
    }
    
    public void analisa(boolean maximizarDiversidade)
    {
        ResultadoAnalise[] resultados = new ResultadoAnalise[4];
        resultados[0] = new ResultadoAnalise("MenorDiversidade");
        resultados[1] = new ResultadoAnalise("MaiorDiversidade");
        resultados[2] = new ResultadoAnalise("ParetoMenorDiversidade");
        resultados[3] = new ResultadoAnalise("ParetoMaiorDiversidade");
        
        
        int index_menor = 0;
        int index_maior = 1;
        int index_pareto_menor_diversidade = 2;
        int index_pareto_maior_diversidade = 3;
        
        int indexMenorDiv, indexMaiorDiv, indexParetoMenor, indexParetoMaior;
        String codMenorDiv, codMaiorDiv, codParetoMenor, codParetoMaior;
        
        Resultado resultadoClassificadorAux;
        RegistroIteracao registroIteracaoAux;
        
        
        for (int iteracao = 1; iteracao <= this.num_iteracoes; iteracao++) {
            
            Solucao[] solucoes = new Solucao[this.listaResultadosClassificador.size()];
            for (int c = 0; c < this.listaResultadosClassificador.size(); c++) {
                
                Resultado resultadoClassificador = this.listaResultadosClassificador.get(c);
                RegistroIteracao registro = resultadoClassificador.getRegistro(iteracao); 
                
                solucoes[c] = new Solucao(c, registro.getDiversidades().getAmbiguidade(), registro.getAcuraciaPrequencial());
                
            }
            ParetoFront paretoFront = new ParetoFront(solucoes, maximizarDiversidade, true);
            
            List<Solucao> listaSolucoes = Arrays.asList(solucoes);
            List<Solucao> listaSolucoesPareto = paretoFront.getParetoSoluctions();
            
          
            //Show Time
            //Menor Diversidade
            Collections.sort(listaSolucoes, Solucao.comparatorValor1_Crescente());
            indexMenorDiv = listaSolucoes.get(0).getIndex();
            resultadoClassificadorAux = this.listaResultadosClassificador.get(indexMenorDiv);
            registroIteracaoAux = resultadoClassificadorAux.getRegistro(iteracao);
            resultados[index_menor].isAcertou(registroIteracaoAux.isAcertou());
            codMenorDiv = resultadoClassificadorAux.getCodigo();
            
            
            //Maior Diversidade
            Collections.sort(listaSolucoes, Solucao.comparatorValor1_Decrescente());
            indexMaiorDiv = listaSolucoes.get(0).getIndex();
            resultadoClassificadorAux = this.listaResultadosClassificador.get(indexMaiorDiv);
            registroIteracaoAux = resultadoClassificadorAux.getRegistro(iteracao);
            resultados[index_maior].isAcertou(registroIteracaoAux.isAcertou());
            codMaiorDiv = resultadoClassificadorAux.getCodigo();
            
            
            //PARETO
            //Pareto Menor Diversidade
            Collections.sort(listaSolucoesPareto, Solucao.comparatorValor1_Crescente());
            indexParetoMenor = listaSolucoesPareto.get(0).getIndex();
            resultadoClassificadorAux = this.listaResultadosClassificador.get(indexParetoMenor);
            registroIteracaoAux = resultadoClassificadorAux.getRegistro(iteracao);
            resultados[index_pareto_menor_diversidade].isAcertou(registroIteracaoAux.isAcertou());
            codParetoMenor = resultadoClassificadorAux.getCodigo();
            
            
            //Pareto Maior Diversidade
            Collections.sort(listaSolucoesPareto, Solucao.comparatorValor1_Decrescente());
            indexParetoMaior = listaSolucoesPareto.get(0).getIndex();
            resultadoClassificadorAux = this.listaResultadosClassificador.get(indexParetoMaior);
            registroIteracaoAux = resultadoClassificadorAux.getRegistro(iteracao);
            resultados[index_pareto_maior_diversidade].isAcertou(registroIteracaoAux.isAcertou());
            codParetoMaior = resultadoClassificadorAux.getCodigo();
            
            //Gravar ITERACAO
            gravarIteracao(iteracao, solucoes, listaSolucoesPareto, codMenorDiv, codMaiorDiv, codParetoMenor, codParetoMaior);
            
        }

        gravarResumo(resultados); //RESUMO
    }
    
    
    private void gravarIteracao(int iteracao, Solucao[] solucoes, List<Solucao> listaPareto, String codMenorDiv, String codMaiorDiv, String codParetoMenor, String codParetoMaior) {
        //Gravar o CSV
        CSVUtil csv = new CSVUtil(path + base + "/", fileName_without_ext + "_it_" + iteracao + ".csv");

        csv.cabecalho("cod,iteracao,diversidade,acc,acertou,menor_div,maior_div,pareto_member,pareto_menor,pareto_maior");
        
        
        for (int c = 0; c < solucoes.length; c++) {
            
            Resultado resultadoClassificador = this.listaResultadosClassificador.get(c);
            RegistroIteracao registro = resultadoClassificador.getRegistro(iteracao); 
            
            boolean pareto_member = false;
            
            for(int i = 0; i < listaPareto.size(); i++)
            {
            	if (listaPareto.get(i).getIndex() == c)
            	{
            		pareto_member = true;
            		break;
            	}
            }
            
            
            boolean menor_div = resultadoClassificador.getCodigo().equals(codMenorDiv);
            boolean maior_div = resultadoClassificador.getCodigo().equals(codMaiorDiv);
            boolean pareto_menor = resultadoClassificador.getCodigo().equals(codParetoMenor);
            boolean pareto_maior = resultadoClassificador.getCodigo().equals(codParetoMaior);

            csv.registro(registro.getCodigo() + ","
                    	+ iteracao + ","
                    	+ registro.getDiversidades().getAmbiguidade() + ","
            			+ registro.getAcuraciaPrequencial() + ","
            			+ registro.isAcertou() + ","
            			+ menor_div + ","
            			+ maior_div + ","
            			+ pareto_member + ","
            			+ pareto_menor + ","
            			+ pareto_maior);
        }
        
        csv.fechar();
    }
    
    
    private void gravarResumo(ResultadoAnalise[] resultadosDaAnalise) {
        //Gravar o CSV
        CSVUtil csv = new CSVUtil(path, fileName_without_ext);

        csv.cabecalho("cod,acertos,iteracoes,taxa");
        
        for (ResultadoAnalise resultadoAnalise : resultadosDaAnalise) {
            csv.registro(
            		  resultadoAnalise.getCodigo() + ","
                    + resultadoAnalise.getNumAcertos() + ","
                    + resultadoAnalise.getNumIteracoes() + ","
                    + resultadoAnalise.getTaxa());
        }
        
        csv.fechar();
    }
}
