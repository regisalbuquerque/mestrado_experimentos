package experimental.analise;

import java.util.ArrayList;
import java.util.List;

import br.ufam.metodo.diversidade.util.Diversidades;
import experimental.testes.Indicadores;

public class ResultadoClassificador{ 
    //private String nome;
    private String codigo;
    private List<RegistroIteracao> listaRegistrosIteracoes;
    private List<Integer> logDrifts;
    private double tempo;
    private double ramHours;

    public ResultadoClassificador() {
        listaRegistrosIteracoes = new ArrayList<>();
        logDrifts = new ArrayList<>();
    }
    
    public void registraTempo(double tempo)
    {
        this.tempo = tempo;
    }
    
    public void registraRamHours(double ramHours)
    {
        this.ramHours = ramHours;
    }
    
    public void registraDrift(int iteracao)
    {
        logDrifts.add(iteracao);
    }
    
    public void registra(int iteracao, Diversidades diversidades, Indicadores indicadores, boolean acertou)
    {
        listaRegistrosIteracoes.add(new RegistroIteracao(iteracao, diversidades, indicadores.getTaxaAcertoAtual(), indicadores.getTaxaErroAtual(), acertou, indicadores.getAcuraciaPrequencial(), indicadores.getDesvioAcuraciaPrequencial(), indicadores.getErroPrequencial(), indicadores.getDesvioErroPrequencial()));
    }
    
    public void registra(int iteracao, Diversidades diversidades, double taxaAcerto, double taxaErro, boolean acertou, double acuraciaPrequencial, double desvioAcuraciaPrequencial, double erroPrequencial, double desvioErroPrequencial)
    {
        listaRegistrosIteracoes.add(new RegistroIteracao(iteracao, diversidades, taxaAcerto, taxaErro, acertou, acuraciaPrequencial, desvioAcuraciaPrequencial, erroPrequencial, desvioErroPrequencial));
    }

    public List<Integer> getLogDrifts() {
        return logDrifts;
    }
    
    
    
    public RegistroIteracao getRegistro(int iteracao)
    {
        RegistroIteracao registroIteracao = listaRegistrosIteracoes.get(iteracao-1);
        if (registroIteracao.getIteracao() != iteracao)
            throw new RuntimeException("ERRO: ITERACAO INVALIDA!!!");
        
        return registroIteracao;
    }

    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public List<RegistroIteracao> getListaRegistrosIteracoes() {
        return listaRegistrosIteracoes;
    }

    public double getTempo() {
        return tempo;
    }

    public double getRamHours() {
        return ramHours;
    }

}
